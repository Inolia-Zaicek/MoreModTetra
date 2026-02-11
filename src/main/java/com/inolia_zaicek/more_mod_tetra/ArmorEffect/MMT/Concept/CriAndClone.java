package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept.HurtAndDamage.infiniteUuid;
import static com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept.HurtAndDamage.substanceUuid;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.concept_of_blade_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.concept_of_sword_Effect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CriAndClone {
    /// 暴击事件
    @SubscribeEvent
    public static void cri(net.minecraftforge.event.entity.player.CriticalHitEvent event) {
        /// 挨打的
        Entity attacked = event.getTarget();
        /// 攻击的
        LivingEntity attacker = event.getEntity();
        Random random = new Random();
        ItemStack weapon = attacker.getMainHandItem();
        double criChance = 0;//基础数额*100了
        float criDamage = event.getDamageModifier();
        //剑——概率暴击
        criChance += MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_blade_Effect);
        boolean cri = false;
        //剑——非原版暴击爆伤提升
        float bladeDamage = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(attacker, concept_of_blade_Effect);
        if (!event.isVanillaCritical() && criChance > 0 && random.nextInt(100) <= criChance) {
            cri = true;
            event.setDamageModifier(criDamage + (float) (bladeDamage/100) );
            event.setResult(Event.Result.ALLOW);
        }
        if (event.isVanillaCritical()) {
            cri = true;
        }
        //刃——爆伤提升
        int swordDamage = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_sword_Effect);
        //二次提升爆伤
        if (cri) {
            event.setDamageModifier(criDamage + (float) (swordDamage/100) );
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity().level().isClientSide) return;
        Player original = event.getOriginal();
        Player newPlayer = event.getEntity();
        ///清除玩家的属性删除
        //物质
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.MAX_HEALTH))
                .map(manager -> manager.getInstance(Attributes.MAX_HEALTH))
                .ifPresent(instance -> instance.removeModifier(substanceUuid));
        //逆反
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                .map(manager -> manager.getInstance(Attributes.ARMOR))
                .ifPresent(instance -> instance.removeModifier(infiniteUuid));
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ARMOR_TOUGHNESS))
                .map(manager -> manager.getInstance(Attributes.ARMOR_TOUGHNESS))
                .ifPresent(instance -> instance.removeModifier(infiniteUuid));
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.MOVEMENT_SPEED))
                .map(manager -> manager.getInstance(Attributes.MOVEMENT_SPEED))
                .ifPresent(instance -> instance.removeModifier(infiniteUuid));
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ATTACK_SPEED))
                .map(manager -> manager.getInstance(Attributes.ATTACK_SPEED))
                .ifPresent(instance -> instance.removeModifier(infiniteUuid));
        Optional.of(newPlayer)
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ATTACK_DAMAGE))
                .map(manager -> manager.getInstance(Attributes.ATTACK_DAMAGE))
                .ifPresent(instance -> instance.removeModifier(infiniteUuid));
    }
}
