package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class AbyssalFinish {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(abyssalFinishEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                abyssalFinishName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(abyssalFinishTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("cataclysm")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                var map = mob.getActiveEffectsMap();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, abyssalFinishEffect);
                if (effectLevel > 0 && mob.hasEffect(ModEffect.EFFECTABYSSAL_CURSE.get())) {
                    int buffLevel = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getAmplifier();
                    if (buffLevel >= 4) {
                        float buffTime = (float) (mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getDuration()) / 20;
                        mob.invulnerableTime = 0;
                        if (livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        mob.hurt(mob.damageSources().magic(), buffTime * 1.6f);
                        if (livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        map.remove(ModEffect.EFFECTABYSSAL_CURSE.get());
                    }
                }
            } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                var map = mob.getActiveEffectsMap();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, abyssalFinishEffect);
                if (effectLevel > 0 && mob.hasEffect(ModEffect.EFFECTABYSSAL_CURSE.get())) {
                    int buffLevel = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getAmplifier();
                    if (buffLevel >= 4) {
                        float buffTime = (float) (mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getDuration()) / 20;
                        mob.invulnerableTime = 0;
                        if (livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        mob.hurt(mob.damageSources().magic(), buffTime * 1.6f);
                        if (livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        map.remove(ModEffect.EFFECTABYSSAL_CURSE.get());
                    }
                }
            }
        }
    }
}
