package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.eterniumDurabilityEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.recoveryEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EterniumDurabilityTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            if(player.level().getGameTime() % 20L == 0) {
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, eterniumDurabilityEffect);
                    if (mainEffectLevel > 0) {
                        //当前耐久
                        int currentDurability = mainHandItem.getDamageValue();
                        int maxDurability = mainHandItem.getMaxDamage();
                        //设置耐久：最大为最高耐久，不高于最大则是当前耐久+最大*比例
                        mainHandItem.setDamageValue(Math.min(maxDurability, currentDurability + maxDurability * (int) (mainEffectLevel / 100)));
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, eterniumDurabilityEffect);
                    if (offEffectLevel > 0) {
                        int currentDurability = offhandItem.getDamageValue();
                        int maxDurability = offhandItem.getMaxDamage();
                        mainHandItem.setDamageValue(Math.min(maxDurability, currentDurability + maxDurability * (int) (offEffectLevel / 100)));
                    }
                }
            }
    }
}
