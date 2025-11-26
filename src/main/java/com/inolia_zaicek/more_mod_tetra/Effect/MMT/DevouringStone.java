package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister.stoneTag;
import static com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister.stoneTag2;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class DevouringStone {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(devouringStoneEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                devouringStoneName, 0, 2, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(devouringStoneTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickItem event) {
        LivingEntity player = event.getEntity();
        // 检查实体是否是玩家
        if (!(player instanceof Player)) {
            return;
        }
        //判断tetra工具
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            if (!mainHandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))
                    && !offhandItem.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tetra", "holo")))) {
                //主手判断词条等级
                float mainEffectLevel = item.getEffectLevel(mainHandItem, devouringStoneEffect);
                // 获取主手工具的当前耐久和最大耐久
                int currentDamage = mainHandItem.getDamageValue();
                int maxDamage = mainHandItem.getMaxDamage();
                //等级大于0且当前损耗值大于0
                if (mainEffectLevel > 0&&currentDamage>0) {
                    if (offhandItem.is(stoneTag) || offhandItem.is(stoneTag2)) {
                        // 获取副手中的物品数量
                        int offhandCount = offhandItem.getCount();
                        if (offhandCount > 0) {
                            // 消耗副手中的物品
                            if (player.isShiftKeyDown()) {
                                //计算需要回复上限：损耗值＞数量*单个恢复度
                                if(currentDamage>offhandCount*mainEffectLevel) {
                                    offhandItem.shrink(offhandCount); // 消耗N个
                                    // 计算新的损耗值（原本损耗值-词条等级
                                    int newDamage = (int) (currentDamage - mainEffectLevel * offhandCount);
                                    //设置损耗值
                                    mainHandItem.setDamageValue(Math.max(0, newDamage));
                                }else{
                                    //若是小于：ceil为向上取整，即当为 损失耐久1/单个回复耐久2时，取得 需要消耗0.5，取消耗数量为1
                                    int countNumber = (int) Math.ceil(currentDamage/mainEffectLevel);
                                    offhandItem.shrink(countNumber); // 消耗N个
                                    // 计算新的损耗值（原本损耗值-词条等级
                                    int newDamage = (int) (currentDamage - mainEffectLevel * countNumber);
                                    //设置损耗值
                                    mainHandItem.setDamageValue(Math.max(0, newDamage));
                                }
                            } else {
                                offhandItem.shrink(1); // 消耗1个
                                // 计算新的损耗值（原本损耗值-词条等级
                                int newDamage = (int) (currentDamage - mainEffectLevel);
                                //设置损耗值
                                mainHandItem.setDamageValue(Math.max(0, newDamage));
                            }
                            if (!player.level().isClientSide) { // 确保只在服务器端播放音效，然后同步到客户端
                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                                        SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F + (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.2F);
                            }
                        }
                    }
                }
            }
        }
    }
}