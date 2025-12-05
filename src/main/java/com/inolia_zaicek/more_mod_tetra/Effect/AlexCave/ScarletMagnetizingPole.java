package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ScarletMagnetizingPole {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(scarletMagnetizingPoleEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                scarletMagnetizingPoleName, 0, 50, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(scarletMagnetizingPoleTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,scarletMagnetizingPoleEffect);
                if (effectLevel > 0&&mob.hasEffect(ACEffectRegistry.MAGNETIZING.get())) {
                    event.addNormalMulti(( (float) effectLevel /100));
                }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,scarletMagnetizingPoleEffect);
                if (effectLevel > 0&&mob.hasEffect(ACEffectRegistry.MAGNETIZING.get())) {
                    event.addNormalMulti(( (float) effectLevel /100));
                }
            }
        }
    }
