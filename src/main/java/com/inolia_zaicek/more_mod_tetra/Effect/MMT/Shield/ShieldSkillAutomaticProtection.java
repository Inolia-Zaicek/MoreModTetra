package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Shield;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ShieldSkillAutomaticProtection {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(shieldSkillAutomaticProtectionEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                shieldSkillAutomaticProtectionName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(shieldSkillAutomaticProtectionTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, shieldSkillAutomaticProtectionEffect);
                    if (mainEffectLevel > 0&&  !(player.getCooldowns().isOnCooldown((Item) item))  ) {
                        effectLevel += (int) mainEffectLevel;
                        if(event.getAmount()>player.getMaxHealth()*effectLevel/100) {
                            event.setAmount(0);
                            player.getCooldowns().addCooldown((Item) item, 15 * 20);
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, shieldSkillAutomaticProtectionEffect);
                    if (offEffectLevel > 0&&  !(player.getCooldowns().isOnCooldown((Item) item))  ) {
                        effectLevel += (int) offEffectLevel;
                        if(event.getAmount()>player.getMaxHealth()*effectLevel/100) {
                            event.setAmount(0);
                            player.getCooldowns().addCooldown((Item) item, 15 * 20);
                        }
                    }
                }
            }
        }
    }
