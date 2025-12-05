package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.arthropods_protection_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.undead_protection_Effect;

public class UndeadAndArthropodsProtection {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(EffectLevelEvent event) {
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getAttacked();
            float undead = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, undead_protection_Effect);
            float arthropods = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, arthropods_protection_Effect);
            if (undead > 0&&mob.getMobType() == MobType.UNDEAD) {
                event.addNormalMulti((1 - undead/100) );
            }
            if (arthropods > 0&&mob.getMobType() == MobType.ARTHROPOD) {
                event.addNormalMulti((1 - arthropods/100) );
            }
        }
        else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getAttacked();
            float undead = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, undead_protection_Effect);
            float arthropods = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, arthropods_protection_Effect);
            if (undead > 0&&mob.getMobType() == MobType.UNDEAD) {
                event.addNormalMulti((1 - undead/100) );
            }
            if (arthropods > 0&&mob.getMobType() == MobType.ARTHROPOD) {
                event.addNormalMulti((1 - arthropods/100) );
            }
        }
    }
}