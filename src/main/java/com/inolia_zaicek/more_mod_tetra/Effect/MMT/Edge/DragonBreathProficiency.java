package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.damagesource.DamageTypes;
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

public class DragonBreathProficiency {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(dragonBreathProficiencyEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                dragonBreathProficiencyName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(dragonBreathProficiencyTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,dragonBreathProficiencyEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(DamageTypes.DRAGON_BREATH)) {
                        float number = (float) effectLevel / 100;
                        float damage = event.getAmount();
                        event.setAmount(damage * (1 + number));
                    }
            }
        }            if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,dragonBreathProficiencyEffect);
            if (effectLevel > 0) {
                if (event.getSource().is(DamageTypes.DRAGON_BREATH)) {
                    float number = (float) effectLevel / 100;
                    float damage = event.getAmount();
                    event.setAmount(damage * (1 + number));
                }
            }
        }
    }
}
