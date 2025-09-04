package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class DragonBreathCombo {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(dragonBreathComboEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                dragonBreathComboName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(dragonBreathComboTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, dragonBreathComboEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, dragonBreathComboEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    float damage =event.getAmount();
                        mob.invulnerableTime=0;
                        mob.setLastHurtByPlayer(player);
                        mob.hurt(mob.damageSources().dragonBreath(),damage*number);
                        mob.setLastHurtByPlayer(player);
            }
        }else            if (event.getSource().getDirectEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, dragonBreathComboEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, dragonBreathComboEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    float damage =event.getAmount();
                    mob.invulnerableTime=0;
                    mob.setLastHurtByPlayer(player);
                    mob.hurt(mob.damageSources().dragonBreath(),damage*number);
                    mob.setLastHurtByPlayer(player);
                }
            }
    }
}
