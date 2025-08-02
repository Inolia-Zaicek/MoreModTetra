package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Storm;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class UnceasingStormTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            Player player = event.player;
            if(player.hasEffect(MMTEffectsRegister.StormEye.get())&&event.player.tickCount % 20 == 0){
                int buffLevel =Math.max(1, player.getEffect(MMTEffectsRegister.StormEye.get()).getAmplifier() );
                var mobList = MMTUtil.mobList(5,player);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        //获取伤害类型
                        mobs.invulnerableTime=0;
                        mobs.setLastHurtByPlayer(player);
                        float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                        mobs.hurt(mobs.damageSources().lightningBolt(),atk*buffLevel*0.3f);
                        mobs.setLastHurtByPlayer(player);
                    }
                }
            }
        }
    }
}
