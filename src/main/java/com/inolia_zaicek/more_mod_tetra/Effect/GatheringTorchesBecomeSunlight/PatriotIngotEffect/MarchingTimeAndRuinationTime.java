package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect;

import com.freefish.torchesbecomesunlight.server.init.EffectHandle;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.marchingTimeTooltip;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class MarchingTimeAndRuinationTime {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ruinationTimeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ruinationTimeName, 0, 6, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ruinationTimeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if(ModList.get().isLoaded("torchesbecomesunlight")) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel1 = 0;
            int effectLevel2 = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel1 = item.getEffectLevel(mainHandItem, ruinationTimeEffect);
                float mainEffectLevel2 = item.getEffectLevel(mainHandItem, marchingTimeEffect);
                if (mainEffectLevel1 > 0) {
                    effectLevel1 += (int) mainEffectLevel1;
                }
                if (mainEffectLevel2 > 0) {
                    effectLevel2 += (int) mainEffectLevel2;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel1 = item.getEffectLevel(offhandItem, ruinationTimeEffect);
                float offEffectLevel2 = item.getEffectLevel(offhandItem, marchingTimeEffect);
                if (offEffectLevel1 > 0) {
                    effectLevel1 += (int) offEffectLevel1;
                }
                if (offEffectLevel2 > 0) {
                    effectLevel2 += (int) offEffectLevel2;
                }
            }
            //行军给状态
            if(effectLevel2>0&& livingEntity.tickCount % 10 == 0&&!livingEntity.hasEffect(MMTEffectsRegister.RuinationTime.get())){
                livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.MarchingTime.get(),40,effectLevel2-1));
            }
            //毁灭--毁灭词条，时间，毁灭状态，防止有行军状态
            if(effectLevel1>0&& livingEntity.tickCount % 10 == 0&&livingEntity.hasEffect(MMTEffectsRegister.RuinationTime.get()) &&livingEntity.hasEffect(MMTEffectsRegister.MarchingTime.get())){
                livingEntity.removeEffect(MMTEffectsRegister.MarchingTime.get());
                //杀戮光环
                float number =(float) effectLevel1 /100;
                var mobList = MMTUtil.mobList(4,livingEntity);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                        if(livingEntity instanceof Player player) {
                            mobs.setLastHurtByPlayer(player);
                        }
                            float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().mobAttack( livingEntity), atk * number);
                    }
                }
            }
        }
    }
    //免死
    @SubscribeEvent(priority = EventPriority.HIGH)
    //全局事件死亡
    public static void LivingDeathVampire(LivingDeathEvent event) {
        //有灾变
        if (ModList.get().isLoaded("torchesbecomesunlight")) {
            if (event.getEntity() instanceof Player livingEntity && !livingEntity.hasEffect(MMTEffectsRegister.PatriotRenascenceCoolingTime.get())
            &&livingEntity.hasEffect(MMTEffectsRegister.MarchingTime.get())) {
                //获取一下玩家盔甲
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, ruinationTimeEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, ruinationTimeEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //设置玩家血量（不要滥用改写
                    livingEntity.setHealth(livingEntity.getMaxHealth() / 2);
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.PatriotRenascenceCoolingTime.get(), 60 * 20, 0, true, true));
                    livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.RuinationTime.get(),30*20,effectLevel-1));
                    //设置玩家死了多久（>0死透了
                    livingEntity.deathTime = -2;
                    //设置玩家是活着的（isAlive是个布尔值
                    livingEntity.isAlive();
                    //设置无敌时间
                    livingEntity.invulnerableTime = 20;
                    //事件可以被取消
                    event.setCanceled(true);
                }

            }
        }
    }
}
