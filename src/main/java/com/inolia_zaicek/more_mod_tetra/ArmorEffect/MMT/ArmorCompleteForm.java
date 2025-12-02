package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.armor_complete_form_Effect;

public class ArmorCompleteForm {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        LivingEntity livingEntity = event.getAttacker();
        float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_complete_form_Effect);
        float mhp = livingEntity.getMaxHealth();
        float hp = livingEntity.getHealth();
        float dhp = (mhp - hp) / mhp;
        if (effectLevel > 0 && hp > 0) {
            event.addNormalMulti(((dhp) * (effectLevel / 100)));
        }
    }
}