package com.inolia_zaicek.more_mod_tetra.ModularCurios; // 定义该类所属的包，表示它是“More Mod Tetra”模组中“Modular Curios”部分的一部分。

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriousHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTTargetMode;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.NotNull;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.module.ItemModule;
import se.mickelus.tetra.module.ItemUpgradeRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosHasteEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosProjectileTrackingEffect;

@SuppressWarnings({"all", "removal"})
public class ModularRing extends ModularItem implements  ICurioItem { // 声明一个名为Modularring的公共类，它继承自ModularItem并实现ICurio接口。
    //部件类型/槽位——[slot]
    public final static String mmt_ringBase_ring = "mmt_ring/base_ring";
    public final static String mmt_ringInlay = "mmt_ring/inlay";
    //public final static String mmt_ringAttachment = "mmt_ring/attachment";
    // --- 物品标识符 ---
    /*** 模块化项链在模组内的唯一标识符。* 这个标识符用于在NBT数据、配方和其他模组交互中引用该物品。*/
    public final static String identifier = "modular_mmt_ring";
    // --- GUI 偏移量定义 ---// 这些常量定义了在GUI界面中，主模块和次要模块的显示位置偏移量。// 这使得Tetra的GUI系统能够正确地将模块放置在物品的UI表示上。
    /*** 定义了主模块（Major Modules）在GUI中的偏移量。* 负数表示向左或向上偏移。
     * * -13: X轴偏移 - 向左偏移13个单位。* -1: Y轴偏移 - 向上偏移1个单位。* 3: Z轴偏移 (通常用于3D模型或某些UI元素) - 向右（或特定方向）偏移3个单位。* 19: 另一个Z轴偏移量 - 向右（或特定方向）偏移19个单位。*/
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(-13, -1, 3, 19);
    /** 定义了次要模块（Minor Modules）在GUI中的偏移量。
     * 6: X轴偏移 - 向右偏移6个单位。* 1: Y轴偏移 - 向下偏移1个单位。*/
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(6, 1);
    // --- 物品实例 ---
    /*** 使用ObjectHolder注解，使得Forge在加载时能够自动获取已注册的“tetra:modular_mmt_ring”物品实例。
     * 这允许其他模组或代码直接通过`Modularring.instance`来访问这个物品，而无需手动注册和查找。
     * `registryName = "item"` 指定了要查找的注册表类型是物品。
     * `value = "tetra:modular_mmt_ring"` 指定了该物品在物品注册表中的名称。
     * 注意：此字段在编译时和运行时可能存在依赖关系，确保“tetra:modular_mmt_ring”已正确注册。*/
    //modular_mmt_ring算是类似“单头工具”、“双头工具”的物品必备ID
    @ObjectHolder(
            registryName = "item",
            value = "tetra:modular_mmt_ring"
    )
    public static ModularRing instance;
    /*** Modularring 类的构造函数。* 这里初始化了该模块化物品的基本属性。*/
    public ModularRing() {
        // 调用父类（ModularItem）的构造函数，并设置物品的基本属性：// new Item.Properties(): 创建物品属性对象。// .stacksTo(1): 设置该物品堆叠上限为1，表示项链是独立的、不可堆叠的物品。// .fireResistant(): 使该物品具有防火属性，在火焰中不会被烧毁。
        super(new Properties().stacksTo(1).fireResistant());
        // 注册事件监听器：在实体加入到世界时触发addMode方法
        MinecraftForge.EVENT_BUS.addListener(ModularRing::addMode);
        //可否打磨
        canHone = false;
        //设置主要部件有什么
        majorModuleKeys = new String[]{mmt_ringBase_ring, mmt_ringInlay};
        //设置次要部件有什么
        minorModuleKeys = new String[]{
                //mmt_ringAttachment
                };
        // 定义该项链所必需的模块（Required Modules）。游戏会确保这些模块至少存在一个，否则物品可能无法正常工作或显示。
        requiredModules = new String[]{mmt_ringBase_ring, mmt_ringInlay};
    }
    /*** 获取该模块化物品所有已安装的模块。** @param stack 当前物品的ItemStack。* @return 包含所有已安装模块的Collection。*/
    //不用动他
    @Override // 覆盖父类ModularItem的方法。
    public Collection<ItemModule> getAllModules(ItemStack stack) {
        // 获取物品的NBT数据。NBT数据用于持久化存储物品的自定义信息，包括所安装的模块。
        CompoundTag stackTag = stack.getTag();
        // 检查NBT数据是否存在，如果不存在（例如物品刚创建时），则返回空列表。
        if (stackTag != null) {
            // 使用Stream API来处理模块。
            return Stream.concat( // 合并两个Stream：主要模块的Stream和次要模块的Stream。
                            Arrays.stream(getMajorModuleKeys(stack)), // 获取该物品定义的主要模块键。
                            Arrays.stream(getMinorModuleKeys(stack)) // 获取该物品定义的次要模块键。
                    )
                    // 从NBT数据中获取每个模块键对应的字符串值（模块的注册名）。
                    .map(stackTag::getString)
                    // 使用ItemUpgradeRegistry来查找对应的ItemModule对象。
                    // ItemUpgradeRegistry.instance::getModule 是一个方法引用，相当于 lambda: moduleKey -> ItemUpgradeRegistry.instance.getModule(moduleKey)。
                    .map(ItemUpgradeRegistry.instance::getModule)
                    // 过滤掉那些未能成功加载的模块（即registry.getModule返回null的情况）。
                    .filter(Objects::nonNull)
                    // 将过滤后的所有模块收集到一个List中。
                    .collect(Collectors.toList());
        }

        // 如果NBT数据为空，则返回一个空的不可变列表。
        return Collections.emptyList();
    }

