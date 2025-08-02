//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.inolia_zaicek.more_mod_tetra.Util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class MMTDamageSourceHelper {
    public MMTDamageSourceHelper() {
    }

    public static @Nullable LivingEntity getAttacker(DamageSource source) {
        Entity var2 = source.getEntity();
        if (var2 instanceof LivingEntity entity) {
            return entity;
        } else {
            return null;
        }
    }
    //判断是否近战
    public static boolean isMeleeAttack(DamageSource source) {
        return !source.isIndirect() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO));
    }

}
