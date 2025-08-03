package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.ripeningHaloTooltip;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
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
    public static void tick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel=0;
            if (mainHandItem.getItem() instanceof ModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ripeningHaloEffect);
                if (mainEffectLevel > 0) {
                    effectLevel += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof ModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, ripeningHaloEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0&&player.level().getGameTime() % (Math.max(2,20-effectLevel)) == 0) {
                Level world = player.level();
                Random random = new Random();
                float rx = random.nextFloat() * 5.0F - 3.0F;
                float rz = random.nextFloat() * 5.0F - 3.0F;
                float ry = random.nextFloat() * 2.0F - 2.0F;
                float ry0 = random.nextFloat() * 2.0F;
                ((ServerLevel)player.getCommandSenderWorld()).sendParticles(ParticleTypes.HAPPY_VILLAGER, player.getX(), player.getY(), player.getZ(), 1, (double)0.5F, (double)1.0F, (double)0.5F, 0.1);
                BlockPos posBelow = player.blockPosition().below();
                BlockState blockStateBelow = player.level().getBlockState(posBelow);
                Block below = blockStateBelow.getBlock();
                if (below == Blocks.GRASS_BLOCK) {
                    BlockPos pos = new BlockPos((int)((float)player.blockPosition().getX() + rx), (int)((float)player.blockPosition().getY() + ry), (int)((float)player.blockPosition().getZ() + rz));
                    BlockState blockstate = world.getBlockState(pos);
                    Block var17 = blockstate.getBlock();
                    if (var17 instanceof BonemealableBlock) {
                        BonemealableBlock igrowable = (BonemealableBlock)var17;
                        if (igrowable.isValidBonemealTarget(world, pos, blockstate, world.isClientSide) && world instanceof ServerLevel && igrowable.isBonemealSuccess(world, world.random, pos, blockstate)) {
                            igrowable.performBonemeal((ServerLevel)world, world.random, pos, blockstate);
                        }
                    }
                } else {
                    BlockPos pos = new BlockPos((int)((float)player.blockPosition().getX() + rx), (int)((float)player.blockPosition().getY() + ry0), (int)((float)player.blockPosition().getZ() + rz));
                    BlockState blockstate = world.getBlockState(pos);
                    Block var22 = blockstate.getBlock();
                    if (var22 instanceof BonemealableBlock) {
                        BonemealableBlock igrowable = (BonemealableBlock)var22;
                        if (igrowable.isValidBonemealTarget(world, pos, blockstate, world.isClientSide) && world instanceof ServerLevel && igrowable.isBonemealSuccess(world, world.random, pos, blockstate)) {
                            igrowable.performBonemeal((ServerLevel)world, world.random, pos, blockstate);
                        }
                    }
                }
            }
    }
}
