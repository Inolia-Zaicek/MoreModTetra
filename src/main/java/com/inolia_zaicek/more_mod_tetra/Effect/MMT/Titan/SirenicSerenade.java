package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SirenicSerenade {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(sirenicSerenadeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                sirenicSerenadeName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(sirenicSerenadeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, sirenicSerenadeEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, sirenicSerenadeEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    Random random = new Random();
                    int debuffNumber =0;
                    if(random.nextInt(70)<10){
                        mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        if(!mob.hasEffect(MobEffects.WEAKNESS)) {
                            map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<20&&random.nextInt(70)>=10){
                        mob.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
                        if(!mob.hasEffect(MobEffects.POISON)) {
                            map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<30&&random.nextInt(70)>=20 ){
                        if(!(mob.getRemainingFireTicks()>0)) {
                            mob.setRemainingFireTicks(mob.getRemainingFireTicks() + 200);
                        }
                    }
                    else if(random.nextInt(70)<40&&random.nextInt(70)>=30){
                        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                        if(!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<50&&random.nextInt(70)>=40) {
                        mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
                        if (!mob.hasEffect(MobEffects.WITHER)) {
                            map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<60&&random.nextInt(70)>=50) {
                        mob.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                        if (!mob.hasEffect(MobEffects.DARKNESS)) {
                            map.put(MobEffects.DARKNESS, new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)>=60) {
                        mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
                        if (!mob.hasEffect(MobEffects.BLINDNESS)) {
                            map.put(MobEffects.BLINDNESS, new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        }
                    }
                    if(mob.hasEffect(MobEffects.WEAKNESS)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.POISON)){debuffNumber +=1;}
                    if(mob.getRemainingFireTicks()>0){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.WITHER)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.DARKNESS)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.BLINDNESS)){debuffNumber +=1;}
                    if(debuffNumber>0){
                        event.setAmount(event.getAmount()*(1+effectLevel/100*debuffNumber));
                    }
                }
        }else if (event.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, sirenicSerenadeEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, sirenicSerenadeEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    Random random = new Random();
                    int debuffNumber =0;
                    if(random.nextInt(70)<10){
                        mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        if(!mob.hasEffect(MobEffects.WEAKNESS)) {
                            map.put(MobEffects.WEAKNESS, new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<20&&random.nextInt(70)>=10){
                        mob.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
                        if(!mob.hasEffect(MobEffects.POISON)) {
                            map.put(MobEffects.POISON, new MobEffectInstance(MobEffects.POISON, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<30&&random.nextInt(70)>=20 ){
                        if(!(mob.getRemainingFireTicks()>0)) {
                            mob.setRemainingFireTicks(mob.getRemainingFireTicks() + 200);
                        }
                    }
                    else if(random.nextInt(70)<40&&random.nextInt(70)>=30){
                        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                        if(!mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<50&&random.nextInt(70)>=40) {
                        mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
                        if (!mob.hasEffect(MobEffects.WITHER)) {
                            map.put(MobEffects.WITHER, new MobEffectInstance(MobEffects.WITHER, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)<60&&random.nextInt(70)>=50) {
                        mob.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                        if (!mob.hasEffect(MobEffects.DARKNESS)) {
                            map.put(MobEffects.DARKNESS, new MobEffectInstance(MobEffects.DARKNESS, 200, 0));
                        }
                    }
                    else if(random.nextInt(70)>=60) {
                        mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
                        if (!mob.hasEffect(MobEffects.BLINDNESS)) {
                            map.put(MobEffects.BLINDNESS, new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
                        }
                    }
                    if(mob.hasEffect(MobEffects.WEAKNESS)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.POISON)){debuffNumber +=1;}
                    if(mob.getRemainingFireTicks()>0){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.WITHER)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.DARKNESS)){debuffNumber +=1;}
                    if(mob.hasEffect(MobEffects.BLINDNESS)){debuffNumber +=1;}
                    if(debuffNumber>0){
                        event.setAmount(event.getAmount()*(1+effectLevel/100*debuffNumber));
                    }
                }
            }
    }
}
