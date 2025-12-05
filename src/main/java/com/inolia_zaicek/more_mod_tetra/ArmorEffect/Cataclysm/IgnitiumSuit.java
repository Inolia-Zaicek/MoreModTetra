package com.inolia_zaicek.more_mod_tetra.ArmorEffect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.ignitium_suit_Effect;
import static net.minecraft.tags.DamageTypeTags.IS_FIRE;

public class IgnitiumSuit {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("cataclysm")) {
            //火减
            LivingEntity livingEntity = event.getAttacked();
            //总火抗
            int effectLevel1 = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, ignitium_suit_Effect);
            //等级
            int effectLevel2 = (int) MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(livingEntity, ignitium_suit_Effect);
            //减免
            if (effectLevel1 > 0 && event.hurtEvent.getSource().is(IS_FIRE)) {
                if (effectLevel1 >= 100) {
                    event.setResult(Event.Result.DENY);
                } else {
                    event.addNormalMulti((-(float) effectLevel1 / 100));
                }
            }
            //赋予
            if (effectLevel2 > 0) {
                LivingEntity mob = event.getAttacker();
                var map = mob.getActiveEffectsMap();
                if (mob.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                    int buffLevel = mob.getEffect(ModEffect.EFFECTBLAZING_BRAND.get()).getAmplifier();
                    mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, Math.min(4, buffLevel + effectLevel2)));
                    map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                            new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, Math.min(4, buffLevel + effectLevel2)));
                } else {
                    mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, effectLevel2 - 1));
                    map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                            new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, effectLevel2 - 1));
                }
            }
        }
    }
}