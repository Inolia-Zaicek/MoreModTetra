package com.inolia_zaicek.more_mod_tetra.Util;

import com.google.common.collect.Lists;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.lang.invoke.MethodHandle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

@SuppressWarnings({"all", "removal"})
public class MMTUtil {
    //获取周围敌人列表
    public static List<Mob> mobList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Mob.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }
    public static boolean no2SameCurio(LivingEntity living, Item item) {
        // 使用predicate判断Item是否相同
        return no2SameCurio(living, (stack) -> stack.getItem() == item);
    }

    public static boolean no2SameCurio(LivingEntity living, Predicate<ItemStack> predicate) {
        // Map用来统计每个Item的数量
        Map<Item, Integer> itemCountMap = new HashMap<>();

        AtomicBoolean isWithinLimit = new AtomicBoolean(true);
        CuriosApi.getCuriosInventory(living).ifPresent((handler) -> {
            for (ICurioStacksHandler curioStacksHandler : handler.getCurios().values()) {
                IDynamicStackHandler stackHandler = curioStacksHandler.getStacks();

                for (int i = 0; i < stackHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty() && predicate.test(stack)) {
                        itemCountMap.put(stack.getItem(), itemCountMap.getOrDefault(stack.getItem(), 0) + 1);
                        // 一旦数量超过2，直接返回false
                        if (itemCountMap.get(stack.getItem()) >= 2) {
                            isWithinLimit.set(false);
                            return;
                        }
                    }
                }
                if (!isWithinLimit.get()) {
                    break;
                }
            }
        });
        return isWithinLimit.get();
    }
    public static boolean noSameCurio(LivingEntity living, Item item) {
        return noSameCurio(living, (Predicate<ItemStack>) (itemStack) -> itemStack.getItem() == item);
    }

    public static boolean noSameCurio(LivingEntity living, Predicate<ItemStack> predicate) {
        AtomicBoolean isEmpty = new AtomicBoolean(true);
        CuriosApi.getCuriosInventory(living).ifPresent((handler) -> {
            for (ICurioStacksHandler curioStacksHandler : handler.getCurios().values()) {
                IDynamicStackHandler stackHandler = curioStacksHandler.getStacks();

                for (int i = 0; i < stackHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty() && predicate.test(stack)) {
                        isEmpty.set(false);
                        return;
                    }
                }
            }
        });
        return isEmpty.get();
    }
    //获取周围玩家列表
    public static List<Player> PlayerList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Player.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }
    public static int getEnchantmentsCount(ItemStack stack) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        int count = 0;
        for (Integer level : enchantments.values()) {
            if (level > 0) {
                count++;
            }
        }
        return count;
    }
    private static final MethodHandle handle_LivingEntity_getDeathSound = null;
    public static @Nullable SoundEvent getDeathSound(LivingEntity living) {
        SoundEvent sound = null;
        if (handle_LivingEntity_getDeathSound != null) {
            try {
                sound = (SoundEvent) handle_LivingEntity_getDeathSound.invokeExact(living);
            } catch (Throwable var3) {
            }
        }

        return sound;
    }
    //判断玩家身上附魔数量
    public static int getTotalEnchantmentsAmount(Player player) {
        int totalCount = 0;
        for (ItemStack stack : getFullEquipment(player)) {
            if (stack != null) {
                totalCount += getEnchantmentsCount(stack);
            }
        }
        return totalCount;
    }
    //全身装备
    public static List<ItemStack> getFullEquipment(Player player) {
        List<ItemStack> equipmentStacks = Lists.newArrayList();
        equipmentStacks.add(player.getMainHandItem());
        equipmentStacks.add(player.getOffhandItem());
        equipmentStacks.addAll(player.getInventory().armor);
        if (CuriosApi.getCuriosHelper().getCuriosHandler(player).isPresent()) {
            ICuriosItemHandler handler = (ICuriosItemHandler)CuriosApi.getCuriosHelper().getCuriosHandler(player).orElse((ICuriosItemHandler) null);
            Map<String, ICurioStacksHandler> curios = handler.getCurios();

            for(Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = (ICurioStacksHandler)entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();

                for(int i = 0; i < stackHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    equipmentStacks.add(stack);
                }
            }
        }

        return equipmentStacks;
    }
}
