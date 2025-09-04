package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StarBurstStream extends MobEffect {
    private static final String UUID1 = "549B6EFE-0140-540D-9F7A-58AC1D1768CE";
    private static final String UUID2 = "B522A40A-2FD8-FC81-B859-DB4F81A625E5";
    private static final String UUID3 = "23639F6B-AE71-6DA2-472A-41E18EE287B8";
    private static final double NUMBER = 0.05;
    public StarBurstStream() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, UUID1, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, UUID2, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, UUID3, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
