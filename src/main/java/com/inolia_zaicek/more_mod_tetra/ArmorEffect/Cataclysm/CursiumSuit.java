package com.inolia_zaicek.more_mod_tetra.ArmorEffect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static net.minecraft.tags.DamageTypeTags.IS_FALL;
import static net.minecraft.tags.DamageTypeTags.IS_PROJECTILE;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CursiumSuit {
    @SubscribeEvent
    public static void effectLevel(EffectLevelEvent event) {
        LivingEntity livingEntity = event.getAttacker();
        int legsArmorEffectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, cursium_legs_Effect);
        int feetArmorEffectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, cursium_feet_Effect);
        //3
        if (legsArmorEffectLevel > 0) {
            Random random = new Random();
            float chance = legsArmorEffectLevel;
            if (event.hurtEvent.getSource().is(IS_PROJECTILE)) {
                chance += legsArmorEffectLevel;
            }
            if (random.nextInt(100) <= chance) {
                event.setResult(Event.Result.DENY);
            }
        }
        //41
        if (feetArmorEffectLevel > 0 && event.hurtEvent.getSource().is(IS_FALL)) {
            if (feetArmorEffectLevel >= 100) {
                event.setResult(Event.Result.DENY);
            } else {
                event.addNormalMulti((-(float) feetArmorEffectLevel / 100));
            }
        }
    }

    //免死
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void LivingDeathVampire(LivingDeathEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity livingEntity = event.getEntity();
            int chestArmorEffectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, cursium_chest_Effect);
            //2
            if (chestArmorEffectLevel>0&&!event.getEntity().hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get())) {
                //设置玩家血量（不要滥用改写
                livingEntity.setHealth(5);
                //给予玩家鬼魅缠身buff（封印一下效果
                livingEntity.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_SICKNESS.get(), 3 * 60 * 20, 0, true, true));
                //给予玩家鬼魂缠身buff（咒魂
                livingEntity.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_FORM.get(), chestArmorEffectLevel * 20, 0, true, true));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, chestArmorEffectLevel * 20, 0, true, true));
                //设置玩家死了多久（>0死透了
                livingEntity.deathTime = -2;
                //设置玩家是活着的（isAlive是个布尔值
                livingEntity.isAlive();
                //设置无敌时间
                livingEntity.invulnerableTime = 10;
                //事件可以被取消
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        int headArmorEffectLevel = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, cursium_head_Effect);
        if (headArmorEffectLevel > 0) {
            if (livingEntity.level().getGameTime() % 10L == 0) {
                var mobList = MMTUtil.mobList( (headArmorEffectLevel*2-1) ,livingEntity);
                for (Mob mobs:mobList) {
                    if (mobs != null) {
                        var maps = mobs.getActiveEffectsMap();
                        mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING, 160, 1, true, true, true));
                        maps.put(MobEffects.GLOWING,
                                new MobEffectInstance(MobEffects.GLOWING,160,1));
                    }
                }
            }
        }
    }
}