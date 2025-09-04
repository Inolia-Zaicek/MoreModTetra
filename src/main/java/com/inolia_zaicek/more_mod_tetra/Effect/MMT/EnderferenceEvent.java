package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnderferenceEvent {
    @SubscribeEvent
    public static void event(EntityTeleportEvent event) {
        //有抑影，阻止传送
        if (event.getEntity() instanceof LivingEntity livingEntity&& livingEntity.hasEffect(MMTEffectsRegister.Enderference.get())) {
            event.setCanceled(true);
        }
    }
}
