package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
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
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ObsessionOfWarden {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(obsessionOfWardenEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                obsessionOfWardenName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(obsessionOfWardenTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //挨打
            if (!( event.getSource().getEntity() instanceof Warden )) {
                LivingEntity livingEntity = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,obsessionOfWardenEffect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 - (float) effectLevel / 100));
                }
            }else if (!( event.getSource().getDirectEntity() instanceof Warden )) {
                LivingEntity livingEntity = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,obsessionOfWardenEffect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 - (float) effectLevel / 100));
                }
            }
            //打人
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity &&!( event.getEntity() instanceof Warden )) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,obsessionOfWardenEffect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity &&!( event.getEntity() instanceof Warden )) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,obsessionOfWardenEffect);
                if (effectLevel > 0) {
                    event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                }
            }
    }
}
