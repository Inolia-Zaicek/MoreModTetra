package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
public class WorkWorkWorkCuirous {
    @SubscribeEvent
    public static void event(PlayerEvent.BreakSpeed event) {
        if (ModList.get().isLoaded("curios")) {
            Entity entity = event.getEntity();
            if (entity instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem item = (IModularItem) itemStack.getItem();
                            int level = item.getEffectLevel(itemStack, workWorkWorkEffect);
                            if (level > 0) {
                                event.setNewSpeed(event.getOriginalSpeed() * (1+ (float) level /100));
                            }
                        }
                ));
            }
        }
    }
}