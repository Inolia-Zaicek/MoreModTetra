package com.inolia_zaicek.more_mod_tetra.Effect;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterAttribute;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;




public class MMTClientGuiRegistry {
    @OnlyIn(Dist.CLIENT)
    public static void registerAllBars() {

        registerAttackKnockbackBar();
        registerAttackFlyingSpeedBar();
        registerFollowRangeBar();
        registerKnockbackResistanceBar();
        registerLuckBar();
        registerMaxHealthBar();
        registerMovementSpeedBar();
    }

    private static void registerAttackKnockbackBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.attack_knockback");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.attack_knockback.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.attack_knockback.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerAttackFlyingSpeedBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.flying_speed");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.flying_speed.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.flying_speed.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerFollowRangeBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.follow_range");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.follow_range.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.follow_range.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerKnockbackResistanceBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.knockback_resistance");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.knockback_resistance.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.knockback_resistance.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerLuckBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.luck");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.luck.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.luck.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerMaxHealthBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.max_health");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.max_health.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.max_health.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

    private static void registerMovementSpeedBar() {
        ResourceLocation res = new ResourceLocation("minecraft:generic.movement_speed");
        Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(res);
        if (attribute == null) {
            return;
        }
        IStatGetter getter = new StatGetterAttribute(attribute, false);
        GuiStatBar bar = new GuiStatBar(
                0, 0, StatsHelper.barLength,
                MoreModTetra.MODID + ".attribute.movement_speed.name",
                0, 10,
                false, false, false,
                getter,
                LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(MoreModTetra.MODID + ".attribute.movement_speed.tooltip", getter)
        );
        WorkbenchStatsGui.addBar(bar);
        HoloStatsGui.addBar(bar);
    }

}