package com.inolia_zaicek.more_mod_tetra.Effect.Azure;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.warden_killer_Effect;

public class WardenKiller {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //挨打
            if ( event.hurtEvent.getSource().getEntity() instanceof Warden ) {
                LivingEntity livingEntity = event.getAttacked();;
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectEfficiency(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.addNormalMulti((1 - (float) effectLevel / 100));
                }
            }else if ( event.hurtEvent.getSource().getDirectEntity() instanceof Warden ) {
                LivingEntity livingEntity = event.getAttacked();;
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectEfficiency(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.addNormalMulti((1 - (float) effectLevel / 100));
                }
            }
            //打人
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity && event.getAttacked()  instanceof Warden ) {
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                        event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity && event.getAttacked()  instanceof Warden ) {
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity,warden_killer_Effect);
                if (effectLevel > 0) {
                    event.addNormalMulti((1 + (float) effectLevel / 100));
                }
            }
    }
}
