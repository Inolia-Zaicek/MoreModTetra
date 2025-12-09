package com.inolia_zaicek.more_mod_tetra.Effect.Goety;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_acid_venom_Effect;

public class GoetyAcidVenom {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("goety")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player && event.getAttacked() != null) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_acid_venom_Effect);
                if (effectLevel > 0) {
                    var mob = event.getAttacked();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "acid_venom")))
                            , 100, effectLevel-1));
                    if(!mob.hasEffect(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "acid_venom")
                            )))) {
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new
                        ResourceLocation("goety","acid_venom")))
                                ,new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("goety", "acid_venom"))),100,effectLevel-1));
                    }
                }
            }
        }
    }
}
