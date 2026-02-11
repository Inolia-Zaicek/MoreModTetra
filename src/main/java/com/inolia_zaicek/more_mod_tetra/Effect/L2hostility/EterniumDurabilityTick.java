package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.eterniumDurabilityEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EterniumDurabilityTick {

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            ItemStack itemStack = player.getInventory().getSelected();

            // 定义一个工具方法，处理装备的扣魂
            handleModularItem(player, mainHandItem);
            handleModularItem(player, offHandItem);

            // 处理盔甲部位
            handleHeadgear(player);
            handleChestplate(player);
            handleLeggings(player);
            handleBoots(player);
    }

    private static void handleModularItem(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof IModularItem item)) return;

        int effectLevel = item.getEffectLevel(stack, eterniumDurabilityEffect);
        if (effectLevel <= 0 || player.level().getGameTime() % 20L != 0) return;

        int currentDamage = stack.getDamageValue();
        int newDamage = (int) (currentDamage - effectLevel);
        if (currentDamage <= 0) return;
        stack.setDamageValue(Math.max(0, newDamage));
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
        int effectLevel = item.getEffectLevel(armorStack, eterniumDurabilityEffect);
        if (effectLevel <= 0 || player.level().getGameTime() % 20L != 0) return;
        int currentDamage = armorStack.getDamageValue();
        int newDamage = (int) (currentDamage - effectLevel);
        if (currentDamage <= 0) return;
        armorStack.setDamageValue(Math.max(0, newDamage));
    }
}
