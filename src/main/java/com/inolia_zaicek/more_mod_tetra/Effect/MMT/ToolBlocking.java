package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ToolBlocking {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(toolBlockingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                toolBlockingName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(toolBlockingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //挨打
            if (event.getEntity() instanceof Player player) {
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                float damage =event.getAmount();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, toolBlockingEffect);
                    if (mainEffectLevel > 0) {
                        //当前耐久
                        int currentDurability = mainHandItem.getMaxDamage()-mainHandItem.getDamageValue();
                        float number =mainEffectLevel / 100;
                        float finalDamage =damage*number;
                        if(currentDurability>=10&&currentDurability>finalDamage){
                            event.setAmount(damage * (1 - mainEffectLevel / 100));
                            // 获取主手工具的损耗值
                            int currentDamage = mainHandItem.getDamageValue();
                            int newDamage = (int) (currentDamage + finalDamage);
                            mainHandItem.setDamageValue(Math.max(0, newDamage));
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, toolBlockingEffect);
                    if (offEffectLevel > 0) {
                        //当前耐久
                        int currentDurability = offhandItem.getMaxDamage()-offhandItem.getDamageValue();
                        float number =offEffectLevel / 100;
                        float finalDamage =damage*number;
                        if(currentDurability>=10&&currentDurability>finalDamage){
                            event.setAmount(damage * (1 - offEffectLevel / 100));
                            // 获取副手工具的损耗值
                            int currentDamage = offhandItem.getDamageValue();
                            int newDamage = (int) (currentDamage + finalDamage);
                            offhandItem.setDamageValue(Math.max(0, newDamage));
                        }
                    }
                }
        }
    }
}
