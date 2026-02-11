package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EvilIngotMaterial {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter00000023 = new StatGetterEffectLevel(evilIngotMaterialEffect, 1);
        var statGetter00000024 = new StatGetterEffectEfficiency(evilIngotMaterialEffect, 1);
        IStatGetter[] statGetters00000033 = {statGetter00000023, statGetter00000024};
        IStatFormat[] statFormats00000033 = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar00000033 = new GuiStatBar(0, 0, StatsHelper.barLength,
                evilIngotMaterialName, 0, 50, false, false, false,
                statGetter00000023, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(evilIngotMaterialTooltip, statGetters00000033, statFormats00000033)
        );
        WorkbenchStatsGui.addBar(statBar00000033);
        HoloStatsGui.addBar(statBar00000033);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //攻击
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity, evilIngotMaterialEffect);
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((number));
                    }
                }
            //挨打
            if (event.getAttacked()!=null) {
                LivingEntity livingEntity = event.getAttacked();;
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(livingEntity, evilIngotMaterialEffect);
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((number));
                }
            }
        }
    }
