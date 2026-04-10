package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FreezingEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(freezingEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                freezingEffectTraitName, 0, 3  , false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(freezingEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity && event.getAttacked() != null) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, freezingEffectTraitEffect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, freezingEffectTraitEffect);
                if (effectLevel > 0 && event.getAttacked() != null) {
                    var mob = event.getAttacked();
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.
                            getValue(new ResourceLocation("l2complements", "frozen"))), (int) (200 * effectLevel), (int) (effectLevel - 1)));
                    if (!EntityType.getKey(mob.getType()).toString().equals("eeeabsmobs:immortal") && !mob.hasEffect(Objects.requireNonNull(
                            ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "frozen")))) ) {
                        var map = mob.getActiveEffectsMap();
                        //通过id直接给buff
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("l2complements", "frozen")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("l2complements", "frozen"))), (int) (200 * effectLevel), (int) (effectLevel - 1)));
                    }
                }
            }
        }
    }
}
