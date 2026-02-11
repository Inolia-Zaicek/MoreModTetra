package com.inolia_zaicek.more_mod_tetra.Modular.Goety;

import com.Polarice3.Goety.api.items.magic.IWand;
import com.Polarice3.Goety.common.items.magic.DarkWand;
import com.Polarice3.Goety.config.SpellConfig;
import com.Polarice3.Goety.utils.SEHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.data.EffectData;
import se.mickelus.tetra.module.data.ItemProperties;
import se.mickelus.tetra.module.data.SynergyData;
import se.mickelus.tetra.module.data.ToolData;
import se.mickelus.tetra.module.schematic.RepairSchematic;
import se.mickelus.tetra.properties.AttributeHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curios_goety_soul_cost_discount_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_cost_discount_Effect;

public class ModularGoetyWandItem  extends DarkWand implements IModularItem {
    private final Cache<String, Multimap<Attribute, AttributeModifier>> attributeCache;
    private final Cache<String, ToolData> toolCache;
    private final Cache<String, EffectData> effectCache;
    private final Cache<String, ItemProperties> propertyCache;
    protected SynergyData[] synergies;
    public final static String identifier = "modular_dark_wand";
    public ModularGoetyWandItem() {
        this.attributeCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.toolCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.effectCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.propertyCache = CacheBuilder.newBuilder().maximumSize(1000L).expireAfterWrite(5L, TimeUnit.MINUTES).build();
        this.synergies = new SynergyData[0];
        DataManager.instance.moduleData.onReload(this::clearCaches);
        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.addAll(this.getTooltip(stack, world, flag));
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

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public void clearCaches() {
        this.attributeCache.invalidateAll();
        this.toolCache.invalidateAll();
        this.effectCache.invalidateAll();
        this.propertyCache.invalidateAll();

    }


    ///主要部件
    @Override
    public String[] getMajorModuleKeys(ItemStack itemStack) {
        return new String[]{"modular_dark_wand/core","modular_dark_wand/end","modular_dark_wand/handle","modular_dark_wand/guard","modular_dark_wand/socket"};
    }
    /// 位置
    @Override
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        return new GuiModuleOffsets(-20, 9, 4, 0, 4, 18, -10, -4, -10, 23, -12, 27);
    }
    @Override
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        return new GuiModuleOffsets(-12, -3, -12, 27);
    }
    /// 次要部件
    @Override
    public String[] getMinorModuleKeys(ItemStack itemStack) {
        return new String[]{};
    }
    /// 必须部件
    @Override
    public String[] getRequiredModules(ItemStack itemStack) {
        return new String[]{"modular_dark_wand/core","modular_dark_wand/end","modular_dark_wand/handle","modular_dark_wand/guard"};
    }
    @Override
    public int getHoneBase(ItemStack itemStack) {
        return 450;
    }

    @Override
    public int getHoneIntegrityMultiplier(ItemStack itemStack) {
        return 200;
    }
    //是否可获得打磨值
    @Override
    public boolean canGainHoneProgress(ItemStack itemStack) {
        return true;
    }

    @Override
    public Cache<String, Multimap<Attribute, AttributeModifier>> getAttributeModifierCache() {
        return attributeCache;
    }

    @Override
    public Cache<String, EffectData> getEffectDataCache() {
        return effectCache;
    }

    @Override
    public Cache<String, ItemProperties> getPropertyCache() {
        return propertyCache;
    }

    @Override
    public SynergyData[] getAllSynergyData(ItemStack itemStack) {
        return synergies;
    }
    //诡厄灵魂
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
    /// 重命名
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return Component.literal(this.getItemName(stack));
    }
}