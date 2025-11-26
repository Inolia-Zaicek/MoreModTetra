package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class CuriosFeatherFalling {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosFeatherFallingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosFeatherFallingName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosFeatherFallingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity()!=null) {
                LivingEntity player = event.getEntity();
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, curiosFeatherFallingEffect);
                if (effectLevel > 0) {
                    if (event.getSource() == player.damageSources().fall()
                            || event.getSource() == player.damageSources().fallingBlock(player)
                            || event.getSource() == player.damageSources().fallingStalactite(player)
                    ) {
                        if (effectLevel < 100) {
                            event.setAmount(event.getAmount() * (1 - effectLevel / 100));
                        } else {
                            event.setAmount(0);
                        }
                    }
                }
            }
        }
    }
}
