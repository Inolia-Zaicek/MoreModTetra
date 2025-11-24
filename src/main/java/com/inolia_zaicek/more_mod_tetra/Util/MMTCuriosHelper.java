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

public class MMTCuriosHelper {
    public static MMTCuriosHelper INSTANCE;

    public static MMTCuriosHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MMTCuriosHelper();
        }
        return INSTANCE;
    }

    public top.theillusivec4.curios.common.CuriosHelper curiosHelper = new top.theillusivec4.curios.common.CuriosHelper();

    public List<ItemStack> getAllModularItemsInCurios(LivingEntity entity) {
        return curiosHelper.findCurios(entity, stack -> stack.getItem() instanceof IModularItem)
                .stream()
                .map(SlotResult::stack)
                .toList();
    }
    //有词条等级
    public boolean hasCuriosEffectLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                .anyMatch(stack -> ((IModularItem) stack.getItem()).getEffects(stack).contains(effect));
    }
    //获取饰品中总词条第一数据
    public int getCuriosEffectLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                .mapToInt(stack -> ((IModularItem) stack.getItem()).getEffectLevel(stack, effect))
                .sum();
    }
    //获取饰品中词条最大第一数据
    public int getCuriosEffectMaxLevel(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                .mapToInt(stack -> ((IModularItem) stack.getItem()).getEffectLevel(stack, effect))
                .reduce(0, Math::max);
    }
    //获取饰品中总词条第二数据
    public float getCuriosEffectEfficiency(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                .map(stack -> ((IModularItem) stack.getItem()).getEffectEfficiency(stack, effect))
                .reduce(0.0F, Float::sum);
    }
    //获取饰品中词条最大第二数据
    public float getCuriosEffectMaxEfficiency(LivingEntity entity, ItemEffect effect) {
        return getAllModularItemsInCurios(entity)
                .stream()
                .map(stack -> ((IModularItem) stack.getItem()).getEffectEfficiency(stack, effect))
                .reduce(0f, Math::max);
    }

    @HideFromJS
    public static Multimap<Attribute, AttributeModifier> Curios$fixIdentifiers(SlotContext slotContext, Multimap<Attribute, AttributeModifier> modifiers) {
        return Optional.ofNullable(modifiers)
                .map(Multimap::entries)
                .map(Collection::stream)
                .map((entries) -> entries.collect(
                        Multimaps.toMultimap(
                                Map.Entry::getKey,
                                (entry) -> {
                                    var key = entry.getValue().getName() + slotContext.identifier() + slotContext.index() + entry.getValue().getOperation().name();
                                    return new AttributeModifier(
                                            UUID.nameUUIDFromBytes(key.getBytes()),
                                            key,
                                            entry.getValue().getAmount(),
                                            entry.getValue().getOperation()
                                    );
                                },
                                ArrayListMultimap::create))
                ).orElse(null);
    }

}
