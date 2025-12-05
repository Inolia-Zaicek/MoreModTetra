package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

public class OdeToPassage {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(odeToPassageEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                odeToPassageName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(odeToPassageTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,odeToPassageEffect);
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    float damage = event.getAmount();
                    mob.invulnerableTime = 0;
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                    var DamageType = MMTTickZero.source(player.level(), MMTTickZero.TRUEDAMAGE);
                    mob.hurt(DamageType, damage * number);
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                }
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,odeToPassageEffect);
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    float damage = event.getAmount();
                    mob.invulnerableTime = 0;
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                    var DamageType = MMTTickZero.source(player.level(), MMTTickZero.TRUEDAMAGE);
                    mob.hurt(DamageType, damage * number);
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                }
            }
        }
    }
}