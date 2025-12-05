package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.LightningDragonSteel;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static net.minecraft.tags.DamageTypeTags.IS_LIGHTNING;

public class LightningDragonPower {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(lightningDragonPowerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                lightningDragonPowerName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(lightningDragonPowerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if(ModList.get().isLoaded("iceandfire")) {
            //攻击（龙霆雷伤增幅单独判断
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                //是雷霆伤害，增伤
                if (effectLevel > 0&&event.hurtEvent.getSource().is(IS_LIGHTNING)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1+number));
                    }
                }
            else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                //是雷霆伤害，增伤
                if (effectLevel > 0&&event.hurtEvent.getSource().is(IS_LIGHTNING)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1+number));
                }
            }
            //雷伤归零
            if (event.getAttacked()!=null) {
                LivingEntity livingEntity = event.getAttacked();;
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                if (effectLevel > 0&&event.hurtEvent.getSource()==livingEntity.damageSources().lightningBolt()) {
                    event.setResult(Event.Result.DENY);
                }
            }
            }
        }
    }
