package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Dominion extends MobEffect {
    private static final String AS_UUID = "EE020C9F-4A4A-299F-4966-2ECBB3EFB159";
    private static final double AS = -0.5;
    public Dominion() {
        super(MobEffectCategory.NEUTRAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_KNOCKBACK, AS_UUID, AS, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
