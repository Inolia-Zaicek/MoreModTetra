package com.inolia_zaicek.more_mod_tetra.ModularCurios; // 定义该类所属的包，表示它是“More Mod Tetra”模组中“Modular Curios”部分的一部分。

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag; // 引入用于处理物品NBT（命名二进制标签）数据的类，这对于保存和加载物品的自定义数据非常重要。
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item; // 引入Minecraft物品的基本类。
import net.minecraft.world.item.ItemStack; // 引入Minecraft物品堆的类，表示一个或多个相同物品的集合。
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.api.distmarker.Dist; // 引入Forge的Dist类，用于区分客户端（Client）和服务器（Server）环境。
import net.minecraftforge.api.distmarker.OnlyIn; // 这是一个注解，用于标记只在特定环境（例如客户端）中才会被使用的代码。
import net.minecraftforge.registries.ObjectHolder; // 引入Forge的ObjectHolder注解，用于在运行时直接获取已注册的物品或块等对象。
import se.mickelus.tetra.gui.GuiModuleOffsets; // 引入Tetra模组提供的用于GUI模块定位的类。
import se.mickelus.tetra.items.modular.ModularItem; // 引入Tetra模组提供的模块化物品的基类。
import se.mickelus.tetra.module.ItemModule; // 引入Tetra模组提供的物品模块的基类。
import se.mickelus.tetra.module.ItemUpgradeRegistry; // 引入Tetra模组提供的物品升级（模块）注册表。
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio; // 引入Curios模组提供的ICurio接口，表示这是一个可以被Curios模组识别和管理的饰品。
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;
import java.util.stream.Collectors; // 引入用于收集流元素的工具类。
import java.util.stream.Stream; // 引入Stream API，用于进行序列化数据处理。

public class ModularNecklace extends ModularItem implements  ICurioItem { // 声明一个名为ModularNecklace的公共类，它继承自ModularItem并实现ICurio接口。
    //部件类型/槽位——[slot]
    public final static String mmt_necklaceChain = "mmt_necklace/chain";
    public final static String mmt_necklacePendant = "mmt_necklace/pendant";
    //public final static String mmt_necklaceAttachment = "mmt_necklace/attachment";
    // --- 物品标识符 ---
    /*** 模块化项链在模组内的唯一标识符。* 这个标识符用于在NBT数据、配方和其他模组交互中引用该物品。*/
    public final static String identifier = "modular_mmt_necklace";
    // --- GUI 偏移量定义 ---// 这些常量定义了在GUI界面中，主模块和次要模块的显示位置偏移量。// 这使得Tetra的GUI系统能够正确地将模块放置在物品的UI表示上。
    /*** 定义了主模块（Major Modules）在GUI中的偏移量。* 负数表示向左或向上偏移。
     * * -13: X轴偏移 - 向左偏移13个单位。* -1: Y轴偏移 - 向上偏移1个单位。* 3: Z轴偏移 (通常用于3D模型或某些UI元素) - 向右（或特定方向）偏移3个单位。* 19: 另一个Z轴偏移量 - 向右（或特定方向）偏移19个单位。*/
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(-13, -1, 3, 19);
    /** 定义了次要模块（Minor Modules）在GUI中的偏移量。
     * 6: X轴偏移 - 向右偏移6个单位。* 1: Y轴偏移 - 向下偏移1个单位。*/
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(6, 1);
    // --- 物品实例 ---
    /*** 使用ObjectHolder注解，使得Forge在加载时能够自动获取已注册的“tetra:modular_mmt_necklace”物品实例。
     * 这允许其他模组或代码直接通过`ModularNecklace.instance`来访问这个物品，而无需手动注册和查找。
     * `registryName = "item"` 指定了要查找的注册表类型是物品。
     * `value = "tetra:modular_mmt_necklace"` 指定了该物品在物品注册表中的名称。
     * 注意：此字段在编译时和运行时可能存在依赖关系，确保“tetra:modular_mmt_necklace”已正确注册。*/
    //modular_mmt_necklace算是类似“单头工具”、“双头工具”的物品必备ID
    @ObjectHolder(
            registryName = "item",
            value = "tetra:modular_mmt_necklace"
    )
    public static ModularNecklace instance;
    /*** ModularNecklace 类的构造函数。* 这里初始化了该模块化物品的基本属性。*/
    public ModularNecklace() {
        // 调用父类（ModularItem）的构造函数，并设置物品的基本属性：// new Item.Properties(): 创建物品属性对象。// .stacksTo(1): 设置该物品堆叠上限为1，表示项链是独立的、不可堆叠的物品。// .fireResistant(): 使该物品具有防火属性，在火焰中不会被烧毁。
        super(new Item.Properties().stacksTo(1).fireResistant());
        //可否打磨
        canHone = false;
        //设置主要部件有什么
        majorModuleKeys = new String[]{mmt_necklaceChain, mmt_necklacePendant};
        //设置次要部件有什么
        minorModuleKeys = new String[]{
                //mmt_necklaceAttachment
                };
        // 定义该项链所必需的模块（Required Modules）。游戏会确保这些模块至少存在一个，否则物品可能无法正常工作或显示。
        requiredModules = new String[]{mmt_necklaceChain, mmt_necklacePendant};
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
        return result;
    }
}