package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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

import java.util.Collection;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EmeraldPillage {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(emeraldPillageEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                emeraldPillageName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(emeraldPillageTooltip, statGetter)
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
                float mainEffectLevel = item.getEffectLevel(mainHandItem, emeraldPillageEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, emeraldPillageEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0) {
                if (mob instanceof Villager ||mob instanceof Pillager ||mob instanceof WanderingTrader ||mob instanceof Evoker ||mob instanceof Vindicator) {
                    Level level = mob.level();
                    BlockPos pos = mob.getOnPos();
                    for (int i = 0; i < effectLevel; i++) {
                        double x = pos.getX() + 0.5;
                        double y = pos.getY() + 1.0;
                        double z = pos.getZ() + 0.5;
                        ItemEntity itemEntity = new ItemEntity(level, x, y, z, Items.EMERALD.getDefaultInstance() );
                        itemEntity.setDeltaMovement((level.random.nextDouble() - 0.5) * 0, 0.3 + level.random.nextDouble() * 0.005, (level.random.nextDouble() - 0.5) * 0);
                        level.addFreshEntity(itemEntity);
                    }
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof Player player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, emeraldPillageEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, emeraldPillageEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0) {
                if (mob instanceof Villager ||mob instanceof Pillager ||mob instanceof WanderingTrader ||mob instanceof Evoker ||mob instanceof Vindicator) {
                    Level level = mob.level();
                    BlockPos pos = mob.getOnPos();
                    for (int i = 0; i < effectLevel; i++) {
                        double x = pos.getX() + 0.5;
                        double y = pos.getY() + 1.0;
                        double z = pos.getZ() + 0.5;
                        ItemEntity itemEntity = new ItemEntity(level, x, y, z, Items.EMERALD.getDefaultInstance() );
                        itemEntity.setDeltaMovement((level.random.nextDouble() - 0.5) * 0, 0.3 + level.random.nextDouble() * 0.005, (level.random.nextDouble() - 0.5) * 0);
                        level.addFreshEntity(itemEntity);
                    }
                }
            }
        }
    }
}
