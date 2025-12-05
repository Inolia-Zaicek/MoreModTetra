package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

public class Necrotic {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(necroticEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                necroticName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(necroticTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,necroticEffect);
            float hp = player.getHealth();
            if (effectLevel > 0) {
                float number = (float) effectLevel / 100;
                player.heal(1+event.getAmount()*number);
                if (hp > 1) {
                    player.invulnerableTime = 0;
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                    player.hurt(player.damageSources().magic(), Math.max(1, hp * number));
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                }else{
                    player.heal(1);
                    player.invulnerableTime = 0;
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                    player.hurt(player.damageSources().magic(), Math.max(1, hp * number));
                    if(player instanceof Player player1) {
                        mob.setLastHurtByPlayer(player1);
                    }
                }
            }
        }
    }
}
