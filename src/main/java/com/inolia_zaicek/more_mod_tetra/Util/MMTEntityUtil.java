package com.inolia_zaicek.more_mod_tetra.Util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class MMTEntityUtil {
    public static final String THRALL_KEY = MoreModTetra.MODID + ":thrall";

    private static final Predicate<Entity> FALLBACK_TARGET_PREDICATE = entity -> true;

    public static void enthrall(LivingEntity caster, LivingEntity thrall) {
        thrall.getPersistentData().putUUID(THRALL_KEY, caster.getUUID());
        if (thrall instanceof Mob mob) {
            mob.setPersistenceRequired();
        }
    }

    public static boolean isEnthralled(LivingEntity entity) {
        return entity.getPersistentData().contains(THRALL_KEY);
    }

    public static boolean isEnthralledBy(LivingEntity entity, LivingEntity owner) {
        return entity != null && owner != null && isEnthralled(entity) && entity.getPersistentData().getUUID(THRALL_KEY).equals(owner.getUUID());
    }

    public static boolean sameMaster(@NotNull LivingEntity entity, @NotNull LivingEntity source) {
        if (!isEnthralled(entity) || !isEnthralled(source)) return false;
        return entity.getPersistentData().getUUID(THRALL_KEY).equals(source.getPersistentData().getUUID(THRALL_KEY));
    }

    public static void moveTowardsTarget(final Entity entity) {
        if (entity == null) {
            return;
        }

        Entity owner;
        Predicate<Entity> targetPredicate;

        if (entity instanceof Projectile projectile) {
            targetPredicate = null;
            owner = projectile.getOwner();
            Predicate<Entity> targetMode = projectile instanceof MMTTargetMode mode ? mode.eidolonrepraised$getMode() : null;
        } else {
            owner = null;
            targetPredicate = FALLBACK_TARGET_PREDICATE;
        }

        List<LivingEntity> entities = entity.level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(12), target -> targetPredicate.test(target) && target != owner && target.isAlive() && !(owner != null && target.isAlliedTo(owner)) && (!entity.level.isClientSide() || target != Minecraft.getInstance().player));

        if (!entities.isEmpty()) {
            //for (Entity e : entities) System.out.println(e);
            LivingEntity nearest = entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(entity))).get();
            Vec3 diff = nearest.position().add(0, nearest.getBbHeight() / 2, 0).subtract(entity.position());
            Vec3 newmotion = entity.getDeltaMovement().add(diff.normalize()).scale(0.75);
            entity.setDeltaMovement(newmotion);
        }
    }
}
