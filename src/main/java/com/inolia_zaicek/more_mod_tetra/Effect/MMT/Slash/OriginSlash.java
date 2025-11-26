package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Slash;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

public class OriginSlash {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(originSlashEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                originSlashName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                //小数点1位用new se.mickelus.tetra.gui.stats.getter.TooltipGetterDecimalSingle(originSlashTooltip, statGetter)
                new TooltipGetterInteger(originSlashTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, originSlashEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, originSlashEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float hp =mob.getHealth();
            float mhp =mob.getMaxHealth();
            //当前生命值比例
            float dhp =hp/mhp;
            //当前血量比例小于数值比例
            if (effectLevel > 0&&hp>0) {
                //比例*增伤==如100/100==1*100满血*1%增伤
                //残血是50%当前血量*100*增伤
                //没血是1%*100*增伤
                float finish =1+dhp*100*(effectLevel /1000);
                event.setAmount(event.getAmount()*finish);
            }
        }else        if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, originSlashEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, originSlashEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            float hp =mob.getHealth();
            float mhp =mob.getMaxHealth();
            //当前生命值比例
            float dhp =hp/mhp;
            //当前血量比例小于数值比例
            if (effectLevel > 0&&hp>0) {
                //比例*增伤==如100/100==1*100满血*1%增伤
                //残血是50%当前血量*100*增伤
                //没血是1%*100*增伤
                float finish =1+dhp*100*(effectLevel /1000);
                event.setAmount(event.getAmount()*finish);
            }
        }
    }
}

