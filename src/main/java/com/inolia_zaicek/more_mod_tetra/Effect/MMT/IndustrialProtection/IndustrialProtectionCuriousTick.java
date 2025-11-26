package com.inolia_zaicek.more_mod_tetra.Effect.MMT.IndustrialProtection;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.industrialProtectionEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class IndustrialProtectionCuriousTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
        CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                slotResult -> {
                    slotResult.stack();
                    ItemStack itemStack = slotResult.stack();
                    IModularItem item = (IModularItem) itemStack.getItem();
                    int level = item.getEffectLevel(itemStack, industrialProtectionEffect);

                    if (level > 0&&player.level().getGameTime() % 10L == 0&&!player.hasEffect(MMTEffectsRegister.UnIndustrialProtection.get())){
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.IndustrialProtection.get(),100,level-1));

                    }
                }
        ));
    }
}
