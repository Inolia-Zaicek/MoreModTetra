package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.reverseMirrorEffect;

public class ReverseMirrorHurtCurious {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity() instanceof Player player && !(event.getSource().getEntity() instanceof Player)) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    int level = item.getEffectLevel(itemStack, reverseMirrorEffect);
                                    if (level > 1 && event.getSource() == player.damageSources().magic()) {
                                        event.setAmount(event.getAmount() * (1 - level * 0.1f));
                                    }
                                }
                        )
                );
            }
        }
    }
}
