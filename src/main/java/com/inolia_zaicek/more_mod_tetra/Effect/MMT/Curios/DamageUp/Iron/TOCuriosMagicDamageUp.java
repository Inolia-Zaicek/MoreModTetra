package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.gametechbc.traveloptics.util.TravelopticsDamageTypes;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class TOCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.getSource().getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    float effectLevel = item.getEffectLevel(itemStack, curiosMagicDamageUpEffect);
                                    if (effectLevel > 0) {
                                        if (event.getSource().is(TravelopticsDamageTypes.AQUA_MAGIC)||event.getSource().is(TravelopticsDamageTypes.VOIDSTRIKE_REAPER_BONUS_DAMAGE)
                                                ||event.getSource().is(TravelopticsDamageTypes.NULLFLARE_BLAST)) {
                                            float finish =event.getAmount()*(1+effectLevel/100);
                                            event.setAmount(finish);
                                        }
                                    }
                                }
                        )
                );
            }else if (event.getSource().getDirectEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    float effectLevel = item.getEffectLevel(itemStack, curiosMagicDamageUpEffect);
                                    if (effectLevel > 0) {
                                        if (event.getSource().is(TravelopticsDamageTypes.AQUA_MAGIC)||event.getSource().is(TravelopticsDamageTypes.VOIDSTRIKE_REAPER_BONUS_DAMAGE)
                                                ||event.getSource().is(TravelopticsDamageTypes.NULLFLARE_BLAST)) {
                                            float finish =event.getAmount()*(1+effectLevel/100);
                                            event.setAmount(finish);
                                        }
                                    }
                                }
                        )
                );
            }
        }
    }
}
