package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.sammy.malum.registry.common.MobEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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

public class AbyssalCurse {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(abyssalCurseEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                abyssalCurseName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(abyssalCurseTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, abyssalCurseEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, abyssalCurseEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(ModEffect.EFFECTABYSSAL_CURSE.get())) {
                        int buffLevel = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getAmplifier();
                        int buffTime = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getDuration();
                        map.put(ModEffect.EFFECTABYSSAL_CURSE.get(),
                                new MobEffectInstance(ModEffect.EFFECTABYSSAL_CURSE.get(), Math.min(20*60,Math.max(200,buffTime*2)), Math.min(4,buffLevel+effectLevel) ));
                    }else{
                        map.put(ModEffect.EFFECTABYSSAL_CURSE.get(),
                                new MobEffectInstance(ModEffect.EFFECTABYSSAL_CURSE.get(), 200, effectLevel - 1));
                    }
                }
            }

            else if (event.getSource().getEntity() instanceof LivingEntity livingEntity ) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, abyssalCurseEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, abyssalCurseEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(ModEffect.EFFECTABYSSAL_CURSE.get())) {
                        int buffLevel = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getAmplifier();
                        int buffTime = mob.getEffect(ModEffect.EFFECTABYSSAL_CURSE.get()).getDuration();
                        map.put(ModEffect.EFFECTABYSSAL_CURSE.get(),
                                new MobEffectInstance(ModEffect.EFFECTABYSSAL_CURSE.get(), Math.min(20*60,Math.max(200,buffTime*2)), Math.min(4,buffLevel+effectLevel) ));
                    }else{
                        map.put(ModEffect.EFFECTABYSSAL_CURSE.get(),
                                new MobEffectInstance(ModEffect.EFFECTABYSSAL_CURSE.get(), 200, effectLevel - 1));
                    }
                }
            }
        }
    }
}
