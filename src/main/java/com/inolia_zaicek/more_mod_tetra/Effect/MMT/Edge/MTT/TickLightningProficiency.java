package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge.MTT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_tetra_tools.Damage.MTTTickZero;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.lightningProficiencyEffect;

public class TickLightningProficiency {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            //攻击
        if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,lightningProficiencyEffect);
            if (effectLevel > 0) {
                if (event.getSource().is(MTTTickZero.TickLightningDamage)) {
                    float number =  effectLevel / 100;
                    float damage = event.getAmount();
                    event.setAmount(damage * (1 + number));
                }
            }
        }else         if (event.getSource().getEntity() instanceof LivingEntity player) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,lightningProficiencyEffect);
            if (effectLevel > 0) {
                if (event.getSource().is(MTTTickZero.TickLightningDamage)) {
                    float number =  effectLevel / 100;
                    float damage = event.getAmount();
                    event.setAmount(damage * (1 + number));
                }
            }
        }
    }
}
