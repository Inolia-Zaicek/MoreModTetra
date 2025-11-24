package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Thorns;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.lightning_thorns_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.wither_thorns_Effect;

public class WitherThorns {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getEntity();
            float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, wither_thorns_Effect);
            if(effectLevel>0) {
                var DamageType = MMTTickZero.hasSource(livingEntity.level(), DamageTypes.WITHER, livingEntity);
                float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                mob.invulnerableTime = 0;
                if(livingEntity instanceof Player player) {
                    mob.setLastHurtByPlayer(player);
                }
                mob.hurt(DamageType, atk * effectLevel/100);
            }
        }
    }
}
