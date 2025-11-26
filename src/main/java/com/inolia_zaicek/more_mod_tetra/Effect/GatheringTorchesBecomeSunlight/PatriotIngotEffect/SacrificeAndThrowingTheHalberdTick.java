package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.SacrificeAndThrowingTheHalberdEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class SacrificeAndThrowingTheHalberdTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("torchesbecomesunlight")) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, SacrificeAndThrowingTheHalberdEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, SacrificeAndThrowingTheHalberdEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0 && player.level().getGameTime() % 10L == 0) {
                //有藏锋
                if (player.hasEffect(MMTEffectsRegister.ThrowingTheHalberd.get())) {
                    int buffLevel = player.getEffect(MMTEffectsRegister.ThrowingTheHalberd.get()).getAmplifier();
                    //每0.5秒计算一次
                    //小于6级
                    if (buffLevel < 5) {
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, buffLevel + 1, true, true, true));
                    }
                    //6级--持续给予藏锋
                    else {
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, 5, true, true, true));
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberdMax.get(), 20 * 60, 0, true, true, true));
                    }
                }
                //无藏锋
                else {
                    player.addEffect(new MobEffectInstance(MMTEffectsRegister.ThrowingTheHalberd.get(), 200, 0, true, true, true));
                }
            }
        }
    }
}