package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect;

import com.freefish.torchesbecomesunlight.server.init.EffectHandle;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.coldWaveEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ColdWaveTick {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if(ModList.get().isLoaded("torchesbecomesunlight")) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, coldWaveEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, coldWaveEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                }
            }
            if(effectLevel>0&&event.player.tickCount % 20 == 0){
                float number =(float) effectLevel /100;
                var mobList = MMTUtil.mobList(5,player);
                for (Mob mobs:mobList){
                    if(mobs!=null) {
                        if(mobs.hasEffect(EffectHandle.FREEZE.get())) {
                            //获取伤害类型
                            mobs.invulnerableTime = 0;
                            mobs.setLastHurtByPlayer(player);
                            float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().freeze(), atk * number);
                            mobs.invulnerableTime = 0;
                            mobs.setLastHurtByPlayer(player);
                        }
                    }
                }
            }
        }
    }
}
