package com.inolia_zaicek.more_mod_tetra.Util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.IModularItem;

public class MMTEffectHelper {
    public static MMTEffectHelper INSTANCE;
    public static MMTEffectHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MMTEffectHelper();
        }
        return INSTANCE;
    }
    //单独获取主副手
    public int getMainHandEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(mainHandItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getMainHandEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float efficiency = item.getEffectEfficiency(mainHandItem, effect);
            return efficiency > 0 ? efficiency : 0;
        }
        return 0;
    }
    public int getOffHandEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (offhandItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(offhandItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getOffHandEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (offhandItem.getItem() instanceof IModularItem item) {
            float efficiency = item.getEffectEfficiency(offhandItem, effect);
            return efficiency > 0 ? efficiency : 0;
        }
        return 0;
    }
    //获取主副手等级之和
    public int getMainOffHandSumEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        int effectLevel = 0;
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            effectLevel += item.getEffectLevel(mainHandItem, effect);
        }
        if (offhandItem.getItem() instanceof IModularItem item) {
            effectLevel += item.getEffectLevel(offhandItem, effect);
        }
        return effectLevel;
    }
    public float getMainOffHandSumEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        float EffectEfficiency = 0;
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            EffectEfficiency += item.getEffectEfficiency(mainHandItem, effect);
        }
        if (offhandItem.getItem() instanceof IModularItem item) {
            EffectEfficiency += item.getEffectEfficiency(offhandItem, effect);
        }
        return EffectEfficiency;
    }
    //取主副手最大值
    public int getMainOffHandMaxEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        int maxEffectLevel = 0;
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float mainLevel = item.getEffectLevel(mainHandItem, effect);
            // 只考虑大于0的等级，并更新最大值
            if (mainLevel > 0) {
                maxEffectLevel = Math.max(maxEffectLevel, (int) mainLevel);
            }
        }
        if (offhandItem.getItem() instanceof IModularItem item) {
            float offLevel = item.getEffectLevel(offhandItem, effect);
            if (offLevel > 0) {
                maxEffectLevel = Math.max(maxEffectLevel, (int) offLevel);
            }
        }
        return maxEffectLevel;
    }
    public float getMainOffHandMaxEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        float maxEffectEfficiency = 0;
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offhandItem = livingEntity.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float mainLevel = item.getEffectEfficiency(mainHandItem, effect);
            // 只考虑大于0的等级，并更新最大值
            if (mainLevel > 0) {
                maxEffectEfficiency = Math.max(maxEffectEfficiency, (float) mainLevel);
            }
        }
        if (offhandItem.getItem() instanceof IModularItem item) {
            float offLevel = item.getEffectEfficiency(offhandItem, effect);
            if (offLevel > 0) {
                maxEffectEfficiency = Math.max(maxEffectEfficiency, (float) offLevel);
            }
        }
        return maxEffectEfficiency;
    }
    //头部护甲
    public int getHeadArmorEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack headArmorItem = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (headArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(headArmorItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getHeadArmorEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack headArmorItem = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (headArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectEfficiency(headArmorItem, effect);
            return level > 0 ?  level : 0;
        }
        return 0;
    }
    //胸甲
    public int getChestArmorEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack chestArmorItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (chestArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(chestArmorItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getChestArmorEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack chestArmorItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (chestArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectEfficiency(chestArmorItem, effect);
            return level > 0 ?  level : 0;
        }
        return 0;
    }
    //护腿
    public int getLegsArmorEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack legsArmorItem = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
        if (legsArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(legsArmorItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getLegsArmorEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack legsArmorItem = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
        if (legsArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectEfficiency(legsArmorItem, effect);
            return level > 0 ?  level : 0;
        }
        return 0;
    }
    //靴子
    public int getFeetArmorEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack bootsArmorItem = livingEntity.getItemBySlot(EquipmentSlot.FEET); 
        if (bootsArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(bootsArmorItem, effect);
            return level > 0 ? (int) level : 0;
        }
        return 0;
    }
    public float getFeetArmorEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        ItemStack bootsArmorItem = livingEntity.getItemBySlot(EquipmentSlot.FEET); 
        if (bootsArmorItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectEfficiency(bootsArmorItem, effect);
            return level > 0 ?  level : 0;
        }
        return 0;
    }
    //护甲综合
    public int getAllArmorSumEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        return getHeadArmorEffectLevel(livingEntity, effect) +
                getChestArmorEffectLevel(livingEntity, effect) +
                getLegsArmorEffectLevel(livingEntity, effect) +
                getFeetArmorEffectLevel(livingEntity, effect);
    }
    public float getAllArmorSumEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        return getHeadArmorEffectEfficiency(livingEntity, effect) +
                getChestArmorEffectEfficiency(livingEntity, effect) +
                getLegsArmorEffectEfficiency(livingEntity, effect) +
                getFeetArmorEffectEfficiency(livingEntity, effect);
    }
    //护甲最高值
    public int getAllArmorMaxEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        int headLevel = getHeadArmorEffectLevel(livingEntity, effect);
        int chestLevel = getChestArmorEffectLevel(livingEntity, effect);
        int legsLevel = getLegsArmorEffectLevel(livingEntity, effect);
        int bootsLevel = getFeetArmorEffectLevel(livingEntity, effect);
        return Math.max(Math.max(headLevel, chestLevel), Math.max(legsLevel, bootsLevel));
    }
    public float getAllArmorMaxEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        float headLevel = getHeadArmorEffectEfficiency(livingEntity, effect);
        float chestLevel = getChestArmorEffectEfficiency(livingEntity, effect);
        float legsLevel = getLegsArmorEffectEfficiency(livingEntity, effect);
        float bootsLevel = getFeetArmorEffectEfficiency(livingEntity, effect);
        return Math.max(Math.max(headLevel, chestLevel), Math.max(legsLevel, bootsLevel));
    }
    //全身
    public int getAllEffectLevel(LivingEntity livingEntity, ItemEffect effect) {
        return getHeadArmorEffectLevel(livingEntity, effect) +
                getChestArmorEffectLevel(livingEntity, effect) +
                getLegsArmorEffectLevel(livingEntity, effect) +
                getFeetArmorEffectLevel(livingEntity, effect) +
                getMainOffHandSumEffectLevel(livingEntity,effect);
    }
    public float getAllEffectEfficiency(LivingEntity livingEntity, ItemEffect effect) {
        return getHeadArmorEffectEfficiency(livingEntity, effect) +
                getChestArmorEffectEfficiency(livingEntity, effect) +
                getLegsArmorEffectEfficiency(livingEntity, effect) +
                getFeetArmorEffectEfficiency(livingEntity, effect) +
                getMainOffHandSumEffectEfficiency(livingEntity,effect);
    }
}