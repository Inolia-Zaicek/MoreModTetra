package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class MarchingTimeHurt {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(marchingTimeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                marchingTimeName, 0, 6, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(marchingTimeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(event.getEntity()!=null){
            LivingEntity livingEntity=event.getEntity();
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, marchingTimeEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, marchingTimeEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0&&livingEntity.hasEffect(MMTEffectsRegister.MarchingTime.get())) {
                int buffLevel =1 + livingEntity.getEffect(MMTEffectsRegister.MarchingTime.get()).getAmplifier();
                event.setAmount(event.getAmount()*( 1-(buffLevel*0.15f) ));
            }
        }
    }
}
