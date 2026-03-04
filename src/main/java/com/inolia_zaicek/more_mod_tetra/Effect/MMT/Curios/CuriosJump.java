package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.List;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosJump {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosJumpEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosJumpName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosJumpTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity player = event.getEntity();
        //1s一次
        if(player.level().getGameTime() % 20L == 0) {
            //
            int reverseMirrorEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, reverseMirrorEffect);
            if (reverseMirrorEffectLevel > 0 && player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                int buffLevel = player.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier();
                int buffTime = player.getEffect(MobEffects.DIG_SLOWDOWN).getDuration();
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, buffTime, buffLevel * 2, true, true, true));
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }
            //
            int minerLanternEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, minerLanternEffect);
            if (minerLanternEffectLevel > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0, true, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, true, true, true));
                if (minerLanternEffectLevel > 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, minerLanternEffectLevel - 1, true, true, true));
                }
            }
            //
            int underwaterOperationsEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, underwaterOperationsEffect);
            if (underwaterOperationsEffectLevel > 0) {
                if (player.isInWaterOrRain() || player.isInWater() || player.isUnderWater()) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, underwaterOperationsEffectLevel-1, true, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, underwaterOperationsEffectLevel-1, true, true, true));
                }
            }
            //
            int hideBladeEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, hideBladeEffect);
            if (hideBladeEffectLevel > 0) {
                //有藏锋
                if (player.hasEffect(MMTEffectsRegister.HideBlade.get())) {
                    int buffLevel = player.getEffect(MMTEffectsRegister.HideBlade.get()).getAmplifier();
                    //每0.5秒计算一次
                    //小于6级
                    if (buffLevel < 5) {
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, buffLevel + 1, true, true, true));
                    }
                    //6级--持续给予藏锋
                    else {
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 5, true, true, true));
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBladeMax.get(), 20 * 60, 0, true, true, true));
                    }
                }
                //无藏锋
                else {
                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 0, true, true, true));
                }
            }
        }
        //
        int smallShulkerEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, smallShulkerEffect);
        if (smallShulkerEffectLevel > 0&&player.isShiftKeyDown()) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, smallShulkerEffectLevel-1, true, true, true));
        }
        //
        int entityResonanceEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, entityResonanceEffect);
        if(entityResonanceEffectLevel>0) {
            var mobList = MMTUtil.mobList(21, player);
            for (Mob mobs : mobList) {
                if (mobs != null) {
                    mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                    var map = mobs.getActiveEffectsMap();
                    map.put(MobEffects.GLOWING, new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                    mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                    if (entityResonanceEffectLevel > 1) {
                        mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, entityResonanceEffectLevel - 1));
                        map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, entityResonanceEffectLevel - 1));
                        mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, entityResonanceEffectLevel - 1));
                    }
                }
            }
            //
        }
        int curiosJumpEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, curiosJumpEffect);
        if (curiosJumpEffectLevel > 0 && player.level().getGameTime() % 10L == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, curiosJumpEffectLevel - 1));
        }
        //
        int curiosJankEffectLevel = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, curiosJankEffect);
        if (curiosJankEffectLevel > 0 && player.level() instanceof ServerLevel level) {
            BlockPos target = player.getOnPos();
            Entity entity = player;
            // 1. 搜索附近的物品实体：
            List<ItemEntity> items = level.getEntities(EntityType.ITEM, new AABB(target).inflate(curiosJankEffectLevel * 0.5), Entity::isAlive);
            // 3. 处理搜寻到的物品实体：
            //    - `items.forEach(item -> { ... })`: 遍历列表中的每一个 `item`（物品实体）。
            items.forEach(item -> {
                level.sendParticles(ParticleTypes.REVERSE_PORTAL, item.getX() + item.getBbWidth() / 2, item.getY() + item.getBbHeight() / 2,
                        item.getZ() + item.getBbWidth() / 2, 1, 0, 0, 0, 0);
                item.moveTo(entity.getPosition(0));
                item.setPickUpDelay(0);
            });
            level.getEntities(EntityType.EXPERIENCE_ORB, new AABB(target).inflate(curiosJankEffectLevel * 0.5), Entity::isAlive).forEach(orb -> orb.moveTo(entity.getPosition(0)));
        }
    }
}