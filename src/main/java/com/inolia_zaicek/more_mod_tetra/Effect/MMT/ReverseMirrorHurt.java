package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;

public class ReverseMirrorHurt {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, reverseMirrorEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, reverseMirrorEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
            if (effectLevel>1&&event.getSource() == player.damageSources().magic()) {
                event.setAmount(event.getAmount()*(1-effectLevel*0.1f));
            }
        }
    }
}