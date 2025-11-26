package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility;

import net.minecraft.world.entity.LivingEntity;
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

public class Recovery {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(recoveryEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                recoveryName, 0, 20, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(recoveryTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("l2complements")) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, recoveryEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, recoveryEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                float mhp =livingEntity.getMaxHealth();
                if (effectLevel > 0) {
                    livingEntity.heal( (1+(mhp/100))*effectLevel );
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, recoveryEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, recoveryEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                float mhp =livingEntity.getMaxHealth();
                if (effectLevel > 0) {
                    livingEntity.heal( (1+(mhp/100))*effectLevel );
                }
            }
        }
    }
}
