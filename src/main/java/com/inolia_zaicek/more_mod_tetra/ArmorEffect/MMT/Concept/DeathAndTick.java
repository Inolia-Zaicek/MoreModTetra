package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept;


import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

import static com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept.HurtAndDamage.eraseUuid;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class DeathAndTick {
    @SubscribeEvent(priority = EventPriority.HIGH)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        if (event.getEntity() != null) {
            LivingEntity livingEntity = event.getEntity();
            //应扣除血量——
            //如：数额为20%%，即设置血量为：当前生命值 减去 80%%最大生命值【因为显示是100-20】
            int guardLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, concept_of_guard_Effect);
            //阈值【当前生命值不低于[100-这个数额]————
            //例如：数额为25%，即当前生命值在75%%以上时，才会触发复活（限伤）
            float guardEfficiency = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, concept_of_guard_Effect);
            //当前生命值
            float hp = livingEntity.getPersistentData().getFloat(concept_of_guard_number);
            float mhp = livingEntity.getMaxHealth();
            //该次伤害应扣除生命值【至少扣除1%最大生命值
            float damage = mhp * Math.max(0.01F, (float) (1 - guardLevel / 100));
            //生命
            float lifeEfficiency = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, concept_of_guard_Effect);//回血百分比
            //守护————双词条大于1，且应扣伤害＜当前生命值
            if (guardLevel > 0 && guardEfficiency > 0 && damage < hp) {
                //设置玩家血量（不要滥用改写
                livingEntity.setHealth(hp - damage);
                //设置玩家死了多久（>0死透了
                livingEntity.deathTime = -2;
                //设置玩家是活着的（isAlive是个布尔值
                livingEntity.isAlive();
                //设置无敌时间
                livingEntity.invulnerableTime = 20;
                //事件可以被取消
                event.setCanceled(true);
            }//生命
            else if (lifeEfficiency > 0) {
                livingEntity.setHealth(mhp * Math.min(1, lifeEfficiency / 100));
                livingEntity.deathTime = -2;
                livingEntity.isAlive();
                livingEntity.invulnerableTime = 20;
                event.setCanceled(true);
                livingEntity.getPersistentData().putInt(concept_of_guard_number, 60 * 20 * 2);
            }
            //双穆连携
            else if(MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, bokushuuSDesireEffect)>0
                    &&MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, bokushuuSDesireEffect)>0
                    &&MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, pure_Effect)>0
                    &&MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, pure_Effect)>0){
                livingEntity.setHealth(mhp * 0.4F);
                livingEntity.deathTime = -2;
                livingEntity.isAlive();
                livingEntity.invulnerableTime = 20;
                event.setCanceled(true);
                livingEntity.getPersistentData().putInt(concept_of_guard_number, 30 * 20 * 2);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,15*20,3));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,15*20,4));
            }
        }
    }

    public static final String concept_of_guard_number = MoreModTetra.MODID + ":concept_of_guard";
    public static final String concept_of_life_time = MoreModTetra.MODID + ":concept_of_life";
    public static final String concept_of_element_time = MoreModTetra.MODID + ":concept_of_element";
    public static final String pure_time = MoreModTetra.MODID + ":pure";
    public static final String bokuka_and_bokushuu_time = MoreModTetra.MODID + ":bokuka_and_bokushuu";
    public static final String erase_time = MoreModTetra.MODID + ":erase";

    public static final String fake_kinetic_number_nbt = MoreModTetra.MODID + ":fake_kinetic_number";
    public static final String fake_kinetic_time_nbt = MoreModTetra.MODID + ":fake_kinetic_time";
    public static final String damage_adaptation_number_nbt = MoreModTetra.MODID + ":damage_adaptation_number";
    public static final String damage_adaptation_time_nbt = MoreModTetra.MODID + ":damage_adaptation_time";

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        if (!event.getEntity().isAlive())
            return;
        LivingEntity livingEntity = event.getEntity();
        //记录生命值
        livingEntity.getPersistentData().putFloat(concept_of_guard_number, livingEntity.getHealth());

        if (livingEntity.getPersistentData().getInt(damage_adaptation_time_nbt) > 0) {
            livingEntity.getPersistentData().putInt(damage_adaptation_time_nbt,
                    livingEntity.getPersistentData().getInt(damage_adaptation_time_nbt) - 1);
        }else{
            livingEntity.getPersistentData().putInt(damage_adaptation_number_nbt,0);
        }

        if (livingEntity.getPersistentData().getInt(fake_kinetic_time_nbt) > 0) {
            livingEntity.getPersistentData().putInt(fake_kinetic_time_nbt,
                    livingEntity.getPersistentData().getInt(fake_kinetic_time_nbt) - 1);
        }else{
            livingEntity.getPersistentData().putInt(fake_kinetic_number_nbt,0);
        }

        if (livingEntity.getPersistentData().getInt(erase_time) > 0) {
            livingEntity.getPersistentData().putInt(erase_time,
                    livingEntity.getPersistentData().getInt(erase_time) - 1);
        }else{
            Optional.of(event.getEntity())
                    .map(LivingEntity::getAttributes)
                    .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                    .map(manager -> manager.getInstance(Attributes.ARMOR))
                    .ifPresent(instance -> instance.removeModifier(eraseUuid));
            Optional.of(event.getEntity())
                    .map(LivingEntity::getAttributes)
                    .filter(manager -> manager.hasAttribute(Attributes.ATTACK_DAMAGE))
                    .map(manager -> manager.getInstance(Attributes.ATTACK_DAMAGE))
                    .ifPresent(instance -> instance.removeModifier(eraseUuid));
        }
        if (livingEntity.getPersistentData().getInt(bokuka_and_bokushuu_time) > 0) {
            livingEntity.getPersistentData().putInt(bokuka_and_bokushuu_time,
                    livingEntity.getPersistentData().getInt(bokuka_and_bokushuu_time) - 1);
        }
        if (livingEntity.getPersistentData().getInt(concept_of_life_time) > 0) {
            livingEntity.getPersistentData().putInt(concept_of_life_time,
                    livingEntity.getPersistentData().getInt(concept_of_life_time) - 1);
        }
        if (livingEntity.getPersistentData().getInt(concept_of_element_time) > 0) {
            livingEntity.getPersistentData().putInt(concept_of_element_time,
                    livingEntity.getPersistentData().getInt(concept_of_element_time) - 1);
        }
        //赤诚
        if (livingEntity.getPersistentData().getInt(pure_time) <= 5*20*2) {
            livingEntity.getPersistentData().putInt(pure_time,
                    livingEntity.getPersistentData().getInt(pure_time) + 1);
        }
        //如果主副手没有赤诚词条
        if (!(MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, pure_Effect) > 0 && MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, pure_Effect) > 0)) {
            //直接将nbtPut为0————无增伤
            livingEntity.getPersistentData().putInt(pure_time, 0);
        }
        if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, killaura_Effect) > 0
                && livingEntity.level().getGameTime() % Math.max(1,20-MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, killaura_Effect)) == 0) {

            var mobList = MMTUtil.mobList(11,livingEntity);
            for (Mob mobs:mobList){
                if(mobs!=null) {
                    //获取伤害类型
                    mobs.invulnerableTime=0;
                    float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    mobs.hurt(MMTTickZero.hasSource(livingEntity.level(), DamageTypes.MOB_ATTACK, livingEntity),
                            atk*MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, killaura_Effect)/100 );
                }
            }
        }
    }
}