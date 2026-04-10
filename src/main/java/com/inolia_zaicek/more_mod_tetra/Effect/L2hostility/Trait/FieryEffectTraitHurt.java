package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.fieryEffectTraitEffect;
import static net.minecraft.tags.DamageTypeTags.IS_FIRE;

public class FieryEffectTraitHurt {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            //挨打的是玩家且攻击者非空
            if (event.getAttacked()!=null&&event.hurtEvent.getSource().getEntity()!=null) {
                LivingEntity livingEntity = event.getAttacked();
                ;
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, fieryEffectTraitEffect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, fieryEffectTraitEffect);
                if (effectLevel > 0) {
                    if (event.hurtEvent.getSource().getEntity().getRemainingFireTicks() < 200) {
                        event.hurtEvent.getSource().getEntity().setRemainingFireTicks(200);
                    }
                    if (event.hurtEvent.getSource().is(DamageTypeTags.IS_FIRE)
                            || event.hurtEvent.getSource() == livingEntity.damageSources().lava()
                            || event.hurtEvent.getSource() == livingEntity.damageSources().inFire()
                            || event.hurtEvent.getSource() == livingEntity.damageSources().onFire()
                            || event.hurtEvent.getSource().is(IS_FIRE)
                    ) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
            //攻击者是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity &&event.getAttacked()!=null) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, fieryEffectTraitEffect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, fieryEffectTraitEffect);
                if (effectLevel > 0&&event.getAttacked().getRemainingFireTicks()<200) {
                    event.getAttacked().setRemainingFireTicks(200);
                }
            }
        }
    }
}
