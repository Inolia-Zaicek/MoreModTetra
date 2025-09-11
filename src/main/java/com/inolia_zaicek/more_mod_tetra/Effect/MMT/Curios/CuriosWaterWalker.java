package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Event.MMTFluidCollisionEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosWaterWalker {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosWaterWalkerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosWaterWalkerName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosWaterWalkerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void onFluidCollide(MMTFluidCollisionEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem iModularItem = (IModularItem) itemStack.getItem();
                            float effectLevel = iModularItem.getEffectLevel(itemStack, curiosWaterWalkerEffect);
                            if (!event.getFluid().is(FluidTags.WATER) || player.isShiftKeyDown())
                                return;
                            // 取消流体碰撞事件，阻止玩家因接触熔岩而受到伤害或被移除。
                            if(effectLevel>0) {
                                event.setCanceled(true);
                            }
                        }
                ));
            }
        }
    }
}
