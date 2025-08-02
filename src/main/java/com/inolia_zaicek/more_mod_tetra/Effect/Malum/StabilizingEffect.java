package com.inolia_zaicek.more_mod_tetra.Effect.Malum;

import com.sammy.malum.registry.common.AttributeRegistry;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.*;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class StabilizingEffect {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(StabilizingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                StabilizingName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(StabilizingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        Entity attacker = event.getSource().getEntity();
        if (attacker instanceof LivingEntity player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, StabilizingEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof ModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, StabilizingEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0) {
               float scythe_proficiency= (float) player.getAttributeValue(AttributeRegistry.SCYTHE_PROFICIENCY.get());
               event.setAmount(event.getAmount()*scythe_proficiency);
            }
        }
    }
    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null&& event.getEntity().getLastAttacker() instanceof Player) {
            Entity mob = event.getEntity();
            Entity attacker = event.getEntity().getLastAttacker();
            if (mob instanceof Player) {
                return;
            }
            Level level = attacker.level();
            //获取实体tag
            EntityType<?> entityType = mob.getType();
            if (!(attacker instanceof Player player)) {
                return;
            }
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel=0;
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, StabilizingEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, StabilizingEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //player.sendSystemMessage(Component.literal("传送至记录点！").withStyle(ChatFormatting.GREEN));
                    // 循环生成effectLevel次
                    int spirit_spoils= (int) player.getAttributeValue(AttributeRegistry.SPIRIT_SPOILS.get());
                    int spiritNumber =effectLevel+spirit_spoils;
                    for (int i = 0; i < spiritNumber; i++) {
                        //神圣
                        if (sacred_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.SACRED_SPIRIT.get()
                                            .getDefaultInstance());
                            // 设置物品实体默认的拾取延迟。
                            //item entity.setDefaultPickUpDelay();
                            // 为物品实体设置一个随机的移动向量，使其在生成时有一定的散开效果。
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            // 将生成的物品实体添加到游戏世界中，使其可见并可以交互。
                            level.addFreshEntity(itementity);
                        }
                        //ELDRITCH_SPIRIT
                        if (eldritch_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.ELDRITCH_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //WICKED_SPIRIT
                        if (wicked_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.WICKED_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //EARTHEN_SPIRIT
                        if (earthen_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.EARTHEN_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //INFERNAL_SPIRIT
                        if (infernal_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.INFERNAL_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //INFERNAL_SPIRIT
                        if (aerial_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.AERIAL_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //AQUEOUS_SPIRIT
                        if (aqueous_spirit.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, attacker.getX(),
                                    attacker.getY(), attacker.getZ(),
                                    ItemRegistry.AQUEOUS_SPIRIT.get()
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                        //默认-奥术ARCANE_SPIRIT
                        ItemEntity itementityA = new ItemEntity(level, attacker.getX(),
                                attacker.getY(), attacker.getZ(),
                                ItemRegistry.ARCANE_SPIRIT.get()
                                        .getDefaultInstance());
                        itementityA.setDeltaMovement(itementityA.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                        level.addFreshEntity(itementityA);
                    }
                }
        }
    }
    //神圣
    protected static final Set<EntityType<?>> sacred_spirit = Set.of(
            EntityType.CHICKEN, EntityType.PIG, EntityType.SHEEP,
            EntityType.COW, EntityType.MOOSHROOM, EntityType.RABBIT,
            EntityType.CAT, EntityType.OCELOT, EntityType.FOX,
            EntityType.BAT, EntityType.PARROT, EntityType.HORSE,
            EntityType.DONKEY, EntityType.MULE, EntityType.ZOMBIE_HORSE,
            EntityType.SKELETON_HORSE, EntityType.CAMEL, EntityType.SALMON,
            EntityType.TROPICAL_FISH, EntityType.COD, EntityType.SQUID, EntityType.GLOW_SQUID,
            EntityType.TADPOLE, EntityType.TURTLE, EntityType.ALLAY,EntityType.FROG, EntityType.AXOLOTL,
            EntityType.SNIFFER, EntityType.STRIDER, EntityType.VILLAGER,
            EntityType.WANDERING_TRADER,EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM

    );
    //邪术
    protected static final Set<EntityType<?>> wicked_spirit = Set.of(
            EntityType.ENDERMITE, EntityType.ENDER_DRAGON, EntityType.ENDERMAN
    );
    //邪恶
    protected static final Set<EntityType<?>> eldritch_spirit = Set.of(
            EntityType.ZOMBIE, EntityType.DROWNED, EntityType.HUSK,
            EntityType.SKELETON, EntityType.WITHER_SKELETON, EntityType.STRAY,
            EntityType.CREEPER, EntityType.SLIME, EntityType.GUARDIAN,
            EntityType.ELDER_GUARDIAN, EntityType.WARDEN, EntityType.SILVERFISH,
            EntityType.PHANTOM, EntityType.VEX, EntityType.WITCH,
            EntityType.ZOMBIE_VILLAGER, EntityType.PILLAGER, EntityType.EVOKER,
            EntityType.VINDICATOR, EntityType.RAVAGER, EntityType.HOGLIN,
            EntityType.ZOGLIN, EntityType.PIGLIN_BRUTE, EntityType.GHAST,
            EntityType.BLAZE, EntityType.MAGMA_CUBE, EntityType.ENDERMITE,
            EntityType.ZOMBIFIED_PIGLIN,
            EntityType.SHULKER,EntityType.ENDER_DRAGON,EntityType.WITHER
    );
    //大地
    protected static final Set<EntityType<?>> earthen_spirit = Set.of(
            EntityType.ZOMBIE, EntityType.HUSK,
            EntityType.SKELETON, EntityType.WITHER_SKELETON, EntityType.STRAY,
            EntityType.CREEPER, EntityType.SLIME, EntityType.WARDEN, EntityType.SILVERFISH,
            EntityType.WITCH,
            EntityType.ZOMBIE_VILLAGER, EntityType.PILLAGER, EntityType.EVOKER,
            EntityType.VINDICATOR, EntityType.RAVAGER, EntityType.HOGLIN,
            EntityType.ZOGLIN, EntityType.PIGLIN_BRUTE, EntityType.MAGMA_CUBE, EntityType.ENDERMITE,
            EntityType.SHULKER,

            EntityType.CHICKEN, EntityType.PIG, EntityType.SHEEP,
            EntityType.COW, EntityType.MOOSHROOM, EntityType.RABBIT,
            EntityType.CAT, EntityType.OCELOT, EntityType.FOX,
            EntityType.HORSE,EntityType.FROG,
            EntityType.DONKEY, EntityType.MULE, EntityType.ZOMBIE_HORSE,
            EntityType.SKELETON_HORSE, EntityType.CAMEL,
            EntityType.SNIFFER, EntityType.VILLAGER,EntityType.ZOMBIFIED_PIGLIN,
            EntityType.WANDERING_TRADER,EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM
    );
    //狱火
    protected static final Set<EntityType<?>> infernal_spirit = Set.of(
            EntityType.BLAZE, EntityType.GHAST, EntityType.MAGMA_CUBE,
            EntityType.PIGLIN_BRUTE, EntityType.PIGLIN, EntityType.STRIDER,
            EntityType.HOGLIN, EntityType.ZOMBIFIED_PIGLIN, EntityType.ZOGLIN
    );
    //澄空
    protected static final Set<EntityType<?>> aerial_spirit = Set.of(
            EntityType.BAT, EntityType.SPIDER, EntityType.CAVE_SPIDER,
            EntityType.HORSE, EntityType.SKELETON_HORSE, EntityType.ZOMBIE_HORSE,
            EntityType.ALLAY, EntityType.VEX
    );
    //碧水
    protected static final Set<EntityType<?>> aqueous_spirit = Set.of(
            EntityType.DROWNED, EntityType.SQUID, EntityType.GLOW_SQUID,
            EntityType.POLAR_BEAR, EntityType.DOLPHIN,
            EntityType.AXOLOTL, EntityType.FROG, EntityType.TADPOLE, EntityType.TURTLE,
            EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.COD
    );
}
