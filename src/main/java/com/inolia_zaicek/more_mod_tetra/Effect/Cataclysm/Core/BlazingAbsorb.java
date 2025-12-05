package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class BlazingAbsorb {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(blazingAbsorbEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                blazingAbsorbName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(blazingAbsorbTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("cataclysm")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, blazingAbsorbEffect);
                if (effectLevel > 0 && mob.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                    int buffLevel = mob.getEffect(ModEffect.EFFECTBLAZING_BRAND.get()).getAmplifier();
                    float mhp = (float) livingEntity.getAttributeValue(Attributes.MAX_HEALTH);
                    float heal = mhp / 100 + 1;
                    livingEntity.heal(heal * buffLevel);
                    event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, blazingAbsorbEffect);
                if (effectLevel > 0 && mob.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                    int buffLevel = mob.getEffect(ModEffect.EFFECTBLAZING_BRAND.get()).getAmplifier();
                    float mhp = (float) livingEntity.getAttributeValue(Attributes.MAX_HEALTH);
                    float heal = mhp / 100 + 1;
                    livingEntity.heal(heal * buffLevel);
                    event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            }
        }
    }
}
