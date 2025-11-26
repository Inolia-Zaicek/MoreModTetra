package com.inolia_zaicek.more_mod_tetra.Util;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface MMTTargetMode {
    @Nullable Predicate<Entity> mmt$getMode();

    void mmt$setMode(Predicate<Entity> var1);
}
