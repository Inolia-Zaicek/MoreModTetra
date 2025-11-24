package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import net.minecraft.world.entity.LivingEntity;
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

public class TorchTheLawsOfOld {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(torchTheLawsOfOldEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                torchTheLawsOfOldName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(torchTheLawsOfOldTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, torchTheLawsOfOldEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, torchTheLawsOfOldEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float mobHp = mob.getHealth();
            float playerHhp = player.getHealth();
            //生命值不等
            if (effectLevel > 0 && mobHp != playerHhp) {
                //怪物大于玩家
                if (mobHp > playerHhp) {
                    //生命值差额
                    float dhp =Math.min(80,mobHp-playerHhp);
                    //增幅强度*生命值差额进度
                    float finish = 1 + (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
                else if (playerHhp > mobHp) {
                    //生命值差额
                    float dhp =Math.min(80,playerHhp-mobHp);
                    //增幅强度*生命值差额进度
                    float finish = 1 + (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
        else         if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, torchTheLawsOfOldEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, torchTheLawsOfOldEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float mobHp = mob.getHealth();
            float playerHhp = player.getHealth();
            //生命值不等
            if (effectLevel > 0 && mobHp != playerHhp) {
                //怪物大于玩家
                if (mobHp > playerHhp) {
                    //生命值差额
                    float dhp =Math.min(80,mobHp-playerHhp);
                    //增幅强度*生命值差额进度
                    float finish = 1 + (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
                else if (playerHhp > mobHp) {
                    //生命值差额
                    float dhp =Math.min(80,playerHhp-mobHp);
                    //增幅强度*生命值差额进度
                    float finish = 1 + (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
        else if (event.getSource().getDirectEntity() instanceof LivingEntity mob) {
            LivingEntity player = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, torchTheLawsOfOldEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, torchTheLawsOfOldEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float mobHp = mob.getHealth();
            float playerHhp = player.getHealth();
            //生命值不等
            if (effectLevel > 0 && mobHp != playerHhp) {
                //怪物大于玩家
                if (mobHp > playerHhp) {
                    //生命值差额
                    float dhp =Math.min(80,mobHp-playerHhp);
                    //增幅强度*生命值差额进度
                    float finish = 1 - (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
                else if (playerHhp > mobHp) {
                    //生命值差额
                    float dhp =Math.min(80,playerHhp-mobHp);
                    //增幅强度*生命值差额进度
                    float finish = 1 - (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
        else if (event.getSource().getEntity() instanceof LivingEntity mob) {
            LivingEntity player = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, torchTheLawsOfOldEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, torchTheLawsOfOldEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float mobHp = mob.getHealth();
            float playerHhp = player.getHealth();
            //生命值不等
            if (effectLevel > 0 && mobHp != playerHhp) {
                //怪物大于玩家
                if (mobHp > playerHhp) {
                    //生命值差额
                    float dhp =Math.min(80,mobHp-playerHhp);
                    //增幅强度*生命值差额进度
                    float finish = 1 - (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
                else if (playerHhp > mobHp) {
                    //生命值差额
                    float dhp =Math.min(80,playerHhp-mobHp);
                    //增幅强度*生命值差额进度
                    float finish = 1 - (effectLevel/100)*(dhp/80);
                    event.setAmount(event.getAmount() * finish);
                }
            }
        }
    }
}
