package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class MagnetizingMetal {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(magnetizingMetalEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                magnetizingMetalName, 0, 50, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(magnetizingMetalTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getDirectEntity() instanceof Player player && !(event.getEntity() instanceof Player)) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, magnetizingMetalEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, magnetizingMetalEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    mob.addEffect(new MobEffectInstance(ACEffectRegistry.MAGNETIZING.get(), 20*effectLevel, effectLevel - 1));
                }
            }
        }
    }
