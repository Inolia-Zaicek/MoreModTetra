package com.inolia_zaicek.more_mod_tetra.Register;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

public class TooltipItem extends Item {
    public TooltipItem(Properties properties) {
        super(properties);
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        //if (Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip." + "more_mod_tetra" + "." + itemName + "_shift_text").withStyle(style -> style.withColor(TextColor.fromRgb(0xA0A0A0))));} else
        {
            // 鼠标经过时的默认文本
            // 翻译键格式: tooltip.<你的ModID>.<物品ID>_text
            pTooltipComponents.add(Component.translatable("tooltip." + "more_mod_tetra" + "." + itemName + ".text")
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xA0A0A0))));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}