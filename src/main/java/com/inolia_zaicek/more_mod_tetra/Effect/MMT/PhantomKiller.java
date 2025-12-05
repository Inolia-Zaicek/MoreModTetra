package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Collection;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class PhantomKiller {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(phantomKillerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                phantomKillerName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(phantomKillerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,phantomKillerEffect);
            if (effectLevel > 0) {
                if (mob instanceof Phantom) {
                    event.addFixedDamage(effectLevel);
                }
            }
        }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
            var mob = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,phantomKillerEffect);
            if (effectLevel > 0) {
                if (mob instanceof Phantom) {
                    event.addFixedDamage(effectLevel);
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
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,phantomKillerEffect);
                if (effectLevel > 0) {
                    if (mob instanceof Phantom) {
                    for (int i = 0; i < effectLevel; i++) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.PHANTOM_MEMBRANE
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                }
            }
        }
    }
}
