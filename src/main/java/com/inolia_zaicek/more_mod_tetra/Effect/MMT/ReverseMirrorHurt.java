package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;
import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class ReverseMirrorHurt {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (event.getAttacked()!=null) {
            LivingEntity player = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,reverseMirrorEffect);
            if (effectLevel>1&&event.hurtEvent.getSource().is(WITCH_RESISTANT_TO)) {
                 event.addNormalMulti((1-effectLevel*0.1f));
            }
        }
    }
}