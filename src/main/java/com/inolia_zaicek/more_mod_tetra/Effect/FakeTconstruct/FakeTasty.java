package com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class FakeTasty {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fakeTastyEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fakeTastyName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fakeTastyTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            //获取饥饿等级，.getSaturationLevel()是获取饱和度
            int food = player.getFoodData().getFoodLevel();
            float saturation = player.getFoodData().getSaturationLevel();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            if(food<20) {
                if (mainHandItem.getItem() instanceof IModularItem item && player.level().getGameTime() % 20L == 0) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fakeTastyEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的损耗值
                        int currentDamage = mainHandItem.getDamageValue();
                        int newDamage = (currentDamage + 10);
                        //大于11，扣10点损耗值
                        if ((mainHandItem.getMaxDamage() - mainHandItem.getDamageValue()) > 11) {
                            mainHandItem.setDamageValue(Math.max(1, newDamage));
                            //回复饱食度
                            player.getFoodData().setSaturation( Math.min(saturation+mainEffectLevel,20)  );
                            player.getFoodData().setFoodLevel((int) Math.min(food+mainEffectLevel,20));
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item && player.level().getGameTime() % 20L == 0) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, fakeTastyEffect);
                    if (offEffectLevel > 0) {
                        // 获取工具的损耗值
                        int currentDamage = offhandItem.getDamageValue();
                        int newDamage = (currentDamage + 10);
                        //大于11，扣10点损耗值
                        if ((offhandItem.getMaxDamage() - offhandItem.getDamageValue()) > 11) {
                            offhandItem.setDamageValue(Math.max(1, newDamage));
                            //回复饱食度
                            player.getFoodData().setSaturation( Math.min(saturation+offEffectLevel,20)  );
                            player.getFoodData().setFoodLevel((int) Math.min(food+offEffectLevel,20));
                        }
                    }
                }
            }
    }
}
