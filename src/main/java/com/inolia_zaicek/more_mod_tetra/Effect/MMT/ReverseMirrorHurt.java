package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;

public class ReverseMirrorHurt {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,reverseMirrorEffect);
            if (effectLevel>1&&event.getSource() == player.damageSources().magic()) {
                event.setAmount(event.getAmount()*(1-effectLevel*0.1f));
            }
        }
    }
}