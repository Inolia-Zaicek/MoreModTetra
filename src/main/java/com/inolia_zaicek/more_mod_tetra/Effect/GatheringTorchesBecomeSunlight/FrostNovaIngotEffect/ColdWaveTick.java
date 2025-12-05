package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.coldWaveEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ColdWaveTick {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();;
        if(ModList.get().isLoaded("torchesbecomesunlight")) {
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,coldWaveEffect);
            if(effectLevel>0&&event.getEntity().tickCount % 20 == 0){
                float number =(float) effectLevel /100;
                var mobList = MMTUtil.mobList(5,livingEntity);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        if(mobs.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().freeze(), atk * number);
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                }
            }
        }
    }
}
