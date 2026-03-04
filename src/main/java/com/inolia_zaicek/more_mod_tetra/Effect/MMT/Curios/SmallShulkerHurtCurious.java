package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.smallShulkerEffect;

public class SmallShulkerHurtCurious {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.hurtEvent.getSource().getEntity() instanceof Player player) {
                int smallShulkerEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, smallShulkerEffect);
                if (smallShulkerEffectLevel > 0&&player.isShiftKeyDown()) {
                    event.getAttacked().addEffect(new MobEffectInstance(MobEffects.LEVITATION,100,smallShulkerEffectLevel-1));
                }
            }
        }
    }
}
