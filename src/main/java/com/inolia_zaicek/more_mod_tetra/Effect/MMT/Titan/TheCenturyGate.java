package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class TheCenturyGate {
    // 这里的 ITEM_HANDLER_CAPABILITY 是 Forge 提供的标准能力，用于识别和访问实现了 IItemHandler 接口的块实体（如箱子）。
    public static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = ForgeCapabilities.ITEM_HANDLER;

    // 定义了用于存储绑定位置 X 坐标的 NBT 标签的 ResourceLocation。
    // 使用 ResourceLocation 可以确保在多模组环境中标签的唯一性，避免命名冲突。
    private static final ResourceLocation X = new ResourceLocation(MoreModTetra.MODID, "global_traveller_x");
    // 定义了用于存储绑定位置 Y 坐标的 NBT 标签的 ResourceLocation。
    private static final ResourceLocation Y = new ResourceLocation(MoreModTetra.MODID, "global_traveller_y");
    // 定义了用于存储绑定位置 Z 坐标的 NBT 标签的 ResourceLocation。
    private static final ResourceLocation Z = new ResourceLocation(MoreModTetra.MODID, "global_traveller_z");
    // 定义了用于存储绑定维度（Dimension）的 NBT 标签的 ResourceLocation。
    private static final ResourceLocation WORLD = new ResourceLocation(MoreModTetra.MODID, "global_traveller_dimension");
    //新建nbt
    public static ResourceLocation theCenturyGateNbt = MoreModTetra.getResource("global_traveler");

    // 定义了一个翻译组件，用于显示绑定的位置信息。
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(theCenturyGateEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                theCenturyGateName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(theCenturyGateTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickBlock event) {
        LivingEntity entity = event.getEntity();
        // 检查实体是否是玩家
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;
        //判断tetra工具
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            if (!mainHandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))
                    && !offhandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))) {
                //主手判断词条等级
                float mainEffectLevel = item.getEffectLevel(mainHandItem, theCenturyGateEffect);
                //当前耐久
                int currentDamage = mainHandItem.getMaxDamage() - mainHandItem.getDamageValue();
                if (mainEffectLevel > 0 && currentDamage > 0 && player.isShiftKeyDown() && !(player.getCooldowns().isOnCooldown((Item) item))) {
                    Level level = event.getLevel();
                    BlockPos pos = event.getPos();
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    BlockEntity blockEntity = level.getBlockEntity(pos); // 尝试获取被点击方块的块实体

                    // 检查被点击的方块是否拥有物品处理能力 (IItemHandler)
                    if (blockEntity != null && blockEntity.getCapability(ITEM_HANDLER_CAPABILITY).isPresent()) {
                        //NBT
                        CompoundTag persistentData = mainHandItem.getOrCreateTag();
                        if (mainHandItem.getOrCreateTag().getInt(String.valueOf(X)) == pos.getX() &&
                                persistentData.getInt(String.valueOf(Y)) == pos.getY() &&
                                persistentData.getInt(String.valueOf(Z)) == pos.getZ() &&
                                persistentData.getString(String.valueOf(WORLD)).equals(level.dimension().location().getPath())) {
                            persistentData.remove(String.valueOf(X));
                            persistentData.remove(String.valueOf(Y));
                            persistentData.remove(String.valueOf(Z));
                            persistentData.remove(String.valueOf(WORLD));
                            player.sendSystemMessage(Component.literal("与容器解绑").withStyle(ChatFormatting.RED));
                        } else {
                            persistentData.putInt(String.valueOf(X), pos.getX());
                            persistentData.putInt(String.valueOf(Y), pos.getY());
                            persistentData.putInt(String.valueOf(Z), pos.getZ());
                            persistentData.putString(String.valueOf(WORLD), level.dimension().location().getPath());
                            player.sendSystemMessage(Component.literal("绑定容器").withStyle(ChatFormatting.YELLOW));
                        }
                    }
                    player.getCooldowns().addCooldown((Item) item, 20);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void drop(LivingDropsEvent event) {
        if (event.getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker ) {
            Entity mob = event.getEntity();
            Collection<ItemEntity> itemEntities = event.getDrops();
            List<ItemStack> generatedLoot = new ArrayList<>();
            for (ItemEntity itemEntity : itemEntities) {
                // ItemEntity.getItem() 方法返回 ItemStack
                generatedLoot.add(itemEntity.getItem());
            }
                ItemStack mainHandItem = attacker.getMainHandItem();
                ItemStack offhandItem = attacker.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    if (!mainHandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))
                            && !offhandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))) {
                        //主手判断词条等级
                        float mainEffectLevel = item.getEffectLevel(mainHandItem, theCenturyGateEffect);
                        //当前耐久
                        int currentDamage = mainHandItem.getMaxDamage() - mainHandItem.getDamageValue();
                        if (mainEffectLevel > 0 && currentDamage > 0) {
                            // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                            String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(theCenturyGateNbt));
                            //NBT
                            CompoundTag persistentData = mainHandItem.getOrCreateTag();
                            // 构建绑定的方块坐标
                            BlockPos pos = new BlockPos(persistentData.getInt(String.valueOf(X)), persistentData.getInt(String.valueOf(Y)), persistentData.getInt(String.valueOf(Z)));
                            // 获取绑定的 Dimension 的 ResourceLocation
                            ResourceLocation dimensionLocation = new ResourceLocation(persistentData.getString(String.valueOf(WORLD)));
                            // 根据 Dimension ResourceKey 获取目标 ServerLevel
                            // Registries.DIMENSION 是 Minecraft 1.18+ 的推荐方式来创建 Dimension Key
                            ServerLevel level = mob.level().getServer().getLevel(ResourceKey.create(Registries.DIMENSION, dimensionLocation));
                            // 如果成功获取到目标 ServerLevel
                            if (level != null) {
                                // 获取目标方块的块实体
                                BlockEntity block = level.getBlockEntity(pos);
                                // 如果块实体存在
                                if (block != null) {
                                    // 尝试获取块实体的物品处理能力 (IItemHandler)
                                    IItemHandler inventory = block.getCapability(ITEM_HANDLER_CAPABILITY).orElse(null);
                                    // 如果成功获取到物品处理能力
                                    if (inventory != null) {
                                        Iterator<ItemStack> iterator = generatedLoot.iterator(); // 创建一个迭代器来遍历生成的战利品
                                        List<ItemStack> leftover = new ArrayList<>(); // 创建一个列表来存放未成功插入的物品
                                        // 遍历所有生成的战利品
                                        while (iterator.hasNext()) {
                                            ItemStack stack = iterator.next(); // 获取当前物品
                                            // 尝试将物品插入到绑定的物品栏中
                                            // insertItemStacked 方法会尝试插入物品，如果物品栏已满，则返回剩余未插入的物品
                                            // false 表示不强制插入，即如果无法插入则返回剩余物品
                                            ItemStack remainder = ItemHandlerHelper.insertItemStacked(inventory, stack, false);
                                            // 如果有剩余的物品（即未能完全插入）
                                            if (!remainder.isEmpty()) {
                                                leftover.add(remainder); // 将剩余物品添加到 leftover 列表中
                                            }
                                        }
                                        // 清空原始的 generatedLoot 列表
                                        generatedLoot.clear();
                                        // 将所有剩余的（未能插入的）物品重新添加回 generatedLoot 列表
                                        // 这样，未能插入的物品会正常作为掉落物出现
                                        generatedLoot.addAll(leftover);
                                        event.getDrops().clear();
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }
    /*
    未能实现的方块部分
    @SubscribeEvent
    public static void event(BlockEvent.BreakEvent event) {
        ServerLevel level = (ServerLevel) event.getLevel();
        Player player = event.getPlayer();
        BlockPos blockPos = event.getPos();
        BlockState state = level.getBlockState(blockPos);
        Block block = state.getBlock();
        BlockEntity blockEntity = level.getBlockEntity(blockPos); // 尝试获取被点击方块的块实体
        Entity mob = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        //获取掉落列表
        List<ItemStack> generatedLoot = net.minecraft.world.level.block.Block.getDrops(state, level, blockPos, blockEntity, player, mainHandItem);
        ItemStack offhandItem = player.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            if (!mainHandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))
                    && !offhandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))) {
                //主手判断词条等级
                float mainEffectLevel = item.getEffectLevel(mainHandItem, theCenturyGateEffect);
                //当前耐久
                int currentDamage = mainHandItem.getMaxDamage() - mainHandItem.getDamageValue();
                if (mainEffectLevel > 0 && currentDamage > 0) {
                    // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                    String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(theCenturyGateNbt));
                    //NBT
                    CompoundTag persistentData = mainHandItem.getOrCreateTag();
                    // 构建绑定的方块坐标
                    BlockPos pos = new BlockPos(persistentData.getInt(String.valueOf(X)), persistentData.getInt(String.valueOf(Y)), persistentData.getInt(String.valueOf(Z)));
                    // 获取绑定的 Dimension 的 ResourceLocation
                    ResourceLocation dimensionLocation = new ResourceLocation(persistentData.getString(String.valueOf(WORLD)));
                    // 根据 Dimension ResourceKey 获取目标 ServerLevel
                    // Registries.DIMENSION 是 Minecraft 1.18+ 的推荐方式来创建 Dimension Key
                    ServerLevel serverLevel = mob.level().getServer().getLevel(ResourceKey.create(Registries.DIMENSION, dimensionLocation));
                    // 如果成功获取到目标 ServerLevel
                    if (serverLevel != null) {
                        // 获取目标方块的块实体
                        BlockEntity blockEntity1 = level.getBlockEntity(pos);
                        // 如果块实体存在
                        if (blockEntity1 != null) {
                            // 尝试获取块实体的物品处理能力 (IItemHandler)
                            IItemHandler inventory = blockEntity1.getCapability(ITEM_HANDLER_CAPABILITY).orElse(null);
                            // 如果成功获取到物品处理能力
                            if (inventory != null) {
                                Iterator<ItemStack> iterator = generatedLoot.iterator(); // 创建一个迭代器来遍历生成的战利品
                                List<ItemStack> leftover = new ArrayList<>(); // 创建一个列表来存放未成功插入的物品
                                // 遍历所有生成的战利品
                                while (iterator.hasNext()) {
                                    ItemStack stack = iterator.next(); // 获取当前物品
                                    // 尝试将物品插入到绑定的物品栏中
                                    // insertItemStacked 方法会尝试插入物品，如果物品栏已满，则返回剩余未插入的物品
                                    // false 表示不强制插入，即如果无法插入则返回剩余物品
                                    ItemStack remainder = ItemHandlerHelper.insertItemStacked(inventory, stack, false);
                                    // 如果有剩余的物品（即未能完全插入）
                                    if (!remainder.isEmpty()) {
                                        leftover.add(remainder); // 将剩余物品添加到 leftover 列表中
                                    }
                                }
                                // 清空原始的 generatedLoot 列表
                                generatedLoot.clear();
                                // 将所有剩余的（未能插入的）物品重新添加回 generatedLoot 列表
                                // 这样，未能插入的物品会正常作为掉落物出现
                                generatedLoot.addAll(leftover);
                                generatedLoot.clear();
                            }
                        }
                    }
                }
            }
        }
    }

     */
}