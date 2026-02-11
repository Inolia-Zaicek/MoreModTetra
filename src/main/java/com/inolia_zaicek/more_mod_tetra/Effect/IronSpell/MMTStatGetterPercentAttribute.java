package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.gui.stats.getter.StatGetterAttribute;

public class MMTStatGetterPercentAttribute extends StatGetterAttribute {
    public MMTStatGetterPercentAttribute(Attribute attribute) {
        super(attribute, false, false, (double)-1.0F);
    }

    public double getValue(Player player, ItemStack itemStack) {
        return (double)100.0F * super.getValue(player, itemStack);
    }

    public double getValue(Player player, ItemStack itemStack, String slot) {
        return (double)100.0F * super.getValue(player, itemStack, slot);
    }

    public double getValue(Player player, ItemStack itemStack, String slot, String improvement) {
        return (double)100.0F * super.getValue(player, itemStack, slot, improvement);
    }

    public boolean shouldShow(Player player, ItemStack currentStack, ItemStack previewStack) {
        double baseValue = (double)0.0F;
        double currentValue = this.getValue(player, currentStack);
        return currentValue != baseValue || this.getValue(player, previewStack) != currentValue;
    }
}
