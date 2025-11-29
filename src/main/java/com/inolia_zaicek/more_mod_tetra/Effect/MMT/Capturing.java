package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Collection;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Capturing {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(capturingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                capturingName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(capturingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> drops = event.getDrops();
            if (mob instanceof Player) {
                return;
            }
            Level level = attacker.level();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(attacker,capturingEffect);
                if (effectLevel > 0) {
                    Random random = new Random();
                    //获取刷怪蛋
                    Item eggItem = ForgeSpawnEggItem.fromEntityType(mob.getType());
                    //没有刷怪蛋就寄
                    if (eggItem == null) return;
                    Item egg = new ItemStack(eggItem, 1).getItem();
                    ItemEntity itementity = new ItemEntity(level, mob.getX(),
                            mob.getY(), mob.getZ(),
                            egg
                                    .getDefaultInstance());
                    if (random.nextInt(100) <= effectLevel) {
                        drops.add(itementity);
                    }
            }
        }
    }
}
