package com.inolia_zaicek.more_mod_tetra.Effect.Eeeab;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EeeabsmobsErodeEffect {
        @OnlyIn(Dist.CLIENT)
        public static void init() {
            var statGetter = new StatGetterEffectLevel(eeeabsmobs_erode_effect_Effect, 1);
            GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                    eeeabsmobs_erode_effect_Name, 0, 5, false, false, false,
                    statGetter, LabelGetterBasic.integerLabel,
                    new TooltipGetterInteger(eeeabsmobs_erode_effect_Tooltip, statGetter)
            );

            WorkbenchStatsGui.addBar(statBar);
            HoloStatsGui.addBar(statBar);
        }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("eeeabsmobs")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof LivingEntity player && event.getEntity() != null) {
                int effectLevel = 0;
                //获取一下玩家主副手
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, eeeabsmobs_erode_effect_Effect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, eeeabsmobs_erode_effect_Effect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0 && event.getEntity() != null) {
                    var mob = event.getEntity();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect"))), 100, effectLevel-1));
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect")))
                            , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect"))), 100, effectLevel-1));
                }
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity player && event.getEntity() != null) {
                int effectLevel = 0;
                //获取一下玩家主副手
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, eeeabsmobs_erode_effect_Effect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, eeeabsmobs_erode_effect_Effect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0 && event.getEntity() != null) {
                    var mob = event.getEntity();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect"))), 100, effectLevel-1));
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect")))
                            , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("eeeabsmobs", "erode_effect"))), 100, effectLevel-1));
                }
            }
        }
    }
}
