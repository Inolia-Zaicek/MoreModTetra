package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PowerlessEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(MMTEffectsRegister.Powerless.get())) {
            int buffLevel = entity.getEffect(MMTEffectsRegister.Powerless.get()).getAmplifier();
            //7级无力
            if (buffLevel >= 7) {
                event.setAmount(0);
            } else {
                //伤害降低比例
                float number=1-( (float) buffLevel *15/100 );
                float heal = event.getAmount();
                event.setAmount(heal*number);
            }
        }
    }
}
