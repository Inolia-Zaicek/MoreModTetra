package com.inolia_zaicek.more_mod_tetra.Modular.Goety;

import com.Polarice3.Goety.Goety;
import com.Polarice3.Goety.api.entities.IOwned;
import com.Polarice3.Goety.api.entities.ally.IServant;
import com.Polarice3.Goety.api.items.magic.IWand;
import com.Polarice3.Goety.api.magic.*;
import com.Polarice3.Goety.common.blocks.BrewCauldronBlock;
import com.Polarice3.Goety.common.blocks.entities.ArcaBlockEntity;
import com.Polarice3.Goety.common.blocks.entities.BrewCauldronBlockEntity;
import com.Polarice3.Goety.common.entities.neutral.AbstractVine;
import com.Polarice3.Goety.common.events.spell.GoetyEventFactory;
import com.Polarice3.Goety.common.items.magic.*;
import com.Polarice3.Goety.common.network.ModNetwork;
import com.Polarice3.Goety.common.network.server.SPlayEntitySoundPacket;
import com.Polarice3.Goety.common.network.server.SPlayPlayerSoundPacket;
import com.Polarice3.Goety.config.MobsConfig;
import com.Polarice3.Goety.config.SpellConfig;
import com.Polarice3.Goety.init.ModKeybindings;
import com.Polarice3.Goety.init.ModSounds;
import com.Polarice3.Goety.init.ModTags;
import com.Polarice3.Goety.utils.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.ItemModule;
import se.mickelus.tetra.module.ItemUpgradeRegistry;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RepairSchematic;
import se.mickelus.tetra.properties.AttributeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curios_goety_soul_cost_discount_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_cost_discount_Effect;

