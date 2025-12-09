package com.inolia_zaicek.more_mod_tetra.Effect.Goety;

import com.Polarice3.Goety.api.items.magic.ITotem;
import com.Polarice3.Goety.utils.TotemFinder;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.Polarice3.Goety.utils.SEHelper.*;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.goety_soul_absorb_Effect;

public class GoetySoulAbsorb {
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("goety")) {
            //攻击者是玩家
            if (event.getAttacker() instanceof Player player) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_soul_absorb_Effect);
                if (effectLevel>0) {
                    //判断是否有灵魂方舟
                    if (getSEActive(player)) {
                        sendSEUpdatePacket(player);
                        increaseSESouls(player, effectLevel);
                    } else {
                        ItemStack foundStack = TotemFinder.FindTotem(player);
                        if (foundStack != null&&foundStack.getTag() != null) {
                                ITotem.increaseSouls(foundStack, effectLevel);
                        }
                    }
                }
            }
        }
    }
}
