package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class BlazingBrand {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(blazingBrandEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                blazingBrandName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(blazingBrandTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            if (event.getSource().getDirectEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                var map = mob.getActiveEffectsMap();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, blazingBrandEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, blazingBrandEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                        int buffLevel = mob.getEffect(ModEffect.EFFECTBLAZING_BRAND.get()).getAmplifier();
                        mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, Math.min(4,buffLevel+effectLevel) ));
                        map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                                new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, Math.min(4,buffLevel+effectLevel) ));
                    }else{
                        mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, effectLevel - 1));
                        map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                                new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, effectLevel - 1));
                    }
                }
            }
            else             if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                var map = mob.getActiveEffectsMap();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, blazingBrandEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, blazingBrandEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                        int buffLevel = mob.getEffect(ModEffect.EFFECTBLAZING_BRAND.get()).getAmplifier();
                        map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                                new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, Math.min(4,buffLevel+effectLevel) ));
                    }else{
                        map.put(ModEffect.EFFECTBLAZING_BRAND.get(),
                                new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, effectLevel - 1));
                    }
                }
            }
        }
    }
}
