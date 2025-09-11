package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Network.Packet.MMTPlayerFlyPacketS2C;
import com.inolia_zaicek.more_mod_tetra.Network.MMTNetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosFly {
    //默认速度
    private static final int MAX_FLY_TICKS = 10 * 20; // 静态常量：最大飞行持续时间，10 秒 * 20 刻/秒 = 200 刻。
    private static final double FLY_SPEED = 0.05; // 静态常量：飞行速度。默认是 0.05，创造模式是 0.1。

    public static final int RECHARGE_RATE_TICKS_PER_TICK = 5; // 每刻恢复 1 刻飞行时间
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        //双数据的显示代码要改
        var statGetter1 = new StatGetterEffectLevel(curiosFlyEffect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(curiosFlyEffect, 1);
        IStatGetter[] statGetters = { statGetter1,statGetter2};
        IStatFormat[] statFormats = {StatFormat.noDecimal,StatFormat.noDecimal};
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosFlyName, 0, 100, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(curiosFlyTooltip, statGetters,statFormats)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    /*
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("curios")&&event.player instanceof ServerPlayer player) {
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                        ItemStack itemStack = slotResult.stack();
                        IModularItem item = (IModularItem) itemStack.getItem();
                        //获取第一个数据————飞行时长[数值*20=数值s]
                        int time = 0;
                        time += item.getEffectLevel(itemStack, curiosFlyEffect) * 20;
                        //获取第二个数据————飞行速度[数值/100=0.01*数值]
                        float speed =0;
                        speed += (item.getEffectEfficiency(itemStack, curiosFlyEffect) )/100;
                        //有装备
                            if (speed > 0&&time > 0) {
                                MMTNetworkHandler.CHANNEL.send(
                                        PacketDistributor.PLAYER.with(() -> player), // 指定发送给当前玩家
                                        new MMTPlayerFlyPacketS2C(time, speed)
                                        //new ZeroingPlayerFlyPacketS2C(MAX_FLY_TICKS, FLY_SPEED)
                                ); // 创建并发送飞行数据包
                            }else{
                                // 通过网络发送数据包给该玩家，表示飞行能力已移除
                                MMTNetworkHandler.CHANNEL.send(
                                        PacketDistributor.PLAYER.with(() -> player), // 指定发送给当前玩家
                                        new MMTPlayerFlyPacketS2C(0, 0.0) // 将飞行 tick 和速度设置为 0
                                );
                        }
                    }
            ));
    }
}

     */
}