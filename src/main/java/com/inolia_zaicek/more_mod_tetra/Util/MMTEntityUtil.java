package com.inolia_zaicek.more_mod_tetra.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MMTEntityUtil {
    public static final String THRALL_KEY = "mmt:thrall";
    private static final Predicate<Entity> FALLBACK_TARGET_PREDICATE = (entity) -> true;

    public static void enthrall(LivingEntity caster, LivingEntity thrall) {
        thrall.getPersistentData().putUUID("mmt:thrall", caster.getUUID());
        if (thrall instanceof Mob mob) {
            mob.setPersistenceRequired();
        }

    }

    public static boolean isEnthralled(LivingEntity entity) {
        return entity.getPersistentData().contains("mmt:thrall");
    }

    public static boolean isEnthralledBy(LivingEntity entity, LivingEntity owner) {
        return entity != null && owner != null && isEnthralled(entity) && entity.getPersistentData().getUUID("mmt:thrall").equals(owner.getUUID());
    }

    public static boolean sameMaster(@NotNull LivingEntity entity, @NotNull LivingEntity source) {
        return isEnthralled(entity) && isEnthralled(source) ? entity.getPersistentData().getUUID("mmt:thrall").equals(source.getPersistentData().getUUID("mmt:thrall")) : false;
    }

    public static void moveTowardsTarget(Entity entity) {
        if (entity != null) {
            Entity owner;
            Predicate<Entity> targetPredicate;
            if (entity instanceof Projectile) {
                Projectile projectile = (Projectile)entity;
                owner = projectile.getOwner();
                Predicate var10000;
                if (projectile instanceof MMTTargetMode) {
                    MMTTargetMode mode = (MMTTargetMode)projectile;
                    var10000 = mode.mmt$getMode();
                } else {
                    var10000 = null;
                }

                Predicate<Entity> MMTTargetMode = var10000;
                    targetPredicate = MMTTargetMode != null ? MMTTargetMode : FALLBACK_TARGET_PREDICATE;
            } else {
                owner = null;
                targetPredicate = FALLBACK_TARGET_PREDICATE;
            }

            List<LivingEntity> entities = entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate((double)12.0F), (target) -> targetPredicate.test(target) && target != owner && target.isAlive() && (owner == null || !target.isAlliedTo(owner)) && (!entity.level().isClientSide() || target != Minecraft.getInstance().player));
            if (!entities.isEmpty()) {
                LivingEntity nearest = (LivingEntity)entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(entity))).get();
                Vec3 diff = nearest.position().add((double)0.0F, (double)(nearest.getBbHeight() / 2.0F), (double)0.0F).subtract(entity.position());
                Vec3 newmotion = entity.getDeltaMovement().add(diff.normalize()).scale((double)0.75F);
                entity.setDeltaMovement(newmotion);
            }

        }
    }
}
