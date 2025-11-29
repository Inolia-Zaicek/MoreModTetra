package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.LightningDragonSteel;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("iceandfire")) {
            //攻击（龙霆雷伤增幅单独判断
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                //是雷霆伤害，增伤
                if (effectLevel > 0&&event.getSource().is(IS_LIGHTNING)) {
                    float number = (float) effectLevel / 100;
                    float damage =event.getAmount();
                    event.setAmount(damage*(1+number));
                    }
                }
            else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                //是雷霆伤害，增伤
                if (effectLevel > 0&&event.getSource().is(IS_LIGHTNING)) {
                    float number = (float) effectLevel / 100;
                    float damage =event.getAmount();
                    event.setAmount(damage*(1+number));
                }
            }
            //雷伤归零
            if (event.getEntity()!=null) {
                LivingEntity livingEntity = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, lightningDragonPowerEffect);
                if (effectLevel > 0&&event.getSource()==livingEntity.damageSources().lightningBolt()) {
                    event.setAmount(0);
                }
            }
            }
        }
    }
