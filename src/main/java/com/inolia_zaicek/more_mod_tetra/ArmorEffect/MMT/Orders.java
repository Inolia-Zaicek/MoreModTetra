package com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.ArmorEffect.MMT.Concept.DeathAndTick.pure_time;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.orders_Effect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.pure_Effect;

public class Orders {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(EffectLevelEvent event) {
        //是随从
        if (event.getAttacker() instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null && ownableEntity instanceof LivingEntity livingEntity
                && ownableEntity.getOwner() != event.getAttacked()) {
            var mobList = MMTUtil.mobList(13, livingEntity);
            var playerList = MMTUtil.PlayerList(13, livingEntity);
            float level1 = 0;
            float level2 = 0;
            //取周围玩家/实体词条最大值
            for (Mob mobs : mobList) {
                level1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(mobs, orders_Effect);
            }
            for (Player player : playerList) {
                level2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, orders_Effect);
            }
            float finish = Math.max(level1, level2);
            if (finish > 0) {
                event.addNormalMulti(finish / 100);
            }
        }
        //赤诚
        LivingEntity attacker = event.getAttacker();
        float effectLevel = Math.max(MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacker, pure_Effect),
                MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacker, pure_Effect));//增伤最大值
        float effectEfficiency = Math.max(MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(attacker, pure_Effect),
                MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(attacker, pure_Effect));//概率
        float time = (float) attacker.getPersistentData().getInt(pure_time) / (20 * 2);
        float number = 0;
        if (effectLevel > 0 && effectEfficiency > 0) {
            attacker.addEffect(new MobEffectInstance(MMTEffectsRegister.Pure.get(), (int) (10 * 20), 0));
            Random random = new Random();
            if (time > 0) {
                //距离5s的进度
                number += (time / 5) * effectLevel;
            }
            //时间大于2
            if (time > 2 && random.nextInt(100) <= effectEfficiency) {
                number *= 1.5F;
            }
            if (number > 0) {
                event.addNormalMulti(number);
            }
        }
    }
}