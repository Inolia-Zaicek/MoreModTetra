package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class OverPostmortal {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(overPostmortalEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                overPostmortalName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(overPostmortalTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("cataclysm")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,overPostmortalEffect);
                if (effectLevel > 0 && livingEntity.hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get())) {
                    int buffLevel = livingEntity.getEffect(ModEffect.EFFECTGHOST_SICKNESS.get()).getAmplifier();
                    int buffTime = livingEntity.getEffect(ModEffect.EFFECTGHOST_SICKNESS.get()).getDuration();
                    if (buffTime > 20) {
                        livingEntity.removeEffect(ModEffect.EFFECTGHOST_SICKNESS.get());
                        livingEntity.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_SICKNESS.get(), buffLevel, buffTime / 2));
                    } else {
                        livingEntity.removeEffect(ModEffect.EFFECTGHOST_SICKNESS.get());
                    }
                }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,overPostmortalEffect);
                if (effectLevel > 0 && livingEntity.hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get())) {
                    int buffLevel = livingEntity.getEffect(ModEffect.EFFECTGHOST_SICKNESS.get()).getAmplifier();
                    int buffTime = livingEntity.getEffect(ModEffect.EFFECTGHOST_SICKNESS.get()).getDuration();
                    if (buffTime > 20) {
                        livingEntity.removeEffect(ModEffect.EFFECTGHOST_SICKNESS.get());
                        livingEntity.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_SICKNESS.get(), buffLevel, buffTime / 2));
                    } else {
                        livingEntity.removeEffect(ModEffect.EFFECTGHOST_SICKNESS.get());
                    }
                }
            }
        }
    }
}