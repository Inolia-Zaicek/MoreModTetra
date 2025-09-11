package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.github.elenterius.biomancy.init.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import java.util.Set;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FoodAcquisition {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(foodAcquisitionEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                foodAcquisitionName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(foodAcquisitionTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null&&event.getEntity().getLastAttacker()!=null) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> drops= event.getDrops();
            Entity attacker = event.getEntity().getLastAttacker();
            if (mob instanceof Player||event.getEntity().getLastAttacker()==null) {
                return;
            }
            if(attacker!=null) {
                Level level = attacker.level();
                //获取实体tag
                EntityType<?> entityType = mob.getType();
                if (!(attacker instanceof Player player)) {
                    return;
                }
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, foodAcquisitionEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, foodAcquisitionEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //player.sendSystemMessage(Component.literal("传送至记录点！").withStyle(ChatFormatting.GREEN));
                    // 循环生成effectLevel次
                    for (int i = 0; i < effectLevel; i++) {
                        if (COW_MOB.contains(entityType)||MOOSHROOM_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.BEEF
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (PIG_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.PORKCHOP
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (SHEEP_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.MUTTON
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (RABBIT_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.RABBIT
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //兔子腿
                        if (RABBIT_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.RABBIT_FOOT
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        //兔子皮
                        if (RABBIT_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.RABBIT_HIDE
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (CHICKEN_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.CHICKEN
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (CHICKEN_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.EGG
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (CHICKEN_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.FEATHER
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (SALMON_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.SALMON
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (TROPICAL_FISH_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.TROPICAL_FISH
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (COD_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.COD
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (PUFFERFISH_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.PUFFERFISH
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                        if (COW_MOB.contains(entityType)||MOOSHROOM_MOB.contains(entityType)) {
                            ItemEntity itementity = new ItemEntity(level, mob.getX(),
                                    mob.getY(), mob.getZ(),
                                    Items.LEATHER
                                            .getDefaultInstance());
                            drops.add(itementity);
                        }
                    }
                }
            }
        }
    }
    protected static final Set<EntityType<?>> COW_MOB = Set.of(EntityType.COW);
    protected static final Set<EntityType<?>> PIG_MOB = Set.of(EntityType.PIG);
    protected static final Set<EntityType<?>> SHEEP_MOB = Set.of(EntityType.SHEEP);
    protected static final Set<EntityType<?>> RABBIT_MOB = Set.of(EntityType.RABBIT);
    protected static final Set<EntityType<?>> MOOSHROOM_MOB = Set.of(EntityType.MOOSHROOM);
    protected static final Set<EntityType<?>> CHICKEN_MOB = Set.of(EntityType.CHICKEN);
    protected static final Set<EntityType<?>> SALMON_MOB = Set.of(EntityType.SALMON);
    protected static final Set<EntityType<?>> TROPICAL_FISH_MOB = Set.of(EntityType.TROPICAL_FISH);
    protected static final Set<EntityType<?>> COD_MOB = Set.of(EntityType.COD);
    protected static final Set<EntityType<?>> PUFFERFISH_MOB = Set.of(EntityType.PUFFERFISH);
}
