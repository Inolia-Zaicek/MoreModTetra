package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.hideBladeEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class HideBladeCuriousTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("curios")) {
            Player player = event.player;
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                        ItemStack itemStack = slotResult.stack();
                        IModularItem item = (IModularItem) itemStack.getItem();
                        int effectLevel = item.getEffectLevel(itemStack, hideBladeEffect);
                        if (effectLevel > 0 && player.level().getGameTime() % 20L == 0) {
                            //有藏锋
                            if (player.hasEffect(MMTEffectsRegister.HideBlade.get())) {
                                int buffLevel = player.getEffect(MMTEffectsRegister.HideBlade.get()).getAmplifier();
                                //每0.5秒计算一次
                                //小于6级
                                if (buffLevel < 5) {
                                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, buffLevel + 1, true, true, true));
                                }
                                //6级--持续给予藏锋
                                else {
                                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 5, true, true, true));
                                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBladeMax.get(), 20 * 60, 0, true, true, true));
                                }
                            }
                            //无藏锋
                            else {
                                player.addEffect(new MobEffectInstance(MMTEffectsRegister.HideBlade.get(), 200, 0, true, true, true));
                            }
                        }
                    }
            ));
    }
}
}