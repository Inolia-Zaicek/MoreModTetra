package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class HeavyChop {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        // 初始化GUI显示
        var statGetter = new StatGetterEffectLevel(heavyChopEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                heavyChopName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(heavyChopTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        // 监听玩家对生物造成的伤害事件
        // 检查伤害来源是否是玩家
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity(); // 被攻击的目标生物
            ItemStack mainHandItem = player.getMainHandItem(); // 玩家主手物品
            float armorPenetrationLevel = 0; // 护甲穿透效果等级
            int effectLevel = 0; // 招架效果等级（HeavyChop的等级）

            // 检查玩家主手物品是否是IModularItem，并获取HeavyChop和护甲穿透效果的等级
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, heavyChopEffect);
                float armorPenetrationMainEffectLevel = item.getEffectLevel(mainHandItem, ItemEffect.get("armorPenetration"));
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                    armorPenetrationLevel += armorPenetrationMainEffectLevel;
                }
            }

            // 获取被攻击者的总护甲值 (虽然此代码中主要用护甲槽位数量，但保留以备未来扩展)
            int armor = (int) event.getEntity().getAttributeValue(Attributes.ARMOR);

            // 只有当HeavyChop效果等级大于0且目标生物存在时才执行后续逻辑
            if (effectLevel > 0 && event.getEntity() != null) {
                int emptyArmorSlots = 0; // 用于计数目标生物身上空着的护甲槽位数量

                // 遍历目标生物的四种主要护甲槽位：头盔、胸甲、护腿、鞋子
                for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
                    ItemStack armorStack = mob.getItemBySlot(slot); // 获取该槽位上的护甲物品
                    if (armorStack.isEmpty()) { // 如果该护甲槽位是空的
                        emptyArmorSlots++; // 增加空护甲槽位的计数
                    }
                }

                // 修复逻辑：
                // 1. 先计算出有多少个空护甲槽位 (emptyArmorSlots)。
                // 2. 然后根据这个总数一次性计算增伤，而不是在每次检测到空槽位时就触发。

                // 计算每次攻击的增伤比例
                // (float) effectLevel / 100 是基础增伤比例，每拥有一个空护甲槽位，这个比例会乘以空护甲槽位的数量
                float damageMultiplier = (float) effectLevel / 100;

                // 检查是否存在护甲穿透效果，或者在没有穿透效果的情况下，
                // 被攻击者的护甲值经过穿透计算后是否为0或更低。
                // 这里的逻辑是：如果护甲穿透了所有护甲（或护甲穿透比例非常高），或者被攻击者本来就没有护甲，
                // 那么就只应用基础增伤（基于effectLevel）。
                if (armorPenetrationLevel >= 100 || ((int) (armor * (1 - armorPenetrationLevel / 100))) <= 0) {
                    // 如果护甲穿透有效，则应用基础增伤（不依赖空护甲槽位数量）
                    //player.sendSystemMessage(Component.literal("穿透有效，应用基础增伤: " + damageMultiplier).withStyle(ChatFormatting.GREEN));
                    event.setAmount(event.getAmount() * (1 + 4*damageMultiplier));
                } else if (emptyArmorSlots > 0) {
                    // 如果存在空护甲槽位，并且护甲穿透效果不完全抵消护甲
                    // 则计算基于空护甲槽位数量的额外增伤
                    // 每次攻击的基础增伤 (effectLevel/100) 乘以空护甲槽位的数量
                    float totalDamageIncrease = emptyArmorSlots * damageMultiplier;
                    //player.sendSystemMessage(Component.literal("发现 " + emptyArmorSlots + " 个空护甲槽位，应用增伤: " + totalDamageIncrease+"，最终伤害: " + (event.getAmount() * (1 + totalDamageIncrease)) ).withStyle(ChatFormatting.RED));
                    // 应用总增伤
                    event.setAmount(event.getAmount() * (1 + totalDamageIncrease));
                }
            }
        }
    }
}