package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
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

public class BlessingsOfWater {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(blessingsOfWaterEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                blessingsOfWaterName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(blessingsOfWaterTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //挨打
        if (event.getAttacked() != null) {
            LivingEntity player = event.getAttacked();
            float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, blessingsOfWaterEffect);
            if (effectLevel > 0) {
                if (player.isInWaterOrRain() || player.isInWater() || player.isUnderWater()) {
                    event.addNormalMulti((1 - (float) effectLevel / 100));
                }
            }
        }
        //打人
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
            float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, blessingsOfWaterEffect);
            if (effectLevel > 0) {
                if (player.isInWaterOrRain() || player.isInWater() || player.isUnderWater()) {
                    event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            }
        } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
            float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, blessingsOfWaterEffect);
            if (effectLevel > 0) {
                if (player.isInWaterOrRain() || player.isInWater() || player.isUnderWater()) {
                    event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            }
        }
    }
}
