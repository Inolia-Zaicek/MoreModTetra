package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

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
            if (event.getEntity()!=null
                    //非虚空伤害
                    &&!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)&&
                    //无诅咒
                    !event.getEntity().hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "curse"))))
            ) {
                LivingEntity livingEntity = event.getEntity();
                //饰品部分（莱特兰必定有饰品
                CuriosApi.getCuriosInventory(livingEntity).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem curiousItem = (IModularItem) itemStack.getItem();
                            //获取一下玩家主副手
                            ItemStack mainHandItem = livingEntity.getMainHandItem();
                            ItemStack offhandItem = livingEntity.getOffhandItem();
                            int effectLevel = 0;
                            effectLevel += curiousItem.getEffectLevel(itemStack, undyingEffectTraitEffect);
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, undyingEffectTraitEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, undyingEffectTraitEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //设置玩家血量（不要滥用改写
                    livingEntity.setHealth(livingEntity.getMaxHealth());
                    livingEntity.deathTime = -10;
                    //设置玩家是活着的（isAlive是个布尔值
                    livingEntity.isAlive();
                    //设置无敌时间
                    livingEntity.invulnerableTime = 0;
                    //事件可以被取消
                    event.setCanceled(true);
                }
                }));
            }
        }
    }
}
