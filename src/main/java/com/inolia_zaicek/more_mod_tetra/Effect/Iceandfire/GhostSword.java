package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Network.GhostSwordChannel;
import com.inolia_zaicek.more_mod_tetra.Network.Packet.GhostSwordPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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
public class GhostSword {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ghostSwordEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ghostSwordName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ghostSwordTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(PlayerInteractEvent.LeftClickEmpty event) {
        if(ModList.get().isLoaded("iceandfire")) {
            var playerEntity = event.getEntity();
            ItemStack mainHandItem = playerEntity.getMainHandItem();
            int nEffectLevel = 0;
            int ubwEffectLevel = 1;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, ghostSwordEffect);
                if (mainEffectLevel > 0) {
                    nEffectLevel += (int) mainEffectLevel;
                }
            }
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, unlimitedPhantasmalBladeWorksEffect);
                if (mainEffectLevel > 0) {
                    ubwEffectLevel += (int) mainEffectLevel;
                }
            }
            //基础等级50
            if (nEffectLevel > 0) {
                // 如果在冷却中，则退出方法，不执行任何操作。
                if (playerEntity.getCooldowns().isOnCooldown(mainHandItem.getItem())) {
                    return;
                }
                //发包
                    GhostSwordChannel.CHANNEL.sendToServer(new GhostSwordPacket(nEffectLevel, ubwEffectLevel));

            }
        }
    }

}
