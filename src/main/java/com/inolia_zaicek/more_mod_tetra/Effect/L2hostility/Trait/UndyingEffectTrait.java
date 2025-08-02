package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
@Mod.EventBusSubscriber(modid = MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UndyingEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(undyingEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                undyingEffectTraitName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(undyingEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        //有灾变
        if (ModList.get().isLoaded("l2complements")) {
            //检测到玩家寄了&&玩家没有鬼魅缠身buff
            if (event.getEntity() instanceof Player player
                    //非虚空伤害
                    &&!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)
            ) {
                //饰品部分（莱特兰必定有饰品
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            ModularItem curiousItem = (ModularItem) itemStack.getItem();
                            //获取一下玩家主副手
                            ItemStack mainHandItem = player.getMainHandItem();
                            ItemStack offhandItem = player.getOffhandItem();
                            int effectLevel = 0;
                            effectLevel += curiousItem.getEffectLevel(itemStack, undyingEffectTraitEffect);
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, undyingEffectTraitEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, undyingEffectTraitEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //设置玩家血量（不要滥用改写
                    player.setHealth(player.getMaxHealth());
                    player.deathTime = -10;
                    //设置玩家是活着的（isAlive是个布尔值
                    player.isAlive();
                    //设置无敌时间
                    player.invulnerableTime = 0;
                    //事件可以被取消
                    event.setCanceled(true);
                }
                }));
            }
        }
    }
}
