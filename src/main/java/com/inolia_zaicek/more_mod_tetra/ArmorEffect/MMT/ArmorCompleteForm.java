package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ArmorCompleteForm {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_complete_form_Effect);
            float mhp = livingEntity.getMaxHealth();
            float hp = livingEntity.getHealth();
            float dhp = (mhp - hp) / mhp;
            if (effectLevel > 0 && hp > 0) {
                event.setAmount(event.getAmount() * (1 + (dhp) * (effectLevel / 100)));
            }
        }
        else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_complete_form_Effect);
            float mhp = livingEntity.getMaxHealth();
            float hp = livingEntity.getHealth();
            float dhp = hp / mhp;
            if (effectLevel > 0 && hp > 0) {
                event.setAmount(event.getAmount() * (1 + (dhp) * (effectLevel / 100)));
            }
        }
    }
}