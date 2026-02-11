package com.inolia_zaicek.more_mod_tetra.Mixins;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEntityUtil;
import com.inolia_zaicek.more_mod_tetra.Util.MMTTargetMode;
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

    // 新增字段：用于保存弹射物的初始速度
    @Unique
    private double mmt$initialSpeed = 0.0;

    // 标志是否已经记录过速度
    @Unique
    private boolean mmt$speedRecorded = false;

    @Unique
    private Predicate<Entity> mmt$targetMode = null;

    @Override
    public void mmt$setMode(final Predicate<Entity> targetMode) {
        this.mmt$targetMode = targetMode;
    }

    @Override
    public @Nullable Predicate<Entity> mmt$getMode() {
        return mmt$targetMode;
    }

    // 在实体初始化或首次追踪时，记录初始速度
    @Inject(method = "tick", at = @At("HEAD"))
    private void mmt$recordInitialSpeed(CallbackInfo ci) {
        if (!mmt$speedRecorded) {
            // 计算当前速度的模，作为初始速度
            this.mmt$initialSpeed = (double) this.getDeltaMovement().length();
            mmt$speedRecorded = true;
        }
    }

    // 在击中目标时，清除mode
    @Inject(method = "onHit", at = @At("TAIL"))
    private void mmt$onHit(HitResult pResult, CallbackInfo ci) {
        if (pResult.getType() != HitResult.Type.MISS)
            mmt$setMode(null);
    }

    // 控制弹射物移动，保持速度
    @Inject(method = "tick", at = @At("TAIL"))
    private void mmt$moveTowardsTarget(CallbackInfo ci) {
        if (mmt$targetMode != null && !onGround()) {
            // 使用保存的初始速度
            MMTEntityUtil.moveTowardsTargetWithSpeed(this, mmt$initialSpeed);
        }
    }
}