package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_tetra_tools.Util.MTTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.orders_Effect;

public class Orders {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(EffectLevelEvent event) {
        //是随从
        if (event.getAttacker() instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity instanceof LivingEntity livingEntity) {
            var mobList = MTTUtil.mobList(13, livingEntity);
            var playerList = MTTUtil.PlayerList(13, livingEntity);
            float level1 = 0;
            float level2 = 0;
            //取周围玩家/实体词条最大值
            for (Mob mobs : mobList) {
                level1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(mobs, orders_Effect);
            }
            for (Player player : playerList) {
                level2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, orders_Effect);
            }
            float finish = Math.max(level1,level2);
            if(finish>0){
                event.addNormalMulti(finish / 100);
            }
        }
    }
}
