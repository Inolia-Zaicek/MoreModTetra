package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.iAmStormEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class IAmStormTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();;
        if(ModList.get().isLoaded("cataclysm")) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,iAmStormEffect);
            if (effectLevel > 0&&livingEntity.level().getGameTime() % 20L == 0){
                var mobList = MMTUtil.mobList(7,livingEntity);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        var map = mobs.getActiveEffectsMap();
                        if(mobs.hasEffect(ModEffect.EFFECTWETNESS.get())){
                            int buffLevel = mobs.getEffect(ModEffect.EFFECTWETNESS.get()).getAmplifier();
                            map.put(ModEffect.EFFECTWETNESS.get(),
                                    new MobEffectInstance(ModEffect.EFFECTWETNESS.get(), 200, Math.min(9,buffLevel+1) ));
                        }else{
                            map.put(ModEffect.EFFECTWETNESS.get(),
                                    new MobEffectInstance(ModEffect.EFFECTWETNESS.get(), 200, 0));
                        }
                    }
                }
            }
        }
    }
}
