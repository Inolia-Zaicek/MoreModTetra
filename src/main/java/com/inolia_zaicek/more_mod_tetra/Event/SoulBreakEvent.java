package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoulBreakEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //buff到时间
    public static void buffOut(MobEffectEvent.Expired event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        if (expiredInstance != null) {
            MobEffect expiredEffect = expiredInstance.getEffect();
            LivingEntity livingEntity = event.getEntity();
            if (expiredEffect == MMTEffectsRegister.SoulBreak.get()) {
                CompoundTag persistentData = livingEntity.getPersistentData();
                persistentData.putBoolean("NoAI",false);
                if(livingEntity instanceof Mob mob){
                    mob.setNoAi(false);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //失去buff
    public static void buffOut(MobEffectEvent.Remove event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        if (expiredInstance != null) {
            MobEffect expiredEffect = expiredInstance.getEffect();
            LivingEntity livingEntity = event.getEntity();
            if (expiredEffect == MMTEffectsRegister.SoulBreak.get()) {
                CompoundTag persistentData = livingEntity.getPersistentData();
                persistentData.putBoolean("NoAI",false);
                if(livingEntity instanceof Mob mob){
                    mob.setNoAi(false);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //获得buff
    public static void buffGet(MobEffectEvent.Added event) {
        MobEffectInstance expiredInstance = event.getEffectInstance();
        MobEffect expiredEffect = expiredInstance.getEffect();
        LivingEntity livingEntity = event.getEntity();
        if (expiredEffect == MMTEffectsRegister.SoulBreak.get()) {
            CompoundTag persistentData = livingEntity.getPersistentData();
            persistentData.putBoolean("NoAI",true);
            if(livingEntity instanceof Mob mob){
                mob.setNoAi(true);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //tick持续赋予
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.hasEffect(MMTEffectsRegister.SoulBreak.get())) {
            CompoundTag persistentData = livingEntity.getPersistentData();
            persistentData.putBoolean("NoAI",true);
            if(livingEntity instanceof Mob mob){
                mob.setNoAi(true);
            }
        }
    }
}
