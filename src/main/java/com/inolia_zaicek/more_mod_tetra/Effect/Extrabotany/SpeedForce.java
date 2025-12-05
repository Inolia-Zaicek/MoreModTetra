package com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SpeedForce {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(speedForceEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                speedForceName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(speedForceTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,speedForceEffect);
            if (effectLevel > 0) {
                float speed = 1 - (float) livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED);
                //百分比大于0
                if (speed > 0) {
                    //上限值
                    float number = (float) effectLevel / 100;
                    //取最小值
                    float finish=Math.min(speed,number);
                    event.addNormalMulti((1 + finish));
                }
            }
        }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,speedForceEffect);
            if (effectLevel > 0) {
                float speed = 1 - (float) livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED);
                //百分比大于0
                if (speed > 0) {
                    //上限值
                    float number = (float) effectLevel / 100;
                    //取最小值
                    float finish=Math.min(speed,number);
                    event.addNormalMulti((1 + finish));
                }
            }
        }
    }
}
