package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.workWorkWorkEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class WorkWorkWorkCuirous {
    @SubscribeEvent
    public static void event(PlayerEvent.BreakSpeed event) {
        if (ModList.get().isLoaded("curios")) {
            Entity entity = event.getEntity();
            if (entity instanceof Player player) {
                int level = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(player, workWorkWorkEffect);
                if (level > 0) {
                    event.setNewSpeed(event.getOriginalSpeed() * (1 + (float) level / 100));
                }
            }
        }
    }
}