package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ReflectEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(reflectEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                reflectEffectTraitName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(reflectEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
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
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, reflectEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = player.getMainHandItem();
                                    ItemStack offhandItem = player.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof ModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, reflectEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof ModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, reflectEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0 &&event.getSource().getEntity()!=null) {
                                        if(event.getSource().getEntity() instanceof LivingEntity livingEntity){
                                            livingEntity.setLastHurtByPlayer(player);
                                        }
                                        event.getSource().getEntity().hurt(event.getSource().getEntity().damageSources().magic(),event.getAmount()*0.3f*effectLevel);
                                    }
                                }
                        )
                );
            }
        }
    }
}
