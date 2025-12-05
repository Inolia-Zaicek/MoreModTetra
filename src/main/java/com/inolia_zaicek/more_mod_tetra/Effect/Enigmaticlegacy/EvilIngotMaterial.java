package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

public class EvilIngotMaterial {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(evilIngotMaterialEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                evilIngotMaterialName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(evilIngotMaterialTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if(ModList.get().isLoaded("enigmaticlegacy")) {
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
    }
