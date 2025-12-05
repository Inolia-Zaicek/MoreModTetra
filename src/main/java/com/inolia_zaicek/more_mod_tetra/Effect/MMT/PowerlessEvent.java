package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;

public class PowerlessEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(EffectLevelEvent event) {
        LivingEntity entity = event.getAttacker();
        if (entity.hasEffect(MMTEffectsRegister.Powerless.get())) {
            int buffLevel = entity.getEffect(MMTEffectsRegister.Powerless.get()).getAmplifier();
            //7级无力
            if (buffLevel >= 7) {
                event.setResult(Event.Result.DENY);
            } else {
                //伤害降低比例
                event.addIndependentMulti((float) buffLevel *15/100 );
            }
        }
    }
}
