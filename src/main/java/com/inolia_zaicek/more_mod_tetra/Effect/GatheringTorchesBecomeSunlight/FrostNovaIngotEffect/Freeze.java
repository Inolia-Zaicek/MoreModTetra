package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Freeze {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(freezeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                freezeName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(freezeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,freezeEffect);
                if (effectLevel > 0&&mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    event.addNormalMulti(((float) effectLevel /100));
                }
            }
            else if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,freezeEffect);
                if (effectLevel > 0&&mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    event.addNormalMulti(((float) effectLevel /100));
            }
        }
            //圣爹
        //挨打————减伤
        if (event.getAttacked()!=null) {
            LivingEntity livingEntity=event.getAttacked();
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity,ritualOfHolyGuardEffect);
            if (effectLevel > 0&&livingEntity.hasEffect(MMTEffectsRegister.RitualOfHolyGuard.get())) {
                int buffLevel = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getAmplifier();
                int buffTime = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getDuration();
                //大于1
                if (buffLevel != 0) {
                    //减伤==buff等级*减伤比例
                    event.addNormalMulti(1 - (buffLevel + 1) * ((float) effectLevel / 100));
                    livingEntity.removeEffect(MMTEffectsRegister.RitualOfHolyGuard.get());
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), buffTime, buffLevel - 1));
                }else{
                    //减伤==buff等级*减伤比例
                    event.addNormalMulti(1 - (buffLevel + 1) * ((float) effectLevel / 100));
                    livingEntity.removeEffect(MMTEffectsRegister.RitualOfHolyGuard.get());
                }
            }
        }
        //打人
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,ritualOfHolyGuardEffect);
            float effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,exhortationOfGunKnightPatriotEffect);
            int effectEfficiency2 = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity,exhortationOfGunKnightPatriotEffect);
            if (effectLevel > 0) {
                //有圣卫核心
                if (effectLevel2 > 0 && event.getAttacked() != null) {
                    event.getAttacked().addEffect(new MobEffectInstance(MMTEffectsRegister.BanHeal.get(), 400, 3));
                }
                //有buff
                if (livingEntity.hasEffect(MMTEffectsRegister.RitualOfHolyGuard.get())) {
                    int buffLevel = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getAmplifier();
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400, Math.min(8, buffLevel + 1 + effectEfficiency2)));
                    //增伤==1+buff等级*减伤比例
                    event.addNormalMulti((buffLevel + 1) * ((effectLevel2+effectLevel) / 100));
                } else {
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400, effectEfficiency2));
                    //增伤伤==1+buff等级*减伤比例
                    event.addNormalMulti( ((effectLevel2+effectLevel) / 100));
                }
            }
        }
        //减伤
        if (event.getAttacked()!=null) {
            LivingEntity livingEntity=event.getAttacked();
            var mob = event.hurtEvent.getSource().getEntity();
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,ritualOfExhortationEffect);
            if (effectLevel > 0) {
                float finish = (1- (float) effectLevel /100);
                var mobList = MMTUtil.mobList(8,livingEntity);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        //如果这些范围里没有人和伤害源一样
                        if(mobs!=mob) {
                            event.addNormalMulti(finish);
                        }
                    }
                }
            }
        }
    }
}
