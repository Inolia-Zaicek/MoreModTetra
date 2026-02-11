package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.GunKnightPatriotIngotEffect;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ExhortationOfGunKnightPatriot {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter00000023 = new StatGetterEffectLevel(exhortationOfGunKnightPatriotEffect, 1);
        var statGetter00000024 = new StatGetterEffectEfficiency(exhortationOfGunKnightPatriotEffect, 1);
        IStatGetter[] statGetters00000033 = {statGetter00000023, statGetter00000024};
        IStatFormat[] statFormats00000033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar00000033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                exhortationOfGunKnightPatriotName, 0, 100, false, false, false,
                statGetter00000023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(exhortationOfGunKnightPatriotTooltip, statGetters00000033, statFormats00000033)
        );
        WorkbenchStatsGui.addBar(statBar00000033);
        HoloStatsGui.addBar(statBar00000033);
    }
}