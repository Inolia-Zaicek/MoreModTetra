package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class CataclysmStun {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(cataclysmStunEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                cataclysmStunName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(cataclysmStunTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            var attacked = event.getEntity();
            var map = attacked.getActiveEffectsMap();
                if(event.getSource().getEntity() instanceof LivingEntity attacker) {
                    int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,cataclysmStunEffect);
                    if (effectLevel > 0) {
                        Random random = new Random();
                        if (effectLevel > 99) {
                            attacked.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                            map.put(ModEffect.EFFECTSTUN.get(),
                                    new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                            map.put(MMTEffectsRegister.Unstun.get(),
                                    new MobEffectInstance(MMTEffectsRegister.Unstun.get(), 200, 0));
                        } else {
                            if (random.nextInt(100) <= effectLevel) {
                                attacked.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                                map.put(ModEffect.EFFECTSTUN.get(),
                                        new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                                map.put(MMTEffectsRegister.Unstun.get(),
                                        new MobEffectInstance(MMTEffectsRegister.Unstun.get(), 200, 0));
                            }
                        }
                        //主手有工具的情况下，解除状态
                        if (attacked.hasEffect(MMTEffectsRegister.Unstun.get()) && attacked.hasEffect(ModEffect.EFFECTSTUN.get())) {
                            //小于100
                            if (effectLevel <= 99) {
                                if (random.nextInt(100) <= 100 - effectLevel) {
                                    attacked.removeEffect(ModEffect.EFFECTSTUN.get());
                                    attacked.removeEffect(MMTEffectsRegister.Unstun.get());
                                }
                            }
                        }
                        //攻击者是玩家但是词条=0
                    }else{
                        if(attacked.hasEffect(MMTEffectsRegister.Unstun.get()) && attacked.hasEffect(ModEffect.EFFECTSTUN.get()) ) {
                            attacked.removeEffect(ModEffect.EFFECTSTUN.get());
                            attacked.removeEffect(MMTEffectsRegister.Unstun.get());
                    }
                }
            }else if(event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
                    int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,cataclysmStunEffect);
                    if (effectLevel > 0) {
                        Random random = new Random();
                        if (effectLevel > 99) {
                            attacked.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                            map.put(ModEffect.EFFECTSTUN.get(),
                                    new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                            map.put(MMTEffectsRegister.Unstun.get(),
                                    new MobEffectInstance(MMTEffectsRegister.Unstun.get(), 200, 0));
                        } else {
                            if (random.nextInt(100) <= effectLevel) {
                                attacked.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                                map.put(ModEffect.EFFECTSTUN.get(),
                                        new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 200, 0));
                                map.put(MMTEffectsRegister.Unstun.get(),
                                        new MobEffectInstance(MMTEffectsRegister.Unstun.get(), 200, 0));
                            }
                        }
                        //主手有工具的情况下，解除状态
                        if (attacked.hasEffect(MMTEffectsRegister.Unstun.get()) && attacked.hasEffect(ModEffect.EFFECTSTUN.get())) {
                            //小于100
                            if (effectLevel <= 99) {
                                if (random.nextInt(100) <= 100 - effectLevel) {
                                    attacked.removeEffect(ModEffect.EFFECTSTUN.get());
                                    attacked.removeEffect(MMTEffectsRegister.Unstun.get());
                                }
                            }
                        }
                        //攻击者是玩家但是词条=0
                    }else{
                        if(attacked.hasEffect(MMTEffectsRegister.Unstun.get()) && attacked.hasEffect(ModEffect.EFFECTSTUN.get()) ) {
                            attacked.removeEffect(ModEffect.EFFECTSTUN.get());
                            attacked.removeEffect(MMTEffectsRegister.Unstun.get());
                        }
                    }
                }
        }
    }
}
