package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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
@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GhostForm {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ghostFormEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ghostFormName, 0, 15, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ghostFormTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        //有灾变
        if (ModList.get().isLoaded("cataclysm")) {
            //检测到玩家寄了&&玩家没有鬼魅缠身buff
            if (event.getEntity() instanceof Player player && !player.hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get())) {
                //获取一下玩家盔甲
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, ghostFormEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, ghostFormEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                        //设置玩家血量（不要滥用改写
                        player.setHealth(player.getMaxHealth() / 2);
                        //给予玩家鬼魅缠身buff（封印一下效果
                        player.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_SICKNESS.get(), 3 * 60 * 20, 0, true, true));
                        //给予玩家鬼魂缠身buff（咒魂
                        player.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_FORM.get(), effectLevel * 20, 0, true, true));
                        //设置玩家死了多久（>0死透了
                        player.deathTime = -2;
                        //设置玩家是活着的（isAlive是个布尔值
                        player.isAlive();
                        //设置无敌时间
                        player.invulnerableTime = 10;
                        //事件可以被取消
                        event.setCanceled(true);
                    }

            }
        }
    }
}
