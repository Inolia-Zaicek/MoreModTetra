package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Ars;

import com.hollingsworth.arsnouveau.setup.registry.DamageTypesRegistry;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class ArsCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("ars_nouveau")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if(event.hurtEvent.getSource().is(DamageTypesRegistry.GENERIC_SPELL_DAMAGE)||event.hurtEvent.getSource().is(DamageTypesRegistry.COLD_SNAP)
                            ||event.hurtEvent.getSource().is(DamageTypesRegistry.FLARE)||event.hurtEvent.getSource().is(DamageTypesRegistry.WINDSHEAR)||
                            event.hurtEvent.getSource().is(DamageTypesRegistry.CRUSH)){
                        event.addIndependentMulti(1+effectLevel / 100);
                    }
                }
            } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if(event.hurtEvent.getSource().is(DamageTypesRegistry.GENERIC_SPELL_DAMAGE)||event.hurtEvent.getSource().is(DamageTypesRegistry.COLD_SNAP)
                            ||event.hurtEvent.getSource().is(DamageTypesRegistry.FLARE)||event.hurtEvent.getSource().is(DamageTypesRegistry.WINDSHEAR)||
                            event.hurtEvent.getSource().is(DamageTypesRegistry.CRUSH)){
                        event.addIndependentMulti(1+effectLevel / 100);
                    }
                }
            }
        }
    }
}
