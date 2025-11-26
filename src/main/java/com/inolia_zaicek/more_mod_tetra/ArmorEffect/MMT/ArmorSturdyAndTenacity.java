package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.armor_sturdy_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.armor_tenacity_Effect;

public class ArmorSturdyAndTenacity {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //百分比
        float percentage = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_tenacity_Effect);
        //固定
        float fixedValue = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_sturdy_Effect);
        if (percentage > 0 || fixedValue > 0) {
            event.setAmount(event.getAmount() * (1 - percentage/100) - fixedValue);
        }
    }
}
