package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.etheriumGuardEffect;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EtheriumGuardTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();;
        int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,etheriumGuardEffect);
        float hp =livingEntity.getHealth();
        float mhp =livingEntity.getMaxHealth();
        if (effectLevel > 0&&hp>(mhp/2) ) {
            livingEntity.setHealth(mhp/2);
        }
    }
}
