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
    }
}
