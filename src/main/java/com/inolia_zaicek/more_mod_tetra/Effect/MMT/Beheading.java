package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.mojang.authlib.GameProfile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Beheading {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(beheadingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                beheadingName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(beheadingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public void onLivingDropEvent(LivingDropsEvent event) {
        LivingEntity target = event.getEntity();
        Entity attackingEntity = event.getSource().getEntity();
        if (attackingEntity instanceof LivingEntity attacker) {
                ItemStack mainHandItem = attacker.getMainHandItem();
                ItemStack offhandItem = attacker.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, beheadingEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, beheadingEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    float chance = (float) effectLevel / 100;
                    ItemStack headDrop = ItemStack.EMPTY;
                    if (target instanceof Zombie) {
                        headDrop = new ItemStack(Items.ZOMBIE_HEAD);
                    } else if (target instanceof Creeper) {
                        headDrop = new ItemStack(Items.CREEPER_HEAD);
                    } else if (target instanceof Skeleton) {
                        headDrop = new ItemStack(Items.SKELETON_SKULL);
                    } else if (!(target instanceof WitherSkeleton) && !(target instanceof WitherBoss)) {
                        if (target instanceof EnderDragon) {
                            headDrop = new ItemStack(Items.DRAGON_HEAD);
                        } else if (target instanceof Player) {
                            Player player = (Player)target;
                            headDrop = new ItemStack(Items.PLAYER_HEAD);
                            GameProfile profile = player.getGameProfile();
                            headDrop.getOrCreateTag().put("SkullOwner", NbtUtils.writeGameProfile(new CompoundTag(), profile));
                        }
                    } else {
                        headDrop = new ItemStack(Items.WITHER_SKELETON_SKULL);
                    }

                    if (!headDrop.isEmpty()) {
                        boolean drop = target.level().random.nextFloat() < chance;
                        if (drop) {
                            ItemEntity itemDrop = new ItemEntity(target.level(), target.getX(), target.getY(), target.getZ(), headDrop);
                            itemDrop.setDefaultPickUpDelay();
                            event.getDrops().add(itemDrop);
                        }
                    }
                }
            }
        }
}
