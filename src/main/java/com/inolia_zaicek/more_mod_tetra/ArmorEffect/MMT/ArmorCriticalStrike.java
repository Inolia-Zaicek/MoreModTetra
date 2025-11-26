package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosCriticalStrikeEffect;

public class ArmorCriticalStrike {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float chance = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(player, curiosCriticalStrikeEffect);
                float damage = MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(player, curiosCriticalStrikeEffect);
                Random random = new Random();
                if (chance > 0&&random.nextInt(100) <= chance) {
                    //最终伤害：与原版不同，是200————200%爆伤
                    float finish = event.getAmount() * (1 + damage / 100);
                    event.setAmount(finish);
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float chance = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(player, curiosCriticalStrikeEffect);
                float damage = MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(player, curiosCriticalStrikeEffect);
                Random random = new Random();
                if (chance > 0&&random.nextInt(100) <= chance) {
                    //最终伤害：与原版不同，是200————200%爆伤
                    float finish = event.getAmount() * (1 + damage / 100);
                    event.setAmount(finish);
            }
        }
    }
}
