package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.assassinateEffect;

public class RideSkill {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                if (effectLevel > 0&&hp==mhp) {
                    float number = (float) effectLevel / 100;
                    event.setAmount(event.getAmount()*(1+number));
                    }
                } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //实体骑乘的东西非空
                if (effectLevel > 0&&hp==mhp&&livingEntity.getVehicle()!=null) {
                    float number = (float) effectLevel / 100;
                    event.setAmount(event.getAmount()*(1+number));
                }
            }
            }
        }

