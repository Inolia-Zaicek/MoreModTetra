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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class BoneFracture {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(boneFractureEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                boneFractureName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(boneFractureTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            if (event.getSource().getDirectEntity() instanceof Player player && !(event.getEntity() instanceof Player)) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, boneFractureEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, boneFractureEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(ModEffect.EFFECTBONE_FRACTURE.get())) {
                        int buffLevel = mob.getEffect(ModEffect.EFFECTBONE_FRACTURE.get()).getAmplifier();
                        mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBONE_FRACTURE.get(), 200, Math.min(4,buffLevel+effectLevel)));
                    }else{
                        mob.addEffect(new MobEffectInstance(ModEffect.EFFECTBONE_FRACTURE.get(), 200, effectLevel - 1));
                    }
                }
            }
        }
    }
}
