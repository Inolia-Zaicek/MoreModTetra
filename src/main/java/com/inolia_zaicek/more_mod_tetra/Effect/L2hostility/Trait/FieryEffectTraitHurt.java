package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.fieryEffectTraitEffect;
import static net.minecraft.tags.DamageTypeTags.IS_FIRE;

public class FieryEffectTraitHurt {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            //挨打的是玩家且攻击者非空
            if (event.getAttacked()!=null&&event.hurtEvent.getSource().getEntity()!=null) {
                LivingEntity livingEntity = event.getAttacked();;
                CuriosApi.getCuriosInventory(livingEntity).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem curiousItem = (IModularItem) itemStack.getItem();
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, fieryEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = livingEntity.getMainHandItem();
                                    ItemStack offhandItem = livingEntity.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof IModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, fieryEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel +=  mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof IModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, fieryEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0) {
                                        event.getAttacked().setRemainingFireTicks(200);
                                        event.hurtEvent.getSource().getEntity().setRemainingFireTicks(200);
                                        if(event.hurtEvent.getSource().is(DamageTypeTags.IS_FIRE)
                                        ||event.hurtEvent.getSource() == livingEntity.damageSources().lava()
                                        ||event.hurtEvent.getSource() == livingEntity.damageSources().inFire()
                                        ||event.hurtEvent.getSource() == livingEntity.damageSources().onFire()
                                                ||event.hurtEvent.getSource().is(IS_FIRE)
                                        ) {
                                            event.setResult(Event.Result.DENY);
                                        }
                                    }
                                }
                        )
                );
            }
            //攻击者是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity &&event.getAttacked()!=null) {
                CuriosApi.getCuriosInventory(livingEntity).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem curiousItem = (IModularItem) itemStack.getItem();
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, fieryEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = livingEntity.getMainHandItem();
                                    ItemStack offhandItem = livingEntity.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof IModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, fieryEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel +=  mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof IModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, fieryEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0) {
                                        event.getAttacked().setRemainingFireTicks(200);
                                        event.hurtEvent.getSource().getEntity().setRemainingFireTicks(200);
                                    }
                                }
                        )
                );
            }
        }
    }
}
