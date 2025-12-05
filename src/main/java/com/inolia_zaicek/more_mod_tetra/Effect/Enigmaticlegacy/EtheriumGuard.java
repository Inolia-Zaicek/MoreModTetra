package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class EtheriumGuard {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(etheriumGuardEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                etheriumGuardName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(etheriumGuardTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            //挨打是玩家
            LivingEntity livingEntity = event.getAttacked();;
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,etheriumGuardEffect);
            float hp = livingEntity.getHealth();
            float mhp = livingEntity.getMaxHealth();
            float finish = hp / mhp;
            if (effectLevel > 0 && finish <= 0.5f) {
                float number = Math.min(0.75f, (float) effectLevel / 100);
                event.addNormalMulti((1 - number));
        }
    }

    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        LivingEntity livingEntity = event.getEntity();;
        int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,etheriumGuardEffect);
        float heal = event.getAmount();
        float hp = livingEntity.getHealth();
        float mhp2 = livingEntity.getMaxHealth() / 2;
        //治疗血量+总血量大于最大hp
        if (effectLevel > 0 && (heal + hp > mhp2)) {
            //超出多少
            float over = heal + hp - mhp2;
            //超出量小于0
            if (over <= 0) {
                event.setAmount(0);
            } else {
                //治疗量-超出量
                event.setAmount(heal - over);
            }
        }
    }
}
