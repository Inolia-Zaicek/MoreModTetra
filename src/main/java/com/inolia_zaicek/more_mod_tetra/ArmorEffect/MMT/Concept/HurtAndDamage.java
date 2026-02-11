package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept.DeathAndTick.*;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class HurtAndDamage {
    public static final UUID fixedUuid = UUID.fromString("7469FD8D-1A3D-EAE5-E671-52DE94982CC3");
    public static final UUID overUuid = UUID.fromString("DDAA400A-3985-613F-344B-A904A834AE0D");
    public static final UUID infiniteUuid = UUID.fromString("5BAC9C84-E9D0-C140-B54D-37C0E8DC69D9");
    public static final UUID substanceUuid = UUID.fromString("A76B61CC-BA09-9755-4B24-381B0DFCF6AD");
    public static final UUID eraseUuid = UUID.fromString("E5FFA9B8-6586-D2C2-A2F7-6F3FBCBC8AAB");
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            LivingEntity attacked = event.getEntity();
            //元素
            float elementLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_element_Effect);//转化率
            float elementEfficiency = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(attacker, concept_of_element_Effect);//冷却时间
            //伤害类型
            float damage = event.getAmount();
            var lava = MMTTickZero.hasSource(attacker.level(), DamageTypes.LAVA, attacker);
            var freeze = MMTTickZero.hasSource(attacker.level(), DamageTypes.FREEZE, attacker);
            var lightningBolt = MMTTickZero.hasSource(attacker.level(), DamageTypes.LIGHTNING_BOLT, attacker);
            var wither = MMTTickZero.hasSource(attacker.level(), DamageTypes.WITHER, attacker);
            var magic = MMTTickZero.hasSource(attacker.level(), DamageTypes.MAGIC, attacker);
            var dragonBreath = MMTTickZero.hasSource(attacker.level(), DamageTypes.DRAGON_BREATH, attacker);
            var projectile = MMTTickZero.hasSource(attacker.level(), DamageTypes.MOB_PROJECTILE, attacker);
            var sonicBoom = MMTTickZero.hasSource(attacker.level(), DamageTypes.SONIC_BOOM, attacker);
            //元素
            if(elementLevel>0&&elementEfficiency>0&&attacker.getPersistentData().getInt(concept_of_element_time)==0) {
                attacker.getPersistentData().putInt(concept_of_element_time, (int)Math.max(1,20*2 - elementEfficiency*2) );
                attacked.invulnerableTime = 0;
                attacked.hurt(lava, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(freeze, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(lightningBolt, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(wither, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(magic, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(dragonBreath, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(projectile, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
                attacked.hurt(sonicBoom, damage * elementLevel / 100);
                attacked.invulnerableTime = 0;
            }
            float deathLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_death_Effect);//斩杀线
            if(deathLevel>0){
                float dhp = attacked.getHealth()/attacked.getMaxHealth();
                if(dhp<=deathLevel/100){
                    // 获取目标实体的死亡音效。
                    SoundEvent deathSound = MMTUtil.getDeathSound(attacked);
                    if (deathSound != null) {
                        attacked.level.playSound((Player)null, attacked.blockPosition(), deathSound, SoundSource.HOSTILE, 1.0F, attacked.getVoicePitch());
                    }
                    // 如果目标实体还未死亡或正在死亡...
                    if (!attacked.isDeadOrDying()) {
                        // 检查目标实体是否是玩家。
                        if (attacked instanceof Player) {
                            // 如果是玩家，则对其造成一个极高的伤害，强制使其死亡。
                            attacked.hurt(MMTTickZero.hasSource(attacker.level(), MMTTickZero.TRUEDAMAGE, attacker), Float.MAX_VALUE);
                        } else {
                            // 如果不是玩家 (而是其他类型的生物)...直接调用 target.die(...) 方法来使其死亡。
                            attacked.die( MMTTickZero.hasSource(attacker.level(), MMTTickZero.TRUEDAMAGE, attacker) );
                            // 移除目标实体。target.discard() 会将实体从游戏中移除，通常在死亡后执行。
                            attacked.discard();
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.getAttacked()!=null) {
                LivingEntity attacked = event.getAttacked();
                LivingEntity attacker = event.getAttacker();
                //使用数据，方便后面加
                float fixedArmorPenetration = 0;
                float fixedArmorToughnessPenetration =0;
                float overArmorPenetration = 0;
                float overArmorToughnessPenetration =0;
                float overHpDown =0;
                Random random = new Random();
                var map = attacked.getActiveEffectsMap();
                //动能
                int fakeKineticLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, fake_kinetic_Effect);
                if(fakeKineticLevel>0){
                    attacked.getPersistentData().putInt(fake_kinetic_time_nbt,20*10*2);
                    attacked.getPersistentData().putInt(fake_kinetic_number_nbt,Math.min(fakeKineticLevel,attacked.getPersistentData().getInt(fake_kinetic_time_nbt)+1) );
                    event.addFixedDamage(attacked.getPersistentData().getInt(fake_kinetic_time_nbt)*0.6F);
                }
                //伤害适应
                int damageAdaptationLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(attacked, damage_adaptation_Effect);
                if(damageAdaptationLevel>0){
                    attacked.getPersistentData().putInt(damage_adaptation_time_nbt,20*10*2);
                    attacked.getPersistentData().putInt(damage_adaptation_number_nbt,attacked.getPersistentData().getInt(damage_adaptation_time_nbt)+1);
                    event.addFixedDamage(1-Math.min(0.4F, (float) (attacked.getPersistentData().getInt(damage_adaptation_time_nbt) * damageAdaptationLevel) /100 ) );
                }
                //生命——扣除冷却时间
                float lifeLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_life_Effect);
                if(lifeLevel>0&&attacker.getPersistentData().getInt(concept_of_life_time)>0) {
                    attacker.getPersistentData().putInt(concept_of_life_time,
                            (int) Math.max(0, attacker.getPersistentData().getInt(concept_of_life_time) * (1 - lifeLevel / 100) ));
                }
                //秩序
                float orderLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, concept_of_order_Effect);//上限
                float orderEfficiency = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(attacker, concept_of_order_Effect);//增伤
                if(orderLevel>0&&orderEfficiency>0&&attacked.getMaxHealth()>attacker.getMaxHealth() ) {
                    //每有1%，增伤(orderEfficiency/100)，至多增伤(orderLevel/100)
                    float number = (float) Math.min( (orderLevel/100) , (int)(1-attacked.getMaxHealth()/attacker.getMaxHealth() / 0.01) * (orderEfficiency/100) );
                    event.addNormalMulti(number);
                }
                //灵魂
                if (random.nextInt(100) <= MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_soul_Effect)
                && MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_soul_Effect) > 0) {
                    int time = (int) (MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_soul_Effect)*20);
                    attacked.addEffect(new MobEffectInstance(MMTEffectsRegister.SoulBreak.get(), time, 0));
                    if (!attacked.hasEffect(MMTEffectsRegister.SoulBreak.get())) {
                        map.put(MMTEffectsRegister.SoulBreak.get(), new MobEffectInstance(MMTEffectsRegister.SoulBreak.get(), time, 0));
                    }
                }
                //刃，近战穿甲
                if(MMTDamageSourceHelper.isMeleeAttack(event.hurtEvent.getSource())) {
                    if(MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_sword_Effect)>0) {
                        fixedArmorPenetration += MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_sword_Effect) / 100;
                    }
                    if(MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_sword_Effect)>0) {
                        fixedArmorToughnessPenetration += MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, concept_of_sword_Effect) / 100;
                    }
                }
                //物质，削减生命值
                if(MMTCuriosHelper.getInstance().getCuriosEffectLevel(attacker, concept_of_substance_Effect)>0) {
                    overHpDown += (float) MMTCuriosHelper.getInstance().getCuriosEffectLevel(attacker, concept_of_substance_Effect) / 100;
                }
                if (fixedArmorPenetration > 0) {
                    float finalArmorPenetration = fixedArmorPenetration;
                    Optional.of(event.getAttacked())
                            .map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                            .map(manager -> manager.getInstance(Attributes.ARMOR))
                            .filter(instance -> instance.getModifier(fixedUuid) == null)
                            .ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(fixedUuid, "concept_of_sword", finalArmorPenetration * -1, AttributeModifier.Operation.ADDITION)));
                }
                if (fixedArmorToughnessPenetration > 0) {
                    float finalArmorToughnessPenetration = fixedArmorToughnessPenetration;
                    Optional.of(event.getAttacked())
                            .map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ARMOR_TOUGHNESS))
                            .map(manager -> manager.getInstance(Attributes.ARMOR_TOUGHNESS))
                            .filter(instance -> instance.getModifier(fixedUuid) == null)
                            .ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(fixedUuid, "concept_of_sword", finalArmorToughnessPenetration * -1, AttributeModifier.Operation.ADDITION)));
                }
                if (overHpDown > 0) {
                    float finalOverHpDown = overHpDown;
                    Optional.of(event.getAttacked())
                            .map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.MAX_HEALTH))
                            .map(manager -> manager.getInstance(Attributes.MAX_HEALTH))
                            .filter(instance -> instance.getModifier(substanceUuid) == null)
                            .ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(substanceUuid, "concept_of_substance", finalOverHpDown * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                }
                //逆反——删除
                float reverse = (float) MMTCuriosHelper.getInstance().getCuriosEffectLevel(attacker, concept_of_reverse_Effect) / 100;
                if(reverse>0){
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                            .map(manager -> manager.getInstance(Attributes.ARMOR))
                            .filter(instance -> instance.getModifier(infiniteUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(infiniteUuid, "concept_of_reverse", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ARMOR_TOUGHNESS))
                            .map(manager -> manager.getInstance(Attributes.ARMOR_TOUGHNESS))
                            .filter(instance -> instance.getModifier(infiniteUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(infiniteUuid, "concept_of_reverse", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.MOVEMENT_SPEED))
                            .map(manager -> manager.getInstance(Attributes.MOVEMENT_SPEED))
                            .filter(instance -> instance.getModifier(infiniteUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(infiniteUuid, "concept_of_reverse", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ATTACK_SPEED))
                            .map(manager -> manager.getInstance(Attributes.ATTACK_SPEED))
                            .filter(instance -> instance.getModifier(infiniteUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(infiniteUuid, "concept_of_reverse", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ATTACK_DAMAGE))
                            .map(manager -> manager.getInstance(Attributes.ATTACK_DAMAGE))
                            .filter(instance -> instance.getModifier(infiniteUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(infiniteUuid, "concept_of_reverse", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
                }
                //抹消
                if (random.nextInt(100) <= MMTCuriosHelper.getInstance().getCuriosEffectEfficiency(attacker, erase_Effect)){
                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                            .map(manager -> manager.getInstance(Attributes.ARMOR))
                            .filter(instance -> instance.getModifier(eraseUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(eraseUuid, "mmt_erase", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));

                    Optional.of(event.getAttacked()).map(LivingEntity::getAttributes)
                            .filter(manager -> manager.hasAttribute(Attributes.ATTACK_DAMAGE))
                            .map(manager -> manager.getInstance(Attributes.ATTACK_DAMAGE))
                            .filter(instance -> instance.getModifier(eraseUuid) == null).ifPresent(instance -> instance.addTransientModifier(
                                    new AttributeModifier(eraseUuid, "mmt_erase", reverse * -1, AttributeModifier.Operation.MULTIPLY_TOTAL)));

                    attacked.getPersistentData().putInt(erase_time, 3*20*2);
                }
            }

    }
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        Optional.of(event.getEntity())
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                .map(manager -> manager.getInstance(Attributes.ARMOR))
                .ifPresent(instance -> instance.removeModifier(fixedUuid));
        Optional.of(event.getEntity())
                .map(LivingEntity::getAttributes)
                .filter(manager -> manager.hasAttribute(Attributes.ARMOR_TOUGHNESS))
                .map(manager -> manager.getInstance(Attributes.ARMOR_TOUGHNESS))
                .ifPresent(instance -> instance.removeModifier(fixedUuid));
    }
}
