package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.armor_final_stand_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.armor_last_stand_Effect;

public class ArmorLastAndFinalStand {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //坚毅不倒
        if (event.getEntity() != null) {
            LivingEntity livingEntity = event.getEntity();
            float effectLevelN = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_last_stand_Effect);
            float effectLevelD = MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(livingEntity, armor_last_stand_Effect);
            float mhp = livingEntity.getMaxHealth();
            float hp = livingEntity.getHealth();
            //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例
            float dhp = (mhp - hp) / mhp;
            //反向阈值
            float dEffect = 1 - effectLevelD/100;
            //例：阈值30%，当前55%——dhp=45/100，阈值为30/100，需要dhp=70/100，应取dhp-反向阈值）/反向阈值——即阈值进度
            //如果阈值40，当前40%，也就是dhp=0.6 dEffect = 0.6，完美
            float finalDhp =Math.min(1, ( dhp-dEffect ) / ( dEffect ) );
            if (effectLevelN > 0 && hp > 0) {
                event.setAmount(event.getAmount() * (1 - (finalDhp) * (effectLevelN / 100)));
            }
        }
        //背水一战
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float effectLevelN = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_final_stand_Effect);
            float effectLevelD = MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(livingEntity, armor_final_stand_Effect);
            float mhp = livingEntity.getMaxHealth();
            float hp = livingEntity.getHealth();
            //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例
            float dhp = (mhp - hp) / mhp;
            //反向阈值
            float dEffect = 1 - effectLevelD/100;
            //例：阈值30%，当前55%——dhp=45/100，阈值为30/100，需要dhp=70/100，应取dhp-反向阈值）/反向阈值——即阈值进度
            //如果阈值40，当前40%，也就是dhp=0.6 dEffect = 0.6，完美
            float finalDhp =Math.min(1, ( dhp-dEffect ) / ( dEffect ) );
            if (effectLevelN > 0 && hp > 0) {
                event.setAmount(event.getAmount() * (1 + (finalDhp) * (effectLevelN / 100)));
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            float effectLevelN = MMTEffectHelper.getInstance().getAllArmorSumEffectLevel(livingEntity, armor_final_stand_Effect);
            float effectLevelD = MMTEffectHelper.getInstance().getAllArmorSumEffectEfficiency(livingEntity, armor_final_stand_Effect);
            float mhp = livingEntity.getMaxHealth();
            float hp = livingEntity.getHealth();
            //最大生命值-当前生命值=已损失生命值 /最大生命值=已损失比例
            float dhp = (mhp - hp) / mhp;
            //反向阈值
            float dEffect = 1 - effectLevelD/100;
            //例：阈值30%，当前55%——dhp=45/100，阈值为30/100，需要dhp=70/100，应取dhp-反向阈值）/反向阈值——即阈值进度
            //如果阈值40，当前40%，也就是dhp=0.6 dEffect = 0.6，完美
            float finalDhp =Math.min(1, ( dhp-dEffect ) / ( dEffect ) );
            if (effectLevelN > 0 && hp > 0) {
                event.setAmount(event.getAmount() * (1 + (finalDhp) * (effectLevelN / 100)));
            }
        }
    }
}