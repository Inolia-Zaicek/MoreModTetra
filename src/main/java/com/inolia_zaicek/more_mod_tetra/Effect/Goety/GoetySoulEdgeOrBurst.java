package com.inolia_zaicek.more_mod_tetra.Effect.Goety;

import com.Polarice3.Goety.api.items.magic.ITotem;
import com.Polarice3.Goety.utils.TotemFinder;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.Polarice3.Goety.utils.SEHelper.*;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_burst_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_edge_Effect;

public class GoetySoulEdgeOrBurst {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("goety")) {
            //攻击者是玩家
            if (event.getAttacker() instanceof Player player) {
                int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_soul_edge_Effect);
                if (effectLevel1>0) {
                    //判断是否有灵魂方舟
                    if (getSEActive(player)) {
                        sendSEUpdatePacket(player);
                        if(decreaseSESouls(player, effectLevel1)) {
                            event.addFixedDamage(effectLevel1);
                        }
                    } else {
                        ItemStack foundStack = TotemFinder.FindTotem(player);
                        if (foundStack != null&&foundStack.getTag() != null) {
                            int soulCount = foundStack.getTag().getInt("Souls");
                            //如果图腾的nbt大于1
                            if(soulCount>=effectLevel1){
                                ITotem.decreaseSouls(foundStack, effectLevel1);
                                event.addFixedDamage(effectLevel1);
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("goety")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof Player player) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_soul_burst_Effect);
                int number = (int) (event.getAmount()*effectLevel/100);
                if (effectLevel>0) {
                    //判断是否有灵魂方舟
                    if (getSEActive(player)) {
                        sendSEUpdatePacket(player);
                        if(decreaseSESouls(player, number)) {
                            event.setAmount(event.getAmount()+number);
                        }
                    } else {
                        ItemStack foundStack = TotemFinder.FindTotem(player);
                        if (foundStack != null&&foundStack.getTag() != null) {
                            int soulCount = foundStack.getTag().getInt("Souls");
                            //如果图腾的nbt大于1
                            if(soulCount>=effectLevel){
                                ITotem.decreaseSouls(foundStack, number);
                                event.setAmount(event.getAmount()+number);
                            }
                        }
                    }
                }
            }else if (event.getSource().getDirectEntity() instanceof Player player) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_soul_burst_Effect);
                int number = (int) (event.getAmount()*effectLevel/100);
                if (effectLevel>0) {
                    //判断是否有灵魂方舟
                    if (getSEActive(player)) {
                        sendSEUpdatePacket(player);
                        if(decreaseSESouls(player, number)) {
                            event.setAmount(event.getAmount()+number);
                        }
                    } else {
                        ItemStack foundStack = TotemFinder.FindTotem(player);
                        if (foundStack != null&&foundStack.getTag() != null) {
                            int soulCount = foundStack.getTag().getInt("Souls");
                            //如果图腾的nbt大于1
                            if(soulCount>=effectLevel){
                                ITotem.decreaseSouls(foundStack, number);
                                event.setAmount(event.getAmount()+number);
                            }
                        }
                    }
                }
            }
        }
    }
}
