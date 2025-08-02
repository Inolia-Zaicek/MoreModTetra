package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Curious;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.radiationAbsorptionEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class RadiationAbsorptionCurious {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("curios")&&ModList.get().isLoaded("alexscaves")) {
            Player player = event.player;
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            ModularItem item = (ModularItem) itemStack.getItem();
                            int level = item.getEffectLevel(itemStack, radiationAbsorptionEffect);

                            if (level > 0 &&player.hasEffect(ACEffectRegistry.IRRADIATED.get())) {
                                player.removeEffect(ACEffectRegistry.IRRADIATED.get());
                        }
                    }
            ));
        }
    }
}