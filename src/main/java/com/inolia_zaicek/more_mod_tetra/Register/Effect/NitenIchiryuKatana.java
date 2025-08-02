package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NitenIchiryuKatana extends MobEffect {
    private static final String ATTACK_DAMAGE_UUID = "82C635F7-EAEB-EF16-9A4D-3F1AC2592389";
    private static final double ATTACK_DAMAGE = 0.15;
    public NitenIchiryuKatana() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_UUID, ATTACK_DAMAGE, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
