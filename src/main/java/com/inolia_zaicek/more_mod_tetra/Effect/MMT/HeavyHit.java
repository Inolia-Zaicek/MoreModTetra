package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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

public class HeavyHit {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(heavyHitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                heavyHitName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(heavyHitTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, heavyHitEffect);
                    float armorPenetrationMainEffectLevel = item.getEffectLevel(mainHandItem, ItemEffect.get("armorPenetration"));
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                float armor = (float) event.getEntity().getAttributeValue(Attributes.ARMOR);
                if (effectLevel > 0&&event.getEntity()!=null) {
                    int emptyArmorSlots = 0; // 用于计数目标生物身上空着的护甲槽位数量

                    // 遍历目标生物的四种主要护甲槽位：头盔、胸甲、护腿、鞋子
                    for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
                        ItemStack armorStack = mob.getItemBySlot(slot); // 获取该槽位上的护甲物品
                        if (!armorStack.isEmpty()) { // 如果该护甲槽位不是空的
                            emptyArmorSlots++; // 增加护甲槽位的计数
                        }
                    }
                    //护甲值每大于10，视为一件装备
                    if(armor>=10){
                        emptyArmorSlots+= (int) (armor/10);
                    }
                    //增伤比例
                    float damageMultiplier = (float) effectLevel / 100;
                    //满额
                    if (emptyArmorSlots > 0) {
                        //护甲数量*增伤
                        float totalDamageIncrease = emptyArmorSlots * damageMultiplier;
                        event.setAmount(event.getAmount() * (1 + totalDamageIncrease));
                    }
            }
        }
    }
}
