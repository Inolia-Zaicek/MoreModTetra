package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class Poison {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(poisonEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                poisonName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(poisonTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, poisonEffect);
            var map = mob.getActiveEffectsMap();
            if (effectLevel > 0) {
                mob.addEffect(new MobEffectInstance(MobEffects.POISON, 200, effectLevel - 1));
                if (!mob.hasEffect(MobEffects.POISON)) {
                    map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200, effectLevel - 1));
                }
            }
            int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, attack_glowing_buff_Effect);
            if (effectLevel1 > 0) {
                mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * effectLevel1, 0));
                if (!mob.hasEffect(MobEffects.GLOWING)) {
                    map.put(MobEffects.GLOWING, new MobEffectInstance(MobEffects.GLOWING, 20 * effectLevel1, 0));
                }
            }
        }
        //千古
        if (ModList.get().isLoaded("immortalers_delight")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity && event.getAttacked() != null) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, immortalers_delight_gas_poison_Effect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, immortalers_delight_gas_poison_Effect);
                if (effectLevel > 0 && event.getAttacked() != null) {
                    var mob = event.getAttacked();
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.
                            getValue(new ResourceLocation("immortalers_delight", "gas_poison"))), 200, (int) (effectLevel - 1)));
                    if (!EntityType.getKey(mob.getType()).toString().equals("eeeabsmobs:immortal") && !mob.hasEffect(Objects.requireNonNull(
                            ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("immortalers_delight", "gas_poison"))))) {
                        var map = mob.getActiveEffectsMap();
                        //通过id直接给buff
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("immortalers_delight", "gas_poison")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("immortalers_delight", "gas_poison"))), 200, (int) (effectLevel - 1)));
                    }
                }
            }
        }
    }
}
