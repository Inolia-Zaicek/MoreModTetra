package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EtheriumProtection {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //百分比
        float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, etherium_protection_Effect);
        if (effectLevel > 0 || (livingEntity.getHealth()<=livingEntity.getMaxHealth()/2) ) {
            event.setAmount(event.getAmount() * (1 - effectLevel/100) );
        }
    }
}
