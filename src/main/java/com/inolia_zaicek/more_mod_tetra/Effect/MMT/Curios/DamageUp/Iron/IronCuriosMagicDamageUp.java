package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class IronCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                                    if (effectLevel > 0) {
                                        if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                                            float finish =event.getAmount()*(1+effectLevel/100);
                                            event.setAmount(finish);
                                        }
                                    }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                        float finish =event.getAmount()*(1+effectLevel/100);
                        event.setAmount(finish);
                    }
                }
            }
        }
    }
}
