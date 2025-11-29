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

public class ShootingSun {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(shootingSunEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                shootingSunName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(shootingSunTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,shootingSunEffect);
                if (effectLevel > 0) {
                    float number =effectLevel/1000;
                    float fireTime =mob.getRemainingFireTicks();
                    float damage= event.getAmount();
                    //xx秒*倍率,最多30秒*倍率
                    float finish =Math.min(30, (fireTime/20) ) * number;
                    if(fireTime>0) {
                        mob.setRemainingFireTicks((int) (fireTime+100));
                        event.setAmount(damage*(1+finish));
                    }else{
                        mob.setRemainingFireTicks(100);
                        event.setAmount(damage*(1+number));
                    }
                }
        }
        else if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,shootingSunEffect);
            if (effectLevel > 0) {
                float number = (float) effectLevel /1000;
                float fireTime =mob.getRemainingFireTicks();
                float damage= event.getAmount();
                //xx秒*倍率,最多30秒*倍率
                float finish =Math.min(30, (fireTime/20) ) * number;
                if(fireTime>0) {
                    mob.setRemainingFireTicks((int) (fireTime+100));
                    event.setAmount(damage*(1+finish));
                }else{
                    mob.setRemainingFireTicks(100);
                    event.setAmount(damage*(1+number));
                }
            }
        }
    }
}
