package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Slash;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ExceedSlash {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(exceedSlashEffect,   1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                exceedSlashName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(exceedSlashTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //攻击
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,exceedSlashEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //当前已损失生命值比例
                float dhp =(mhp-hp)/mhp;
                //当前血量比例小于数值比例
                if (effectLevel > 0&&hp<mhp) {
                    //比例*增伤==如100-99/100==1%已损失*100*1%增伤
                    float finish =dhp*100*(effectLevel /1000);
                     event.addNormalMulti(finish);
                    }
                }else            if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,exceedSlashEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //当前已损失生命值比例
                float dhp =(mhp-hp)/mhp;
                //当前血量比例小于数值比例
                if (effectLevel > 0&&hp<mhp) {
                    //比例*增伤==如100-99/100==1%已损失*100*1%增伤
                    float finish =dhp*100*(effectLevel /1000);
                     event.addNormalMulti(finish);
                }
            }
            }
        }

