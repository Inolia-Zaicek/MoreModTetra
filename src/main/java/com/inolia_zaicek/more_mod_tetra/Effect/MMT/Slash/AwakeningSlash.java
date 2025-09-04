package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Slash;

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

public class AwakeningSlash {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(awakeningSlashEffect,   1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                awakeningSlashName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(awakeningSlashTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, awakeningSlashEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, awakeningSlashEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += offEffectLevel;
                    }
                }
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //当前已损失生命值
                float dhp =(mhp-hp);
                //当前血量比例小于数值比例
                if (effectLevel > 0&&hp<mhp) {
                    //数额*比例
                    float finish =dhp*(effectLevel /100);
                    event.setAmount(event.getAmount()+finish);
                    }
                }else            if (event.getSource().getDirectEntity() instanceof Player player) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, awakeningSlashEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, awakeningSlashEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += offEffectLevel;
                    }
                }
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //当前已损失生命值
                float dhp =(mhp-hp);
                //当前血量比例小于数值比例
                if (effectLevel > 0&&hp<mhp) {
                    //数额*比例
                    float finish =dhp*(effectLevel /100);
                    event.setAmount(event.getAmount()+finish);
                }
            }
            }
        }

