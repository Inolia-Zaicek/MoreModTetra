package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTTargetMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Predicate;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosProjectileTrackingEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.projectileTrackingEffect;
import static com.inolia_zaicek.more_mod_tetra.Modular.ModularRing.Tracking_Mode;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EntityJoinEvent {

    // 关键部分：当实体加入到某个层级（世界）时触发
    @SubscribeEvent
    public static void addMode(net.minecraftforge.event.entity.EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Projectile projectile) {
            // 如果实体是投射物（如箭），尝试找到它的发射者
            Entity owner = projectile.getOwner();
            if (owner instanceof LivingEntity player &&
                    // 对追踪词条进行判断
                    MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosProjectileTrackingEffect) > 0
            ) {
                // 获取玩家存储的追踪模式（已修改存储在玩家PersistentData中）
                // 你需要传入正确的玩家对象，这里假设已获取到player
                CompoundTag playerData = player.getPersistentData();
                int mode = playerData.getInt(Tracking_Mode);

                // 根据玩家的模式设置目标筛选规则
                Predicate targetPredicate;
                switch (mode) {
                    //锁定非玩家实体
                    case 1 -> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player)
                            //自己随从不锁
                            && !(target instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() != null && ownableEntity.getOwner() == player);
                    //锁定敌对实体
                    case 2 -> targetPredicate = (target) -> target instanceof Enemy
                            //自己随从不锁
                            && !(target instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() != null && ownableEntity.getOwner() == player);
                    //锁定所有实体
                    default -> targetPredicate = (target) -> target instanceof LivingEntity;
                }

                Predicate<Entity> targetMode = targetPredicate;

                if (projectile instanceof MMTTargetMode modeObj) {
                    modeObj.mmt$setMode(targetMode);
                }
            }
            //如果是工具
            else if (owner instanceof LivingEntity player &&
                    // 对追踪词条进行判断
                    MMTEffectHelper.getInstance().getAllEffectLevel(player, projectileTrackingEffect)>0
            ) {
                // 获取玩家存储的追踪模式（已修改存储在玩家PersistentData中）
                // 你需要传入正确的玩家对象，这里假设已获取到player
                CompoundTag playerData = player.getPersistentData();
                int mode = playerData.getInt(Tracking_Mode);

                // 根据玩家的模式设置目标筛选规则
                Predicate targetPredicate;
                switch (mode) {
                    //固定锁定
                    case 1 -> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player) && !(target!=player);
                    case 2 -> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player) && !(target!=player);
                    default -> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player) && !(target!=player);
                }

                Predicate<Entity> targetMode = targetPredicate;

                if (projectile instanceof MMTTargetMode modeObj) {
                    modeObj.mmt$setMode(targetMode);
                }
            }
        }
    }
}
