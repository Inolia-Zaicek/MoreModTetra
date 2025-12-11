package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.flawless_attack_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.flawless_protection_Effect;

public class FlawlessEffect {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //挨打
        if (event.getAttacked() != null) {
            LivingEntity livingEntity = event.getAttacked();
            //全身等级
            float allEffectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity, flawless_protection_Effect);
            if (allEffectLevel > 0) {
                event.addNormalMulti((1 - allEffectLevel / 100));
            }
        }
        //打人
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            /// 无瑕之刃
            //主手
            if(MMTEffectHelper.getInstance().getMainHandEffectLevel(livingEntity,flawless_attack_Effect)>0&&livingEntity.getMainHandItem().getDamageValue()==0){
                event.addNormalMulti(((float) MMTEffectHelper.getInstance().getMainHandEffectLevel(livingEntity,flawless_attack_Effect) /100));
                int currentDamage = livingEntity.getMainHandItem().getDamageValue();
                int newDamage =  (currentDamage + 1);
                //设置损耗值
                livingEntity.getMainHandItem().setDamageValue(Math.max(0, newDamage));
            }
            //副手
            else if(MMTEffectHelper.getInstance().getOffHandEffectLevel(livingEntity,flawless_attack_Effect)>0&&livingEntity.getOffhandItem().getDamageValue()==0){
                event.addNormalMulti(((float) MMTEffectHelper.getInstance().getOffHandEffectLevel(livingEntity,flawless_attack_Effect) /200));
                int currentDamage = livingEntity.getOffhandItem().getDamageValue();
                int newDamage =  (currentDamage + 1);
                //设置损耗值
                livingEntity.getOffhandItem().setDamageValue(Math.max(0, newDamage));
            }
        }
    }
}
