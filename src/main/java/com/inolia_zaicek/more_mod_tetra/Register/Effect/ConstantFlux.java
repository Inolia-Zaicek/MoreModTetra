package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ConstantFlux extends MobEffect {
    private static final String ATTACK_SPEED_UUID_CU = "917C7850-0693-B7AE-587A-2ED13B90F753";
    private static final double ATTACK_SPEED_CU = 0.15;
    public ConstantFlux() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ATTACK_SPEED_UUID_CU, ATTACK_SPEED_CU, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
