package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

public class SanctuaryOfMooncocoon {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(sanctuaryOfMooncocoonEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                sanctuaryOfMooncocoonName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(sanctuaryOfMooncocoonTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        //挨打
        if (event.getEntity().hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get()) && !event.getEntity().hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoonCooldown.get())) {
            event.setAmount(0);
        }
        //攻击
        if(event.getSource().getEntity() instanceof LivingEntity livingEntity){
            if(livingEntity.hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get())){
                livingEntity.removeEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get());
            }
        }
        if(event.getSource().getDirectEntity() instanceof LivingEntity livingEntity){
            if(livingEntity.hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get())){
                livingEntity.removeEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get());
            }
        }
    }
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        if (event.getEntity().hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get()) ) {
            event.getEntity().removeEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get());
        }
    }
}
