package com.inolia_zaicek.more_mod_tetra.Effect.Botania;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import vazkii.botania.api.mana.ManaItemHandler;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ManaRegeneration {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(manaRegenerationEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                manaRegenerationName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(manaRegenerationTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if(ModList.get().isLoaded("botania")) {
            Player player = event.player;
            int effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, manaRegenerationEffect);
            if (effectLevel > 0&&player.level().getGameTime() % 20L == 0){
                ItemStack itemStack = player.getInventory().getSelected();
                ManaItemHandler.instance().dispatchManaExact(itemStack, player, effectLevel, true);
            }
        }
    }
}
