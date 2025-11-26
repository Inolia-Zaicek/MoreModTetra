package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core;

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
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.underoceanEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class UnderoceanTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("alexscaves")) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, underoceanEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, underoceanEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0) {
                if (player.level().getGameTime() % 10L == 0) {
                    player.addEffect(new MobEffectInstance(ACEffectRegistry.DEEPSIGHT.get(), 100, effectLevel - 1, true, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, effectLevel - 1, true, true, true));
                    player.addEffect(new MobEffectInstance(ACEffectRegistry.BUBBLED.get(), 100, effectLevel - 1, true, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, effectLevel - 1, true, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 100, effectLevel - 1, true, true, true));
                }
            }
        }
    }
}