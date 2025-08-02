package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.items.modular.ModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FieryEffectTraitHurt {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            //挨打的是玩家且攻击者非空
            if (event.getEntity() instanceof Player player &&event.getSource().getEntity()!=null) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    ModularItem curiousItem = (ModularItem) itemStack.getItem();
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, fieryEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = player.getMainHandItem();
                                    ItemStack offhandItem = player.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof ModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, fieryEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof ModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, fieryEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0) {
                                        event.getEntity().setRemainingFireTicks(200);
                                        event.getSource().getEntity().setRemainingFireTicks(200);
                                        if(event.getSource().is(DamageTypeTags.IS_FIRE)
                                        ||event.getSource() == player.damageSources().lava()
                                        ||event.getSource() == player.damageSources().inFire()
                                        ||event.getSource() == player.damageSources().onFire()
                                        ) {
                                            event.setAmount(0);
                                        }
                                    }
                                }
                        )
                );
            }
            //攻击者是玩家
            if (event.getSource().getDirectEntity() instanceof Player player &&event.getEntity()!=null) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    ModularItem curiousItem = (ModularItem) itemStack.getItem();
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, fieryEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = player.getMainHandItem();
                                    ItemStack offhandItem = player.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof ModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, fieryEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof ModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, fieryEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0) {
                                        event.getEntity().setRemainingFireTicks(200);
                                        event.getSource().getEntity().setRemainingFireTicks(200);
                                    }
                                }
                        )
                );
            }
        }
    }
}
