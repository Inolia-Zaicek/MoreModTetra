package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class ArmorSturdyAndTenacity {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void hurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        //百分比
        float percentage = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, armor_tenacity_Effect);
        //固定
        float fixedValue = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, armor_sturdy_Effect);
        if (percentage > 0 || fixedValue > 0) {
            event.setAmount((event.getAmount() - fixedValue) * (1 - percentage / 100));
        }
        float effectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, etheriumGuardEffect);
        if (effectLevel > 0 && (attacked.getHealth() <= attacked.getMaxHealth() / 2)) {
            event.setAmount(event.getAmount() * (1 - effectLevel / 100));
        }
        //全身等级
        float allEffectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(attacked, flawless_protection_Effect);
        if (allEffectLevel > 0) {
            event.setAmount(event.getAmount() * (1 - allEffectLevel / 100));
        }
        if (event.getSource().getEntity() instanceof LivingEntity mob) {
            float undead = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, undead_protection_Effect);
            float arthropods = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, arthropods_protection_Effect);
            if (undead > 0 && mob.getMobType() == MobType.UNDEAD) {
                event.setAmount(event.getAmount() * (1 - undead / 100));
            }
            if (arthropods > 0 && mob.getMobType() == MobType.ARTHROPOD) {
                event.setAmount(event.getAmount() * (1 - arthropods / 100));
            }
        }
        //挨打
        if (event.getSource().getEntity() instanceof Warden) {
            float wardenKillerEffect = MMTEffectHelper.getInstance().getAllEffectEfficiency(attacked, warden_killer_Effect);
            if (effectLevel > 0) {
                event.setAmount(event.getAmount() * (1 - (float) wardenKillerEffect / 100));
            }
        }
        int etheriumGuardEffectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(attacked, etheriumGuardEffect);
        float hp = attacked.getHealth();
        float mhp = attacked.getMaxHealth();
        float finish = hp / mhp;
        if (etheriumGuardEffectLevel > 0 && finish <= 0.5f) {
            float number = Math.min(0.75f, (float) etheriumGuardEffectLevel / 100);
            event.setAmount(event.getAmount() * (1 - number));
        }
        if (attacked instanceof Player player && !(event.getSource().getEntity() instanceof Player)) {
            int reverseMirrorEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, reverseMirrorEffect);
            if (reverseMirrorEffectLevel > 1 && event.getSource().is(WITCH_RESISTANT_TO)) {
                event.setAmount(event.getAmount() * (1 - reverseMirrorEffectLevel * 0.1f));
            }
        }
        float curiosFeatherFallingEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacked, curiosFeatherFallingEffect);
        if (curiosFeatherFallingEffectLevel > 0) {
            event.setAmount(event.getAmount() * (1-curiosFeatherFallingEffectLevel / 100));
        }
    }
}
