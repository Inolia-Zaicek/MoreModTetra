package com.inolia_zaicek.more_mod_tetra.Effect.Botania.AncientWill;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
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

public class AncientWillKaril {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ancientWillKarilEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ancientWillKarilName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ancientWillKarilTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("botania")) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                int gaia = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, ancientWillKarilEffect);
                    float gaiaLevel = item.getEffectLevel(offhandItem, willOfGaiaEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                    if (gaiaLevel > 0) {
                        gaia += (int) mainEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if(!livingEntity.onGround()||gaia>0){
                        mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,60,1));
                    }
                }
            }
        }
    }
}
