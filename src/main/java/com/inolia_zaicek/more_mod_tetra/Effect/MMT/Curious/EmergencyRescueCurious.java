package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.ModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.emergencyRescueEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.minerLanternEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EmergencyRescueCurious {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("curios")) {
            Player player = event.player;
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            ModularItem item = (ModularItem) itemStack.getItem();
                            int level = item.getEffectLevel(itemStack, emergencyRescueEffect);

                            if (level > 0) {
                                if (player.getCooldowns().isOnCooldown(item.getItem()))
                                    return;
                                if (player.isInLava()) {
                                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20*level, 0, true, true, true));
                                    player.getCooldowns().addCooldown(item.getItem(), 20 * 15);
                                }
                        }
                    }
            ));
        }
    }
}
