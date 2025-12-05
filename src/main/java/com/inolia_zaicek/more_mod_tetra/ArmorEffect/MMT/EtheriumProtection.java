package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.etherium_protection_Effect;

public class EtheriumProtection {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        LivingEntity livingEntity = event.getAttacked();
        //百分比
        float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, etherium_protection_Effect);
        if (effectLevel > 0 || (livingEntity.getHealth()<=livingEntity.getMaxHealth()/2) ) {
            event.addNormalMulti((1 - effectLevel/100) );
        }
    }
}
