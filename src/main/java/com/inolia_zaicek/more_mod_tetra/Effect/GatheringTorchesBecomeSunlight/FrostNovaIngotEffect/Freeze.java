package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect;

import com.freefish.torchesbecomesunlight.server.init.EffectHandle;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Freeze {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(freezeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                freezeName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(freezeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, freezeEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, freezeEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0&&mob.hasEffect(EffectHandle.FREEZE.get())) {
                    float damage =event.getAmount();
                    event.setAmount(damage*(1+ (float) effectLevel /100));
                }
            }
            else if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getEntity();
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, freezeEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, freezeEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
                if (effectLevel > 0&&mob.hasEffect(EffectHandle.FREEZE.get())) {
                float damage =event.getAmount();
                event.setAmount(damage*(1+ (float) effectLevel /100));
            }
        }
            //圣爹
        //挨打
        if (event.getEntity() instanceof Player livingEntity) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ritualOfHolyGuardEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, ritualOfHolyGuardEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0&&livingEntity.hasEffect(MMTEffectsRegister.RitualOfHolyGuard.get())) {
                int buffLevel = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getAmplifier();
                int buffTime = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getDuration();
                float damage = event.getAmount();
                //大于1
                if (buffLevel != 0) {
                    //减伤==buff等级*减伤比例
                    float finish = damage * (1 - (buffLevel + 1) * ((float) effectLevel / 100));
                    event.setAmount(finish);
                    livingEntity.removeEffect(MMTEffectsRegister.RitualOfHolyGuard.get());
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), buffTime, buffLevel - 1));
                }else{
                    //减伤==buff等级*减伤比例
                    float finish = damage * (1 - (buffLevel + 1) * ((float) effectLevel / 100));
                    event.setAmount(finish);
                    livingEntity.removeEffect(MMTEffectsRegister.RitualOfHolyGuard.get());
                }
            }
        }
        //打人
        if (event.getSource().getEntity() instanceof Player livingEntity) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            int effectLevel2 = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ritualOfHolyGuardEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
                float mainEffectLevel2 = item.getEffectLevel(mainHandItem, exhortationOfGunKnightPatriotEffect);
                if (mainEffectLevel2 > 0) {
                    effectLevel2 += (int) mainEffectLevel2;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, ritualOfHolyGuardEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
                float offEffectLevel2 = item.getEffectLevel(mainHandItem, exhortationOfGunKnightPatriotEffect);
                if (offEffectLevel2 > 0) {
                    effectLevel2 += (int) offEffectLevel2;
                }
            }
            if (effectLevel > 0) {
                //有圣卫核心
                if(effectLevel2>0){
                    //挨打的上重伤
                    if(event.getEntity()!=null) {
                        event.getEntity().addEffect(new MobEffectInstance(MMTEffectsRegister.BanHeal.get(), 400, 3 ));
                    }
                    //有buff
                    if (livingEntity.hasEffect(MMTEffectsRegister.RitualOfHolyGuard.get())) {
                        int buffLevel = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getAmplifier();
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400,  Math.min(8,buffLevel + 2)  ));
                        float damage = event.getAmount();
                        //增伤==1+buff等级*减伤比例
                        float finish = damage * (1 + (buffLevel + 1) * ((float) effectLevel / 100));
                        event.setAmount(finish);
                    }else{
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400, 1));
                        float damage = event.getAmount();
                        //增伤伤==1+buff等级*减伤比例
                        float finish = damage * (1 +((float) effectLevel / 100));
                        event.setAmount(finish);
                    }
                }else{
                    //无核心
                    //有buff
                    if (livingEntity.hasEffect(MMTEffectsRegister.RitualOfHolyGuard.get())) {
                        int buffLevel = livingEntity.getEffect(MMTEffectsRegister.RitualOfHolyGuard.get()).getAmplifier();
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400, Math.min(8,buffLevel + 1)  ));
                        float damage = event.getAmount();
                        //增伤==1+buff等级*减伤比例
                        float finish = damage * (1 + (buffLevel + 1) * ((float) effectLevel / 200));
                        event.setAmount(finish);
                    }else{
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RitualOfHolyGuard.get(), 400, 0));
                        float damage = event.getAmount();
                        //增伤伤==1+buff等级*减伤比例
                        float finish = damage * (1 +((float) effectLevel / 200));
                        event.setAmount(finish);
                    }
                }
            }
        }
        //减伤
        if (event.getEntity() instanceof Player livingEntity) {
            var mob = event.getSource().getEntity();
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ritualOfExhortationEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, ritualOfExhortationEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0) {
                float damage = event.getAmount();
                float finish = damage*(1- (float) effectLevel /100);
                var mobList = MMTUtil.mobList(8,livingEntity);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        //如果这些范围里没有人和伤害源一样
                        if(mobs!=mob) {
                            event.setAmount(finish);
                        }else{
                            //有人和伤害源一样
                            event.setAmount(damage);
                        }
                    }else{
                        //周围没人
                        event.setAmount(damage);
                    }
                }
            }
        }
    }
}
