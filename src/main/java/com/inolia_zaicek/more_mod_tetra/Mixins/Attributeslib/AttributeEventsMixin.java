package com.inolia_zaicek.more_mod_tetra.Mixins.Attributeslib;

import dev.shadowsoffire.attributeslib.impl.AttributeEvents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.mickelus.tetra.items.modular.impl.bow.ModularBowItem;
import se.mickelus.tetra.items.modular.impl.crossbow.ModularCrossbowItem;

// 标记为伪Mixin，确保不影响原始类（特别是在不存在的类或环境下使用）
@Pseudo
// 指定混入目标为WandUtil类
@Mixin(AttributeEvents.class)
public class AttributeEventsMixin {
    @Inject(
            method = "canBenefitFromDrawSpeed", // 目标方法名称
            at = @At("RETURN"), // 在方法返回点插入（即调用getLevels后）
            cancellable = true, // 允许修改返回值
            remap = false // 不进行混淆映射
    )
    private void canBenefitFromDrawSpeed(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof ModularBowItem || stack.getItem() instanceof ModularCrossbowItem) {
            cir.setReturnValue(true);
        }
    }
}
