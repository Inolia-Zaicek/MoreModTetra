package com.inolia_zaicek.more_mod_tetra.Register.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class L2Repelling extends MobEffect {
    private static final String AK_UUID = "E680F8FB-D26F-94B5-B131-AB198945EF2E";
    private static final double AK = 1;
    public L2Repelling() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ATTACK_KNOCKBACK, AK_UUID, AK, AttributeModifier.Operation.ADDITION);
    }
}
