package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CuriosTotemEvent {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        //buff时长提升与buff等级提升
        var statGetter1 = new StatGetterEffectLevel(curiosTotemEffectEffect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(curiosTotemEffectEffect, 1);
        IStatGetter[] statGetters = {statGetter1, statGetter2};
        IStatFormat[] statFormats = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosTotemEffectName, 0, 10, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(curiosTotemEffectTooltip, statGetters, statFormats)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
        //图腾冷却
        var statGetter = new StatGetterEffectLevel(curiosTotemCooldownEffect, 1);
        GuiStatBar statBar1 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosTotemCooldownName, 0, 30, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosTotemCooldownTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar1);
        HoloStatsGui.addBar(statBar1);
        //图腾生命值回复
        var statGetter4 = new StatGetterEffectLevel(curiosTotemHealthEffect, 1);
        GuiStatBar statBar4 = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosTotemHealthName, 0, 10, false, false, false,
                statGetter4, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosTotemHealthTooltip, statGetter4)
        );
        WorkbenchStatsGui.addBar(statBar4);
        HoloStatsGui.addBar(statBar4);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, curiosTotemHealthEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, curiosTotemHealthEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                }
            }
            //主副手有
            if (effectLevel > 0) {
                //主手有
                if (mainHandItem.getItem() instanceof IModularItem item && item.getEffectLevel(mainHandItem, curiosTotemHealthEffect)>0) {
                    float cooldown = item.getEffectLevel(mainHandItem, curiosTotemCooldownEffect);
                    float finalCooldown = Math.max(0, cooldown * 20);
                    //生命恢复词条
                    float health = item.getEffectLevel(mainHandItem, curiosTotemHealthEffect);
                    float hp = Math.max(1, player.getHealth() * health / 100);
                    //获取第一个数据————时长
                    int time = (int) item.getEffectLevel(mainHandItem, curiosTotemEffectEffect) * 20;
                    //获取第二个数据————等级
                    int level = (int) item.getEffectEfficiency(mainHandItem, curiosTotemEffectEffect);
                    if (!(player.getCooldowns().isOnCooldown((Item) item)) && health >= 0) {
                        player.setHealth(Math.min(player.getMaxHealth(),hp));
                        player.deathTime = -10;
                        player.isAlive();
                        player.invulnerableTime = 10;
                        event.setCanceled(true);
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 40 + time, level));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 45 + time, 1 + level));
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 5 + time, 1 + level));
                        player.getCooldowns().addCooldown((Item) item, (int) finalCooldown);
                    }
                }
                //副手有
                else if (offhandItem.getItem() instanceof IModularItem item && item.getEffectLevel(offhandItem, curiosTotemHealthEffect)>0) {
                    float cooldown = item.getEffectLevel(offhandItem, curiosTotemCooldownEffect);
                    float finalCooldown = Math.max(0, cooldown * 20);
                    //生命恢复词条
                    float health = item.getEffectLevel(offhandItem, curiosTotemHealthEffect);
                    float hp = Math.max(1, player.getHealth() * health / 100);
                    //获取第一个数据————时长
                    int time = (int) item.getEffectLevel(offhandItem, curiosTotemEffectEffect) * 20;
                    //获取第二个数据————等级
                    int level = (int) item.getEffectEfficiency(offhandItem, curiosTotemEffectEffect);
                    if (!(player.getCooldowns().isOnCooldown((Item) item)) && health >= 1) {
                        player.setHealth(Math.min(player.getMaxHealth(),hp));
                        player.deathTime = -10;
                        player.isAlive();
                        player.invulnerableTime = 10;
                        event.setCanceled(true);
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 40 + time, level));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 45 + time, 1 + level));
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 5 + time, 1 + level));
                        player.getCooldowns().addCooldown((Item) item, (int) finalCooldown);
                    }
                }
            }
            //若主副手都没有
            else {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    //冷却词条
                                    float cooldown = item.getEffectLevel(itemStack, curiosTotemCooldownEffect);
                                    float finalCooldown = Math.max(0, cooldown * 20);
                                    //生命恢复词条
                                    float health = item.getEffectLevel(itemStack, curiosTotemHealthEffect);
                                    float hp = Math.max(1, player.getHealth() * health / 100);
                                    //获取第一个数据————时长
                                    int time = (int) item.getEffectLevel(itemStack, curiosTotemEffectEffect) * 20;
                                    //获取第二个数据————等级
                                    int level = (int) item.getEffectEfficiency(itemStack, curiosTotemEffectEffect);
                                    if (!(player.getCooldowns().isOnCooldown((Item) item)) && health >= 1) {
                                        player.setHealth(Math.min(player.getMaxHealth(),hp));
                                        player.deathTime = -10;
                                        player.isAlive();
                                        player.invulnerableTime = 10;
                                        event.setCanceled(true);
                                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 40 + time, level));
                                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 45 + time, 1 + level));
                                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 5 + time, 1 + level));
                                        player.getCooldowns().addCooldown((Item) item, (int) finalCooldown);
                                    }
                                }
                        )
                );
            }
        }
    }
}