package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.assassinateEffect;

public class RideSkill {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //攻击
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                if (effectLevel > 0&&hp==mhp) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((number));
                    }
                } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //实体骑乘的东西非空
                if (effectLevel > 0&&hp==mhp&&livingEntity.getVehicle()!=null) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((number));
                }
            }
            }
        }

