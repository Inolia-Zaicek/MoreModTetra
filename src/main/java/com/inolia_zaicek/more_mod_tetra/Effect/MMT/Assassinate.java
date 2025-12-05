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

public class Assassinate {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(assassinateEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                assassinateName, 0, 1000, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(assassinateTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,assassinateEffect);
            float hp =mob.getHealth();
            float mhp =mob.getMaxHealth();
            if (effectLevel > 0&&hp==mhp) {
                float number = (float) effectLevel / 100;
                event.addNormalMulti(number);
            }
        } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,assassinateEffect);
            float hp =mob.getHealth();
            float mhp =mob.getMaxHealth();
            if (effectLevel > 0&&hp==mhp) {
                float number = (float) effectLevel / 100;
                event.addNormalMulti((number));
            }
        }
    }
}

