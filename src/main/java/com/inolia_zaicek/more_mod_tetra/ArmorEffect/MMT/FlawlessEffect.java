package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.cursium_legs_Effect;

public class FlawlessEffect {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //挨打
        if (event.getAttacked() != null) {
            LivingEntity livingEntity = event.getAttacked();
            //主手等级
            float allEffectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity, cursium_legs_Effect);
            if (allEffectLevel > 0) {
                event.addNormalMulti((1 - allEffectLevel / 100));
            }
        }
        //打人
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float number = 0;
            //主手等级
            float mainHandEffectLevel = MMTEffectHelper.getInstance().getMainHandEffectLevel(livingEntity, cursium_legs_Effect);
            //副手
            float offHandEffectLevel = MMTEffectHelper.getInstance().getOffHandEffectLevel(livingEntity, cursium_legs_Effect);
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            if (mainHandItem.getItem() instanceof IModularItem && mainHandEffectLevel > 0) {
                // 获取主手工具的损耗值
                int currentDamage = mainHandItem.getDamageValue();
                //损耗值为0————耐久满
                if (currentDamage <= 0 ) {
                    number += mainHandEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem && offHandEffectLevel > 0) {
                // 获取副手工具的损耗值
                int currentDamage = offhandItem.getDamageValue();
                //损耗值为0————耐久满
                if (currentDamage <= 0 ) {
                    number += offHandEffectLevel;
                }
            }
            if (number > 0) {
                event.addNormalMulti((1 + number / 100));
                if (mainHandItem.getItem() instanceof IModularItem && mainHandEffectLevel > 0) {
                    int currentDamage = mainHandItem.getDamageValue();
                    int newDamage =  (currentDamage + 1);
                    //设置损耗值
                    mainHandItem.setDamageValue(Math.max(0, newDamage));
                }
                if (offhandItem.getItem() instanceof IModularItem && offHandEffectLevel > 0) {
                    int currentDamage = offhandItem.getDamageValue();
                    int newDamage =  (currentDamage + 1);
                    //设置损耗值
                    offhandItem.setDamageValue(Math.max(0, newDamage));
                }
            }
        }
    }
}
