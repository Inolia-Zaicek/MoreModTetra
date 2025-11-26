package com.inolia_zaicek.more_mod_tetra.Mixins;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEntityUtil;
import com.inolia_zaicek.more_mod_tetra.Util.MMTTargetMode;
import elucent.eidolon.util.EntityUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Projectile.class)
public abstract class MMTProjectileMixin extends Entity implements MMTTargetMode {

    public MMTProjectileMixin(EntityType<?> type, Level level) {
        super(type, level);
    }


    @Unique
    private Predicate<Entity> eidolonrepraised$targetMode = null;

    @Override
    public void eidolonrepraised$setMode(final Predicate<Entity> targetMode) {
        this.eidolonrepraised$targetMode = targetMode;
    }

    @Override
    public @Nullable Predicate<Entity> eidolonrepraised$getMode() {
        return eidolonrepraised$targetMode;
    }

    //set target mode to null after the entity is hit
    @Inject(method = "onHit", at = @At("TAIL"))
    private void eidolonrepraised$onHit(HitResult pResult, CallbackInfo ci) {
        if (pResult.getType() != HitResult.Type.MISS)
            eidolonrepraised$setMode(null);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void eidolonrepraised$moveTowardsTarget(CallbackInfo ci) {
        if (eidolonrepraised$targetMode != null && !onGround()) {
            EntityUtil.moveTowardsTarget(this);
        }
    }
}
