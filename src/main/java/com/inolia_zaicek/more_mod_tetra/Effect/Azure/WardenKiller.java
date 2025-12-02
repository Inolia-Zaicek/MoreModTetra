package com.inolia_zaicek.more_mod_tetra.Effect.Azure;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.warden_killer_Effect;

public class WardenKiller {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //挨打
            if ( event.getSource().getEntity() instanceof Warden ) {
                LivingEntity livingEntity = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectEfficiency(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 - (float) effectLevel / 100));
                }
            }else if ( event.getSource().getDirectEntity() instanceof Warden ) {
                LivingEntity livingEntity = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectEfficiency(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 - (float) effectLevel / 100));
                }
            }
            //打人
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity && event.getEntity() instanceof Warden ) {
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity && event.getEntity() instanceof Warden ) {
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                    event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                }
            }
    }
}
