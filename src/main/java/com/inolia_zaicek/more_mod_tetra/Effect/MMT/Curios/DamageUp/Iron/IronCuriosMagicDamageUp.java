package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class IronCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                                    if (effectLevel > 0) {
                                        if (event.hurtEvent.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.ICE_MAGIC)
                                        ||event.hurtEvent.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                                        ||event.hurtEvent.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                                        ||event.hurtEvent.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                                        ||event.hurtEvent.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                                            event.addIndependentMulti(1+effectLevel / 100);
                                        }
                                    }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.hurtEvent.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.ICE_MAGIC)
                            ||event.hurtEvent.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                            ||event.hurtEvent.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                            ||event.hurtEvent.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.hurtEvent.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                            ||event.hurtEvent.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                        event.addIndependentMulti(1+effectLevel / 100);
                    }
                }
            }
        }
    }
}
