package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.underoceanEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class UnderoceanTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (ModList.get().isLoaded("alexscaves")) {
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,underoceanEffect);
            if (effectLevel > 0) {
                if (livingEntity.level().getGameTime() % 10L == 0) {
                    livingEntity.addEffect(new MobEffectInstance(ACEffectRegistry.DEEPSIGHT.get(), 100, effectLevel - 1, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, effectLevel - 1, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(ACEffectRegistry.BUBBLED.get(), 100, effectLevel - 1, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, effectLevel - 1, true, true, true));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 100, effectLevel - 1, true, true, true));
                }
            }
        }
    }
}