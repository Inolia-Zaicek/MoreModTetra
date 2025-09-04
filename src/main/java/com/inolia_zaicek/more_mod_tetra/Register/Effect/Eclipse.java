package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Eclipse extends MobEffect {
    private static final String UUID1 = "19EE4587-293D-B99F-7D62-A999B3F96001";
    private static final String UUID2 = "B734F2CC-357F-B721-38BB-A09CFEC983BF";
    private static final String UUID3 = "80074D07-FD27-2FC3-1EFC-C86B25C586D1";
    private static final double NUMBER = 0.075;
    public Eclipse() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, UUID1, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, UUID2, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, UUID3, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
