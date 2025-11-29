package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class UniversePower {
    // NBT 键的常量，用于计时器。
    // 建议使用 String 常量，而不是 ResourceLocation 来直接作为 NBT 键。
    private static final String UNIVERSE_POWER_TIME_NBT = MoreModTetra.MODID + ":universe_power_time";
    // NBT 键的前缀，用于标记不同伤害类型的增伤。
    private static final String DAMAGE_TYPE_TAG_PREFIX = "more_mod_tetra_damage_type";

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter1 = new StatGetterEffectLevel(universe_power_Effect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(universe_power_Effect, 1);
        IStatGetter[] statGetters = {statGetter1, statGetter2};
        IStatFormat[] statFormats = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                universe_power_Name, 0, 100, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(universe_power_Tooltip, statGetters, statFormats)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        // 确保伤害来源是 LivingEntity（例如玩家或怪物）
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            // 获取玩家的增伤等级和最大种类等级
            float damageLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, universe_power_Effect);
            int numberLevel = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, universe_power_Effect);

            // 只有当增伤和种类等级都有效时才执行
            if (damageLevel > 0 && numberLevel > 0) {
                // 获取玩家的持久数据 NBT
                CompoundTag compoundTag = livingEntity.getPersistentData();

                // 设置实体计时器。这里使用 String 常量作为键。
                // 200 ticks 约等于 10 秒 (20 ticks/sec)。
                compoundTag.putInt(UNIVERSE_POWER_TIME_NBT, 200);

                // 记录伤害类型。
                // event.getSource().type() 返回 DamageType 对象，会自动转换为字符串（如 "player", "mob", "fall"）。
                // 我们在后面加上自定义的前缀，以避免与其他模组的 NBT 键冲突。
                String damageType = event.getSource().type().toString(); // 获取伤害类型字符串
                String nbtKeyForDamageType = damageType + DAMAGE_TYPE_TAG_PREFIX; // 构建完整的 NBT 键
                // 存储这个键，值也一样
                compoundTag.putString(nbtKeyForDamageType, nbtKeyForDamageType);
                //增伤计算逻辑
                // 创建一个 List 用于存储找到的增伤标记的数量。
                List<Integer> damageTypeTagsFound = new ArrayList<>();
                // 遍历玩家持久化数据中的所有 NBT 键。
                for (String existingKey : compoundTag.getAllKeys()) {
                    // 如果键名包含我们自定义的前缀 "more_mod_tetra"
                    // 并且这个键不是我们用于计时器的键，那么它就是一个伤害类型标记。
                    if (existingKey.contains(DAMAGE_TYPE_TAG_PREFIX)) {
                        // 将 1 添加到列表中，表示找到了一种新的增伤类型。
                        damageTypeTagsFound.add(1);
                    }
                }
                // 计算增伤的“层数”（即不同伤害类型的数量）。
                // number = 0 表示基础层数，然后加上找到的实际伤害类型标记的数量。（因为造成伤害就+1，所以基础是0+1，设置成1那么基础就是2了
                int numberOfDamageTypes = 0;
                for (int ignored : damageTypeTagsFound) {
                    ++numberOfDamageTypes;
                }
                // 确保增伤层数不超过最大允许种类数。
                int finalNumberOfDamageTypes = Math.min(numberOfDamageTypes, numberLevel);
                //结算
                event.setAmount(event.getAmount() * (1 + (float)finalNumberOfDamageTypes * damageLevel / 100.0F)); // 使用 float 确保精度
            }
        }
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();

        // 再次检查玩家是否满足生效条件，避免不必要的 NBT 操作。
        // 也可以选择在这里直接访问 PersistentData，而不用重复获取 effectLevel 和 numberLevel。
        // 但保留这些检查是为了确保只在需要时执行 NBT 操作。
        float damageLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, universe_power_Effect);
        int numberLevel = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, universe_power_Effect);

        if (damageLevel > 0 && numberLevel > 0) {
            // 获取玩家的持久化数据。
            CompoundTag compoundTag = livingEntity.getPersistentData();

            // --- 计时器更新 ---
            // 检查计时器 NBT 键是否存在且大于 0。
            if (compoundTag.contains(UNIVERSE_POWER_TIME_NBT)) {
                int currentTime = compoundTag.getInt(UNIVERSE_POWER_TIME_NBT);
                if (currentTime > 0) {
                    // 计时器还在倒计时，将其减 1。
                    compoundTag.putInt(UNIVERSE_POWER_TIME_NBT, currentTime - 1);
                } else {
                    // 计时器已到 0，开始清理过期的伤害类型 NBT 标签。
                    Set<String> keys = compoundTag.getAllKeys();
                    // 创建一个列表来存储需要删除的键，以便安全地修改 compoundTag。
                    List<String> keysToRemove = new ArrayList<>();

                    // 遍历所有现有的 NBT 键。
                    for (String key : keys) {
                        // 检查键名是否包含我们的伤害类型标记前缀，并且这个键不是计时器键。
                        // 这样可以确保我们只删除由 `hurt` 方法添加的、记录具体伤害类型的 NBT 标签。
                        if (key.contains(DAMAGE_TYPE_TAG_PREFIX) && !key.equals(UNIVERSE_POWER_TIME_NBT)) {
                            keysToRemove.add(key); // 将需要删除的键添加到列表中
                        }
                    }
                    // 遍历需要删除的键列表，并从 compoundTag 中移除它们。
                    for (String keyToRemove : keysToRemove) {
                        compoundTag.remove(keyToRemove);
                    }
                }
            }
        }
    }
}