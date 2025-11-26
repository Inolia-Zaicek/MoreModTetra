package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge.MTT;

import com.inolia_zaicek.more_tetra_tools.Damage.MTTTickZero;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.lightningProficiencyEffect;

public class TickLightningProficiency {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            //攻击
        if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, lightningProficiencyEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, lightningProficiencyEffect);
                    if (offEffectLevel > 0) {
                        effectLevel +=offEffectLevel;
                    }
                }
            if (effectLevel > 0) {
                if (event.getSource().is(MTTTickZero.TickLightningDamage)) {
                    float number =  effectLevel / 100;
                    float damage = event.getAmount();
                    event.setAmount(damage * (1 + number));
                }
            }
        }else         if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, lightningProficiencyEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, lightningProficiencyEffect);
                if (offEffectLevel > 0) {
                    effectLevel +=offEffectLevel;
                }
            }
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
