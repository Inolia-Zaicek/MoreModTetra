package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import net.minecraft.tags.DamageTypeTags;
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

public class DispellEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(dispellEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                dispellEffectTraitName, 0, 1  , false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(dispellEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            //挨打是玩家
            if (event.getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    ModularItem curiousItem = (ModularItem) itemStack.getItem();
                                    int effectLevel = curiousItem.getEffectLevel(itemStack, dispellEffectTraitEffect);
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = player.getMainHandItem();
                                    ItemStack offhandItem = player.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof ModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, dispellEffectTraitEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof ModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, dispellEffectTraitEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0) {
                                        if (event.getSource().is(DamageTypeTags.BYPASSES_EFFECTS) || event.getSource().is(DamageTypeTags.WITCH_RESISTANT_TO) ) {
                                            event.setAmount(0);
                                        }
                                    }
                                }
                        )
                );
            }
        }
    }
}
