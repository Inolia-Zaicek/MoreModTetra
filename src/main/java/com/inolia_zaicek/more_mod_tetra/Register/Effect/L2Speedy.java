package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class L2Speedy extends MobEffect {
    private static final String SPEED_UUID = "61A4E2A5-B173-34FB-5330-8F6133F97882";
    private static final double SPEED = 0.2;
    public L2Speedy() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SPEED_UUID, SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.FLYING_SPEED, SPEED_UUID, SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
