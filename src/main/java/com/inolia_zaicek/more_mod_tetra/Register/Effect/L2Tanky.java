package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class L2Tanky extends MobEffect {
    private static final String HP_UUID = "0B52AA6C-1552-BA78-1575-6C45E81AB318";
    private static final double HP = 0.2;
    private static final String A_UUID = "0B52AA6C-1552-BA78-1575-6C45E81AB318";
    private static final double A = 10;
    private static final String AM_UUID = "0B52AA6C-1552-BA78-1575-6C45E81AB318";
    private static final double AM = 4;
    public L2Tanky() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MAX_HEALTH, HP_UUID, HP, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ARMOR, A_UUID, A, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, AM_UUID, AM, AttributeModifier.Operation.ADDITION);
    }
}
