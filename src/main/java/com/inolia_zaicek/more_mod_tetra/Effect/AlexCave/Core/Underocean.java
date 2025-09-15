package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import net.minecraft.world.entity.LivingEntity;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Underocean {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(underoceanEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                underoceanName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(underoceanTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof Player player&&event.getEntity()!=null) {
                LivingEntity mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, underoceanEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, underoceanEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += offEffectLevel;
                    }
                }
                if (mob != null && effectLevel > 0 && mob.hasEffect(ACEffectRegistry.BUBBLED.get())) {
                    event.setAmount(event.getAmount() * (1 + effectLevel / 100));
                }
            }
            //挨打
        if (event.getEntity() instanceof Player player&&event.getSource().getEntity() != null) {
            LivingEntity mob = event.getSource().getEntity().getControllingPassenger();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, underoceanEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, underoceanEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            if (mob!=null&&effectLevel > 0&&mob.hasEffect(ACEffectRegistry.BUBBLED.get())) {
                event.setAmount(event.getAmount()*(1- (float) effectLevel /100));
            }
        }
        }
    }
