package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class HideBlade {
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
            if (event.getSource().getDirectEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, hideBladeEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, hideBladeEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //是近战
                    if(MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                        //藏锋已满
                        if(player.hasEffect(MMTEffectsRegister.HideBladeMax.get())){
                            event.setAmount(event.getAmount()*(1+ (float) effectLevel /100));
                        }
                        else{
                            player.removeEffect(MMTEffectsRegister.HideBlade.get());
                            player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                        }
                    }
                    //不是近战
                    else{
                        player.removeEffect(MMTEffectsRegister.HideBlade.get());
                        player.removeEffect(MMTEffectsRegister.HideBladeMax.get());
                    }
                }
            }
        }
    }
