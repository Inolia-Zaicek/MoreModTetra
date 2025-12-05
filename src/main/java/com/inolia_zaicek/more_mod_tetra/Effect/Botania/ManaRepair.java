package com.inolia_zaicek.more_mod_tetra.Effect.Botania;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import vazkii.botania.api.mana.ManaItemHandler;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ManaRepair {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(manaRepairEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                manaRepairName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(manaRepairTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("botania")) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            ItemStack itemStack = player.getInventory().getSelected();

            // 定义一个工具方法，处理装备的减魔逻辑
            handleModularItem(player, mainHandItem);
            handleModularItem(player, offHandItem);

            // 处理盔甲部位
            handleHeadgear(player);
            handleChestplate(player);
            handleLeggings(player);
            handleBoots(player);
        }
    }

    // 处理单个工具或装备的魔力修复
    private static void handleModularItem(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof IModularItem item)) return;

        float effectLevel = item.getEffectLevel(stack, manaRepairEffect);
        if (effectLevel <= 0 || player.level().getGameTime() % 20L != 0) return;

        int currentDamage = stack.getDamageValue();
        if (ManaItemHandler.INSTANCE.requestManaExactForTool(stack, player, 140, true) && currentDamage > 0) {
            int newDamage = (int) (currentDamage - effectLevel);
            stack.setDamageValue(Math.max(0, newDamage));
            ManaItemHandler.INSTANCE.dispatchManaExact(stack, player, 140, false);
        }
    }

    // 处理头盔
    private static void handleHeadgear(Player player) {
        ItemStack headStack = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
        processArmorPart(player, headStack);
    }
    // 处理胸甲
    private static void handleChestplate(Player player) {
        ItemStack chestStack = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST);
        processArmorPart(player, chestStack);
    }
    // 处理护腿
    private static void handleLeggings(Player player) {
        ItemStack legStack = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS);
        processArmorPart(player, legStack);
    }
    // 处理靴子
    private static void handleBoots(Player player) {
        ItemStack feetStack = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
        processArmorPart(player, feetStack);
    }

    // 统一处理装备盔甲部分的逻辑
    private static void processArmorPart(Player player, ItemStack armorStack) {
        if (!(armorStack.getItem() instanceof IModularItem item)) return;
        float effectLevel = item.getEffectLevel(armorStack, manaRepairEffect);
        if (effectLevel <= 0 || player.level().getGameTime() % 20L != 0) return;

        int currentDamage = armorStack.getDamageValue();
        if (ManaItemHandler.INSTANCE.requestManaExactForTool(armorStack, player, 140, true) && currentDamage > 0) {
            int newDamage = (int) (currentDamage - effectLevel);
            armorStack.setDamageValue(Math.max(0, newDamage));
            ManaItemHandler.INSTANCE.dispatchManaExact(armorStack, player, 140, false);
        }
    }
}
