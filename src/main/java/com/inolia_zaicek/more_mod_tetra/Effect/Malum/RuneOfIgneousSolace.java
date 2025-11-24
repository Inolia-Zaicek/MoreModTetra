package com.inolia_zaicek.more_mod_tetra.Effect.Malum;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class RuneOfIgneousSolace {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(runeOfIgneousSolaceEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                runeOfIgneousSolaceName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(runeOfIgneousSolaceTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("malum")) {
            Entity entity = event.getEntity();
            if (entity.isOnFire() || entity.getRemainingFireTicks() > 0) {
                if (entity instanceof LivingEntity livingEntity) {
                    ItemStack mainHandItem = livingEntity.getMainHandItem();
                    ItemStack offhandItem = livingEntity.getOffhandItem();
                    int effectLevel = 0;
                    if (mainHandItem.getItem() instanceof IModularItem item) {
                        float mainEffectLevel = item.getEffectLevel(mainHandItem, runeOfIdleRestorationEffect);
                        if (mainEffectLevel > 0) {
                            effectLevel += (int) mainEffectLevel;
                        }
                    }
                    if (offhandItem.getItem() instanceof IModularItem item) {
                        float offEffectLevel = item.getEffectLevel(offhandItem, runeOfIdleRestorationEffect);
                        if (offEffectLevel > 0) {
                            effectLevel += (int) offEffectLevel;
                        }
                    }
                    if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * 0.75f);
                    }
                }
            }
        }
    }
}
