package com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
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

public class ShadowiumPower {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(shadowiumPowerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                shadowiumPowerName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(shadowiumPowerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, shadowiumPowerEffect);
            //亡灵
            Level level = livingEntity.level();
            BlockPos pos = livingEntity.blockPosition();
            int brightness = level.getMaxLocalRawBrightness(pos);
            if (effectLevel > 0 && brightness <= 8) {
                float number = (float) effectLevel / 100;
                event.setAmount(event.getAmount() * (1 + number));
            }
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, shadowiumPowerEffect);
            //亡灵
            Level level = livingEntity.level();
            BlockPos pos = livingEntity.blockPosition();
            int brightness = level.getMaxLocalRawBrightness(pos);
            if (effectLevel > 0 && brightness <= 8) {
                float number = (float) effectLevel / 100;
                event.setAmount(event.getAmount() * (1 + number));
            }
        }
    }
}

