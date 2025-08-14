package com.inolia_zaicek.more_mod_tetra.Effect;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Duel {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(duelEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                duelName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(duelTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        Entity attacker = event.getSource().getEntity();
        var attacked=event.getEntity();
        float damage=event.getAmount();
        if(attacked!=null&&attacker!=null) {
            //攻击者是玩家
            if (attacker instanceof Player player&&event.getEntity().getLastAttacker()!=null) {
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, duelEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, duelEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                //我手持武器
                //判断等级与近战
                if (effectLevel > 0&& MMTDamageSourceHelper.isMeleeAttack(event.getSource())&&event.getEntity().getLastAttacker()!=null) {
                    //用list添加①
                    var map = attacked.getActiveEffectsMap();
                    //上buff
                    if(attacked.hasEffect(MMTEffectsRegister.NeutralReverDuel.get())){
                        int buffLevel = attacked.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        map.put(MMTEffectsRegister.NeutralReverDuel.get(),
                                new MobEffectInstance(MMTEffectsRegister.NeutralReverDuel.get(), 200, Math.min(9,buffLevel+1)));
                    }else {
                        //负面
                        map.put(MMTEffectsRegister.NeutralReverDuel.get(),
                                new MobEffectInstance(MMTEffectsRegister.NeutralReverDuel.get(), 200, 0));
                    }
                    //正面
                    if(player.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())){
                        int buffLevel = player.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.BeneficialReverDuel.get(), 200, Math.min(9,buffLevel+1)));
                    }else{
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.BeneficialReverDuel.get(), 200, 0));
                    }
                    //增伤
                    if(attacked.hasEffect(MMTEffectsRegister.NeutralReverDuel.get()) &&player.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get()) ){
                        int BeLevel = player.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        int NeLevel = attacked.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        event.setAmount(damage * (1+(BeLevel+NeLevel)/100f));
                    }
                }
            }
            //挨打的是玩家
            if (attacked instanceof Player player&&event.getSource().getEntity() instanceof LivingEntity) {
                LivingEntity mob = event.getSource().getEntity().getControllingPassenger();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, duelEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, duelEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                //我手持武器
                //判断等级与近战
                if (effectLevel > 0&&mob!=null) {
                    //攻击者没有buff
                    if (  !(mob.hasEffect(MMTEffectsRegister.NeutralReverDuel.get()))
                            || !(mob.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get()))) {
                        //挨打的有两个buff之一
                        if (event.getEntity().hasEffect(MMTEffectsRegister.NeutralReverDuel.get())
                                ||event.getEntity().hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())) {
                            event.setAmount(damage - damage * 0.2f);
                        }
                    }
                    //敌人打我&&都有buff（敌负我正
                    if (mob.hasEffect(MMTEffectsRegister.NeutralReverDuel.get())
                            && player.hasEffect(MMTEffectsRegister.BeneficialReverDuel.get())) {
                        int BeLevel = mob.getEffect(MMTEffectsRegister.NeutralReverDuel.get()).getAmplifier();
                        int NeLevel = attacked.getEffect(MMTEffectsRegister.BeneficialReverDuel.get()).getAmplifier();
                        event.setAmount(damage * (1-(BeLevel+NeLevel)/100f));
                    }
                }
            }
        }
    }
}
