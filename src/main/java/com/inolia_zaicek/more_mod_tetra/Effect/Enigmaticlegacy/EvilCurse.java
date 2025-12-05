package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.player.Player;
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
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EvilCurse {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(evilCurseEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                evilCurseName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(evilCurseTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof Player player) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, evilCurseEffect);
            if (effectLevel > 0) {
                int curses = SuperpositionHandler.getCurseAmount(player);
                float number = (float) effectLevel / 100;
                event.addNormalMulti((number * curses));
            }
        }
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //挨打
        if (event.getEntity() instanceof Player player) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, evilCurseEffect);
            if (effectLevel > 0) {
                int curses = SuperpositionHandler.getCurseAmount(player);
                float number = (float) effectLevel / 100;
                float damage = event.getAmount();
                float mhp = player.getMaxHealth();
                //计算伤害阈值
                float damageN = Math.max(0.1f, (1 - number * curses));
                //这次伤害占生命值的比例(伤害/最大生命值)＞伤害阈值比例（0.1*最大生命值
                if (damage / mhp >= mhp * damageN) ;
                {
                    event.setAmount(mhp * damageN);
                }
            }
        }
    }
}
