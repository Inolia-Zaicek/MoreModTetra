package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class HideBladeCurious {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(hideBladeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                hideBladeName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(hideBladeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getSource().getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    int effectLevel = item.getEffectLevel(itemStack, hideBladeEffect);
                                    if (effectLevel > 0) {
                                        //是近战
                                        if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                                            //藏锋已满
                                            if (player.hasEffect(MMTEffectsRegister.HideBladeMax.get())) {
                                                event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                                                player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                                player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                            } else {
                                                player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                                player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                            }
                                        }
                                        //不是近战
                                        else {
                                            player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                            player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                        }
                                    }
                                }
                        )
                );
            }
            else if (event.getSource().getDirectEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    int effectLevel = item.getEffectLevel(itemStack, hideBladeEffect);
                                    if (effectLevel > 0) {
                                        //是近战
                                        if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                                            //藏锋已满
                                            if (player.hasEffect(MMTEffectsRegister.HideBladeMax.get())) {
                                                event.setAmount(event.getAmount() * (1 + (float) effectLevel / 100));
                                                player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                                player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                            } else {
                                                player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                                player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                            }
                                        }
                                        //不是近战
                                        else {
                                            player.removeEffect(MMTEffectsRegister.HideBlade.get());
                                            player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                                        }
                                    }
                                }
                        )
                );
            }
        }
    }
}
