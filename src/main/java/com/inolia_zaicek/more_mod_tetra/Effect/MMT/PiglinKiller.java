package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
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

public class PiglinKiller {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(piglinKillerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                piglinKillerName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(piglinKillerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,piglinKillerEffect);
            if (effectLevel > 0) {
                if (mob instanceof Piglin||mob instanceof ZombifiedPiglin||mob instanceof PiglinBrute) {
                    event.setAmount(event.getAmount() + effectLevel);
                }
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,piglinKillerEffect);
            if (effectLevel > 0) {
                if (mob instanceof Piglin||mob instanceof ZombifiedPiglin||mob instanceof PiglinBrute) {
                    event.setAmount(event.getAmount() + effectLevel);
                }
            }
        }
    }
    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker ) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> drops = event.getDrops();
            if (mob instanceof Player) {
                return;
            }
            Level level = attacker.level();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,piglinKillerEffect);
                if (effectLevel > 0) {
                    if (mob instanceof Piglin||mob instanceof ZombifiedPiglin||mob instanceof PiglinBrute) {
                    for (int i = 0; i < effectLevel; i++) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.GOLD_NUGGET
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                }
            }
        }
    }
}
