package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.emergencyRescueEffect;
import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.entityResonanceEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EntityResonanceCurious {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("curios")) {
            Player player = event.player;
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();

                        if (event.player.tickCount % 10 == 0) {
                            ItemStack itemStack = slotResult.stack();
                            IModularItem item = (IModularItem) itemStack.getItem();
                            int level = item.getEffectLevel(itemStack, entityResonanceEffect);

                            if (level > 0) {
                                var mobList = MMTUtil.mobList(21, player);
                                for (Mob mobs : mobList) {
                                    if (mobs != null) {
                                        mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING,200, 0));
                                        var map = mobs.getActiveEffectsMap();
                                        map.put(MobEffects.GLOWING, new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                                        mobs.addEffect(new MobEffectInstance(MobEffects.GLOWING,200, 0));
                                        if(level>1){
                                            mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200, level-1));
                                            map.put(MobEffects.MOVEMENT_SLOWDOWN, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, level-1));
                                            mobs.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200, level-1));
                                        }
                                    }
                                }
                            }
                        }
                    }
            ));
        }
    }
}
