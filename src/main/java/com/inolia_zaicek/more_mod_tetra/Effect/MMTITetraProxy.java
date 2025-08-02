//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.inolia_zaicek.more_mod_tetra.Effect;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public interface MMTITetraProxy {
    static void initClient() {
    }

    boolean canImbue(ItemStack var1);

    void handleLivingAttackEvent(LivingAttackEvent var1);
}
