package com.inolia_zaicek.more_mod_tetra.ArmorEffect;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class MMTEffectClent {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter023 = new StatGetterEffectLevel(goety_soul_cost_discount_Effect, 1);
        var statGetter024 = new StatGetterEffectEfficiency(goety_soul_cost_discount_Effect, 1);
        IStatGetter[] statGetters033 = {statGetter023, statGetter024};
        IStatFormat[] statFormats033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_cost_discount_Name, 0, 5, false, false, false,
                statGetter023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(goety_soul_cost_discount_Tooltip, statGetters033, statFormats033)
        );
        WorkbenchStatsGui.addBar(statBar033);
        HoloStatsGui.addBar(statBar033);
        var statGetter025 = new StatGetterEffectLevel(goety_soul_focus_potency_Effect, 1);
        var statGetter026 = new StatGetterEffectEfficiency(goety_soul_focus_potency_Effect, 1);
        IStatGetter[] statGetters034 = {statGetter025, statGetter026};
        IStatFormat[] statFormats034 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar034 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_focus_potency_Name, 0, 5, false, false, false,
                statGetter025, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(goety_soul_focus_potency_Tooltip, statGetters034, statFormats034)
        );
        WorkbenchStatsGui.addBar(statBar034);
        HoloStatsGui.addBar(statBar034);
        var statGetter0023 = new StatGetterEffectLevel(curios_goety_soul_cost_discount_Effect, 1);
        var statGetter0024 = new StatGetterEffectEfficiency(curios_goety_soul_cost_discount_Effect, 1);
        IStatGetter[] statGetters0033 = {statGetter0023, statGetter0024};
        IStatFormat[] statFormats0033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar0033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_soul_cost_discount_Name, 0, 10, false, false, false,
                statGetter0023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(curios_goety_soul_cost_discount_Tooltip, statGetters0033, statFormats0033)
        );
        WorkbenchStatsGui.addBar(statBar0033);
        HoloStatsGui.addBar(statBar0033);
        var statGetter0025 = new StatGetterEffectLevel(curios_goety_soul_focus_potency_Effect, 1);
        var statGetter0026 = new StatGetterEffectEfficiency(curios_goety_soul_focus_potency_Effect, 1);
        IStatGetter[] statGetters0034 = {statGetter0025, statGetter0026};
        IStatFormat[] statFormats0034 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar0034 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_soul_focus_potency_Name, 0, 10, false, false, false,
                statGetter0025, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(curios_goety_soul_focus_potency_Tooltip, statGetters0034, statFormats0034)
        );
        WorkbenchStatsGui.addBar(statBar0034);
        HoloStatsGui.addBar(statBar0034);
        /*
        var statGetter1 = new StatGetterEffectLevel(ignitium_suit_Effect, 1);
        GuiStatBar statBar1 = new GuiStatBar(0, 0, StatsHelper.barLength,
                ignitium_suit_Name, 0, 12, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(ignitium_suit_Tooltip, statGetter1)
        );
        WorkbenchStatsGui.addBar(statBar1);
        HoloStatsGui.addBar(statBar1);
        */
        var statGetter1 = new StatGetterEffectLevel(ignitium_suit_Effect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(ignitium_suit_Effect, 1);
        IStatGetter[] statGetters1 = { statGetter1,statGetter2};
        IStatFormat[] statFormats1 = {StatFormat.noDecimal,StatFormat.noDecimal};
        GuiStatBar statBar1 = new GuiStatBar(0, 0, StatsHelper.barLength,
                ignitium_suit_Name, 0, 100, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(ignitium_suit_Tooltip, statGetters1,statFormats1)
        );
        WorkbenchStatsGui.addBar(statBar1);
        HoloStatsGui.addBar(statBar1);

        var statGetter3 = new StatGetterEffectLevel(cursium_feet_Effect, 1);
        GuiStatBar statBar3 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cursium_feet_Name, 0, 24, false, false, false,
                statGetter3, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cursium_feet_Tooltip, statGetter3)
        );
        WorkbenchStatsGui.addBar(statBar3);
        HoloStatsGui.addBar(statBar3);

        var statGetter4 = new StatGetterEffectLevel(cursium_legs_Effect, 1);
        GuiStatBar statBar4 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cursium_legs_Name, 0, 10, false, false, false,
                statGetter4, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cursium_legs_Tooltip, statGetter4)
        );
        WorkbenchStatsGui.addBar(statBar4);
        HoloStatsGui.addBar(statBar4);

        var statGetter5 = new StatGetterEffectLevel(cursium_head_Effect, 1);
        GuiStatBar statBar5 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cursium_head_Name, 0, 10, false, false, false,
                statGetter5, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cursium_head_Tooltip, statGetter5)
        );
        WorkbenchStatsGui.addBar(statBar5);
        HoloStatsGui.addBar(statBar5);

        var statGetter6 = new StatGetterEffectLevel(cursium_chest_Effect, 1);
        GuiStatBar statBar6 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cursium_chest_Name, 0, 100, false, false, false,
                statGetter6, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cursium_chest_Tooltip, statGetter6)
        );
        WorkbenchStatsGui.addBar(statBar6);
        HoloStatsGui.addBar(statBar6);

        var statGetter7 = new StatGetterEffectLevel(armor_critical_strike_Effect, 1);
        var statGetter8 = new StatGetterEffectEfficiency(armor_critical_strike_Effect, 1);
        IStatGetter[] statGetters = { statGetter7,statGetter8};
        IStatFormat[] statFormats = {StatFormat.noDecimal,StatFormat.noDecimal};
        GuiStatBar statBar8 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_critical_strike_Name, 0, 100, false, false, false,
                statGetter7, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(armor_critical_strike_Tooltip, statGetters,statFormats)
        );
        WorkbenchStatsGui.addBar(statBar8);
        HoloStatsGui.addBar(statBar8);

        var statGetter9 = new StatGetterEffectLevel(armor_sturdy_Effect, 1);
        GuiStatBar statBar9 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_sturdy_Name, 0, 10, false, false, false,
                statGetter9, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_sturdy_Tooltip, statGetter9)
        );
        WorkbenchStatsGui.addBar(statBar9);
        HoloStatsGui.addBar(statBar9);

        var statGetter10 = new StatGetterEffectLevel(armor_tenacity_Effect, 1);
        GuiStatBar statBar10 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_tenacity_Name, 0, 100, false, false, false,
                statGetter10, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_tenacity_Tooltip, statGetter10)
        );
        WorkbenchStatsGui.addBar(statBar10);
        HoloStatsGui.addBar(statBar10);

        var statGetter11 = new StatGetterEffectLevel(armor_heal_Effect, 1);
        GuiStatBar statBar11 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_heal_Name, 0, 10, false, false, false,
                statGetter11, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_heal_Tooltip, statGetter11)
        );
        WorkbenchStatsGui.addBar(statBar11);
        HoloStatsGui.addBar(statBar11);

        var statGetter12 = new StatGetterEffectLevel(armor_revival_Effect, 1);
        GuiStatBar statBar12 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_revival_Name, 0, 10, false, false, false,
                statGetter12, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_revival_Tooltip, statGetter12)
        );
        WorkbenchStatsGui.addBar(statBar12);
        HoloStatsGui.addBar(statBar12);

        var statGetter13 = new StatGetterEffectLevel(arthropods_protection_Effect, 1);
        GuiStatBar statBar13 = new GuiStatBar(0, 0, StatsHelper.barLength,
                arthropods_protection_Name, 0, 100, false, false, false,
                statGetter13, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(arthropods_protection_Tooltip, statGetter13)
        );
        WorkbenchStatsGui.addBar(statBar13);
        HoloStatsGui.addBar(statBar13);

        var statGetter14 = new StatGetterEffectLevel(undead_protection_Effect, 1);
        GuiStatBar statBar14 = new GuiStatBar(0, 0, StatsHelper.barLength,
                undead_protection_Name, 0, 100, false, false, false,
                statGetter14, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(undead_protection_Tooltip, statGetter14)
        );
        WorkbenchStatsGui.addBar(statBar14);
        HoloStatsGui.addBar(statBar14);

        var statGetter15 = new StatGetterEffectLevel(armor_complete_form_Effect, 1);
        GuiStatBar statBar15 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_complete_form_Name, 0, 100, false, false, false,
                statGetter15, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_complete_form_Tooltip, statGetter15)
        );
        WorkbenchStatsGui.addBar(statBar15);
        HoloStatsGui.addBar(statBar15);

        var statGetter16 = new StatGetterEffectLevel(armor_final_stand_Effect, 1);
        GuiStatBar statBar16 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_final_stand_Name, 0, 100, false, false, false,
                statGetter16, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_final_stand_Tooltip, statGetter16)
        );
        WorkbenchStatsGui.addBar(statBar16);
        HoloStatsGui.addBar(statBar16);

        var statGetter17 = new StatGetterEffectLevel(armor_last_stand_Effect, 1);
        GuiStatBar statBar17 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_last_stand_Name, 0, 100, false, false, false,
                statGetter17, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_last_stand_Tooltip, statGetter17)
        );
        WorkbenchStatsGui.addBar(statBar17);
        HoloStatsGui.addBar(statBar17);


        var statGetter18 = new StatGetterEffectLevel(armor_thorns_Effect, 1);
        GuiStatBar statBar18 = new GuiStatBar(0, 0, StatsHelper.barLength,
                armor_thorns_Name, 0, 10, false, false, false,
                statGetter18, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(armor_thorns_Tooltip, statGetter18)
        );
        WorkbenchStatsGui.addBar(statBar18);
        HoloStatsGui.addBar(statBar18);

        var statGetter19 = new StatGetterEffectLevel(magic_thorns_Effect, 1);
        GuiStatBar statBar19 = new GuiStatBar(0, 0, StatsHelper.barLength,
                magic_thorns_Name, 0, 10, false, false, false,
                statGetter19, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(magic_thorns_Tooltip, statGetter19)
        );
        WorkbenchStatsGui.addBar(statBar19);
        HoloStatsGui.addBar(statBar19);

        var statGetter20 = new StatGetterEffectLevel(lightning_thorns_Effect, 1);
        GuiStatBar statBar20 = new GuiStatBar(0, 0, StatsHelper.barLength,
                lightning_thorns_Name, 0, 100, false, false, false,
                statGetter20, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(lightning_thorns_Tooltip, statGetter20)
        );
        WorkbenchStatsGui.addBar(statBar20);
        HoloStatsGui.addBar(statBar20);

        var statGetter21 = new StatGetterEffectLevel(fire_thorns_Effect, 1);
        GuiStatBar statBar21 = new GuiStatBar(0, 0, StatsHelper.barLength,
                fire_thorns_Name, 0, 100, false, false, false,
                statGetter21, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(fire_thorns_Tooltip, statGetter21)
        );
        WorkbenchStatsGui.addBar(statBar21);
        HoloStatsGui.addBar(statBar21);

        var statGetter22 = new StatGetterEffectLevel(freeze_thorns_Effect, 1);
        GuiStatBar statBar22 = new GuiStatBar(0, 0, StatsHelper.barLength,
                freeze_thorns_Name, 0, 100, false, false, false,
                statGetter22, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(freeze_thorns_Tooltip, statGetter22)
        );
        WorkbenchStatsGui.addBar(statBar22);
        HoloStatsGui.addBar(statBar22);

        var statGetter23 = new StatGetterEffectLevel(wither_thorns_Effect, 1);
        GuiStatBar statBar23 = new GuiStatBar(0, 0, StatsHelper.barLength,
                wither_thorns_Name, 0, 100, false, false, false,
                statGetter23, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(wither_thorns_Tooltip, statGetter23)
        );
        WorkbenchStatsGui.addBar(statBar23);
        HoloStatsGui.addBar(statBar23);

        var statGetter24 = new StatGetterEffectLevel(dragon_breath_thorns_Effect, 1);
        GuiStatBar statBar24 = new GuiStatBar(0, 0, StatsHelper.barLength,
                dragon_breath_thorns_Name, 0, 100, false, false, false,
                statGetter24, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(dragon_breath_thorns_Tooltip, statGetter24)
        );
        WorkbenchStatsGui.addBar(statBar24);
        HoloStatsGui.addBar(statBar24);

        var statGetter25 = new StatGetterEffectLevel(etherium_protection_Effect, 1);
        GuiStatBar statBar25 = new GuiStatBar(0, 0, StatsHelper.barLength,
                etherium_protection_Name, 0, 100, false, false, false,
                statGetter25, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(etherium_protection_Tooltip, statGetter25)
        );
        WorkbenchStatsGui.addBar(statBar25);
        HoloStatsGui.addBar(statBar25);

        var statGetter26 = new StatGetterEffectLevel(flawless_attack_Effect, 1);
        GuiStatBar statBar26 = new GuiStatBar(0, 0, StatsHelper.barLength,
                flawless_attack_Name, 0, 100, false, false, false,
                statGetter26, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(flawless_attack_Tooltip, statGetter26)
        );
        WorkbenchStatsGui.addBar(statBar26);
        HoloStatsGui.addBar(statBar26);

        var statGetter27 = new StatGetterEffectLevel(flawless_protection_Effect, 1);
        GuiStatBar statBar27 = new GuiStatBar(0, 0, StatsHelper.barLength,
                flawless_protection_Name, 0, 100, false, false, false,
                statGetter27, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(flawless_protection_Tooltip, statGetter27)
        );
        WorkbenchStatsGui.addBar(statBar27);
        HoloStatsGui.addBar(statBar27);

        var statGetter28 = new StatGetterEffectLevel(ride_skill_Effect, 1);
        GuiStatBar statBar28 = new GuiStatBar(0, 0, StatsHelper.barLength,
                ride_skill_Name, 0, 100, false, false, false,
                statGetter28, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(ride_skill_Tooltip, statGetter28)
        );
        WorkbenchStatsGui.addBar(statBar28);
        HoloStatsGui.addBar(statBar28);

        var statGetter29 = new StatGetterEffectLevel(dolphins_grace_buff_Effect, 1);
        GuiStatBar statBar29 = new GuiStatBar(0, 0, StatsHelper.barLength,
                dolphins_grace_buff_Name, 0, 1, false, false, false,
                statGetter29, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(dolphins_grace_buff_Tooltip, statGetter29)
        );
        WorkbenchStatsGui.addBar(statBar29);
        HoloStatsGui.addBar(statBar29);

        var statGetter30 = new StatGetterEffectLevel(saturation_buff_Effect, 1);
        GuiStatBar statBar30 = new GuiStatBar(0, 0, StatsHelper.barLength,
                saturation_buff_Name, 0, 1, false, false, false,
                statGetter30, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(saturation_buff_Tooltip, statGetter30)
        );
        WorkbenchStatsGui.addBar(statBar30);
        HoloStatsGui.addBar(statBar30);

        var statGetter31 = new StatGetterEffectLevel(hero_of_the_village_buff_Effect, 1);
        GuiStatBar statBar31 = new GuiStatBar(0, 0, StatsHelper.barLength,
                hero_of_the_village_buff_Name, 0, 1, false, false, false,
                statGetter31, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(hero_of_the_village_buff_Tooltip, statGetter31)
        );
        WorkbenchStatsGui.addBar(statBar31);
        HoloStatsGui.addBar(statBar31);

        var statGetter32 = new StatGetterEffectLevel(luck_buff_Effect, 1);
        GuiStatBar statBar32 = new GuiStatBar(0, 0, StatsHelper.barLength,
                luck_buff_Name, 0, 1, false, false, false,
                statGetter32, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(luck_buff_Tooltip, statGetter32)
        );
        WorkbenchStatsGui.addBar(statBar32);
        HoloStatsGui.addBar(statBar32);

        var statGetter33 = new StatGetterEffectLevel(speed_buff_Effect, 1);
        GuiStatBar statBar33 = new GuiStatBar(0, 0, StatsHelper.barLength,
                speed_buff_Name, 0, 1, false, false, false,
                statGetter33, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(speed_buff_Tooltip, statGetter33)
        );
        WorkbenchStatsGui.addBar(statBar33);
        HoloStatsGui.addBar(statBar33);

        var statGetter34 = new StatGetterEffectLevel(fire_resistance_buff_Effect, 1);
        GuiStatBar statBar34 = new GuiStatBar(0, 0, StatsHelper.barLength,
                fire_resistance_buff_Name, 0, 1, false, false, false,
                statGetter34, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(fire_resistance_buff_Tooltip, statGetter34)
        );
        WorkbenchStatsGui.addBar(statBar34);
        HoloStatsGui.addBar(statBar34);

        var statGetter35 = new StatGetterEffectLevel(slow_falling_buff_Effect, 1);
        GuiStatBar statBar35 = new GuiStatBar(0, 0, StatsHelper.barLength,
                slow_falling_buff_Name, 0, 1, false, false, false,
                statGetter35, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(slow_falling_buff_Tooltip, statGetter35)
        );
        WorkbenchStatsGui.addBar(statBar35);
        HoloStatsGui.addBar(statBar35);

        var statGetter36 = new StatGetterEffectLevel(haste_buff_Effect, 1);
        GuiStatBar statBar36 = new GuiStatBar(0, 0, StatsHelper.barLength,
                haste_buff_Name, 0, 1, false, false, false,
                statGetter36, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(haste_buff_Tooltip, statGetter36)
        );
        WorkbenchStatsGui.addBar(statBar36);
        HoloStatsGui.addBar(statBar36);

        var statGetter37 = new StatGetterEffectLevel(resistance_buff_Effect, 1);
        GuiStatBar statBar37 = new GuiStatBar(0, 0, StatsHelper.barLength,
                resistance_buff_Name, 0, 1, false, false, false,
                statGetter37, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(resistance_buff_Tooltip, statGetter37)
        );
        WorkbenchStatsGui.addBar(statBar37);
        HoloStatsGui.addBar(statBar37);

        var statGetter38 = new StatGetterEffectLevel(strength_buff_Effect, 1);
        GuiStatBar statBar38 = new GuiStatBar(0, 0, StatsHelper.barLength,
                strength_buff_Name, 0, 1, false, false, false,
                statGetter38, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(strength_buff_Tooltip, statGetter38)
        );
        WorkbenchStatsGui.addBar(statBar38);
        HoloStatsGui.addBar(statBar38);

        var statGetter39 = new StatGetterEffectLevel(regeneration_buff_Effect, 1);
        GuiStatBar statBar39 = new GuiStatBar(0, 0, StatsHelper.barLength,
                regeneration_buff_Name, 0, 1, false, false, false,
                statGetter39, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(regeneration_buff_Tooltip, statGetter39)
        );
        WorkbenchStatsGui.addBar(statBar39);
        HoloStatsGui.addBar(statBar39);

        var statGetter40 = new StatGetterEffectLevel(invisibility_buff_Effect, 1);
        GuiStatBar statBar40 = new GuiStatBar(0, 0, StatsHelper.barLength,
                invisibility_buff_Name, 0, 1, false, false, false,
                statGetter40, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(invisibility_buff_Tooltip, statGetter40)
        );
        WorkbenchStatsGui.addBar(statBar40);
        HoloStatsGui.addBar(statBar40);

        var statGetter41 = new StatGetterEffectLevel(curiosProjectileTrackingEffect, 1);
        GuiStatBar statBar41 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosProjectileTrackingName, 0, 1, false, false, false,
                statGetter41, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curiosProjectileTrackingTooltip, statGetter41)
        );
        WorkbenchStatsGui.addBar(statBar41);
        HoloStatsGui.addBar(statBar41);
        var statGetter42 = new StatGetterEffectLevel(gather_ore_Effect, 1);
        GuiStatBar statBar42 = new GuiStatBar(0, 0, StatsHelper.barLength,
                gather_ore_Name, 0, 10, false, false, false,
                statGetter42, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(gather_ore_Tooltip, statGetter42)
        );
        WorkbenchStatsGui.addBar(statBar42);
        HoloStatsGui.addBar(statBar42);
        var statGetter43 = new StatGetterEffectLevel(mineral_essence_Effect, 1);
        GuiStatBar statBar43 = new GuiStatBar(0, 0, StatsHelper.barLength,
                mineral_essence_Name, 0, 10, false, false, false,
                statGetter43, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(mineral_essence_Tooltip, statGetter43)
        );
        WorkbenchStatsGui.addBar(statBar43);
        HoloStatsGui.addBar(statBar43);
        var statGetter44 = new StatGetterEffectLevel(turning_stone_into_gold_Effect, 1);
        GuiStatBar statBar44 = new GuiStatBar(0, 0, StatsHelper.barLength,
                turning_stone_into_gold_Name, 0, 50, false, false, false,
                statGetter44, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(turning_stone_into_gold_Tooltip, statGetter44)
        );
        WorkbenchStatsGui.addBar(statBar44);
        HoloStatsGui.addBar(statBar44);
        var statGetter45 = new StatGetterEffectLevel(hunting_Effect, 1);
        GuiStatBar statBar45 = new GuiStatBar(0, 0, StatsHelper.barLength,
                hunting_Name, 0, 20, false, false, false,
                statGetter45, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(hunting_Tooltip, statGetter45)
        );
        WorkbenchStatsGui.addBar(statBar45);
        HoloStatsGui.addBar(statBar45);
        var statGetter46 = new StatGetterEffectLevel(archaeology_Effect, 1);
        GuiStatBar statBar46 = new GuiStatBar(0, 0, StatsHelper.barLength,
                archaeology_Name, 0, 10, false, false, false,
                statGetter46, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(archaeology_Tooltip, statGetter46)
        );
        WorkbenchStatsGui.addBar(statBar46);
        HoloStatsGui.addBar(statBar46);
        var statGetter47 = new StatGetterEffectLevel(warden_killer_Effect, 1);
        GuiStatBar statBar47 = new GuiStatBar(0, 0, StatsHelper.barLength,
                warden_killer_Name, 0, 50, false, false, false,
                statGetter47, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(warden_killer_Tooltip, statGetter47)
        );
        WorkbenchStatsGui.addBar(statBar47);
        HoloStatsGui.addBar(statBar47);
        var statGetter48 = new StatGetterEffectLevel(projectileTrackingEffect, 1);
        GuiStatBar statBar48 = new GuiStatBar(0, 0, StatsHelper.barLength,
                projectileTrackingName, 0, 1, false, false, false,
                statGetter48, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(projectileTrackingTooltip, statGetter48)
        );
        WorkbenchStatsGui.addBar(statBar48);
        HoloStatsGui.addBar(statBar48);
        var statGetter49 = new StatGetterEffectLevel(goety_soul_repair_Effect, 1);
        GuiStatBar statBar49 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_repair_Name, 0, 1, false, false, false,
                statGetter49, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_repair_Tooltip, statGetter49)
        );
        WorkbenchStatsGui.addBar(statBar49);
        HoloStatsGui.addBar(statBar49);
        var statGetter50 = new StatGetterEffectLevel(goety_soul_absorb_Effect, 1);
        GuiStatBar statBar50 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_absorb_Name, 0, 1, false, false, false,
                statGetter50, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_absorb_Tooltip, statGetter50)
        );
        WorkbenchStatsGui.addBar(statBar50);
        HoloStatsGui.addBar(statBar50);
        var statGetter51 = new StatGetterEffectLevel(goety_acid_venom_Effect, 1);
        GuiStatBar statBar51 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_acid_venom_Name, 0, 5, false, false, false,
                statGetter51, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_acid_venom_Tooltip, statGetter51)
        );
        WorkbenchStatsGui.addBar(statBar51);
        HoloStatsGui.addBar(statBar51);
        var statGetter52 = new StatGetterEffectLevel(goety_ally_vex_Effect, 1);
        GuiStatBar statBar52 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_ally_vex_Name, 0, 50, false, false, false,
                statGetter52, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_ally_vex_Tooltip, statGetter52)
        );
        WorkbenchStatsGui.addBar(statBar52);
        HoloStatsGui.addBar(statBar52);
        var statGetter53 = new StatGetterEffectLevel(goety_soul_extract_Effect, 1);
        GuiStatBar statBar53 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_extract_Name, 0, 100, false, false, false,
                statGetter53, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_extract_Tooltip, statGetter53)
        );
        WorkbenchStatsGui.addBar(statBar53);
        HoloStatsGui.addBar(statBar53);
        var statGetter54 = new StatGetterEffectLevel(goety_soul_edge_Effect, 1);
        GuiStatBar statBar54 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_edge_Name, 0, 10, false, false, false,
                statGetter54, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_edge_Tooltip, statGetter54)
        );
        WorkbenchStatsGui.addBar(statBar54);
        HoloStatsGui.addBar(statBar54);
        var statGetter55 = new StatGetterEffectLevel(goety_soul_burst_Effect, 1);
        GuiStatBar statBar55 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_burst_Name, 0, 50, false, false, false,
                statGetter55, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_burst_Tooltip, statGetter55)
        );
        WorkbenchStatsGui.addBar(statBar55);
        HoloStatsGui.addBar(statBar55);
        var statGetter56 = new StatGetterEffectLevel(ars_mana_regen_Effect, 1);
        GuiStatBar statBar56 = new GuiStatBar(0, 0, StatsHelper.barLength,
                ars_mana_regen_Name, 0, 1, false, false, false,
                statGetter56, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(ars_mana_regen_Tooltip, statGetter56)
        );
        WorkbenchStatsGui.addBar(statBar56);
        HoloStatsGui.addBar(statBar56);
        var statGetter57 = new StatGetterEffectLevel(ars_spell_damage_Effect, 1);
        GuiStatBar statBar57 = new GuiStatBar(0, 0, StatsHelper.barLength,
                ars_spell_damage_Name, 0, 1, false, false, false,
                statGetter57, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(ars_spell_damage_Tooltip, statGetter57)
        );
        WorkbenchStatsGui.addBar(statBar57);
        HoloStatsGui.addBar(statBar57);
        var statGetter58 = new StatGetterEffectLevel(curios_ars_mana_regen_Effect, 1);
        GuiStatBar statBar58 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_ars_mana_regen_Name, 0, 1, false, false, false,
                statGetter58, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_ars_mana_regen_Tooltip, statGetter58)
        );
        WorkbenchStatsGui.addBar(statBar58);
        HoloStatsGui.addBar(statBar58);
        var statGetter59 = new StatGetterEffectLevel(curios_ars_spell_damage_Effect, 1);
        GuiStatBar statBar59 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_ars_spell_damage_Name, 0, 1, false, false, false,
                statGetter59, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_ars_spell_damage_Tooltip, statGetter59)
        );
        WorkbenchStatsGui.addBar(statBar59);
        HoloStatsGui.addBar(statBar59);
        var statGetter60 = new StatGetterEffectLevel(irons_spellbooks_oakskin_Effect, 1);
        GuiStatBar statBar60 = new GuiStatBar(0, 0, StatsHelper.barLength,
                irons_spellbooks_oakskin_Name, 0, 1, false, false, false,
                statGetter60, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(irons_spellbooks_oakskin_Tooltip, statGetter60)
        );
        WorkbenchStatsGui.addBar(statBar60);
        HoloStatsGui.addBar(statBar60);
        var statGetter61 = new StatGetterEffectLevel(curios_irons_spellbooks_oakskin_Effect, 1);
        GuiStatBar statBar61 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_irons_spellbooks_oakskin_Name, 0, 1, false, false, false,
                statGetter61, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_irons_spellbooks_oakskin_Tooltip, statGetter61)
        );
        WorkbenchStatsGui.addBar(statBar61);
        HoloStatsGui.addBar(statBar61);
        var statGetter62 = new StatGetterEffectLevel(irons_spellbooks_true_invisibility_Effect, 1);
        GuiStatBar statBar62 = new GuiStatBar(0, 0, StatsHelper.barLength,
                irons_spellbooks_true_invisibility_Name, 0, 1, false, false, false,
                statGetter62, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(irons_spellbooks_true_invisibility_Tooltip, statGetter62)
        );
        WorkbenchStatsGui.addBar(statBar62);
        HoloStatsGui.addBar(statBar62);
        var statGetter63 = new StatGetterEffectLevel(curios_irons_spellbooks_true_invisibility_Effect, 1);
        GuiStatBar statBar63 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_irons_spellbooks_true_invisibility_Name, 0, 1, false, false, false,
                statGetter63, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_irons_spellbooks_true_invisibility_Tooltip, statGetter63)
        );
        WorkbenchStatsGui.addBar(statBar63);
        HoloStatsGui.addBar(statBar63);
        var statGetter64 = new StatGetterEffectLevel(goety_climbing_Effect, 1);
        GuiStatBar statBar64 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_climbing_Name, 0, 1, false, false, false,
                statGetter64, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_climbing_Tooltip, statGetter64)
        );
        WorkbenchStatsGui.addBar(statBar64);
        HoloStatsGui.addBar(statBar64);
        var statGetter65 = new StatGetterEffectLevel(curios_goety_climbing_Effect, 1);
        GuiStatBar statBar65 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_climbing_Name, 0, 1, false, false, false,
                statGetter65, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_climbing_Tooltip, statGetter65)
        );
        WorkbenchStatsGui.addBar(statBar65);
        HoloStatsGui.addBar(statBar65);
        var statGetter66 = new StatGetterEffectLevel(goety_explosive_Effect, 1);
        GuiStatBar statBar66 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_explosive_Name, 0, 1, false, false, false,
                statGetter66, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_explosive_Tooltip, statGetter66)
        );
        WorkbenchStatsGui.addBar(statBar66);
        HoloStatsGui.addBar(statBar66);
        var statGetter67 = new StatGetterEffectLevel(curios_goety_explosive_Effect, 1);
        GuiStatBar statBar67 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_explosive_Name, 0, 1, false, false, false,
                statGetter67, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_explosive_Tooltip, statGetter67)
        );
        WorkbenchStatsGui.addBar(statBar67);
        HoloStatsGui.addBar(statBar67);
        var statGetter68 = new StatGetterEffectLevel(goety_swift_swim_Effect, 1);
        GuiStatBar statBar68 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_swift_swim_Name, 0, 1, false, false, false,
                statGetter68, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_swift_swim_Tooltip, statGetter68)
        );
        WorkbenchStatsGui.addBar(statBar68);
        HoloStatsGui.addBar(statBar68);
        var statGetter69 = new StatGetterEffectLevel(curios_goety_swift_swim_Effect, 1);
        GuiStatBar statBar69 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_swift_swim_Name, 0, 1, false, false, false,
                statGetter69, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_swift_swim_Tooltip, statGetter69)
        );
        WorkbenchStatsGui.addBar(statBar69);
        HoloStatsGui.addBar(statBar69);
        var statGetter70 = new StatGetterEffectLevel(goety_frog_leg_Effect, 1);
        GuiStatBar statBar70 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_frog_leg_Name, 0, 1, false, false, false,
                statGetter70, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_frog_leg_Tooltip, statGetter70)
        );
        WorkbenchStatsGui.addBar(statBar70);
        HoloStatsGui.addBar(statBar70);
        var statGetter71 = new StatGetterEffectLevel(curios_goety_frog_leg_Effect, 1);
        GuiStatBar statBar71 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_frog_leg_Name, 0, 1, false, false, false,
                statGetter71, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_frog_leg_Tooltip, statGetter71)
        );
        WorkbenchStatsGui.addBar(statBar71);
        HoloStatsGui.addBar(statBar71);
        var statGetter72 = new StatGetterEffectLevel(goety_flame_hands_Effect, 1);
        GuiStatBar statBar72 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_flame_hands_Name, 0, 1, false, false, false,
                statGetter72, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_flame_hands_Tooltip, statGetter72)
        );
        WorkbenchStatsGui.addBar(statBar72);
        HoloStatsGui.addBar(statBar72);
        var statGetter73 = new StatGetterEffectLevel(curios_goety_flame_hands_Effect, 1);
        GuiStatBar statBar73 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_flame_hands_Name, 0, 1, false, false, false,
                statGetter73, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_flame_hands_Tooltip, statGetter73)
        );
        WorkbenchStatsGui.addBar(statBar73);
        HoloStatsGui.addBar(statBar73);
        var statGetter74 = new StatGetterEffectLevel(goety_venomous_hands_Effect, 1);
        GuiStatBar statBar74 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_venomous_hands_Name, 0, 1, false, false, false,
                statGetter74, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_venomous_hands_Tooltip, statGetter74)
        );
        WorkbenchStatsGui.addBar(statBar74);
        HoloStatsGui.addBar(statBar74);
        var statGetter75 = new StatGetterEffectLevel(curios_goety_venomous_hands_Effect, 1);
        GuiStatBar statBar75 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_venomous_hands_Name, 0, 1, false, false, false,
                statGetter75, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_venomous_hands_Tooltip, statGetter75)
        );
        WorkbenchStatsGui.addBar(statBar75);
        HoloStatsGui.addBar(statBar75);
        var statGetter76 = new StatGetterEffectLevel(goety_repulsive_Effect, 1);
        GuiStatBar statBar76 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_repulsive_Name, 0, 1, false, false, false,
                statGetter76, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_repulsive_Tooltip, statGetter76)
        );
        WorkbenchStatsGui.addBar(statBar76);
        HoloStatsGui.addBar(statBar76);
        var statGetter77 = new StatGetterEffectLevel(curios_goety_repulsive_Effect, 1);
        GuiStatBar statBar77 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_repulsive_Name, 0, 1, false, false, false,
                statGetter77, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_repulsive_Tooltip, statGetter77)
        );
        WorkbenchStatsGui.addBar(statBar77);
        HoloStatsGui.addBar(statBar77);
        var statGetter78 = new StatGetterEffectLevel(goety_fiery_aura_Effect, 1);
        GuiStatBar statBar78 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_fiery_aura_Name, 0, 1, false, false, false,
                statGetter78, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_fiery_aura_Tooltip, statGetter78)
        );
        WorkbenchStatsGui.addBar(statBar78);
        HoloStatsGui.addBar(statBar78);
        var statGetter79 = new StatGetterEffectLevel(curios_goety_fiery_aura_Effect, 1);
        GuiStatBar statBar79 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_fiery_aura_Name, 0, 1, false, false, false,
                statGetter79, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_fiery_aura_Tooltip, statGetter79)
        );
        WorkbenchStatsGui.addBar(statBar79);
        HoloStatsGui.addBar(statBar79);
        var statGetter80 = new StatGetterEffectLevel(goety_frosty_aura_Effect, 1);
        GuiStatBar statBar80 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_frosty_aura_Name, 0, 1, false, false, false,
                statGetter80, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_frosty_aura_Tooltip, statGetter80)
        );
        WorkbenchStatsGui.addBar(statBar80);
        HoloStatsGui.addBar(statBar80);
        var statGetter81 = new StatGetterEffectLevel(curios_goety_frosty_aura_Effect, 1);
        GuiStatBar statBar81 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_frosty_aura_Name, 0, 1, false, false, false,
                statGetter81, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_frosty_aura_Tooltip, statGetter81)
        );
        WorkbenchStatsGui.addBar(statBar81);
        HoloStatsGui.addBar(statBar81);
        var statGetter82 = new StatGetterEffectLevel(goety_photosynthesis_Effect, 1);
        GuiStatBar statBar82 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_photosynthesis_Name, 0, 1, false, false, false,
                statGetter82, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_photosynthesis_Tooltip, statGetter82)
        );
        WorkbenchStatsGui.addBar(statBar82);
        HoloStatsGui.addBar(statBar82);
        var statGetter83 = new StatGetterEffectLevel(curios_goety_photosynthesis_Effect, 1);
        GuiStatBar statBar83 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_photosynthesis_Name, 0, 1, false, false, false,
                statGetter83, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_photosynthesis_Tooltip, statGetter83)
        );
        WorkbenchStatsGui.addBar(statBar83);
        HoloStatsGui.addBar(statBar83);
        var statGetter84 = new StatGetterEffectLevel(goety_insight_Effect, 1);
        GuiStatBar statBar84 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_insight_Name, 0, 1, false, false, false,
                statGetter84, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_insight_Tooltip, statGetter84)
        );
        WorkbenchStatsGui.addBar(statBar84);
        HoloStatsGui.addBar(statBar84);
        var statGetter85 = new StatGetterEffectLevel(curios_goety_insight_Effect, 1);
        GuiStatBar statBar85 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_insight_Name, 0, 1, false, false, false,
                statGetter85, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_insight_Tooltip, statGetter85)
        );
        WorkbenchStatsGui.addBar(statBar85);
        HoloStatsGui.addBar(statBar85);
        var statGetter86 = new StatGetterEffectLevel(goety_fortunate_Effect, 1);
        GuiStatBar statBar86 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_fortunate_Name, 0, 1, false, false, false,
                statGetter86, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_fortunate_Tooltip, statGetter86)
        );
        WorkbenchStatsGui.addBar(statBar86);
        HoloStatsGui.addBar(statBar86);
        var statGetter87 = new StatGetterEffectLevel(curios_goety_fortunate_Effect, 1);
        GuiStatBar statBar87 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_fortunate_Name, 0, 1, false, false, false,
                statGetter87, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_fortunate_Tooltip, statGetter87)
        );
        WorkbenchStatsGui.addBar(statBar87);
        HoloStatsGui.addBar(statBar87);
        var statGetter88 = new StatGetterEffectLevel(goety_bottling_Effect, 1);
        GuiStatBar statBar88 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_bottling_Name, 0, 1, false, false, false,
                statGetter88, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_bottling_Tooltip, statGetter88)
        );
        WorkbenchStatsGui.addBar(statBar88);
        HoloStatsGui.addBar(statBar88);
        var statGetter89 = new StatGetterEffectLevel(curios_goety_bottling_Effect, 1);
        GuiStatBar statBar89 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_bottling_Name, 0, 1, false, false, false,
                statGetter89, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_bottling_Tooltip, statGetter89)
        );
        WorkbenchStatsGui.addBar(statBar89);
        HoloStatsGui.addBar(statBar89);
        var statGetter90 = new StatGetterEffectLevel(goety_radiance_Effect, 1);
        GuiStatBar statBar90 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_radiance_Name, 0, 1, false, false, false,
                statGetter90, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_radiance_Tooltip, statGetter90)
        );
        WorkbenchStatsGui.addBar(statBar90);
        HoloStatsGui.addBar(statBar90);
        var statGetter91 = new StatGetterEffectLevel(curios_goety_radiance_Effect, 1);
        GuiStatBar statBar91 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_radiance_Name, 0, 1, false, false, false,
                statGetter91, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_radiance_Tooltip, statGetter91)
        );
        WorkbenchStatsGui.addBar(statBar91);
        HoloStatsGui.addBar(statBar91);
        var statGetter92 = new StatGetterEffectLevel(goety_deflective_Effect, 1);
        GuiStatBar statBar92 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_deflective_Name, 0, 1, false, false, false,
                statGetter92, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_deflective_Tooltip, statGetter92)
        );
        WorkbenchStatsGui.addBar(statBar92);
        HoloStatsGui.addBar(statBar92);
        var statGetter93 = new StatGetterEffectLevel(curios_goety_deflective_Effect, 1);
        GuiStatBar statBar93 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_deflective_Name, 0, 1, false, false, false,
                statGetter93, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_deflective_Tooltip, statGetter93)
        );
        WorkbenchStatsGui.addBar(statBar93);
        HoloStatsGui.addBar(statBar93);
        var statGetter94 = new StatGetterEffectLevel(goety_leeching_Effect, 1);
        GuiStatBar statBar94 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_leeching_Name, 0, 1, false, false, false,
                statGetter94, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_leeching_Tooltip, statGetter94)
        );
        WorkbenchStatsGui.addBar(statBar94);
        HoloStatsGui.addBar(statBar94);
        var statGetter95 = new StatGetterEffectLevel(curios_goety_leeching_Effect, 1);
        GuiStatBar statBar95 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_leeching_Name, 0, 1, false, false, false,
                statGetter95, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_leeching_Tooltip, statGetter95)
        );
        WorkbenchStatsGui.addBar(statBar95);
        HoloStatsGui.addBar(statBar95);
        var statGetter96 = new StatGetterEffectLevel(goety_swirling_Effect, 1);
        GuiStatBar statBar96 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_swirling_Name, 0, 1, false, false, false,
                statGetter96, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_swirling_Tooltip, statGetter96)
        );
        WorkbenchStatsGui.addBar(statBar96);
        HoloStatsGui.addBar(statBar96);
        var statGetter97 = new StatGetterEffectLevel(curios_goety_swirling_Effect, 1);
        GuiStatBar statBar97 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_swirling_Name, 0, 1, false, false, false,
                statGetter97, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_swirling_Tooltip, statGetter97)
        );
        WorkbenchStatsGui.addBar(statBar97);
        HoloStatsGui.addBar(statBar97);
        var statGetter98 = new StatGetterEffectLevel(goety_shielding_Effect, 1);
        GuiStatBar statBar98 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_shielding_Name, 0, 1, false, false, false,
                statGetter98, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_shielding_Tooltip, statGetter98)
        );
        WorkbenchStatsGui.addBar(statBar98);
        HoloStatsGui.addBar(statBar98);
        var statGetter99 = new StatGetterEffectLevel(curios_goety_shielding_Effect, 1);
        GuiStatBar statBar99 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_shielding_Name, 0, 1, false, false, false,
                statGetter99, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_shielding_Tooltip, statGetter99)
        );
        WorkbenchStatsGui.addBar(statBar99);
        HoloStatsGui.addBar(statBar99);
        var statGetter100 = new StatGetterEffectLevel(goety_rallying_Effect, 1);
        GuiStatBar statBar100 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_rallying_Name, 0, 1, false, false, false,
                statGetter100, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_rallying_Tooltip, statGetter100)
        );
        WorkbenchStatsGui.addBar(statBar100);
        HoloStatsGui.addBar(statBar100);
        var statGetter101 = new StatGetterEffectLevel(curios_goety_rallying_Effect, 1);
        GuiStatBar statBar101 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_rallying_Name, 0, 1, false, false, false,
                statGetter101, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_rallying_Tooltip, statGetter101)
        );
        WorkbenchStatsGui.addBar(statBar101);
        HoloStatsGui.addBar(statBar101);
        var statGetter102 = new StatGetterEffectLevel(goety_gravity_pulse_Effect, 1);
        GuiStatBar statBar102 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_gravity_pulse_Name, 0, 1, false, false, false,
                statGetter102, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_gravity_pulse_Tooltip, statGetter102)
        );
        WorkbenchStatsGui.addBar(statBar102);
        HoloStatsGui.addBar(statBar102);
        var statGetter103 = new StatGetterEffectLevel(curios_goety_gravity_pulse_Effect, 1);
        GuiStatBar statBar103 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_gravity_pulse_Name, 0, 1, false, false, false,
                statGetter103, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_gravity_pulse_Tooltip, statGetter103)
        );
        WorkbenchStatsGui.addBar(statBar103);
        HoloStatsGui.addBar(statBar103);;
        var statGetter104 = new StatGetterEffectLevel(attack_glowing_buff_Effect, 1);
        GuiStatBar statBar104 = new GuiStatBar(0, 0, StatsHelper.barLength,
                attack_glowing_buff_Name, 0, 1, false, false, false,
                statGetter104, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(attack_glowing_buff_Tooltip, statGetter104)
        );
        WorkbenchStatsGui.addBar(statBar104);
        HoloStatsGui.addBar(statBar104);

        var statGetter105 = new StatGetterEffectLevel(l2complements_curse_clear_Effect, 1);
        GuiStatBar statBar105 = new GuiStatBar(0, 0, StatsHelper.barLength,
                l2complements_curse_clear_Name, 0, 1, false, false, false,
                statGetter105, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(l2complements_curse_clear_Tooltip, statGetter105)
        );
        WorkbenchStatsGui.addBar(statBar105);
        HoloStatsGui.addBar(statBar105);
        var statGetter106 = new StatGetterEffectLevel(curios_l2complements_curse_clear_Effect, 1);
        GuiStatBar statBar106 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_l2complements_curse_clear_Name, 0, 1, false, false, false,
                statGetter106, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_l2complements_curse_clear_Tooltip, statGetter106)
        );
        WorkbenchStatsGui.addBar(statBar106);
        HoloStatsGui.addBar(statBar106);
        var statGetter107 = new StatGetterEffectLevel(l2complements_flame_clear_Effect, 1);
        GuiStatBar statBar107 = new GuiStatBar(0, 0, StatsHelper.barLength,
                l2complements_flame_clear_Name, 0, 1, false, false, false,
                statGetter107, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(l2complements_flame_clear_Tooltip, statGetter107)
        );
        WorkbenchStatsGui.addBar(statBar107);
        HoloStatsGui.addBar(statBar107);
        var statGetter108 = new StatGetterEffectLevel(curios_l2complements_flame_clear_Effect, 1);
        GuiStatBar statBar108 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_l2complements_flame_clear_Name, 0, 1, false, false, false,
                statGetter108, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_l2complements_flame_clear_Tooltip, statGetter108)
        );
        WorkbenchStatsGui.addBar(statBar108);
        HoloStatsGui.addBar(statBar108);
        var statGetter109 = new StatGetterEffectLevel(l2complements_frozen_clear_Effect, 1);
        GuiStatBar statBar109 = new GuiStatBar(0, 0, StatsHelper.barLength,
                l2complements_frozen_clear_Name, 0, 1, false, false, false,
                statGetter109, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(l2complements_frozen_clear_Tooltip, statGetter109)
        );
        WorkbenchStatsGui.addBar(statBar109);
        HoloStatsGui.addBar(statBar109);
        var statGetter110 = new StatGetterEffectLevel(curios_l2complements_frozen_clear_Effect, 1);
        GuiStatBar statBar110 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_l2complements_frozen_clear_Name, 0, 1, false, false, false,
                statGetter110, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_l2complements_frozen_clear_Tooltip, statGetter110)
        );
        WorkbenchStatsGui.addBar(statBar110);
        HoloStatsGui.addBar(statBar110);
        var statGetter111 = new StatGetterEffectLevel(goety_cursed_clear_Effect, 1);
        GuiStatBar statBar111 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_cursed_clear_Name, 0, 1, false, false, false,
                statGetter111, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_cursed_clear_Tooltip, statGetter111)
        );
        WorkbenchStatsGui.addBar(statBar111);
        HoloStatsGui.addBar(statBar111);
        var statGetter112 = new StatGetterEffectLevel(curios_goety_cursed_clear_Effect, 1);
        GuiStatBar statBar112 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_cursed_clear_Name, 0, 1, false, false, false,
                statGetter112, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_cursed_clear_Tooltip, statGetter112)
        );
        WorkbenchStatsGui.addBar(statBar112);
        HoloStatsGui.addBar(statBar112);
        var statGetter113 = new StatGetterEffectLevel(goety_burn_hex_clear_Effect, 1);
        GuiStatBar statBar113 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_burn_hex_clear_Name, 0, 1, false, false, false,
                statGetter113, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_burn_hex_clear_Tooltip, statGetter113)
        );
        WorkbenchStatsGui.addBar(statBar113);
        HoloStatsGui.addBar(statBar113);
        var statGetter114 = new StatGetterEffectLevel(curios_goety_burn_hex_clear_Effect, 1);
        GuiStatBar statBar114 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_burn_hex_clear_Name, 0, 1, false, false, false,
                statGetter114, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_burn_hex_clear_Tooltip, statGetter114)
        );
        WorkbenchStatsGui.addBar(statBar114);
        HoloStatsGui.addBar(statBar114);
        var statGetter115 = new StatGetterEffectLevel(goety_sapped_clear_Effect, 1);
        GuiStatBar statBar115 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_sapped_clear_Name, 0, 1, false, false, false,
                statGetter115, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_sapped_clear_Tooltip, statGetter115)
        );
        WorkbenchStatsGui.addBar(statBar115);
        HoloStatsGui.addBar(statBar115);
        var statGetter116 = new StatGetterEffectLevel(curios_goety_sapped_clear_Effect, 1);
        GuiStatBar statBar116 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_sapped_clear_Name, 0, 1, false, false, false,
                statGetter116, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_sapped_clear_Tooltip, statGetter116)
        );
        WorkbenchStatsGui.addBar(statBar116);
        HoloStatsGui.addBar(statBar116);
        var statGetter117 = new StatGetterEffectLevel(goety_freezing_clear_Effect, 1);
        GuiStatBar statBar117 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_freezing_clear_Name, 0, 1, false, false, false,
                statGetter117, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_freezing_clear_Tooltip, statGetter117)
        );
        WorkbenchStatsGui.addBar(statBar117);
        HoloStatsGui.addBar(statBar117);
        var statGetter118 = new StatGetterEffectLevel(curios_goety_freezing_clear_Effect, 1);
        GuiStatBar statBar118 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_freezing_clear_Name, 0, 1, false, false, false,
                statGetter118, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_freezing_clear_Tooltip, statGetter118)
        );
        WorkbenchStatsGui.addBar(statBar118);
        HoloStatsGui.addBar(statBar118);
        var statGetter119 = new StatGetterEffectLevel(alexscaves_magnetizing_clear_Effect, 1);
        GuiStatBar statBar119 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexscaves_magnetizing_clear_Name, 0, 1, false, false, false,
                statGetter119, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexscaves_magnetizing_clear_Tooltip, statGetter119)
        );
        WorkbenchStatsGui.addBar(statBar119);
        HoloStatsGui.addBar(statBar119);
        var statGetter120 = new StatGetterEffectLevel(curios_alexscaves_magnetizing_clear_Effect, 1);
        GuiStatBar statBar120 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexscaves_magnetizing_clear_Name, 0, 1, false, false, false,
                statGetter120, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexscaves_magnetizing_clear_Tooltip, statGetter120)
        );
        WorkbenchStatsGui.addBar(statBar120);
        HoloStatsGui.addBar(statBar120);
        var statGetter121 = new StatGetterEffectLevel(alexscaves_irradiated_clear_Effect, 1);
        GuiStatBar statBar121 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexscaves_irradiated_clear_Name, 0, 1, false, false, false,
                statGetter121, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexscaves_irradiated_clear_Tooltip, statGetter121)
        );
        WorkbenchStatsGui.addBar(statBar121);
        HoloStatsGui.addBar(statBar121);
        var statGetter122 = new StatGetterEffectLevel(curios_alexscaves_irradiated_clear_Effect, 1);
        GuiStatBar statBar122 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexscaves_irradiated_clear_Name, 0, 1, false, false, false,
                statGetter122, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexscaves_irradiated_clear_Tooltip, statGetter122)
        );
        WorkbenchStatsGui.addBar(statBar122);
        HoloStatsGui.addBar(statBar122);
        var statGetter123 = new StatGetterEffectLevel(aether_inebriation_clear_Effect, 1);
        GuiStatBar statBar123 = new GuiStatBar(0, 0, StatsHelper.barLength,
                aether_inebriation_clear_Name, 0, 1, false, false, false,
                statGetter123, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(aether_inebriation_clear_Tooltip, statGetter123)
        );
        WorkbenchStatsGui.addBar(statBar123);
        HoloStatsGui.addBar(statBar123);
        var statGetter124 = new StatGetterEffectLevel(curios_aether_inebriation_clear_Effect, 1);
        GuiStatBar statBar124 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_aether_inebriation_clear_Name, 0, 1, false, false, false,
                statGetter124, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_aether_inebriation_clear_Tooltip, statGetter124)
        );
        WorkbenchStatsGui.addBar(statBar124);
        HoloStatsGui.addBar(statBar124);
        var statGetter125 = new StatGetterEffectLevel(cataclysm_blazing_brand_clear_Effect, 1);
        GuiStatBar statBar125 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cataclysm_blazing_brand_clear_Name, 0, 1, false, false, false,
                statGetter125, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cataclysm_blazing_brand_clear_Tooltip, statGetter125)
        );
        WorkbenchStatsGui.addBar(statBar125);
        HoloStatsGui.addBar(statBar125);
        var statGetter126 = new StatGetterEffectLevel(curios_cataclysm_blazing_brand_clear_Effect, 1);
        GuiStatBar statBar126 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_cataclysm_blazing_brand_clear_Name, 0, 1, false, false, false,
                statGetter126, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_cataclysm_blazing_brand_clear_Tooltip, statGetter126)
        );
        WorkbenchStatsGui.addBar(statBar126);
        HoloStatsGui.addBar(statBar126);
        var statGetter127 = new StatGetterEffectLevel(cataclysm_curse_of_desert_clear_Effect, 1);
        GuiStatBar statBar127 = new GuiStatBar(0, 0, StatsHelper.barLength,
                cataclysm_curse_of_desert_clear_Name, 0, 1, false, false, false,
                statGetter127, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cataclysm_curse_of_desert_clear_Tooltip, statGetter127)
        );
        WorkbenchStatsGui.addBar(statBar127);
        HoloStatsGui.addBar(statBar127);
        var statGetter128 = new StatGetterEffectLevel(curios_cataclysm_curse_of_desert_clear_Effect, 1);
        GuiStatBar statBar128 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_cataclysm_curse_of_desert_clear_Name, 0, 1, false, false, false,
                statGetter128, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_cataclysm_curse_of_desert_clear_Tooltip, statGetter128)
        );
        WorkbenchStatsGui.addBar(statBar128);
        HoloStatsGui.addBar(statBar128);
        var statGetter129 = new StatGetterEffectLevel(born_in_chaos_v1_bone_fracture_clear_Effect, 1);
        GuiStatBar statBar129 = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_bone_fracture_clear_Name, 0, 1, false, false, false,
                statGetter129, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(born_in_chaos_v1_bone_fracture_clear_Tooltip, statGetter129)
        );
        WorkbenchStatsGui.addBar(statBar129);
        HoloStatsGui.addBar(statBar129);
        var statGetter130 = new StatGetterEffectLevel(curios_born_in_chaos_v1_bone_fracture_clear_Effect, 1);
        GuiStatBar statBar130 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_born_in_chaos_v1_bone_fracture_clear_Name, 0, 1, false, false, false,
                statGetter130, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_born_in_chaos_v1_bone_fracture_clear_Tooltip, statGetter130)
        );
        WorkbenchStatsGui.addBar(statBar130);
        HoloStatsGui.addBar(statBar130);
        var statGetter131 = new StatGetterEffectLevel(born_in_chaos_v1_intoxicating_decoction_clear_Effect, 1);
        GuiStatBar statBar131 = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_intoxicating_decoction_clear_Name, 0, 1, false, false, false,
                statGetter131, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(born_in_chaos_v1_intoxicating_decoction_clear_Tooltip, statGetter131)
        );
        WorkbenchStatsGui.addBar(statBar131);
        HoloStatsGui.addBar(statBar131);
        var statGetter132 = new StatGetterEffectLevel(curios_born_in_chaos_v1_intoxicating_decoction_clear_Effect, 1);
        GuiStatBar statBar132 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_born_in_chaos_v1_intoxicating_decoction_clear_Name, 0, 1, false, false, false,
                statGetter132, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_born_in_chaos_v1_intoxicating_decoction_clear_Tooltip, statGetter132)
        );
        WorkbenchStatsGui.addBar(statBar132);
        HoloStatsGui.addBar(statBar132);
        var statGetter133 = new StatGetterEffectLevel(born_in_chaos_v1_magic_depletion_clear_Effect, 1);
        GuiStatBar statBar133 = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_magic_depletion_clear_Name, 0, 1, false, false, false,
                statGetter133, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(born_in_chaos_v1_magic_depletion_clear_Tooltip, statGetter133)
        );
        WorkbenchStatsGui.addBar(statBar133);
        HoloStatsGui.addBar(statBar133);
        var statGetter134 = new StatGetterEffectLevel(curios_born_in_chaos_v1_magic_depletion_clear_Effect, 1);
        GuiStatBar statBar134 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_born_in_chaos_v1_magic_depletion_clear_Name, 0, 1, false, false, false,
                statGetter134, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_born_in_chaos_v1_magic_depletion_clear_Tooltip, statGetter134)
        );
        WorkbenchStatsGui.addBar(statBar134);
        HoloStatsGui.addBar(statBar134);
        var statGetter135 = new StatGetterEffectLevel(born_in_chaos_v1_myiasis_clear_Effect, 1);
        GuiStatBar statBar135 = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_myiasis_clear_Name, 0, 1, false, false, false,
                statGetter135, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(born_in_chaos_v1_myiasis_clear_Tooltip, statGetter135)
        );
        WorkbenchStatsGui.addBar(statBar135);
        HoloStatsGui.addBar(statBar135);
        var statGetter136 = new StatGetterEffectLevel(curios_born_in_chaos_v1_myiasis_clear_Effect, 1);
        GuiStatBar statBar136 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_born_in_chaos_v1_myiasis_clear_Name, 0, 1, false, false, false,
                statGetter136, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_born_in_chaos_v1_myiasis_clear_Tooltip, statGetter136)
        );
        WorkbenchStatsGui.addBar(statBar136);
        HoloStatsGui.addBar(statBar136);
        var statGetter137 = new StatGetterEffectLevel(born_in_chaos_v1_rotten_smell_clear_Effect, 1);
        GuiStatBar statBar137 = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_rotten_smell_clear_Name, 0, 1, false, false, false,
                statGetter137, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(born_in_chaos_v1_rotten_smell_clear_Tooltip, statGetter137)
        );
        WorkbenchStatsGui.addBar(statBar137);
        HoloStatsGui.addBar(statBar137);
        var statGetter138 = new StatGetterEffectLevel(curios_born_in_chaos_v1_rotten_smell_clear_Effect, 1);
        GuiStatBar statBar138 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_born_in_chaos_v1_rotten_smell_clear_Name, 0, 1, false, false, false,
                statGetter138, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_born_in_chaos_v1_rotten_smell_clear_Tooltip, statGetter138)
        );
        WorkbenchStatsGui.addBar(statBar138);
        HoloStatsGui.addBar(statBar138);
        var statGetter139 = new StatGetterEffectLevel(quark_resilience_Effect, 1);
        GuiStatBar statBar139 = new GuiStatBar(0, 0, StatsHelper.barLength,
                quark_resilience_Name, 0, 1, false, false, false,
                statGetter139, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(quark_resilience_Tooltip, statGetter139)
        );
        WorkbenchStatsGui.addBar(statBar139);
        HoloStatsGui.addBar(statBar139);
        var statGetter140 = new StatGetterEffectLevel(curios_quark_resilience_Effect, 1);
        GuiStatBar statBar140 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_quark_resilience_Name, 0, 1, false, false, false,
                statGetter140, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_quark_resilience_Tooltip, statGetter140)
        );
        WorkbenchStatsGui.addBar(statBar140);
        HoloStatsGui.addBar(statBar140);
        var statGetter141 = new StatGetterEffectLevel(alexsmobs_bug_pheromones_Effect, 1);
        GuiStatBar statBar141 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexsmobs_bug_pheromones_Name, 0, 1, false, false, false,
                statGetter141, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexsmobs_bug_pheromones_Tooltip, statGetter141)
        );
        WorkbenchStatsGui.addBar(statBar141);
        HoloStatsGui.addBar(statBar141);
        var statGetter142 = new StatGetterEffectLevel(curios_alexsmobs_bug_pheromones_Effect, 1);
        GuiStatBar statBar142 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexsmobs_bug_pheromones_Name, 0, 1, false, false, false,
                statGetter142, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexsmobs_bug_pheromones_Tooltip, statGetter142)
        );
        WorkbenchStatsGui.addBar(statBar142);
        HoloStatsGui.addBar(statBar142);
        var statGetter143 = new StatGetterEffectLevel(alexsmobs_clinging_Effect, 1);
        GuiStatBar statBar143 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexsmobs_clinging_Name, 0, 1, false, false, false,
                statGetter143, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexsmobs_clinging_Tooltip, statGetter143)
        );
        WorkbenchStatsGui.addBar(statBar143);
        HoloStatsGui.addBar(statBar143);
        var statGetter144 = new StatGetterEffectLevel(curios_alexsmobs_clinging_Effect, 1);
        GuiStatBar statBar144 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexsmobs_clinging_Name, 0, 1, false, false, false,
                statGetter144, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexsmobs_clinging_Tooltip, statGetter144)
        );
        WorkbenchStatsGui.addBar(statBar144);
        HoloStatsGui.addBar(statBar144);
        var statGetter145 = new StatGetterEffectLevel(alexsmobs_knockback_resistance_Effect, 1);
        GuiStatBar statBar145 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexsmobs_knockback_resistance_Name, 0, 1, false, false, false,
                statGetter145, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexsmobs_knockback_resistance_Tooltip, statGetter145)
        );
        WorkbenchStatsGui.addBar(statBar145);
        HoloStatsGui.addBar(statBar145);
        var statGetter146 = new StatGetterEffectLevel(curios_alexsmobs_knockback_resistance_Effect, 1);
        GuiStatBar statBar146 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexsmobs_knockback_resistance_Name, 0, 1, false, false, false,
                statGetter146, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexsmobs_knockback_resistance_Tooltip, statGetter146)
        );
        WorkbenchStatsGui.addBar(statBar146);
        HoloStatsGui.addBar(statBar146);
        var statGetter147 = new StatGetterEffectLevel(alexsmobs_soulsteal_Effect, 1);
        GuiStatBar statBar147 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexsmobs_soulsteal_Name, 0, 1, false, false, false,
                statGetter147, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexsmobs_soulsteal_Tooltip, statGetter147)
        );
        WorkbenchStatsGui.addBar(statBar147);
        HoloStatsGui.addBar(statBar147);
        var statGetter148 = new StatGetterEffectLevel(curios_alexsmobs_soulsteal_Effect, 1);
        GuiStatBar statBar148 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexsmobs_soulsteal_Name, 0, 1, false, false, false,
                statGetter148, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexsmobs_soulsteal_Tooltip, statGetter148)
        );
        WorkbenchStatsGui.addBar(statBar148);
        HoloStatsGui.addBar(statBar148);
        var statGetter149 = new StatGetterEffectLevel(alexscaves_deepsight_Effect, 1);
        GuiStatBar statBar149 = new GuiStatBar(0, 0, StatsHelper.barLength,
                alexscaves_deepsight_Name, 0, 1, false, false, false,
                statGetter149, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(alexscaves_deepsight_Tooltip, statGetter149)
        );
        WorkbenchStatsGui.addBar(statBar149);
        HoloStatsGui.addBar(statBar149);
        var statGetter150 = new StatGetterEffectLevel(curios_alexscaves_deepsight_Effect, 1);
        GuiStatBar statBar150 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_alexscaves_deepsight_Name, 0, 1, false, false, false,
                statGetter150, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_alexscaves_deepsight_Tooltip, statGetter150)
        );
        WorkbenchStatsGui.addBar(statBar150);
            HoloStatsGui.addBar(statBar150);
            var statGetter151 = new StatGetterEffectLevel(cataclysm_blessing_of_amethyst_Effect, 1);
            GuiStatBar statBar151 = new GuiStatBar(0, 0, StatsHelper.barLength,
                    cataclysm_blessing_of_amethyst_Name, 0, 1, false, false, false,
                    statGetter151, LabelGetterBasic.integerLabel,
                    new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(cataclysm_blessing_of_amethyst_Tooltip, statGetter151)
            );
            WorkbenchStatsGui.addBar(statBar151);
            HoloStatsGui.addBar(statBar151);
            var statGetter152 = new StatGetterEffectLevel(curios_cataclysm_blessing_of_amethyst_Effect, 1);
            GuiStatBar statBar152 = new GuiStatBar(0, 0, StatsHelper.barLength,
                    curios_cataclysm_blessing_of_amethyst_Name, 0, 1, false, false, false,
                    statGetter152, LabelGetterBasic.integerLabel,
                    new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_cataclysm_blessing_of_amethyst_Tooltip, statGetter152)
            );
            WorkbenchStatsGui.addBar(statBar152);
            HoloStatsGui.addBar(statBar152);
        var statGetter153 = new StatGetterEffectLevel(goety_soul_spell_cooldown_Effect, 1);
        GuiStatBar statBar153 = new GuiStatBar(0, 0, StatsHelper.barLength,
                goety_soul_spell_cooldown_Name, 0, 10, false, false, false,
                statGetter153, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(goety_soul_spell_cooldown_Tooltip, statGetter153)
        );
        WorkbenchStatsGui.addBar(statBar153);
        HoloStatsGui.addBar(statBar153);
        var statGetter154 = new StatGetterEffectLevel(curios_goety_soul_spell_cooldown_Effect, 1);
        GuiStatBar statBar154 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curios_goety_soul_spell_cooldown_Name, 0, 10, false, false, false,
                statGetter154, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(curios_goety_soul_spell_cooldown_Tooltip, statGetter154)
        );
        WorkbenchStatsGui.addBar(statBar154);
        HoloStatsGui.addBar(statBar154);
        var statGetter155 = new StatGetterEffectLevel(orders_Effect, 1);
        GuiStatBar statBar155 = new GuiStatBar(0, 0, StatsHelper.barLength,
                orders_Name, 0, 100, false, false, false,
                statGetter155, LabelGetterBasic.integerLabel,
                new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(orders_Tooltip, statGetter155)
        );
        WorkbenchStatsGui.addBar(statBar155);
        HoloStatsGui.addBar(statBar155);

    }
}
