package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.hideBladeEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class HideBladeTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity player = event.getEntity();
        int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,hideBladeEffect);
            if (effectLevel > 0&&player.level().getGameTime() % 20L == 0) {
                //有藏锋
                if (player.hasEffect(MMTEffectsRegister.HideBlade.get())) {
                    int buffLevel = player.getEffect(MMTEffectsRegister.HideBlade.get()).getAmplifier();
                    //每0.5秒计算一次
                    //小于6级
                    if(buffLevel<5){
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, buffLevel+1, true, true, true));
                    }
                    //6级--持续给予藏锋
                    else{
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 5, true, true, true));
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBladeMax.get(), 20*60, 0, true, true, true));
                    }
                }
                //无藏锋
                else{
                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 0, true, true, true));
                }
        }
    }
}
