package com.inolia_zaicek.more_mod_tetra.Effect;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class FantasyDevour {
    //新建nbt
    public static ResourceLocation FantasyDevourNbt = MoreModTetra.getResource("fantasy_devour");
    // 定义了用于存储绑定位置 X 坐标的 NBT 标签的 ResourceLocation。
    // 使用 ResourceLocation 可以确保在多模组环境中标签的唯一性，避免命名冲突。
    private static final ResourceLocation Kill = new ResourceLocation(MoreModTetra.MODID, "fantasy_devour_kill_number");

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fantasy_devourEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fantasy_devourName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fantasy_devourTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fantasy_devourEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(FantasyDevourNbt));
                        //NBT
                        CompoundTag persistentData = mainHandItem.getOrCreateTag();
                        //击杀数
                        int kill =mainHandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //击杀数小于，+1
                        if(kill<499366777) {
                            persistentData.putInt(String.valueOf(Kill), kill + 1);
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(offhandItem, fantasy_devourEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = offhandItem.getOrCreateTag().getString(String.valueOf(FantasyDevourNbt));
                        //NBT
                        CompoundTag persistentData = offhandItem.getOrCreateTag();
                        //击杀数
                        int kill =offhandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        if(kill<499366777) {
                            persistentData.putInt(String.valueOf(Kill), kill + 1);
                        }
                    }
                }
            }
            else if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fantasy_devourEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(FantasyDevourNbt));
                        //NBT
                        CompoundTag persistentData = mainHandItem.getOrCreateTag();
                        //击杀数
                        int kill =mainHandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(kill<499366777) {
                            persistentData.putInt(String.valueOf(Kill), kill + 1);
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(offhandItem, fantasy_devourEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = offhandItem.getOrCreateTag().getString(String.valueOf(FantasyDevourNbt));
                        //NBT
                        CompoundTag persistentData = offhandItem.getOrCreateTag();
                        //击杀数
                        int kill =offhandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(kill<499366777) {
                            persistentData.putInt(String.valueOf(Kill), kill + 1);
                        }
                    }
                }
            }
    }
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof IModularItem item) {
            float mainEffectLevel = item.getEffectLevel(itemStack, fantasy_devourEffect);
            if (mainEffectLevel > 0) {
                //击杀数
                int kill = itemStack.getOrCreateTag().getInt(String.valueOf(Kill));
                if (kill > 0) {
                    event.getToolTip().add(Component.translatable("tooltip.more_mod_tetra.fantasy_devour_tooltip", kill).withStyle(Style.EMPTY.withColor(0x70c8e4)));
                }
            }
        }
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            LivingEntity entity =event.getEntity();
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offhandItem = livingEntity.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, fantasy_devourEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, fantasy_devourEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            //火种(轮回）
            int kill =Math.min(499366777, offhandItem.getOrCreateTag().getInt(String.valueOf(Kill)) + mainHandItem.getOrCreateTag().getInt(String.valueOf(Kill)) );
            if (effectLevel > 0&&kill>0) {
                //最大增幅数额*火种进度
                float finish =1+(float) (((float) effectLevel/100)*((float) kill /499366777));
                event.setAmount(event.getAmount()*(finish));
            }
        }
    }
}