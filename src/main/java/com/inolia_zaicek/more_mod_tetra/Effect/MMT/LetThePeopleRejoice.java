package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
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

public class LetThePeopleRejoice {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(letThePeopleRejoiceEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                letThePeopleRejoiceName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(letThePeopleRejoiceTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,letThePeopleRejoiceEffect);
            if (effectLevel > 0&&player.hasEffect(MMTEffectsRegister.LetThePeopleRejoice.get())) {
                float number = (float) effectLevel / 100;
                event.addNormalMulti((number));
            }
        }
        //挨打
        if (event.getAttacked()!=null) {
            LivingEntity player = event.getAttacked();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,letThePeopleRejoiceEffect);
            if (effectLevel > 0) {
                player.addEffect(new MobEffectInstance(MMTEffectsRegister.LetThePeopleRejoice.get(),200,0));
                if(player.hasEffect(MMTEffectsRegister.LetThePeopleRejoice.get())){
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((number));
                }
            }
        }
    }
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,letThePeopleRejoiceEffect);
            if (effectLevel > 0) {
                if(event.getAmount()>0){
                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.LetThePeopleRejoice.get(),200,0));
                }
                if(player.hasEffect(MMTEffectsRegister.LetThePeopleRejoice.get())){
                    float number = (float) effectLevel / 100;
                    event.setAmount(event.getAmount()*(1+number));
                }
            }
        }
    }
}
