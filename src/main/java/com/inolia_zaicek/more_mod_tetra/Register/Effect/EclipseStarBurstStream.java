package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EclipseStarBurstStream extends MobEffect {
    private static final String UUID1 = "1D2DF96C-5A54-9A0B-B4ED-0DAE2A0F9A4F";
    private static final String UUID2 = "996CE9A8-BAFC-BA1A-275A-C294ED30142D";
    private static final String UUID3 = "1CCEA9D3-6416-945F-8448-B2E23B0EF2A6";
    private static final double NUMBER = 0.1;
    public EclipseStarBurstStream() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, UUID1, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, UUID2, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, UUID3, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
