package com.inolia_zaicek.more_mod_tetra.Effect.EidolonRepraised;

import elucent.eidolon.registries.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Collection;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SoulShard {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(soulShardEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                soulShardName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(soulShardTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> drops = event.getDrops();
            if (mob instanceof Player) {
                return;
            }
            Level level = attacker.level();
            //获取实体tag
            EntityType<?> entityType = mob.getType();
            ItemStack mainHandItem = attacker.getMainHandItem();
            ItemStack offhandItem = attacker.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, soulShardEffect);
                if (mainEffectLevel > 0) {
                    int lootingLevel = mainHandItem.getEnchantmentLevel(Enchantments.MOB_LOOTING);
                    effectLevel += (int) mainEffectLevel + lootingLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, soulShardEffect);
                if (offEffectLevel > 0) {
                    int lootingLevel = offhandItem.getEnchantmentLevel(Enchantments.MOB_LOOTING);
                    effectLevel += (int) offEffectLevel + lootingLevel;
                }
            }
            if (effectLevel > 0) {
                for (int i = 0; i < effectLevel; i++) {
                    ItemEntity itementity = new ItemEntity(level, mob.getX(),
                            mob.getY(), mob.getZ(),
                            //灵魂碎片
                            Registry.SOUL_SHARD.get()
                                    .getDefaultInstance());
                    // 设置物品实体默认的拾取延迟。
                    //item entity.setDefaultPickUpDelay();
                    // 为物品实体设置一个随机的移动向量，使其在生成时有一定的散开效果。
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    // 将生成的物品实体添加到游戏世界中，使其可见并可以交互。
                    level.addFreshEntity(itementity);

                }
            }
        }
    }
}