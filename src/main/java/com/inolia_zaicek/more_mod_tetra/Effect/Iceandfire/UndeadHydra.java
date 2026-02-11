package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class UndeadHydra {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter00000023 = new StatGetterEffectLevel(undeadHydraEffect, 1);
        var statGetter00000024 = new StatGetterEffectEfficiency(undeadHydraEffect, 1);
        IStatGetter[] statGetters00000033 = {statGetter00000023, statGetter00000024};
        IStatFormat[] statFormats00000033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar00000033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                undeadHydraName, 0, 25, false, false, false,
                statGetter00000023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(undeadHydraTooltip, statGetters00000033, statFormats00000033)
        );
        WorkbenchStatsGui.addBar(statBar00000033);
        HoloStatsGui.addBar(statBar00000033);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //挨打
            if (event.getAttacked()!=null) {
                LivingEntity livingEntity = event.getAttacked();
                //阈值
                float effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, undeadHydraEffect);
                float effectLevel2 = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity, undeadHydraEffect);
                float effectLevel3 = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(livingEntity, undeadHydraEffect);
                int effectLevel = (int) Math.max(effectLevel3,Math.max(effectLevel1,effectLevel2) );
                //时长
                float effectEfficiency1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, undeadHydraEffect);
                float effectEfficiency2 = MMTEffectHelper.getInstance().getAllEffectEfficiency(livingEntity, undeadHydraEffect);
                float effectEfficiency3 = MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(livingEntity, undeadHydraEffect);
                int effectEfficiency = (int) Math.max(effectEfficiency3,Math.max(effectEfficiency1,effectEfficiency2) );
                if (effectLevel > 0&&effectEfficiency>0) {
                    float hp =livingEntity.getHealth();
                    float mhp =livingEntity.getMaxHealth();
                    float finish =100*hp/mhp;
                    if(finish>effectLevel*3){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,effectEfficiency*20,0));
                    }
                    else if(finish<=effectLevel*3&&finish>effectLevel*2){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,effectEfficiency*20,1));
                    }
                    else if(finish<=effectLevel*2&&finish>effectLevel){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,effectEfficiency*20,2));
                    }
                    else if(finish<=effectLevel){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,effectEfficiency*20,3));
                    }
                }
            }
        }
    }