    /** 在客户端获取主模块在GUI中的偏移量。* OnlyIn(Dist.CLIENT) 注解表明这个方法只会在客户端运行。* @param itemStack 当前 ItemStack。* @return 主模块的GUI偏移量。*/
    @OnlyIn(Dist.CLIENT) // 标记此方法仅在客户端环境执行。
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        // 返回预先定义的majorOffsets，用于在GUI中定位主模块。
        return majorOffsets;
    }
    /*** 在客户端获取次要模块在GUI中的偏移量。* OnlyIn(Dist.CLIENT) 注解表明这个方法只会在客户端运行。** @param itemStack 当前 ItemStack。* @return 次要模块的GUI偏移量。*/
    @OnlyIn(Dist.CLIENT) // 标记此方法仅在客户端执行。
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        // 返回预先定义的minorOffsets，用于在GUI中定位次要模块。
        return minorOffsets;
    }
    //这个部分可以让饰品栏的Attribute生效
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> result = ArrayListMultimap.create();
        if (!this.isBroken(stack)) {
            Multimap<Attribute, AttributeModifier> Tetra = this.getAttributeModifiersCached(stack);
            result.putAll(Tetra);
        }
        return MMTCuriousHelper.Curios$fixIdentifiers(slotContext,result);
    }

    // 判断是否允许从用此物品的槽里直接装备（即是否可以在没有Shift键的情况下装备）————不能
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return !slotContext.entity().isShiftKeyDown();
    }
    // 使用此物品（右键点击），切换显示模式
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pPlayer.isShiftKeyDown() && !pLevel.isClientSide) {
            // 按住Shift时，切换AngelSight的“模式”，将模式存储在玩家的PersistentData里
            // 以前存储在ItemStack的NBT中，现在改为存储在玩家的PersistentData
            CompoundTag playerData = pPlayer.getPersistentData(); // 获取玩家的持久化数据
            int mode = playerData.getInt(Tracking_Mode); // 读取存储的模式（默认0）
            mode = (mode + 1) % 3; // 模式轮换：0,1,2
            playerData.putInt(Tracking_Mode, mode); // 保存更新后的模式到玩家数据
            pPlayer.sendSystemMessage(Component.translatable("eidolon.angels_sight.mode." + mode));
            return InteractionResultHolder.success(ItemStack.EMPTY); // 不用返回物品，避免影响
        } else {
            return super.use(pLevel, pPlayer, pUsedHand);
        }
    }

    private static final String Tracking_Mode = MoreModTetra.MODID + ":tracking_mode_nbt";

    // 在物品提示里显示当前模式（读取玩家的PersistentData）
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        // 获取玩家的PersistentData（需要传入玩家对象，这里示例假设获取到对应玩家，实际应用中应在调用时传入玩家对象）
        // 因为这个方法不带玩家参数，实际使用时你可能需要在调用时传入玩家对象
        // 示例：假设当前在某个上下文中能获取玩家对象，比如在事件中
        // 这里简化处理：假设有个全局或已知的玩家引用player（需要你调整调用方式）
        Player player = Minecraft.getInstance().player; // 你需要自己传入或定义这个方法获取当前玩家
        if (player == null) return;

        CompoundTag playerData = player.getPersistentData();
        int mode = playerData.getInt(Tracking_Mode); // 获取玩家当前的模式

        String modeDescription;
        switch (mode) {
            case 1 -> modeDescription = "lore.eidolon.angels_sight.mode.1";
            case 2 -> modeDescription = "lore.eidolon.angels_sight.mode.2";
            default -> modeDescription = "lore.eidolon.angels_sight.mode.3";
        }
        tooltip.add(Component.translatable(modeDescription).withStyle(ChatFormatting.DARK_GRAY));
    }
    // 关键部分：当实体加入到某个层级（世界）时触发
    @SubscribeEvent
    public static void addMode(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Projectile projectile) {
            // 如果实体是投射物（如箭），尝试找到它的发射者
            Entity owner = projectile.getOwner();
            if (owner instanceof Player player &&
                    // 对追踪词条进行判断（这里假设你已有验证逻辑）
                    MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosProjectileTrackingEffect) > 0) {
                // 获取玩家存储的追踪模式（已修改存储在玩家PersistentData中）
                // 你需要传入正确的玩家对象，这里假设已获取到player
                CompoundTag playerData = player.getPersistentData();
                int mode = playerData.getInt(Tracking_Mode);

                // 根据玩家的模式设置目标筛选规则
                Predicate targetPredicate;
                switch (mode) {
                    case 1 -> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player);
                    case 2 -> targetPredicate = (target) -> target instanceof Enemy;
                    default -> targetPredicate = (target) -> target instanceof LivingEntity;
                }

                Predicate<Entity> targetMode = targetPredicate;

                // 如果投射物支持自身追踪目标（实现TargetMode接口）
                if (projectile instanceof MMTTargetMode modeObj) {
                    // 设置投射物的目标筛选规则，使其“追踪”符合规则的目标
                    modeObj.eidolonrepraised$setMode(targetMode);
                }
            }
        }
    }
}