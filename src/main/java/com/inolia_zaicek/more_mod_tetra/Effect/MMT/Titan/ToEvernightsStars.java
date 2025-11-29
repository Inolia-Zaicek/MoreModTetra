package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

public class ToEvernightsStars {
    //新建nbt
    public static ResourceLocation ToEvernightsStarsNbt = MoreModTetra.getResource("to_evernights_stars");
    // 定义了用于存储绑定位置 X 坐标的 NBT 标签的 ResourceLocation。
    // 使用 ResourceLocation 可以确保在多模组环境中标签的唯一性，避免命名冲突。
    private static final ResourceLocation HP = new ResourceLocation(MoreModTetra.MODID, "to_evernights_stars_health");

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(toEvernightsStarsEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                toEvernightsStarsName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(toEvernightsStarsTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            float effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item
            &&item.getEffectLevel(mainHandItem, toEvernightsStarsEffect)>0) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, toEvernightsStarsEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += mainEffectLevel;
                    //NBT
                    CompoundTag persistentData = mainHandItem.getOrCreateTag();
                    //获取生命值数额
                    float healthNbt = mainHandItem.getOrCreateTag().getInt(String.valueOf(HP));
                    //当前血量比例小于数值比例
                    if (effectLevel > 0) {
                        float hp = player.getHealth();
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        //未记录生命值
                        if (healthNbt == 0) {
                            persistentData.putInt(String.valueOf(HP), (int) hp);
                        } else {
                            //生命值相等或者记录的小于当前
                            if (healthNbt == hp || healthNbt <= hp) {
                                event.setAmount(finish / 2);
                            } else {
                                //记录生命值大于当前
                                player.heal((healthNbt - hp) * effectLevel / 100);
                                event.setAmount(finish);
                            }
                        }
                        //反正最后都会记录一下
                        persistentData.putInt(String.valueOf(HP), (int) hp);
                    }
                }
            }else if (offHandItem.getItem() instanceof IModularItem item
                    &&item.getEffectLevel(offHandItem, toEvernightsStarsEffect)>0) {
                float offEffectLevel = item.getEffectLevel(offHandItem, toEvernightsStarsEffect);
                if (offEffectLevel > 0) {
                    effectLevel += offEffectLevel;
                    //NBT
                    CompoundTag persistentData = offHandItem.getOrCreateTag();
                    //获取生命值数额
                    float healthNbt = offHandItem.getOrCreateTag().getInt(String.valueOf(HP));
                    //当前血量比例小于数值比例
                    if (effectLevel > 0) {
                        float hp = player.getHealth();
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        //未记录生命值
                        if (healthNbt == 0) {
                            persistentData.putInt(String.valueOf(HP), (int) hp);
                        } else {
                            //生命值相等或者记录的小于当前
                            if (healthNbt == hp || healthNbt <= hp) {
                                event.setAmount(finish / 2);
                            } else {
                                //记录生命值大于当前
                                player.heal((healthNbt - hp) * effectLevel / 100);
                                event.setAmount(finish);
                            }
                        }
                        //反正最后都会记录一下
                        persistentData.putInt(String.valueOf(HP), (int) hp);
                    }
                }
            }
        }
    }
}
