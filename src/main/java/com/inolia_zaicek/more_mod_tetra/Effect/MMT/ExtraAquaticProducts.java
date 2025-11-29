package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

public class ExtraAquaticProducts {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(extraAquaticProductsEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                extraAquaticProductsName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(extraAquaticProductsTooltip, statGetter)
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
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,extraAquaticProductsEffect);
            if (effectLevel > 0) {
                    Random random = new Random();
                    for (int i = 0; i < effectLevel; i++) {
                        if (random.nextInt(100)<20) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.KELP
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (random.nextInt(100)>=20&&random.nextInt(100)<40) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.SALMON
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (random.nextInt(100)>=40&&random.nextInt(100)<60) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.TROPICAL_FISH
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (random.nextInt(100)>=60&&random.nextInt(100)<80) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.COD
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (random.nextInt(100)>=80) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.PUFFERFISH
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                    }
                }
            }

    }
}
