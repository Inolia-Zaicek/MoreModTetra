package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.TO;

import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.Entity;
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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class AquaStaffSocket {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(aquaStaffSocketEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                aquaStaffSocketName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(aquaStaffSocketTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        Entity attackingEntity = event.getSource().getEntity();
        if (attackingEntity instanceof LivingEntity attacker) {
            if(event.getSource().is(TravelopticsDamageTypes.AQUA_MAGIC)) {
                ItemStack mainStack = attacker.getMainHandItem();
                ItemStack offStack = attacker.getMainHandItem();
                if (mainStack.getItem() instanceof ModularItem item) {
                    float level = item.getEffectLevel(mainStack, aquaStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                } else if (offStack.getItem() instanceof ModularItem item) {
                    float level = item.getEffectLevel(offStack, aquaStaffSocketEffect);
                    if (level > 0) {
                        event.setAmount(event.getAmount() * 1.1f);
                    }
                }
            }
        }
    }
}
