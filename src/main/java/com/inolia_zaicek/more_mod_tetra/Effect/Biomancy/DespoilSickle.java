package com.inolia_zaicek.more_mod_tetra.Effect.Biomancy;

import com.github.elenterius.biomancy.init.ModItems;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
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
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Collection;
import java.util.Set;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class DespoilSickle {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(despoilSickleEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                despoilSickleName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(despoilSickleTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker ) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> drops= event.getDrops();
            if (mob instanceof Player) {
                return;
            }
            if(attacker!=null) {
                Level level = attacker.level();
                //获取实体tag
                EntityType<?> entityType = mob.getType();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker,despoilSickleEffect);
                if (effectLevel > 0) {
                    //livingEntity.sendSystemMessage(Component.literal("传送至记录点！").withStyle(ChatFormatting.GREEN));
                    // 循环生成effectLevel次
                    for (int i = 0; i < effectLevel * 3; i++) {
                        //神圣
                        if (BONE_MARROW_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.MOB_MARROW.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //ELDRITCH_SPIRIT
                        if (WITHERED_BONE_MARROW_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.WITHERED_MOB_MARROW.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //WICKED_SPIRIT
                        if (TOXIC_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.TOXIN_GLAND.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //EARTHEN_SPIRIT
                        if (VOLATILE_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.VOLATILE_GLAND.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //INFERNAL_SPIRIT
                        if (!(INVALID_MOBS_FOR_MEATY_LOOT.contains(entityType))) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.GENERIC_MOB_GLAND.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //INFERNAL_SPIRIT
                        if (!(INVALID_MOBS_FOR_MEATY_LOOT.contains(entityType))) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.MOB_SINEW.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //AQUEOUS_SPIRIT
                        if (SHARP_CLAW_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.MOB_CLAW.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (SHARP_FANG_MOBS.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    ModItems.MOB_FANG.get()
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                    }
                }
            }
        }
    }
    //掉骨髓的
    protected static final Set<EntityType<?>> BONE_MARROW_MOBS = Set.of(
            EntityType.SKELETON_HORSE, EntityType.SKELETON, EntityType.STRAY,
            EntityType.WARDEN
    );
    //掉凋灵骨髓的
    protected static final Set<EntityType<?>> WITHERED_BONE_MARROW_MOBS = Set.of(
            EntityType.WITHER_SKELETON, EntityType.WITHER
    );
    //掉毒腺的
    protected static final Set<EntityType<?>> TOXIC_MOBS = Set.of(
            EntityType.CAVE_SPIDER,
            EntityType.PUFFERFISH,
            EntityType.BEE
    );
    //挥发物
    protected static final Set<EntityType<?>> VOLATILE_MOBS = Set.of(
            EntityType.CREEPER,
            EntityType.GHAST, EntityType.BLAZE,
            EntityType.WITHER, EntityType.ENDER_DRAGON
    );
    //有爪子的
    protected static final Set<EntityType<?>> SHARP_CLAW_MOBS = Set.of(
            EntityType.BAT, EntityType.PARROT,
            EntityType.CAT, EntityType.OCELOT,
            EntityType.WOLF, EntityType.FOX,
            EntityType.POLAR_BEAR, EntityType.PANDA,
            EntityType.ENDER_DRAGON
    );
    //有牙齿
    protected static final Set<EntityType<?>> SHARP_FANG_MOBS = Set.of(
            EntityType.BAT,
            EntityType.CAT, EntityType.OCELOT,
            EntityType.WOLF, EntityType.FOX,
            EntityType.POLAR_BEAR, EntityType.PANDA,
            EntityType.HOGLIN, EntityType.ZOGLIN,
            EntityType.ENDER_DRAGON
    );
    //不适用于肉类战利品的生物
    protected static final Set<EntityType<?>> INVALID_MOBS_FOR_MEATY_LOOT = Set.of(
            EntityType.SLIME, EntityType.MAGMA_CUBE,
            EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.SHULKER,
            EntityType.VEX, EntityType.GHAST, EntityType.ALLAY, EntityType.PHANTOM,
            EntityType.BLAZE,
            EntityType.HUSK, EntityType.DROWNED, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER,
            EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.WITHER,
            EntityType.WARDEN,
            EntityType.CREEPER
    );

}
