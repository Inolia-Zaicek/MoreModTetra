package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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

import java.util.List;
import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class PyricCorpus {
    //新建nbt
    public static ResourceLocation CoreFlamesNbt = MoreModTetra.getResource("pyric_corpus_coreflames");
    // 定义了用于存储绑定位置 X 坐标的 NBT 标签的 ResourceLocation。
    // 使用 ResourceLocation 可以确保在多模组环境中标签的唯一性，避免命名冲突。
    private static final ResourceLocation Number = new ResourceLocation(MoreModTetra.MODID, "pyric_corpus_coreflames_number");
    private static final ResourceLocation Kill = new ResourceLocation(MoreModTetra.MODID, "pyric_corpus_coreflames_kill_number");

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(pyricCorpusEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                pyricCorpusName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(pyricCorpusTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void entityKilled(LivingDeathEvent event) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, pyricCorpusEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(CoreFlamesNbt));
                        //NBT
                        CompoundTag persistentData = mainHandItem.getOrCreateTag();
                        //火种
                        int coreFlames =mainHandItem.getOrCreateTag().getInt(String.valueOf(Number));
                        //击杀数
                        int kill =mainHandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(coreFlames<33550336) {
                            if (kill < 11) {
                                persistentData.putInt(String.valueOf(Kill), kill + 1);
                            } else {
                                persistentData.putInt(String.valueOf(Kill), 0);
                                persistentData.putInt(String.valueOf(Number), coreFlames + 1);
                            }
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(offhandItem, pyricCorpusEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = offhandItem.getOrCreateTag().getString(String.valueOf(CoreFlamesNbt));
                        //NBT
                        CompoundTag persistentData = offhandItem.getOrCreateTag();
                        //火种
                        int coreFlames =offhandItem.getOrCreateTag().getInt(String.valueOf(Number));
                        //击杀数
                        int kill =offhandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(coreFlames<33550336) {
                            if (kill < 11) {
                                persistentData.putInt(String.valueOf(Kill), kill + 1);
                            } else {
                                persistentData.putInt(String.valueOf(Kill), 0);
                                persistentData.putInt(String.valueOf(Number), coreFlames + 1);
                            }
                        }
                    }
                }
            }
            else if (event.getSource().getEntity() instanceof LivingEntity player) {
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, pyricCorpusEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = mainHandItem.getOrCreateTag().getString(String.valueOf(CoreFlamesNbt));
                        //NBT
                        CompoundTag persistentData = mainHandItem.getOrCreateTag();
                        //火种
                        int coreFlames =mainHandItem.getOrCreateTag().getInt(String.valueOf(Number));
                        //击杀数
                        int kill =mainHandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(coreFlames<33550336) {
                            if (kill < 11) {
                                persistentData.putInt(String.valueOf(Kill), kill + 1);
                            } else {
                                persistentData.putInt(String.valueOf(Kill), 0);
                                persistentData.putInt(String.valueOf(Number), coreFlames + 1);
                            }
                        }
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(offhandItem, pyricCorpusEffect);
                    if (mainEffectLevel > 0) {
                        // 获取工具的持久化 NBT 数据——————————————————————————————————————————【名字】
                        String persistentNBT = offhandItem.getOrCreateTag().getString(String.valueOf(CoreFlamesNbt));
                        //NBT
                        CompoundTag persistentData = offhandItem.getOrCreateTag();
                        //火种
                        int coreFlames =offhandItem.getOrCreateTag().getInt(String.valueOf(Number));
                        //击杀数
                        int kill =offhandItem.getOrCreateTag().getInt(String.valueOf(Kill));
                        //火种小于完美数
                        if(coreFlames<33550336) {
                            if (kill < 11) {
                                persistentData.putInt(String.valueOf(Kill), kill + 1);
                            } else {
                                persistentData.putInt(String.valueOf(Kill), 0);
                                persistentData.putInt(String.valueOf(Number), coreFlames + 1);
                            }
                        }
                    }
                }
            }
    }
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof IModularItem item) {
            float mainEffectLevel = item.getEffectLevel(itemStack, pyricCorpusEffect);
            if (mainEffectLevel > 0) {
                //火种
                int coreFlames = itemStack.getOrCreateTag().getInt(String.valueOf(Number));
                //击杀数
                int kill = itemStack.getOrCreateTag().getInt(String.valueOf(Kill));
                if (kill > 0) {
                    event.getToolTip().add(Component.translatable("tooltip.more_mod_tetra.kills_tooltip", kill).withStyle(Style.EMPTY.withColor(0x6ED0DE)));
                }
                if (coreFlames > 0) {
                    event.getToolTip().add(Component.translatable("tooltip.more_mod_tetra.coreflames_tooltip", coreFlames).withStyle(Style.EMPTY.withColor(0xFFF000)));
                }
            }
        }
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            LivingEntity entity =event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, pyricCorpusEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, pyricCorpusEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            //火种(轮回）
            int coreFlames =Math.min(33550336, offhandItem.getOrCreateTag().getInt(String.valueOf(Number)) + mainHandItem.getOrCreateTag().getInt(String.valueOf(Number)) );
            if (effectLevel > 0&&coreFlames>0) {
                //最大增幅数额*火种进度
                float finish =1+(float) (((float) effectLevel/100)*((float) coreFlames /33550336));
                event.setAmount(event.getAmount()*(finish));
            }
        }
    }
}