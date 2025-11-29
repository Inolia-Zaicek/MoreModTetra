package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MoreModTetra.MODID)
public class RipeningHalo {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ripeningHaloEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ripeningHaloName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ripeningHaloTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity player = event.getEntity();
        Level world = player.level(); // 获取当前世界，可能是 ClientLevel 或 ServerLevel
        int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,ripeningHaloEffect);
        if (effectLevel > 0 && world.getGameTime() % (Math.max(2, 20 - effectLevel)) == 0) {
            Random random = new Random();
            float rx = random.nextFloat() * 5.0F - 3.0F;
            float rz = random.nextFloat() * 5.0F - 3.0F;
            float ry = random.nextFloat() * 2.0F - 2.0F;
            float ry0 = random.nextFloat() * 2.0F;

            // **修复点1：粒子效果的处理**
            // 粒子效果通常在客户端显示。
            // 如果是服务器端，ServerLevel 会将粒子发送到客户端。
            // 如果是客户端，ClientLevel 可以直接渲染粒子。
            if (world instanceof ServerLevel serverLevel) { // 如果是服务器端，安全地转换为 ServerLevel
                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, player.getX(), player.getY(), player.getZ(), 1, 0.5D, 1.0D, 0.5D, 0.1D);
            } else { // 如果是客户端（ClientLevel），则在本地生成粒子
                world.addParticle(ParticleTypes.HAPPY_VILLAGER, player.getX(), player.getY(), player.getZ(), 0.5D, 1.0D, 0.5D); // 参数可能需要调整
            }


            // **修复点2：骨粉效果只在服务器端执行**
            // BonemealableBlock 的 performBonemeal 方法会修改方块状态，这是服务器端的行为。
            // 因此，必须确保只在服务器端调用此逻辑。
            if (!world.isClientSide) { // 只有在服务器端才执行骨粉逻辑
                BlockPos posBelow = player.blockPosition().below();
                BlockState blockStateBelow = world.getBlockState(posBelow);
                Block below = blockStateBelow.getBlock();

                if (below == Blocks.GRASS_BLOCK) {
                    BlockPos pos = new BlockPos((int) ((float) player.blockPosition().getX() + rx), (int) ((float) player.blockPosition().getY() + ry), (int) ((float) player.blockPosition().getZ() + rz));
                    BlockState blockstate = world.getBlockState(pos);
                    Block var17 = blockstate.getBlock();
                    if (var17 instanceof BonemealableBlock) {
                        BonemealableBlock igrowable = (BonemealableBlock) var17;
                        // 确保 world 是 ServerLevel 才能调用 performBonemeal
                        if (igrowable.isValidBonemealTarget(world, pos, blockstate, false) // isClientSide 应该为 false 在服务端
                                && world instanceof ServerLevel serverWorld // 再次确认是 ServerLevel
                                && igrowable.isBonemealSuccess(world, world.random, pos, blockstate)) {
                            igrowable.performBonemeal(serverWorld, world.random, pos, blockstate);
                        }
                    }
                } else {
                    BlockPos pos = new BlockPos((int) ((float) player.blockPosition().getX() + rx), (int) ((float) player.blockPosition().getY() + ry0), (int) ((float) player.blockPosition().getZ() + rz));
                    BlockState blockstate = world.getBlockState(pos);
                    Block var22 = blockstate.getBlock();
                    if (var22 instanceof BonemealableBlock) {
                        BonemealableBlock igrowable = (BonemealableBlock) var22;
                        // 确保 world 是 ServerLevel 才能调用 performBonemeal
                        if (igrowable.isValidBonemealTarget(world, pos, blockstate, false) // isClientSide 应该为 false 在服务端
                                && world instanceof ServerLevel serverWorld // 再次确认是 ServerLevel
                                && igrowable.isBonemealSuccess(world, world.random, pos, blockstate)) {
                            igrowable.performBonemeal(serverWorld, world.random, pos, blockstate);
                        }
                    }
                }
            }
        }
    }
}
