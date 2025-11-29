package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
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
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Extinction {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(extinctionEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                extinctionName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(extinctionTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,extinctionEffect);
                float mhp = player.getMaxHealth();
                float hp = player.getHealth();
                float dhp = hp / mhp;
                float mobMhp = mob.getMaxHealth();
                float mobHp = mob.getHealth();
                float mobDhp = mobHp / mobMhp;
                if (effectLevel > 0&&dhp>mobDhp) {
                        event.setAmount(event.getAmount()*(1+ (float) effectLevel /100));
            }
        }else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,extinctionEffect);
                float mhp = player.getMaxHealth();
                float hp = player.getHealth();
                float dhp = hp / mhp;
                float mobMhp = mob.getMaxHealth();
                float mobHp = mob.getHealth();
                float mobDhp = mobHp / mobMhp;
                if (effectLevel > 0&&dhp>mobDhp) {
                    event.setAmount(event.getAmount()*(1+ (float) effectLevel /100));
                }
            }
    }
}
