package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class TOCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.hurtEvent.getSource().is(TravelopticsDamageTypes.AQUA_MAGIC) || event.hurtEvent.getSource().is(TravelopticsDamageTypes.VOIDSTRIKE_REAPER_BONUS_DAMAGE)
                            || event.hurtEvent.getSource().is(TravelopticsDamageTypes.NULLFLARE_BLAST)) {
                        event.addIndependentMulti(1+effectLevel / 100);
                    }
                }
            } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.hurtEvent.getSource().is(TravelopticsDamageTypes.AQUA_MAGIC) || event.hurtEvent.getSource().is(TravelopticsDamageTypes.VOIDSTRIKE_REAPER_BONUS_DAMAGE)
                            || event.hurtEvent.getSource().is(TravelopticsDamageTypes.NULLFLARE_BLAST)) {
                        event.addIndependentMulti(1+effectLevel / 100);
                    }
                }
            }

        }
    }
}
