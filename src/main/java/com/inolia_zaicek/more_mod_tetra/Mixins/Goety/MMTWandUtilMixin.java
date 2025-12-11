package com.inolia_zaicek.more_mod_tetra.Mixins.Goety;

import com.Polarice3.Goety.utils.WandUtil;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.Polarice3.Goety.utils.WandUtil.findFocus;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curios_goety_soul_focus_potency_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_focus_potency_Effect;

// 标记为伪Mixin，确保不影响原始类（特别是在不存在的类或环境下使用）
@Pseudo
// 指定混入目标为WandUtil类
@Mixin(WandUtil.class)
public class MMTWandUtilMixin {
    // 在WandUtil类的getLevels方法返回后插入代码（钩子）
    @Inject(
            method = "getLevels", // 目标方法名称
            at = @At("RETURN"), // 在方法返回点插入（即调用getLevels后）
            cancellable = true, // 允许修改返回值
            remap = false // 不进行混淆映射
    )
    private static void getLevelsMixin(Enchantment enchantment, LivingEntity livingEntity, CallbackInfoReturnable<Integer> cir){
        // 获取原方法的返回值，即返回的等级
        float number = cir.getReturnValue();
        float percentageDiscount = 0;
        float fixedDiscount = 0;
        /// 对词条进行判断
        if(livingEntity.getMainHandItem().getItem() == MoreModTetraItemRegister.MODULAR_GoetyWandItem.get() || livingEntity.getOffhandItem().getItem() == MoreModTetraItemRegister.MODULAR_GoetyWandItem.get()) {
            //饰品取最大值
            fixedDiscount += (float) MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(livingEntity, curios_goety_soul_focus_potency_Effect);
            percentageDiscount += MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(livingEntity, curios_goety_soul_focus_potency_Effect) / 100;
            //主副手取最大值
            fixedDiscount += (float) MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_soul_focus_potency_Effect);
            percentageDiscount += MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, goety_soul_focus_potency_Effect) / 100;
            // 这里可以添加自定义逻辑，例如修改等级
            if (fixedDiscount > 0) {
                number += fixedDiscount; // 自定义增加的等级
            }
            if (percentageDiscount > 0) {
                number *= 1 + percentageDiscount / 100;
            }
        }
        // 设置新的返回值
        cir.setReturnValue((int) number);
    }
    @Inject(
            method = "isMatchingItem", // 目标方法名称
            at = @At("RETURN"), // 在方法返回点插入（即调用getLevels后）
            cancellable = true, // 允许修改返回值
            remap = false // 不进行混淆映射
    )
    private static void getMatchingItemMixin(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir){
        if(itemStack.getItem() == MoreModTetraItemRegister.MODULAR_GoetyWandItem.get()) {
            cir.setReturnValue(true);
        }
    }
    @Inject(
            method = "enchantedFocus", // 目标方法名称
            at = @At("RETURN"), // 在方法返回点插入（即调用getLevels后）
            cancellable = true, // 允许修改返回值
            remap = false // 不进行混淆映射
    )
    private static void enchantedFocusMixin(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir){
        //主副手其中一个是诡厄法杖
        if(!findFocus(livingEntity).isEmpty() && (livingEntity.getMainHandItem().getItem() == MoreModTetraItemRegister.MODULAR_GoetyWandItem.get()
        || livingEntity.getOffhandItem().getItem() == MoreModTetraItemRegister.MODULAR_GoetyWandItem.get() )
        ) {
            cir.setReturnValue(true);
        }
    }
}