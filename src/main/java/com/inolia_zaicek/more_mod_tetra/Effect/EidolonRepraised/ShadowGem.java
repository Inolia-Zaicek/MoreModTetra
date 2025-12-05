package com.inolia_zaicek.more_mod_tetra.Effect.EidolonRepraised;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import elucent.eidolon.registries.EidolonPotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

public class ShadowGem {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(shadowGemEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                shadowGemName, 0, 50, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(shadowGemTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if(ModList.get().isLoaded("eidolon")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,shadowGemEffect);
                if (effectLevel > 0) {
                    event.getAttacked().addEffect(new MobEffectInstance(EidolonPotions.UNDEATH_EFFECT.get(), (int) (effectLevel*20), 0, true, true, true));
                }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,shadowGemEffect);
                if (effectLevel > 0) {
                    event.getAttacked().addEffect(new MobEffectInstance(EidolonPotions.UNDEATH_EFFECT.get(), (int) (effectLevel*20), 0, true, true, true));
                }
            }
        }
    }
}
