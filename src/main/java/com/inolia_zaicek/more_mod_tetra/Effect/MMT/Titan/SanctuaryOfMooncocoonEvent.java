package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;


import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SanctuaryOfMooncocoonEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
                //获取一下玩家盔甲
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, sanctuaryOfMooncocoonEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, sanctuaryOfMooncocoonEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0 && !player.hasEffect(MMTEffectsRegister.SanctuaryOfMooncocoon.get())) {
                        //设置玩家血量（不要滥用改写
                        player.setHealth(player.getMaxHealth() * effectLevel / 100);
                        //给予玩家月茧之庇
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.SanctuaryOfMooncocoon.get(), 10 * 20, 0, true, true));
                    //给予玩家月茧之庇冷却
                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.SanctuaryOfMooncocoonCooldown.get(), 5*60 * 20, 0, true, true));
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
