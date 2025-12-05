package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.SacrificeAndThrowingTheHalberdEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class SacrificeAndThrowingTheHalberdTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();;
        if(ModList.get().isLoaded("torchesbecomesunlight")) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,SacrificeAndThrowingTheHalberdEffect);
            if (effectLevel > 0 && livingEntity.level().getGameTime() % 10L == 0) {
                //有藏锋
                if (livingEntity.hasEffect(MMTEffectsRegister.ThrowingTheHalberd.get())) {
                    int buffLevel = livingEntity.getEffect(MMTEffectsRegister.ThrowingTheHalberd.get()).getAmplifier();
                    //每0.5秒计算一次
                    //小于6级
                    if (buffLevel < 5) {
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, buffLevel + 1, true, true, true));
                    }
                    //6级--持续给予藏锋
                    else {
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, 5, true, true, true));
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberdMax.get(), 20 * 60, 0, true, true, true));
                    }
                }
                //无藏锋
                else {
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, 0, true, true, true));
                }
            }
        }
    }
}