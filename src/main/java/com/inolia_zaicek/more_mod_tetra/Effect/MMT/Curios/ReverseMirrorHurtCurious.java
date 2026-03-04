package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;

public class ReverseMirrorHurtCurious {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getAttacked() instanceof Player player && !(event.hurtEvent.getSource().getEntity() instanceof Player)) {
                int reverseMirrorEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, reverseMirrorEffect);
                if (reverseMirrorEffectLevel > 1 && event.hurtEvent.getSource() == player.damageSources().magic()) {
                    event.addNormalMulti((1 - reverseMirrorEffectLevel * 0.1f));
                }
            }
        }
    }
}
