package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.iAmStormEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class IAmStormTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel=0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, iAmStormEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, iAmStormEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if (effectLevel > 0&&player.level().getGameTime() % 20L == 0){
                var mobList = MMTUtil.mobList(7,player);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        var map = mobs.getActiveEffectsMap();
                        if(mobs.hasEffect(ModEffect.EFFECTWETNESS.get())){
                            int buffLevel = mobs.getEffect(ModEffect.EFFECTWETNESS.get()).getAmplifier();
                            map.put(ModEffect.EFFECTWETNESS.get(),
                                    new MobEffectInstance(ModEffect.EFFECTWETNESS.get(), 200, Math.min(9,buffLevel+1) ));
                        }else{
                            map.put(ModEffect.EFFECTWETNESS.get(),
                                    new MobEffectInstance(ModEffect.EFFECTWETNESS.get(), 200, 0));
                        }
                    }
                }
            }
        }
    }
}
