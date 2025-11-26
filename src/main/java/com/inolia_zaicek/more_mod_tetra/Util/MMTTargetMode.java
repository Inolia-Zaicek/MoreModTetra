package com.inolia_zaicek.more_mod_tetra.Util;

import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface MMTTargetMode {
    @Nullable Predicate<Entity> eidolonrepraised$getMode();

    void eidolonrepraised$setMode(Predicate<Entity> var1);
}
