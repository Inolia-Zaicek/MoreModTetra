package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MarchingTime extends MobEffect {
    private static final String ATTACK_DAMAGE_UUID = "5BFF0A2E-4393-64D0-AB9E-EF6DDE26C11C";
    private static final double ATTACK_DAMAGE = 0.2;
    public MarchingTime() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_UUID, ATTACK_DAMAGE, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
