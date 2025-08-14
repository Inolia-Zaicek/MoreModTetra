package com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
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
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("enigmaticlegacy")) {
            //挨打是玩家
            if (event.getEntity() instanceof Player player) {
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, etheriumGuardEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, etheriumGuardEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                float hp =player.getHealth();
                float mhp =player.getMaxHealth();
                float finish =hp/mhp;
                if (effectLevel > 0&&finish<=0.5f) {
                    float number = Math.min(0.75f, (float) effectLevel / 100 );
                    event.setAmount(event.getAmount()*(1-number));
                }
            }
            }
        }
    @SubscribeEvent
    public static void heal(LivingHealEvent event) {
        if(event.getEntity() instanceof Player player){
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, etheriumGuardEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof ModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, etheriumGuardEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            float heal =event.getAmount();
            float hp =player.getHealth();
            float mhp2 =player.getMaxHealth() / 2 ;
            //治疗血量+总血量大于最大hp
            if (effectLevel > 0&& (heal+hp>mhp2) ) {
                //超出多少
                float over = heal+hp-mhp2;
                //超出量小于0
                if(over<=0){
                    event.setAmount(0);
                }else {
                    //治疗量-超出量
                    event.setAmount(heal - over);
                }
            }
        }
    }
    }
