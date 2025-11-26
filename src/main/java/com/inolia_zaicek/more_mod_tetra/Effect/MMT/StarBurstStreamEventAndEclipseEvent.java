package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ItemModularHandheld;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class StarBurstStreamEventAndEclipseEvent {
    //实体互动
    @SubscribeEvent
    public static void aaa(PlayerInteractEvent.EntityInteract event) {
        Player player =event.getEntity();
        Entity target =event.getTarget();
        if(InteractionHand.OFF_HAND.equals(event.getHand())&&target instanceof LivingEntity livingEntity){
            ItemStack offhandItem = player.getOffhandItem();
            if (offhandItem.getItem() instanceof ItemModularHandheld item) {
                int jabLevel = item.getEffectLevel(offhandItem, ItemEffect.jab);
                int starBurstStreamLevel = item.getEffectLevel(offhandItem, starBurstStreamEffect);
                int eclipseLevel = item.getEffectLevel(offhandItem, eclipseEffect);
                int eclipseStarBurstStreamLevel = item.getEffectLevel(offhandItem, eclipseStarBurstStreamEffect);
                if(jabLevel>0){
                    if (player.getCooldowns().isOnCooldown(offhandItem.getItem()))
                        return;
                    //主动触发刺击效果
                    if(starBurstStreamLevel>0||eclipseLevel>0||eclipseStarBurstStreamLevel>0) {
                        //item.jabEntity(offhandItem,jabLevel,player,livingEntity);
                    }
                }
            }
        }
    }
}

