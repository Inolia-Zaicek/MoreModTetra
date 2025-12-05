package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

public class Levitation {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(levitationEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                levitationName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(levitationTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getAttacked();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,levitationEffect);
                if (effectLevel > 0) {
                    mob.addEffect(new MobEffectInstance(MobEffects.LEVITATION,200,effectLevel-1));
                }
            }else            if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getAttacked();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,levitationEffect);
                if (effectLevel > 0) {
                    mob.addEffect(new MobEffectInstance(MobEffects.LEVITATION,200,effectLevel-1));
                }
            }
        }
    }
