package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
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


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class Growing {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(growingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                growingName, 0, 2, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(growingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickBlock event) {
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
                float mainEffectLevel = item.getEffectLevel(mainHandItem, growingEffect);
                if (mainEffectLevel > 0) {
                    Level level = event.getLevel();
                    BlockPos pos = event.getPos();
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();
                    // 获取主手工具的损耗值
                    int currentDamage = mainHandItem.getDamageValue();
                    int newDamage =  (currentDamage + 9);
                    // 检测是否为农作物（包括原版作物）【最大耐久-损耗值=剩余耐久＞10】
                    if (block instanceof CropBlock crop&&(mainHandItem.getMaxDamage()-mainHandItem.getDamageValue())>10) {
                        int cropAge = crop.getAge(state);
                        int cropMaxAge = crop.getMaxAge();
                        if (cropAge < cropMaxAge) {
                            //新的生长时间
                            int newAge = (int) Math.min(cropAge + mainEffectLevel, cropMaxAge);
                            // 更新方块状态
                            level.setBlock(pos, crop.getStateForAge(newAge), Block.UPDATE_ALL);
                            // 播放效果
                            level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                            mainHandItem.setDamageValue(Math.max(1, newDamage));
                        }
                    }
                }
            }
        }
    }
}