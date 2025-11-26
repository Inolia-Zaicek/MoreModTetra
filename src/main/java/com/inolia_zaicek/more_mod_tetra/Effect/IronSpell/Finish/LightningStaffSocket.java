package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Finish;

import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.LivingEntity;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class LightningStaffSocket {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(lightningStaffSocketEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                lightningStaffSocketName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(lightningStaffSocketTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if(event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)) {
                ItemStack mainStack = attacker.getMainHandItem();
                ItemStack offStack = attacker.getMainHandItem();
                if (mainStack.getItem() instanceof IModularItem item) {
                    float level = item.getEffectLevel(mainStack, lightningStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                } else if (offStack.getItem() instanceof IModularItem item) {
                    float level = item.getEffectLevel(offStack, lightningStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            if(event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)) {
                ItemStack mainStack = attacker.getMainHandItem();
                ItemStack offStack = attacker.getMainHandItem();
                if (mainStack.getItem() instanceof IModularItem item) {
                    float level = item.getEffectLevel(mainStack, lightningStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                } else if (offStack.getItem() instanceof IModularItem item) {
                    float level = item.getEffectLevel(offStack, lightningStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                }
            }
        }
    }
}
