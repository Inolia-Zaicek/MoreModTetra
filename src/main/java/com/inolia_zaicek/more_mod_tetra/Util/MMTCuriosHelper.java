package com.inolia_zaicek.more_mod_tetra.Util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;

import java.util.*;

/**
 * 改造版MMTCuriosHelper，加入缓存优化
 */
public class MMTCuriosHelper {
    public static MMTCuriosHelper INSTANCE;

    public static MMTCuriosHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MMTCuriosHelper();
        }
        return INSTANCE;
    }

    public static top.theillusivec4.curios.common.CuriosHelper curiosHelper = new top.theillusivec4.curios.common.CuriosHelper();

    // 缓存定义：用WeakHashMap避免内存泄露（实体弱引用）
    private static final Map<LivingEntity, CachedData> cacheMap = new WeakHashMap<>();
    private static final long CACHE_EXPIRE_MS = 500; // 缓存有效期：0.5秒

    // 缓存数据结构
    private static class CachedData {
        List<ItemStack> modularItems;
        long timestamp;
        CachedData(List<ItemStack> items, long time) {
            this.modularItems = items;
            this.timestamp = time;
        }
    }

    /**
     * 在装备发生变化或者需要刷新缓存时调用
     */
    public static void refreshCache(LivingEntity entity) {
        if (entity == null) return;
        List<ItemStack> items = _getAllModularItemsInCuriosDirect(entity);
        cacheMap.put(entity, new CachedData(items, System.currentTimeMillis()));
    }

    /**
     * 获取所有符合条件的Curios物品，优先使用缓存
     */
    public List<ItemStack> getAllModularItemsInCurios(LivingEntity entity) {
        if (entity == null) return Collections.emptyList();

        long now = System.currentTimeMillis();
        CachedData cached = cacheMap.get(entity);
        if (cached != null && (now - cached.timestamp) < CACHE_EXPIRE_MS) {
            return cached.modularItems;
        }
        // 缓存不存在或已过期，重新扫描
        List<ItemStack> result = _getAllModularItemsInCuriosDirect(entity);
        cacheMap.put(entity, new CachedData(result, now));
        return result;
    }

    // 实际扫描Curios的核心方法
    private static List<ItemStack> _getAllModularItemsInCuriosDirect(LivingEntity entity) {
        // 增加 !stack.isEmpty() 检查，进一步确保不会处理空物品栈 (ItemStack.EMPTY 的 Item 是 AirItem)
        return curiosHelper.findCurios(entity, stack -> !stack.isEmpty() && stack.getItem() instanceof IModularItem)
                .stream()
                .map(SlotResult::stack)
                // 再次过滤，确保返回的 ItemStack 集合只包含非空且实现了 IModularItem 的物品
                .filter(stack -> !stack.isEmpty() && stack.getItem() instanceof IModularItem)
                .toList();
    }

    // 以下为原有的效果获取方法，调用getAllModularItemsInCurios()会自动使用缓存
    public boolean hasCuriosEffectLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                // 在进行强制类型转换前增加过滤
                .filter(stack -> stack.getItem() instanceof IModularItem)
                .anyMatch(stack -> ((IModularItem) stack.getItem()).getEffects(stack).contains(effect));
    }

    public int getCuriosEffectLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                // 在进行强制类型转换前增加过滤
                .filter(stack -> stack.getItem() instanceof IModularItem)
                .mapToInt(stack -> ((IModularItem) stack.getItem()).getEffectLevel(stack, effect))
                .sum();
    }

    public int getCuriosEffectMaxLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                // 在进行强制类型转换前增加过滤，解决行99的ClassCastException
                .filter(stack -> stack.getItem() instanceof IModularItem)
                .mapToInt(stack -> ((IModularItem) stack.getItem()).getEffectLevel(stack, effect))
                .reduce(0, Math::max);
    }

    public float getCuriosEffectEfficiency(LivingEntity entity, ItemEffect effect) {
        return (float) getAllModularItemsInCurios(entity)
                .stream()
                // 在进行强制类型转换前增加过滤
                .filter(stack -> stack.getItem() instanceof IModularItem)
                .mapToDouble(stack -> ((IModularItem) stack.getItem()).getEffectEfficiency(stack, effect))
                .sum();
    }

    public float getCuriosEffectMaxEfficiency(LivingEntity entity, ItemEffect effect) {
        return (float) getAllModularItemsInCurios(entity)
                .stream()
                // 在进行强制类型转换前增加过滤
                .filter(stack -> stack.getItem() instanceof IModularItem)
                .mapToDouble(stack -> ((IModularItem) stack.getItem()).getEffectEfficiency(stack, effect))
                .max()
                .orElse(0.0f);
    }

    /**
     * 固定属性标识符，主要不变
     */
    @HideFromJS
    public static Multimap<Attribute, AttributeModifier> Curios$fixIdentifiers(SlotContext slotContext, Multimap<Attribute, AttributeModifier> modifiers) {
        return Optional.ofNullable(modifiers)
                .map(Multimap::entries)
                .map(Collection::stream)
                .map((entries) -> entries.collect(
                        Multimaps.toMultimap(
                                Map.Entry::getKey,
                                (entry) -> {
                                    String keyStr = entry.getValue().getName() + slotContext.identifier() + slotContext.index() + entry.getValue().getOperation().name();
                                    UUID uuid = UUID.nameUUIDFromBytes(keyStr.getBytes());
                                    return new AttributeModifier(
                                            uuid,
                                            keyStr,
                                            entry.getValue().getAmount(),
                                            entry.getValue().getOperation()
                                    );
                                },
                                ArrayListMultimap::create))
                ).orElse(null);
    }
}