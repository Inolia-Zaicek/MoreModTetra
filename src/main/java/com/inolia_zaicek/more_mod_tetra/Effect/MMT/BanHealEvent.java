package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BanHealEvent {
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(MMTEffectsRegister.BanHeal.get())) {
            int buffLevel = entity.getEffect(MMTEffectsRegister.BanHeal.get()).getAmplifier();
            //5级禁疗
            if (buffLevel >= 4) {
                event.setAmount(0);
            } else {
                //禁疗比例
                float number=1-( (float) buffLevel *20/100 );
                float heal = event.getAmount();
                event.setAmount(heal*number);
            }
        }
    }
}
