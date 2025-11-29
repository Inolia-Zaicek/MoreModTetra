package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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

public class BokushuuSDesire {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(bokushuuSDesireEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                bokushuuSDesireName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(bokushuuSDesireTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,bokushuuSDesireEffect);
            if (effectLevel > 0) {
                player.addEffect(new MobEffectInstance(MMTEffectsRegister.BokushuuSDesire.get(), 100, 0));
                if (player.hasEffect(MMTEffectsRegister.BokushuuSDesire.get())) {
                    //按tick算
                    int time = player.getEffect(MMTEffectsRegister.BokushuuSDesire.get()).getDuration();
                    //tick数量*增伤%%*0.01
                    float finish = 1 + (float) (time * (effectLevel / 100)) /100;
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
        else if (event.getSource().getEntity() instanceof LivingEntity player) {
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,bokushuuSDesireEffect);
            if (effectLevel > 0) {
                player.addEffect(new MobEffectInstance(MMTEffectsRegister.BokushuuSDesire.get(), 100, 0));
                if (player.hasEffect(MMTEffectsRegister.BokushuuSDesire.get())) {
                    //按tick算
                    int time = player.getEffect(MMTEffectsRegister.BokushuuSDesire.get()).getDuration();
                    float finish = 1 + (float) (time * (effectLevel / 100)) /100;
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
    }
}

