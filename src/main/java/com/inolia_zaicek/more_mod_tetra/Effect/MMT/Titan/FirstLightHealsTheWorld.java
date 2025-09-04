package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FirstLightHealsTheWorld {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(firstLightHealsTheWorldEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                firstLightHealsTheWorldName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(firstLightHealsTheWorldTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, firstLightHealsTheWorldEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, firstLightHealsTheWorldEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            if (effectLevel > 0) {
                float number=1+(effectLevel/100 );
                float heal = event.getAmount();
                event.setAmount(heal*number);
            }
        }
    }
}