public class ModularGoetyWandItem  extends ItemModularHandheld implements IWand {
    //部件类型/槽位——[slot]
    public final static String modular_dark_wand_core = "modular_dark_wand/core";
    public final static String modular_dark_wand_end = "modular_dark_wand/end";
    public final static String modular_dark_wand_handle = "modular_dark_wand/handle";
    public final static String modular_dark_wand_guard = "modular_dark_wand/guard";
    public final static String modular_dark_wand_socket = "modular_dark_wand/socket";
    public final static String identifier = "modular_dark_wand";
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(-20, 9, 4, 0, 4, 18, -10, -4, -10, 23, -12, 27);
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(-12, -3, -12, 27);
    public static ModularGoetyWandItem instance;
    public ModularGoetyWandItem() {
        super(new Properties().stacksTo(1).fireResistant());
        //可否打磨
        canHone = true;
        //设置主要部件有什么
        majorModuleKeys = new String[]{modular_dark_wand_core,modular_dark_wand_end,modular_dark_wand_handle,
                modular_dark_wand_guard,modular_dark_wand_socket};
        //设置次要部件有什么
        minorModuleKeys = new String[]{};
        // 定义该项链所必需的模块（Required Modules）。游戏会确保这些模块至少存在一个，否则物品可能无法正常工作或显示。
        requiredModules = new String[]{modular_dark_wand_core,modular_dark_wand_end,modular_dark_wand_handle,
                modular_dark_wand_guard};
        //可修复
        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
    }
    //连携
    @Override
    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.synergyData.getOrdered("modular_dark_wand/"));
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
    @OnlyIn(Dist.CLIENT) // 标记此方法仅在客户端环境执行。
    @Override
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        // 返回预先定义的majorOffsets，用于在GUI中定位主模块。
        return majorOffsets;
    }
    /*** 在客户端获取次要模块在GUI中的偏移量。* OnlyIn(Dist.CLIENT) 注解表明这个方法只会在客户端运行。** @param itemStack 当前 ItemStack。* @return 次要模块的GUI偏移量。*/
    @OnlyIn(Dist.CLIENT) // 标记此方法仅在客户端执行。
    @Override
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        // 返回预先定义的minorOffsets，用于在GUI中定位次要模块。
        return minorOffsets;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        if (isBroken(itemStack)) {
            return AttributeHelper.emptyMap;
        }

        if (slot == EquipmentSlot.MAINHAND) {
            return getAttributeModifiersCached(itemStack);
        }

        if (slot == EquipmentSlot.OFFHAND) {
            return getAttributeModifiersCached(itemStack).entries().stream()
                    .filter(entry -> !(entry.getKey().equals(Attributes.ATTACK_DAMAGE) || entry.getKey().equals(Attributes.ATTACK_DAMAGE)))
                    .collect(Multimaps.toMultimap(Map.Entry::getKey, Map.Entry::getValue, ArrayListMultimap::create));
        }

        return AttributeHelper.emptyMap;
    }
    /// 诡厄部分
    // 魔法杖所属的魔法类型
    public SpellType spellType;
    // 构造函数，接收Item的属性和SpellType，设置对应属性和类型

    public ModularGoetyWandItem(Item.Properties properties, SpellType spellType) {
        super(properties);
        this.spellType = spellType;
    }

    // 使用默认魔法杖属性并指定魔法类型来创建魔法杖
    public ModularGoetyWandItem(SpellType spellType) {
        this(wandProperties(), spellType);
    }

    // 获取魔法杖的魔法类型
    @Override
    public SpellType getSpellType() {
        return this.spellType;
    }

    // 静态方法，返回魔法杖的Item属性，稀有(RARE)，不可修复，不可堆叠（堆叠数量为1）
    public static Item.Properties wandProperties() {
        return (new Item.Properties()).rarity(Rarity.RARE).setNoRepair().stacksTo(1);
    }

    /**
     * 魔法杖的更新逻辑处理
     * 每个tick调用，用来更新魔法杖堆叠中的信息（如魔力消耗，施放时间等）
     * @param stack 当前魔法杖物品堆叠
     * @param worldIn 当前世界
     * @param entityIn 持有该魔法杖的实体
     * @param itemSlot 魔法杖所在物品栏槽位
     * @param isSelected 是否被选中
     */
    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        // 仅处理持有生物实体的情况
        if (entityIn instanceof LivingEntity livingEntity) {
            // 获取或创建NBT数据标签
            CompoundTag compound = stack.getOrCreateTag();
            // 如果NBT为空，初始化一些魔法杖相关变量（灵魂消耗，施法时间，冷却等）
            if (stack.getTag() == null) {
                compound.putInt("Soul Use", this.SoulUse(livingEntity, stack)); // 当前灵魂消耗
                compound.putInt("Soul Cost", 0);  // 灵魂总消耗
                compound.putInt("Cast Time", this.CastDuration(stack)); // 施法持续时间
                compound.putInt("Cool", 0); // 冷却计数
                compound.putInt("Shots", 0); // 发射次数
                compound.putInt("Seconds", 0); // 计时器秒数
            } else if (!compound.contains("Shots")) {
                // 如果没有“Shots”字段，则初始化为0
                compound.putInt("Shots", 0);
            }

            // 根据魔法杖上的法术，设置施法条件参数
            if (this.getSpell(stack) != null) {
                this.setSpellConditions(this.getSpell(stack), stack, livingEntity);
            } else {
                this.setSpellConditions((ISpell)null, stack, livingEntity);
            }

            // 再次更新灵魂消耗和施法时间
            compound.putInt("Soul Use", this.SoulUse(livingEntity, stack));
            compound.putInt("Cast Time", this.CastDuration(stack));
            // 处理魔法杖上的“聚晶”物品的 tick 事件（比如魔杖的附属物）
            if (IWand.getFocus(stack) != null) {
                IWand.getFocus(stack).inventoryTick(worldIn, entityIn, itemSlot, isSelected);
            }
        }

        // 调用父类的更新方法
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    /**
     * 魔法杖制作完成后调用，初始化NBT数据
     * @param pStack 制作好的物品
     * @param pLevel 当前世界
     * @param pPlayer 制作的玩家
     */
    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        CompoundTag compound = pStack.getOrCreateTag();
        compound.putInt("Soul Use", this.SoulUse(pPlayer, pStack)); // 初始化灵魂消耗
        compound.putInt("Soul Cost", 0); // 灵魂花费为0
        compound.putInt("Cast Time", this.CastDuration(pStack)); // 施法时间
        compound.putInt("Cool", 0);  // 冷却
        compound.putInt("Seconds", 0);
        compound.putInt("Shots", 0);
        // 初始化法术条件
        this.setSpellConditions((ISpell)null, pStack, pPlayer);
    }

    /**
     * 计算当前魔法杖施法时需要消耗的灵魂数量
     * @param entityLiving 持有魔法杖的生物
     * @param stack 魔法杖物品堆叠
     * @return 消耗的灵魂数量
     */
    @Override
    public int SoulUse(LivingEntity entityLiving, ItemStack stack) {
        // 获取基本灵魂消耗，并应用折扣
        float baseSoulCost = (float) this.SoulCost(stack) * SEHelper.soulDiscount(entityLiving);
        float percentageDiscount = 0;
        float fixedDiscount = 0;
        // 判断魔杖聚晶是否附魔且配置允许附魔多倍消耗
        if (IWand.getFocus(stack).isEnchanted() && (Boolean) SpellConfig.EnchantMultiCost.get()) {
            // 如果满足多倍消耗条件，则基础消耗翻倍
            baseSoulCost *= 2;
        }
        /// 对词条进行判断
        //饰品取最大值
        percentageDiscount += (float) MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(entityLiving, curios_goety_soul_cost_discount_Effect) /100;
        fixedDiscount += MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(entityLiving, curios_goety_soul_cost_discount_Effect);
        //主副手取最大值
        percentageDiscount += (float) MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(entityLiving,goety_soul_cost_discount_Effect)/100;
        fixedDiscount += MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(entityLiving,goety_soul_cost_discount_Effect);
        //如果百分比减免有数额
        if(percentageDiscount>0){
            baseSoulCost*= Math.max(0,1-percentageDiscount);
        }
        //如果固定减免有额度
        if(percentageDiscount>0){
            baseSoulCost -= fixedDiscount;
        }
        //防止小于0，爆炸
        if(baseSoulCost<0){
            baseSoulCost = 0;
        }
        return (int) baseSoulCost;
    }

    /**
     * 玩家左键攻击实体触发时的逻辑
     * 这个方法主要处理召唤物和奴仆的管理相关事件（赋予命令，传递召唤生物等）
     * @param stack 魔法杖Stack
     * @param player 玩家
     * @param entity 点击的实体
     * @return true表示事件被处理，false则不是相关实体或不处理
     */
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        boolean flag = false;
        // 只处理生物实体
        if (entity instanceof LivingEntity target) {
            // 只有实现IOwned接口的实体才响应（有所有者的生物如召唤物）
            if (target instanceof IOwned owned) {
                // 判断所有者是否是当前玩家，防止对别人的召唤物操作
                if (owned.getTrueOwner() != player) {
                    LivingEntity var8 = owned.getTrueOwner();
                    // 如果拥有者不是IOwned对象，则禁止操作
                    if (!(var8 instanceof IOwned)) {
                        return false;
                    }

                    IOwned owned1 = (IOwned)var8;
                    // 递归判断所有者关系，确保玩家是最终所有者
                    if (owned1.getTrueOwner() != player) {
                        return false;
                    }
                }

                // 服务器端执行以下逻辑
                if (!player.level.isClientSide) {
                    // 判断魔法杖聚晶类型是否为召唤聚晶且当前还没有召唤
                    if (IWand.getFocus(stack).getItem() instanceof CallFocus && !CallFocus.hasSummon(IWand.getFocus(stack))) {
                        CompoundTag compoundTag = new CompoundTag();
                        // 读取已有NBT数据（如果有）
                        if (IWand.getFocus(stack).hasTag()) {
                            compoundTag = IWand.getFocus(stack).getTag();
                        }
                        // 将目标实体设置为召唤目标
                        CallFocus.setSummon(compoundTag, target);
                        IWand.getFocus(stack).setTag(compoundTag);
                        // 播放命中音效
                        player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                        ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                        flag = true;
                    }
                    // 判断魔法杖聚晶类型为部队聚晶且还未设置召唤类型
                    else if (IWand.getFocus(stack).getItem() instanceof TroopFocus && !TroopFocus.hasSummonType(IWand.getFocus(stack))) {
                        CompoundTag compoundTag = new CompoundTag();
                        if (IWand.getFocus(stack).hasTag()) {
                            compoundTag = IWand.getFocus(stack).getTag();
                        }
                        // 设置召唤生物的类型
                        TroopFocus.setSummonType(compoundTag, target.getType());
                        IWand.getFocus(stack).setTag(compoundTag);
                        player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                        ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                        flag = true;
                    } else {
                        // 复杂处理多个聚晶类型的赋命逻辑
                        label116: {
                            // 赋予命令魔法聚晶且实体为奴仆类型
                            if (IWand.getFocus(stack).getItem() instanceof CommandFocus && owned instanceof IServant) {
                                IServant servant = (IServant)owned;
                                // 奴仆允许接受命令且魔法杖未绑定奴仆时进行绑定
                                if (servant.canBeCommanded() && !CommandFocus.hasServant(IWand.getFocus(stack))) {
                                    CompoundTag compoundTag = new CompoundTag();
                                    if (IWand.getFocus(stack).hasTag()) {
                                        compoundTag = IWand.getFocus(stack).getTag();
                                    }
                                    // 绑定奴仆
                                    CommandFocus.setServant(compoundTag, target);
                                    IWand.getFocus(stack).setTag(compoundTag);
                                    player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                                    ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                                    flag = true;
                                    break label116;
                                }
                            }
                            // 部队命令类型聚晶处理多奴仆集合，进行添加
                            if (IWand.getFocus(stack).getItem() instanceof OrderFocus && owned instanceof IServant) {
                                IServant servant = (IServant)owned;
                                if (servant.canBeCommanded()) {
                                    List<LivingEntity> list = OrderFocus.getServants(IWand.getFocus(stack));
                                    // 限制最大可添加数量且避免重复添加
                                    if ((list.isEmpty() || list.size() < 8) && !list.contains(target)) {
                                        OrderFocus.setServants(IWand.getFocus(stack), player, target);
                                        player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                                        ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }

                    // 如果未被处理且持有对象是奴仆或者藤蔓生物
                    if (!flag) {
                        if (owned instanceof IServant) {
                            IServant summonedEntity = (IServant)owned;
                            // 当玩家不按shift并且不蹲下时，切换奴仆模式
                            if (!player.isShiftKeyDown() && !player.isCrouching()) {
                                // 配置允许通过主人打击命令奴仆且奴仆可更新
                                if ((Boolean)SpellConfig.OwnerHitCommand.get() && summonedEntity.canUpdateMove()) {
                                    summonedEntity.updateMoveMode(player);
                                }
                            }
                            // 按下shift或蹲下时，根据配置尝试杀死奴仆
                            else if ((Integer)SpellConfig.OwnerHitKill.get() == 0) {
                                summonedEntity.tryKill(player);
                            }
                        } else if (owned instanceof AbstractVine) {
                            // 藤蔓生物相关逻辑，踩住按shift或者蹲下尝试杀死
                            AbstractVine vine = (AbstractVine)owned;
                            if ((player.isShiftKeyDown() || player.isCrouching()) && (Integer)SpellConfig.OwnerHitKill.get() == 0) {
                                vine.kill();
                            }
                        }
                    }
                }

                // 左键点击有管理作用，返回true阻止默认攻击
                return true;
            }
        }

        // 非拥有实体或者不满足条件返回false
        return false;
    }

    /**
     * 玩家使用魔法杖右键点击生物时触发
     * 实现对奴仆的命令发放以及触摸法术的施放
     * @param stack 魔杖物品
     * @param player 玩家
     * @param target 被点击生物
     * @param hand 持有手
     * @return 交互结果：成功、失败或者默认
     */
    @Nonnull
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        // 如果魔法杖聚晶为命令聚晶
        if (IWand.getFocus(stack).getItem() instanceof CommandFocus) {
            LivingEntity var6 = CommandFocus.getServant(IWand.getFocus(stack));
            if (var6 instanceof IServant) {
                IServant summoned = (IServant)var6;
                // 目标和当前奴仆不同，且奴仆拥有者是当前玩家，且距离小于64格
                if (summoned != target && summoned.getTrueOwner() == player && target.distanceTo(player) <= 64.0F) {
                    // 设置奴仆命令目标实体
                    summoned.setCommandPosEntity(target);
                    // 播放命令音效
                    player.playSound((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F);
                    if (!player.level.isClientSide) {
                        ModNetwork.sendTo(player, new SPlayPlayerSoundPacket((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        // 如果聚晶为部队命令且存在奴仆列表
        else if (IWand.getFocus(stack).getItem() instanceof OrderFocus && !OrderFocus.getServants(IWand.getFocus(stack)).isEmpty()) {
            int i = 0;
            // 遍历所有奴仆，发出命令目标
            for(LivingEntity livingEntity : OrderFocus.getServants(IWand.getFocus(stack))) {
                if (livingEntity instanceof IServant) {
                    IServant summoned = (IServant)livingEntity;
                    if (summoned != target && summoned.getTrueOwner() == player && target.distanceTo(player) <= 64.0F) {
                        summoned.setCommandPosEntityOrder(target);
                        ++i;
                    }
                }
            }
            // 如果确实发送了命令，播放声音并返回成功
            if (i > 0) {
                player.playSound((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F);
                if (!player.level.isClientSide) {
                    ModNetwork.sendTo(player, new SPlayPlayerSoundPacket((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F));
                }
                return InteractionResult.SUCCESS;
            }
        }

        // 处理被拥有生物
        if (target instanceof IOwned owned) {
            label87: {
                // 递归检查拥有者是否是当前玩家
                if (owned.getTrueOwner() != player) {
                    LivingEntity summonedEntity = owned.getTrueOwner();
                    if (!(summonedEntity instanceof IOwned)) {
                        break label87;
                    }
                    IOwned owned1 = (IOwned)summonedEntity;
                    if (owned1.getTrueOwner() != player) {
                        break label87;
                    }
                }

                if (owned instanceof IServant summonedEntity) {
                    // 若没按下shift且不蹲下，且配置关闭杀戮，改变奴仆动作
                    if (!player.isShiftKeyDown() && !player.isCrouching()) {
                        if (!(Boolean)SpellConfig.OwnerHitCommand.get() && summonedEntity.canUpdateMove()) {
                            summonedEntity.updateMoveMode(player);
                            return InteractionResult.SUCCESS;
                        }
                    }
                    // 按shift或蹲下，且配置允许杀戮，则尝试杀死奴仆
                    else if ((Integer)SpellConfig.OwnerHitKill.get() == 1) {
                        summonedEntity.tryKill(player);
                        return InteractionResult.SUCCESS;
                    }
                } else if (owned instanceof AbstractVine vine) {
                    // 对藤蔓生物在特定操作时强制杀死
                    if ((player.isShiftKeyDown() || player.isCrouching()) && (Integer)SpellConfig.OwnerHitKill.get() == 1) {
                        vine.kill();
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        // 若所附魔法存在并实现触摸法术接口，可施放触摸法术
        ISpell var14 = this.getSpell(stack);
        if (var14 instanceof ITouchSpell touchSpells) {
            if (this.canCastTouch(stack, player.level, player)) {
                Level var18 = player.level;
                // 服务器端执行触摸效果
                if (var18 instanceof ServerLevel serverLevel) {
                    touchSpells.touchResult(serverLevel, player, target, stack, WandUtil.getStats(player, touchSpells));
                }
                return InteractionResult.SUCCESS;
            }
        }

        // 默认调用父类逻辑
        return super.interactLivingEntity(stack, player, target, hand);
    }

    /**
     * 使用魔法杖点击方块时触发的交互事件
     * @param pContext 上下文，包含点击位置，玩家，手等信息
     * @return 交互结果
     */
    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        InteractionHand hand = pContext.getHand();
        ItemStack stack = pContext.getItemInHand();

        if (player != null) {
            // 取出魔法杖上的聚晶物品
            Item var10 = IWand.getFocus(stack).getItem();

            // 聚晶为召回聚晶（RecallFocus）
            if (var10 instanceof RecallFocus recallFocus) {
                CompoundTag compoundTag = IWand.getFocus(stack).getOrCreateTag();

                // 如果尚未设置召回点
                if (!RecallFocus.hasRecall(IWand.getFocus(stack))) {
                    BlockEntity tileEntity = level.getBlockEntity(blockpos);

                    // 检查点击是否是特殊方块（ArcaBlockEntity）
                    if (tileEntity instanceof ArcaBlockEntity arcaTile) {
                        if (pContext.getPlayer() == arcaTile.getPlayer() && arcaTile.getLevel() != null) {
                            // 设置位置及维度到召回聚晶中
                            recallFocus.addRecallTags(arcaTile.getLevel().dimension(), arcaTile.getBlockPos(), compoundTag);
                            IWand.getFocus(stack).setTag(compoundTag);
                            // 播放聚晶音效
                            player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                            if (!level.isClientSide) {
                                ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                            }
                            return InteractionResult.sidedSuccess(level.isClientSide);
                        }
                    }

                    // 点击的是召回标记块（Recall Blocks）
                    BlockState blockstate = level.getBlockState(blockpos);
                    if (blockstate.is(ModTags.Blocks.RECALL_BLOCKS)) {
                        recallFocus.addRecallTags(level.dimension(), blockpos, compoundTag);
                        IWand.getFocus(stack).setTag(compoundTag);
                        player.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                        if (!level.isClientSide) {
                            ModNetwork.sendTo(player, new SPlayPlayerSoundPacket(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
            // 聚晶为命令聚晶
            else if (IWand.getFocus(stack).getItem() instanceof CommandFocus) {
                if (CommandFocus.hasServant(IWand.getFocus(stack))) {
                    LivingEntity livingEntity = CommandFocus.getServant(IWand.getFocus(stack));
                    if (livingEntity instanceof IServant) {
                        IServant summoned = (IServant)livingEntity;
                        livingEntity = CommandFocus.getServant(IWand.getFocus(stack));
                        // 执行命令给奴仆，根据位置判断
                        if (livingEntity != null && summoned.getTrueOwner() == player && livingEntity.distanceTo(player) <= 64.0F) {
                            BlockPos above = blockpos.above();
                            boolean flag = false;

                            // 奴仆能否移动到点击方块坐标
                            if (summoned.canCommandToBlock(level, blockpos)) {
                                summoned.setCommandPos(blockpos);
                                flag = true;
                            }
                            // 若不能，尝试移动到上方一格
                            else if (summoned.canCommandToBlock(level, above)) {
                                summoned.setCommandPos(above);
                                flag = true;
                            }

                            // 发送命令音效及服务器同步
                            if (flag) {
                                player.playSound((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F);
                                if (!level.isClientSide) {
                                    ModNetwork.sendTo(player, new SPlayPlayerSoundPacket((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F));
                                }
                                return InteractionResult.sidedSuccess(level.isClientSide);
                            }
                        }
                    }
                }
            }
            // 聚晶为部队命令聚晶
            else if (IWand.getFocus(stack).getItem() instanceof OrderFocus) {
                if (!OrderFocus.getServants(IWand.getFocus(stack)).isEmpty()) {
                    int i = 0;
                    // 遍历所有奴仆，尝试为每个奴仆发送移动命令
                    for(LivingEntity livingEntity : OrderFocus.getServants(IWand.getFocus(stack))) {
                        if (livingEntity instanceof IServant) {
                            IServant summoned = (IServant)livingEntity;
                            if (summoned.canBeCommanded() && summoned.getTrueOwner() == player && livingEntity.distanceTo(player) <= 64.0F) {
                                BlockPos above = blockpos.above();
                                if (summoned.canCommandToBlock(level, blockpos)) {
                                    summoned.setCommandPos(blockpos);
                                    ++i;
                                } else if (summoned.canCommandToBlock(level, above)) {
                                    summoned.setCommandPos(above);
                                    ++i;
                                }
                            }
                        }
                    }
                    if (i > 0) {
                        // 播放命令音效
                        player.playSound((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F);
                        if (!level.isClientSide) {
                            ModNetwork.sendTo(player, new SPlayPlayerSoundPacket((SoundEvent)ModSounds.COMMAND.get(), 1.0F, 0.45F));
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
            // 其他情况，根据法术类型决定行为
            else {
                ISpell spell2 = this.getSpell(stack);
                // 如果是基于方块的法术
                if (spell2 instanceof IBlockSpell) {
                    IBlockSpell blockSpell0 = (IBlockSpell)spell2;
                    // 事件拦截器，处理可能更改的法术
                    spell2 = GoetyEventFactory.onBlockBasedSpell(player.level, blockpos, player.level.getBlockState(blockpos), blockSpell0, pContext.getClickedFace(), player);
                    if (spell2 instanceof IBlockSpell) {
                        IBlockSpell blockSpell = (IBlockSpell)spell2;
                        Level var33 = player.level;
                        if (var33 instanceof ServerLevel) {
                            ServerLevel serverLevel = (ServerLevel)var33;
                            // 处理右键方块的逻辑
                            if (blockSpell.rightBlock(serverLevel, player, blockpos, pContext.getClickedFace(), WandUtil.getStats(player, blockSpell))) {
                                // 是否能施放触摸法术
                                if (this.canCastTouch(stack, level, player)) {
                                    blockSpell.blockResult(serverLevel, player, stack, blockpos, pContext.getClickedFace(), WandUtil.getStats(player, blockSpell));
                                }
                                return InteractionResult.SUCCESS;
                            }
                        }
                    }
                } else {
                    // 处理旗帜方块互动，修改玩家数据
                    if (level.getBlockState(blockpos).is(BlockTags.BANNERS)) {
                        BlockEntity var20 = level.getBlockEntity(blockpos);
                        if (var20 instanceof BannerBlockEntity) {
                            BannerBlockEntity bannerBlock = (BannerBlockEntity)var20;
                            if (!level.isClientSide) {
                                CompoundTag compoundtag = BlockItem.getBlockEntityData(bannerBlock.getItem());
                                if (compoundtag != null && compoundtag.contains("Patterns")) {
                                    SEHelper.setBannerBaseColor(player, bannerBlock.getBaseColor());
                                    SEHelper.setBannerPattern(player, compoundtag.getList("Patterns", 10));
                                    player.displayClientMessage(Component.translatable("info.goety.banner.add", new Object[]{player.getDisplayName()}), true);
                                    level.playSound((Player)null, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), (SoundEvent)ModSounds.CAST_SPELL.get(), SoundSource.BLOCKS, 1.0F, 0.5F);
                                    return InteractionResult.SUCCESS;
                                }
                            }
                            return super.useOn(pContext);
                        }
                    }

                    // 处理酿造坩埚方块的特殊交互：重置坩埚状态
                    if (level.getBlockState(blockpos).getBlock() instanceof BrewCauldronBlock) {
                        if (!level.isClientSide) {
                            BlockEntity var27 = level.getBlockEntity(blockpos);
                            if (var27 instanceof BrewCauldronBlockEntity) {
                                BrewCauldronBlockEntity cauldronBlock = (BrewCauldronBlockEntity)var27;
                                if (MobUtil.isShifting(player) && stack.getItem() instanceof IWand) {
                                    cauldronBlock.fullReset(); // 重置坩埚
                                    level.playSound((Player)null, blockpos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                                    level.playSound((Player)null, blockpos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                                    return InteractionResult.SUCCESS;
                                }
                            }
                        }
                    }
                    // 如果是非空气方块，且为服务器端，调用方块自身的右键事件
                    else if (!level.getBlockState(blockpos).isAir() && !level.isClientSide) {
                        return level.getBlockState(blockpos).use(level, player, hand, new BlockHitResult(pContext.getClickLocation(), pContext.getClickedFace(), pContext.getClickedPos(), pContext.isInside()));
                    }
                }
            }
        }

        // 默认调用父类方法处理
        return super.useOn(pContext);
    }


    /**
     * 魔法杖使用中每Tick调用，处理持续施法逻辑
     * @param worldIn 世界对象
     * @param livingEntityIn 持法者实体
     * @param stack 魔法杖堆叠
     * @param count 当前剩余使用时间
     */
    @Override
    public void onUseTick(Level worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        // 触发开始施法事件，可能修正法术
        ISpell iSpell = GoetyEventFactory.onStartSpell(livingEntityIn, stack, this.getSpell(stack));
        // 如果是服务器端，同时施法条件不满足，则终止施法
        if (worldIn instanceof ServerLevel && this.cannotCast(livingEntityIn, stack, iSpell)) {
            livingEntityIn.stopUsingItem();
        } else {
            // 计算已经持续施法的时间
            int CastTime = stack.getUseDuration() - count;

            // 判断持有实体是否仍在使用该魔杖，且法术存在且不是瞬发
            if (livingEntityIn.getUseItem() == stack && iSpell != null && this.isNotInstant(iSpell, livingEntityIn, stack)) {
                // 取得施法声音
                SoundEvent soundevent = this.CastingSound(stack, livingEntityIn);
                // 第一次tick播放开始施法音效
                if (CastTime == 1 && soundevent != null) {
                    if (worldIn instanceof ServerLevel) {
                        ServerLevel serverLevel = (ServerLevel)worldIn;
                        // 触发法术开始施放，执行法术开始逻辑
                        iSpell.startSpell(serverLevel, livingEntityIn, stack, WandUtil.getStats(livingEntityIn, iSpell));
                    }
                    worldIn.playSound((Player)null, livingEntityIn.getX(), livingEntityIn.getY(), livingEntityIn.getZ(), soundevent, SoundSource.PLAYERS, this.castingVolume(stack), this.castingPitch(stack));
                }

                if (worldIn instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)worldIn;
                    // 触发施法中事件，法术可能被更改
                    iSpell = GoetyEventFactory.onCastingSpell(livingEntityIn, stack, iSpell, CastTime);
                    if (iSpell != null) {
                        // 使用法术（施法中行为）
                        iSpell.useSpell(serverLevel, livingEntityIn, stack, CastTime, WandUtil.getStats(livingEntityIn, iSpell));
                    } else {
                        // 法术被终止后停止使用物品
                        livingEntityIn.stopUsingItem();
                    }
                }

                if (iSpell != null) {
                    label65: {
                        // 如果是充能法术
                        if (iSpell instanceof IChargingSpell) {
                            IChargingSpell spell = (IChargingSpell)iSpell;
                            // 充能阶段大于0,使用粒子效果
                            if (spell.castUp(livingEntityIn, stack) > 0) {
                                this.useParticles(worldIn, livingEntityIn, stack, iSpell);
                                break label65;
                            }
                        }
                        // 非充能法术，直接使用粒子
                        if (!(iSpell instanceof IChargingSpell)) {
                            this.useParticles(worldIn, livingEntityIn, stack, iSpell);
                        }
                    }

                    // 再次处理充能法术逻辑
                    if (iSpell instanceof IChargingSpell) {
                        IChargingSpell spell = (IChargingSpell)iSpell;
                        // 计算冷却累计并判断是否达到触发魔法逻辑阈值
                        if (stack.getTag() != null && (CastTime >= spell.castUp(livingEntityIn, stack) || spell.castUp(livingEntityIn, stack) <= 0)) {
                            stack.getTag().putInt("Cool", stack.getTag().getInt("Cool") + 1);
                            if (stack.getTag().getInt("Cool") >= this.Cooldown(stack)) {
                                stack.getTag().putInt("Cool", 0);
                                if (spell.shotsNumber(livingEntityIn, stack) > 0) {
                                    this.increaseShots(stack);
                                }
                                // 产生法术结果效果
                                this.MagicResults(stack, worldIn, livingEntityIn, spell);
                            }
                        }

                        // 检查玩家灵魂是否足够，若不足则停止使用物品
                        if (livingEntityIn instanceof Player) {
                            Player player = (Player)livingEntityIn;
                            if (!SEHelper.getSoulsAmount(player, iSpell.soulCost(player, stack)) && !player.isCreative()) {
                                player.stopUsingItem();
                            }
                        }
                    }
                } else {
                    // 没有法术时停止使用
                    livingEntityIn.stopUsingItem();
                }
            }

        }
    }

    /**
     * 玩家释放魔法杖使用（松开右键）时调用，停止施法
     * @param stack 物品堆
     * @param level 世界
     * @param livingEntity 持有者
     * @param useTimeRemaining 剩余使用时间（未使用的）
     */
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int useTimeRemaining) {
        if (level instanceof ServerLevel serverLevel) {
            // 计算实际使用的施法时间
            int CastTime = stack.getUseDuration() - useTimeRemaining;
            // 触发停止施法事件，法术可能被修改
            ISpell spell = GoetyEventFactory.onStopSpell(livingEntity, stack, this.getSpell(stack), CastTime, useTimeRemaining);
            if (spell != null) {
                // 终止法术，执行停止效果
                spell.stopSpell(serverLevel, livingEntity, stack, IWand.getFocus(stack), CastTime, WandUtil.getStats(livingEntity, spell));
                if (livingEntity instanceof Player) {
                    Player player = (Player)livingEntity;
                    // 特别处理充能法术与发射次数相关的冷却加成
                    if (spell instanceof IChargingSpell) {
                        IChargingSpell chargeSpell = (IChargingSpell)spell;
                        if (chargeSpell.shotsNumber(player, stack) > 0) {
                            if (this.ShotsFired(stack) > 0) {
                                // 依照发射次数比例增加冷却
                                float coolPercent = (float)this.ShotsFired(stack) / (float)chargeSpell.shotsNumber(player, stack);
                                this.setShots(stack, 0);
                                SEHelper.addCooldown(player, IWand.getFocus(stack).getItem(), Mth.floor((float)chargeSpell.spellCooldown(player) * coolPercent));
                            }
                        } else {
                            // 普通充能法术冷却累加
                            SEHelper.addCooldown(player, IWand.getFocus(stack).getItem(), Mth.floor((float)chargeSpell.spellCooldown(player)));
                        }
                    }
                }
            }
        }

    }

    /**
     * 获取使用的最大持续时间
     * @param stack 魔杖ItemStack
     * @return 持续时间（tick数）
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        return stack.getTag() != null ? stack.getTag().getInt("Cast Time") : this.CastDuration(stack);
    }

    /**
     * 获取使用动画类型，这里自定义动画
     * @param stack 魔杖
     * @return 自定义类型UseAnim.CUSTOM
     */
    @Nonnull
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CUSTOM;
    }

    /**
     * 完成物品使用时调用（施法结束）
     * @param stack 物品堆
     * @param worldIn 世界
     * @param entityLiving 使用物品的实体
     * @return 物品堆叠
     */
    @Nonnull
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        super.finishUsingItem(stack, worldIn, entityLiving);
        // 触发施法事件，允许替换法术
        ISpell iSpell = GoetyEventFactory.onCastSpell(entityLiving, this.getSpell(stack));
        // 法术条件检测，满足施法要求则执行魔法结果（并非瞬发且非触摸法术）
        if (iSpell != null && (!(iSpell instanceof IChargingSpell) || this.isNotInstant(iSpell, entityLiving, stack) || this.notTouch(iSpell)) && !this.cannotCast(entityLiving, stack)) {
            this.MagicResults(stack, worldIn, entityLiving, iSpell);
        }

        // 清理冷却计数和发射计数
        if (stack.getTag() != null) {
            if (stack.getTag().getInt("Cool") > 0) {
                stack.getTag().putInt("Cool", 0);
            }
            if (stack.getTag().getInt("Shots") > 0) {
                stack.getTag().putInt("Shots", 0);
            }
        }

        return stack;
    }

    /**
     * 玩家右键使用魔杖时调用方法
     * 主要处理聚晶物品的交互，及施法事件启动
     * @param worldIn 世界
     * @param playerIn 玩家
     * @param handIn 手部
     * @return 交互结果和物品堆叠
     */
    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        ItemStack focus = IWand.getFocus(itemstack);

        // 处理命令聚晶进行奴仆解绑操作（按shift时）
        if (focus.getItem() instanceof CommandFocus && playerIn.isCrouching()) {
            if (CommandFocus.hasServant(focus) && focus.getTag() != null) {
                focus.getTag().remove("Servant"); // 移除奴仆标签，解绑
                playerIn.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                if (!worldIn.isClientSide) {
                    ModNetwork.sendTo(playerIn, new SPlayEntitySoundPacket(playerIn.getUUID(), SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                }
            }
            return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
        }
        // 处理部队命令聚晶解绑部队
        else if (focus.getItem() instanceof OrderFocus && playerIn.isCrouching()) {
            if (focus.getTag() != null) {
                focus.getTag().remove(OrderFocus.SERVANT_LIST);
                focus.getTag().remove(OrderFocus.SERVANT_CLIENT_LIST);
                playerIn.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                if (!worldIn.isClientSide) {
                    ModNetwork.sendTo(playerIn, new SPlayEntitySoundPacket(playerIn.getUUID(), SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                }
            }
            return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
        }
        // 召唤聚晶取消召唤标记
        else if (focus.getItem() instanceof CallFocus && playerIn.isCrouching()) {
            if (CallFocus.hasSummon(focus) && focus.getTag() != null) {
                focus.getTag().remove("Summoned");
                playerIn.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F);
                if (!worldIn.isClientSide) {
                    ModNetwork.sendTo(playerIn, new SPlayEntitySoundPacket(playerIn.getUUID(), SoundEvents.ARROW_HIT_PLAYER, 1.0F, 0.45F));
                }
            }
            return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
        } else {
            // 普通施法流程，根据法术判断是否能施法
            if (this.getSpell(itemstack) != null) {
                // 超过施法条件限制，无法施法，传递不处理
                if (this.cannotCast(playerIn, itemstack)) {
                    return InteractionResultHolder.pass(itemstack);
                }
                // 若法术非瞬发
                if (this.isNotInstant(this.getSpell(itemstack), playerIn, itemstack)) {
                    // 有足够灵魂或在创造模式，开始持续施法
                    if ((SEHelper.getSoulsAmount(playerIn, this.getSpell(itemstack).soulCost(playerIn, itemstack)) || playerIn.getAbilities().instabuild) && !worldIn.isClientSide) {
                        playerIn.startUsingItem(handIn);
                    }
                }
                // 瞬发不触摸法术，直接发动法术并播放动画
                else if (this.notTouch(this.getSpell(itemstack))) {
                    playerIn.swing(handIn);
                    ISpell iSpell = GoetyEventFactory.onCastSpell(playerIn, this.getSpell(itemstack));
                    this.MagicResults(itemstack, worldIn, playerIn, iSpell);
                }
            }
            return InteractionResultHolder.consume(itemstack);
        }
    }

    /**
     * 根据法术设置魔法杖的施法条件（灵魂消耗，持续时间，冷却）
     * @param spell 施法的法术（nullable）
     * @param stack 魔杖ItemStack
     * @param livingEntity 施法者
     */
    public void setSpellConditions(@Nullable ISpell spell, ItemStack stack, LivingEntity livingEntity) {
        if (stack.getTag() != null) {
            if (spell != null) {
                // 设置灵魂消耗
                stack.getTag().putInt("Soul Cost", spell.soulCost(livingEntity, stack));
                // 设置施法时长
                stack.getTag().putInt("Duration", spell.castDuration(livingEntity, stack));
                if (spell instanceof IChargingSpell) {
                    // 设置冷却时间（受充能状态影响）
                    IChargingSpell chargingSpell = (IChargingSpell)spell;
                    stack.getTag().putInt("Cooldown", chargingSpell.Cooldown(livingEntity, stack, stack.getTag().contains("Shots") ? stack.getTag().getInt("Shots") : 0));
                } else {
                    stack.getTag().putInt("Cooldown", 0);
                }
            } else {
                // 无法术，置0
                stack.getTag().putInt("Soul Cost", 0);
                stack.getTag().putInt("Duration", 0);
                stack.getTag().putInt("Cooldown", 0);
            }
        }

    }

    // 以下4个方法是读取和修改魔杖NBT中写入的属性（灵魂消耗，施法时长，冷却时间，已发射次数）

    /** 获取灵魂消耗 */
    public int SoulCost(ItemStack itemStack) {
        return itemStack.getTag() == null ? 0 : itemStack.getTag().getInt("Soul Cost");
    }

    /** 获取施法时长 */
    public int CastDuration(ItemStack itemStack) {
        return itemStack.getTag() != null ? itemStack.getTag().getInt("Duration") : 0;
    }

    /** 获取冷却时长 */
    public int Cooldown(ItemStack itemStack) {
        return itemStack.getTag() != null ? itemStack.getTag().getInt("Cooldown") : 0;
    }

    /** 获取已发射次数 */
    @Override
    public int ShotsFired(ItemStack itemStack) {
        return itemStack.getTag() != null ? itemStack.getTag().getInt("Shots") : 0;
    }

    /** 增加发射次数 */
    public void increaseShots(ItemStack itemStack) {
        if (itemStack.getTag() != null) {
            itemStack.getTag().putInt("Shots", this.ShotsFired(itemStack) + 1);
        }
    }

    /** 设置发射次数 */
    public void setShots(ItemStack itemStack, int amount) {
        if (itemStack.getTag() != null) {
            itemStack.getTag().putInt("Shots", amount);
        }
    }

    /**
     * 获取施法声音资源
     * @param stack 魔杖
     * @param caster 施法者
     * @return 施法音效
     */
    @Nullable
    public SoundEvent CastingSound(ItemStack stack, LivingEntity caster) {
        return this.getSpell(stack) != null ? this.getSpell(stack).CastingSound(caster) : null;
    }

    /** 施法声音音量 */
    public float castingVolume(ItemStack stack) {
        return this.getSpell(stack) != null ? this.getSpell(stack).castingVolume() : 0.5F;
    }

    /** 施法声音音调 */
    public float castingPitch(ItemStack stack) {
        return this.getSpell(stack) != null ? this.getSpell(stack).castingPitch() : 1.0F;
    }

    /**
     * 判断玩家是否可以施放触摸法术
     * @param stack 魔杖
     * @param worldIn 世界
     * @param caster 施法者（玩家）
     * @return true能施放，false不能施放
     */
    public boolean canCastTouch(ItemStack stack, Level worldIn, LivingEntity caster) {
        Player playerEntity = (Player)caster;
        if (!worldIn.isClientSide) {
            // 触发触摸法术事件并读取可能被修改的法术
            ISpell spell = GoetyEventFactory.onTouchBasedSpell(caster, stack, this.getSpell(stack));
            // 进行灵魂消耗等判断
            if (spell != null && !this.cannotCast(caster, stack, spell)) {
                // 创造模式跳过灵魂扣除
                if (playerEntity.isCreative()) {
                    SEHelper.addCooldown(playerEntity, IWand.getFocus(stack).getItem(), spell.spellCooldown(playerEntity));
                    return stack.getTag() != null;
                }
                // 常规模式判断灵魂是否足够，并扣除灵魂与添加冷却
                if (SEHelper.getSoulsAmount(playerEntity, this.SoulUse(caster, stack)) && stack.getTag() != null) {
                    SEHelper.decreaseSouls(playerEntity, this.SoulUse(caster, stack));
                    SEHelper.addCooldown(playerEntity, IWand.getFocus(stack).getItem(), spell.spellCooldown(playerEntity));
                    SEHelper.sendSEUpdatePacket(playerEntity);
                    return true;
                }
            }
        }

        return false;
    }

    /** @deprecated 不带参数的法术结果调用，转为带法术参数的函数 */
    @Deprecated
    public void MagicResults(ItemStack stack, Level worldIn, LivingEntity caster) {
        this.MagicResults(stack, worldIn, caster, this.getSpell(stack));
    }

    /**
     * 施法结果逻辑，根据法术不同有不同的效果表现及灵魂耗费
     * @param stack 魔杖物品
     * @param worldIn 世界
     * @param caster 施法者
     * @param spell 法术对象
     */
    public void MagicResults(ItemStack stack, Level worldIn, LivingEntity caster, ISpell spell) {
        // 法术和施法者必须存在且施法者为玩家
        if (spell != null && caster instanceof Player playerEntity) {
            if (!worldIn.isClientSide) {
                ServerLevel serverWorld = (ServerLevel)worldIn;

                // 创造模允许无消耗施法
                if (playerEntity.isCreative()) {
                    if (stack.getTag() != null) {
                        spell.SpellResult(serverWorld, caster, stack, WandUtil.getStats(caster, spell));
                        boolean flag = false;
                        if (spell instanceof IChargingSpell chargingSpell) {
                            // 当发射数达到最大值后标记清空
                            if (chargingSpell.shotsNumber(playerEntity, stack) > 0 && this.ShotsFired(stack) >= chargingSpell.shotsNumber(playerEntity, stack)) {
                                flag = true;
                            }
                        } else {
                            flag = true;
                        }
                        // 如果为true，则清理发射次数并添加冷却时间
                        if (flag) {
                            this.setShots(stack, 0);
                            SEHelper.addCooldown(playerEntity, IWand.getFocus(stack).getItem(), spell.spellCooldown(playerEntity));
                        }
                    }
                }
                // 非创造模式，检查灵魂是否充足
                else if (SEHelper.getSoulsAmount(playerEntity, this.SoulUse(caster, stack))) {
                    boolean spent = true;

                    // 充能法术特判，按秒累计计时减少灵魂消耗频率（减负机制）
                    if (spell instanceof IChargingSpell) {
                        IChargingSpell spell1 = (IChargingSpell)spell;
                        if (spell1.everCharge() && stack.getTag() != null) {
                            stack.getTag().putInt("Seconds", stack.getTag().getInt("Seconds") + 1);
                            if (stack.getTag().getInt("Seconds") != 20) {
                                spent = false;
                            } else {
                                stack.getTag().putInt("Seconds", 0);
                            }
                        }
                    }

                    // 如果到了实际扣费时机
                    if (spent) {
                        SEHelper.decreaseSouls(playerEntity, this.SoulUse(caster, stack));
                        SEHelper.sendSEUpdatePacket(playerEntity);

                        // 村民讨厌法术（使得玩家被村民负面八卦）
                        if ((Integer)MobsConfig.VillagerHateSpells.get() > 0) {
                            for(Villager villager : caster.level.getEntitiesOfClass(Villager.class, caster.getBoundingBox().inflate((double)16.0F))) {
                                if (villager.hasLineOfSight(caster)) {
                                    villager.getGossips().add(caster.getUUID(), GossipType.MINOR_NEGATIVE, (Integer)MobsConfig.VillagerHateSpells.get());
                                }
                            }
                        }
                    }

                    // 施放法术结果
                    if (stack.getTag() != null) {
                        spell.SpellResult(serverWorld, caster, stack, WandUtil.getStats(caster, spell));
                        boolean flag = false;
                        if (spell instanceof IChargingSpell) {
                            IChargingSpell chargingSpell = (IChargingSpell)spell;
                            if (chargingSpell.shotsNumber(playerEntity, stack) > 0 && this.ShotsFired(stack) >= chargingSpell.shotsNumber(playerEntity, stack)) {
                                flag = true;
                            }
                        } else {
                            flag = true;
                        }
                        // 清理发射次数并添加冷却时间
                        if (flag) {
                            this.setShots(stack, 0);
                            SEHelper.addCooldown(playerEntity, IWand.getFocus(stack).getItem(), spell.spellCooldown(playerEntity));
                        }
                    }
                } else {
                    // 灵魂不足时播放失败音效（喷火音）
                    worldIn.playSound((Player)null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
                }
            }

            // 客户端表现呈现粒子效果等
            if (worldIn.isClientSide) {
                if (playerEntity.isCreative()) {
                    if (spell instanceof IBreathingSpell) {
                        IBreathingSpell breathingSpells = (IBreathingSpell)spell;
                        breathingSpells.showWandBreath(caster, WandUtil.getStats(caster, spell));
                    }
                } else if (SEHelper.getSoulsAmount(playerEntity, this.SoulUse(caster, stack))) {
                    if (spell instanceof IBreathingSpell) {
                        IBreathingSpell breathingSpells = (IBreathingSpell)spell;
                        breathingSpells.showWandBreath(caster, WandUtil.getStats(caster, spell));
                    }
                } else {
                    this.failParticles(worldIn, caster);
                }
            }
        } else {
            // 法术或施法者为空时，表现失败粒子及音效
            this.failParticles(worldIn, caster);
            worldIn.playSound((Player)null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }

    }

    /**
     * 为魔法杖添加提示文本（鼠标悬停时显示）
     * @param stack 物品堆
     * @param worldIn 世界（可空）
     * @param tooltip 提示列表
     * @param flagIn 显示标志
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        Player player = Goety.PROXY.getPlayer();
        if (stack.getTag() != null) {
            int SoulUse = stack.getTag().getInt("Soul Use");
            // 显示灵魂消耗
            tooltip.add(Component.translatable("info.goety.wand.cost", new Object[]{SoulUse}));
            if (this.getSpell(stack) != null) {
                // 显示施法时间（秒数）
                if (this.isNotInstant(this.getSpell(stack), player, stack) && !(this.getSpell(stack) instanceof IChargingSpell)) {
                    int CastTime = stack.getTag().getInt("Cast Time");
                    tooltip.add(Component.translatable("info.goety.wand.castTime", new Object[]{(float)CastTime / 20.0F}));
                }

                // 显示冷却时间（秒数）
                if (this.getSpell(stack).spellCooldown(player) > 0) {
                    tooltip.add(
                            Component.translatable("info.goety.wand.coolDown", new Object[]{(float)this.getSpell(stack).spellCooldown(player) / 20.0F}));
                }
            }
        } else {
            // 未初始化时显示灵魂消耗
            tooltip.add(Component.translatable("info.goety.wand.cost", new Object[]{this.SoulCost(stack)}));
        }

        // 魔杖有聚晶物品时显示聚晶物品信息
        if (!IWand.getFocus(stack).isEmpty()) {
            tooltip.add(Component.translatable("info.goety.wand.focus", new Object[]{IWand.getFocus(stack).getItem().getDescription()}));
            if (IWand.getFocus(stack).getItem() instanceof RecallFocus) {
                ItemStack recallFocus = IWand.getFocus(stack);
                RecallFocus.addRecallText(recallFocus, tooltip);
            }

            // 按shift显示额外信息
            ItemHelper.addOnShift(tooltip, () -> this.addInformationAfterShift(IWand.getFocus(stack).getItem(), tooltip));
        } else {
            // 没有聚晶时显示空提示
            tooltip.add(Component.translatable("info.goety.wand.focus", new Object[]{Component.translatable("info.goety.wand.empty")}));
            if (ModKeybindings.wandSlot() != null) {
                tooltip.add(Component.translatable("info.goety.wand.open", new Object[]{ModKeybindings.wandSlot().getTranslatedKeyMessage()}).withStyle(ChatFormatting.BLUE));
            }
        }

        // 显示切换聚晶快捷键提示
        if (ModKeybindings.wandCircle() != null) {
            tooltip.add(Component.translatable("info.goety.wand.switch", new Object[]{ModKeybindings.wandCircle().getTranslatedKeyMessage()}).withStyle(ChatFormatting.BLUE));
        }

    }

    /**
     * 在Shift按下时添加聚晶物品的额外信息
     * @param item 聚晶物品
     * @param tooltip 信息列表
     */
    public void addInformationAfterShift(Item item, List<Component> tooltip) {
        tooltip.add(Component.translatable(item.getDescriptionId() + ".info").withStyle(ChatFormatting.GRAY));
    }

    /**
     * 初始化客户端扩展，绑定自定义模型或显示逻辑
     * @param consumer 接受IClientItemExtensions的消费者
     */
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        // 绑定魔法杖客户端扩展实现
        consumer.accept(new ModularGoetyWandItemClient());
    }

    /**
     * 魔法杖的客户端扩展类，处理自定义手部动画和转换
     */
    public static class ModularGoetyWandItemClient implements IClientItemExtensions {
        // 自定义施法动作动画，手臂旋转调整
        private static final HumanoidModel.ArmPose SPELL = HumanoidModel.ArmPose.create("GOETY_SPELL", false, (model, entity, arm) -> {
            float f5 = entity.walkAnimation.position(Minecraft.getInstance().getPartialTick());
            if (arm == HumanoidArm.RIGHT) {
                ModelPart var10000 = model.rightArm;
                var10000.xRot -= MathHelper.modelDegrees(105.0F);
                model.rightArm.zRot = Mth.cos(f5 * 0.6662F) * 0.25F;
                var10000 = model.leftArm;
                var10000.xRot += MathHelper.modelDegrees(25.0F);
            } else {
                ModelPart var5 = model.leftArm;
                var5.xRot -= MathHelper.modelDegrees(105.0F);
                model.leftArm.zRot = -Mth.cos(f5 * 0.6662F) * 0.25F;
                var5 = model.rightArm;
                var5.xRot += MathHelper.modelDegrees(25.0F);
            }

        });

        // 飞行姿态动画调整
        private static final HumanoidModel.ArmPose FLIGHT_POSE = HumanoidModel.ArmPose.create("GOETY_FLYING", false, (model, entity, arm) -> {
            float f5 = 1.0F;
            if (arm == HumanoidArm.RIGHT) {
                model.rightArm.xRot = -MathHelper.modelDegrees(105.0F);
                model.rightArm.zRot = Mth.cos(f5 * 0.6662F) * 0.25F;
                model.leftArm.xRot = MathHelper.modelDegrees(25.0F);
            } else {
                model.leftArm.xRot = -MathHelper.modelDegrees(105.0F);
                model.leftArm.zRot = -Mth.cos(f5 * 0.6662F) * 0.25F;
                model.rightArm.xRot = MathHelper.modelDegrees(25.0F);
            }

            model.rightLeg.xRot = MathHelper.modelDegrees(17.5F);
            model.leftLeg.xRot = MathHelper.modelDegrees(17.5F);
            ModelPart var10000 = model.rightLeg;
            var10000.xRot += 1.0F * Mth.sin(Minecraft.getInstance().getPartialTick() * 0.067F) * 0.05F;
            var10000 = model.leftLeg;
            var10000.xRot += -1.0F * Mth.sin(Minecraft.getInstance().getPartialTick() * 0.067F) * 0.05F;
        });

        // 手持魔杖姿态动画
        private static final HumanoidModel.ArmPose HOLD_STAFF = HumanoidModel.ArmPose.create("HOLD_STAFF", false, (model, entity, arm) -> {
            float f5 = entity.walkAnimation.position(Minecraft.getInstance().getPartialTick());
            if (arm == HumanoidArm.RIGHT) {
                ModelPart var10000 = model.rightArm;
                var10000.xRot -= MathHelper.modelDegrees(90.0F);
                model.rightArm.zRot = Mth.cos(f5 * 0.6662F) * 0.1F;
            } else {
                ModelPart var4 = model.leftArm;
                var4.xRot -= MathHelper.modelDegrees(90.0F);
                model.leftArm.zRot = -Mth.cos(f5 * 0.6662F) * 0.1F;
            }

        });

        /**
         * 获取对应当前手持魔法杖时的动画手势
         * @param entityLiving 实体
         * @param hand 当前手
         * @param itemStack 当前手物品
         * @return 当前使用的手势姿势
         */
        @Override
    public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof IWand && entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                ISpell spell = WandUtil.getSpell(entityLiving);
                if (spell != null) {
                    return spell.getPose(entityLiving, itemStack, WandUtil.getStats(entityLiving, spell));
                }
            }
            return HumanoidModel.ArmPose.EMPTY;
        }

        /**
         * 应用魔法杖手上变换效果（渲染时调用）
         * @param poseStack 变换栈
         * @param player 本地玩家
         * @param arm 手臂
         * @param itemInHand 持有物品
         * @param partialTick 渲染帧偏移
         * @param equipProcess 装备移动进度
         * @param swingProcess 挥动进度
         * @return 是否已处理为true
         */
        @Override
    public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
            int i = arm == HumanoidArm.RIGHT ? 1 : -1;
            if (player.isUsingItem()) {
                this.applyItemArmTransform(poseStack, arm, equipProcess);
                poseStack.translate((double)((float)i * -0.2785682F), (double)0.18344387F, (double)0.15731531F);
                poseStack.mulPose(Axis.XP.rotationDegrees(-13.935F));
                poseStack.mulPose(Axis.YP.rotationDegrees((float)i * 35.3F));
                poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * -9.785F));
                float f8 = (float)itemInHand.getUseDuration() - ((float)player.getUseItemRemainingTicks() - partialTick + 1.0F);
                float f12 = f8 / 20.0F;
                f12 = (f12 * f12 + f12 * 2.0F) / 3.0F;
                if (f12 > 1.0F) {
                    f12 = 1.0F;
                }

                if (f12 > 0.1F) {
                    float f15 = Mth.sin((f8 - 0.1F) * 1.3F);
                    float f18 = f12 - 0.1F;
                    float f20 = f15 * f18;
                    poseStack.translate((double)(f20 * 0.0F), (double)(f20 * 0.004F), (double)(f20 * 0.0F));
                }

                poseStack.translate((double)(f12 * 0.0F), (double)(f12 * 0.0F), (double)(f12 * 0.04F));
                poseStack.scale(1.0F, 1.0F, 1.0F + f12 * 0.2F);
                poseStack.mulPose(Axis.YN.rotationDegrees((float)i * 45.0F));
            } else {
                float f5 = -0.4F * Mth.sin(Mth.sqrt(swingProcess) * (float)Math.PI);
                float f6 = 0.2F * Mth.sin(Mth.sqrt(swingProcess) * ((float)Math.PI * 2F));
                float f10 = -0.2F * Mth.sin(swingProcess * (float)Math.PI);
                poseStack.translate((double)((float)i * f5), (double)f6, (double)f10);
                this.applyItemArmTransform(poseStack, arm, equipProcess);
                this.applyItemArmAttackTransform(poseStack, arm, swingProcess);
            }
            return true;
        }

        // 辅助方法，用于手臂动画的物品位置初始变换
        private void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float equipProcess) {
            int i = arm == HumanoidArm.RIGHT ? 1 : -1;
            poseStack.translate((double)((float)i * 0.56F), (double)(-0.52F + equipProcess * -0.6F), (double)-0.72F);
        }

        // 辅助方法，挥动攻击时手部额外旋转变换
        private void applyItemArmAttackTransform(PoseStack poseStack, HumanoidArm humanoidArm, float swingProcess) {
            int i = humanoidArm == HumanoidArm.RIGHT ? 1 : -1;
            float f = Mth.sin(swingProcess * swingProcess * (float)Math.PI);
            poseStack.mulPose(Axis.YP.rotationDegrees((float)i * (45.0F + f * -20.0F)));
            float f1 = Mth.sin(Mth.sqrt(swingProcess) * (float)Math.PI);
            poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * f1 * -20.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(f1 * -80.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees((float)i * -45.0F));
        }
    }
}