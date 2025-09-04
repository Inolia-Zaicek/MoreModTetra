package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Slash;

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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class RisingSlash {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(risingSlashEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                risingSlashName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(risingSlashTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, risingSlashEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, risingSlashEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                Random random = new Random();
                float mhp =mob.getMaxHealth();
                if (effectLevel > 0&&random.nextInt(100) <= (effectLevel)) {
                    if (player.getCooldowns().isOnCooldown(mainHandItem.getItem()))
                        return;
                    event.setAmount(event.getAmount()+mhp*0.05f);
                    }
                }
            }
        }

