package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NitenIchiryuWakizashi extends MobEffect {
    private static final String ATTACK_SPEED_UUID = "C1BAABF6-6AD4-E676-6B90-D9D09E32FE33";
    private static final double ATTACK_SPEED = 0.25;
    public NitenIchiryuWakizashi() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ATTACK_SPEED_UUID, ATTACK_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
