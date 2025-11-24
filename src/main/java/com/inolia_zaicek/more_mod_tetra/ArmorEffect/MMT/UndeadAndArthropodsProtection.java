package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class UndeadAndArthropodsProtection {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getEntity();
            float undead = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, undead_protection_Effect);
            float arthropods = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, arthropods_protection_Effect);
            if (undead > 0&&mob.getMobType() == MobType.UNDEAD) {
                event.setAmount(event.getAmount() * (1 - undead/100) );
            }
            if (arthropods > 0&&mob.getMobType() == MobType.ARTHROPOD) {
                event.setAmount(event.getAmount() * (1 - arthropods/100) );
            }
        }
        else if (event.getSource().getDirectEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getEntity();
            float undead = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, undead_protection_Effect);
            float arthropods = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, arthropods_protection_Effect);
            if (undead > 0&&mob.getMobType() == MobType.UNDEAD) {
                event.setAmount(event.getAmount() * (1 - undead/100) );
            }
            if (arthropods > 0&&mob.getMobType() == MobType.ARTHROPOD) {
                event.setAmount(event.getAmount() * (1 - arthropods/100) );
            }
        }
    }
}