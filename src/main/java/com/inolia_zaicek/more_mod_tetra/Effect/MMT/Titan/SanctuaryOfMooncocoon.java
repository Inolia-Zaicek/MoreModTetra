package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
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
    public static void hurt(EffectLevelEvent event) {
        //挨打
        if (event.getAttacked().hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get()) && !event.getAttacked().hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoonCooldown.get())) {
            event.setResult(Event.Result.DENY);
        }
        //攻击
        if(event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity){
            if(livingEntity.hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get())){
                livingEntity.removeEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get());
            }
        }
        if(event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity livingEntity){
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
