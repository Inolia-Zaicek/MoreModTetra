package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RuinationTime extends MobEffect {
    private static final String ATTACK_DAMAGE_UUID = "A0AA4F8F-4D64-0A5D-BC6E-C88641EDE535";
    private static final String ATTACK_SPEED_UUID = "D7099725-68C6-6536-7245-39A1785807BA";
    private static final String SPEED_UUID = "7E93AB10-CDFD-C6DC-7118-DFACE7450204";
    private static final double NUMBER = 0.1;
    public RuinationTime() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ATTACK_SPEED_UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SPEED_UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
