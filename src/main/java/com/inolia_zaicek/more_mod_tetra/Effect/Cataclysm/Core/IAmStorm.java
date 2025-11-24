package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;


public class IAmStorm {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(iAmStormEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                iAmStormName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(iAmStormTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntity();
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offhandItem = livingEntity.getOffhandItem();
        int effectLevel = 0;
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float mainEffectLevel = item.getEffectLevel(mainHandItem, iAmStormEffect);
            if (mainEffectLevel > 0) {
                effectLevel += mainEffectLevel;
            }
        }
        if (offhandItem.getItem() instanceof IModularItem item) {
            float offEffectLevel = item.getEffectLevel(offhandItem, iAmStormEffect);
            if (offEffectLevel > 0) {
                effectLevel += (int) offEffectLevel;
            }
        }
        if (effectLevel > 0 && event.getSource() == livingEntity.damageSources().lightningBolt()) {
            event.setAmount(event.getAmount() * 0.25f);

        }
    }
}
