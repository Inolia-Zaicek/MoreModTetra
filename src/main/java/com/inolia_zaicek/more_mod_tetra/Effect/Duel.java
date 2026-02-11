package com.inolia_zaicek.more_mod_tetra.Effect;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Duel {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter00000023 = new StatGetterEffectLevel(duelEffect, 1);
        var statGetter00000024 = new StatGetterEffectEfficiency(duelEffect, 1);
        IStatGetter[] statGetters00000033 = {statGetter00000023, statGetter00000024};
        IStatFormat[] statFormats00000033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar00000033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                duelName, 0, 50, false, false, false,
                statGetter00000023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(duelTooltip, statGetters00000033, statFormats00000033)
        );
        WorkbenchStatsGui.addBar(statBar00000033);
        HoloStatsGui.addBar(statBar00000033);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        DamageSource damageSource = event.hurtEvent.getSource();
        Entity attacker = event.hurtEvent.getSource().getEntity();
        LivingEntity attacked=event.getAttacked();
        if(attacked!=null&&attacker!=null) {
            //攻击者是玩家
            if (attacker instanceof LivingEntity livingEntity) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,duelEffect);
                //我手持武器
                //判断等级与近战
                if (effectLevel > 0&& MMTDamageSourceHelper.isMeleeAttack(event.hurtEvent.getSource()) ) {
                    //用list添加①
                    var map = attacked.getActiveEffectsMap();
                    //上buff
                    if(attacked.hasEffect(MMTEffectsRegister.NeutralReverDuel.get())){
                        int buffLevel = attacked.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        map.put(MMTEffectsRegister.NeutralReverDuel.get(),
                                new MobEffectInstance(MMTEffectsRegister.NeutralReverDuel.get(), 200, Math.min(4,buffLevel+1)));
                    }else {
                        //负面
                        map.put(MMTEffectsRegister.NeutralReverDuel.get(),
                                new MobEffectInstance(MMTEffectsRegister.NeutralReverDuel.get(), 200, 0));
                    }
                    //正面
                    if(livingEntity.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())){
                        int buffLevel = livingEntity.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.BeneficialReverDuel.get(), 200, Math.min(4,buffLevel+1)));
                    }else{
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.BeneficialReverDuel.get(), 200, 0));
                    }
                    //增伤计算
                    if(attacked.hasEffect(MMTEffectsRegister.NeutralReverDuel.get()) &&livingEntity.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get()) ){
                        int BeLevel = livingEntity.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        int NeLevel = attacked.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        event.addNormalMulti((BeLevel+NeLevel)*effectLevel/100f);
                    }
                }
            }
            //挨打的是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity) {
                var livingEntity = attacked;
                LivingEntity mob = event.hurtEvent.getSource().getEntity().getControllingPassenger();
                float effectEfficiency = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity,duelEffect);
                //我手持武器
                //判断等级与近战
                if (effectEfficiency > 0&&mob!=null) {
                    //攻击者没有buff
                    if (  !(mob.hasEffect(MMTEffectsRegister.NeutralReverDuel.get()))
                            || !(mob.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get()))) {
                        //挨打的有两个buff之一
                        if (event.getAttacked().hasEffect(MMTEffectsRegister.NeutralReverDuel.get())
                                ||event.getAttacked().hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())) {
                            event.addNormalMulti(0.75F);;
                        }
                    }
                    //敌人打我&&都有buff（敌负我正——减伤
                    if (mob.hasEffect(MMTEffectsRegister.NeutralReverDuel.get())
                            && livingEntity.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())) {
                        int BeLevel = mob.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        int NeLevel = attacked.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        event.addNormalMulti((1-(BeLevel+NeLevel)*effectEfficiency/100f));
                    }
                }
            }
        }
    }
}
