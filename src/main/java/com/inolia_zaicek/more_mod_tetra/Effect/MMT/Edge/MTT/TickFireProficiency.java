package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge.MTT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_tetra_tools.Damage.MTTTickZero;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.fireProficiencyEffect;

public class TickFireProficiency {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,fireProficiencyEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(MTTTickZero.TickFireDamage)) {
                        float number =  effectLevel / 100;
                        float damage = event.getAmount();
                        event.setAmount(damage * (1 + number));
                    }
            }
        }
            else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,fireProficiencyEffect);
            if (effectLevel > 0) {
                if (event.getSource().is(MTTTickZero.TickFireDamage)) {
                    float number = effectLevel / 100;
                    float damage = event.getAmount();
                    event.setAmount(damage * (1 + number));
                }
            }
        }
    }
}
