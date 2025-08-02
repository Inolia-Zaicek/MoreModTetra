package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class RepellingEffectTrait {
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            Player player = event.player;
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof ModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                        ItemStack itemStack = slotResult.stack();
                        ModularItem curiousItem = (ModularItem) itemStack.getItem();
                        //获取一下玩家主副手
                        ItemStack mainHandItem = player.getMainHandItem();
                        ItemStack offhandItem = player.getOffhandItem();
                        int effectLevel = 0;
                        effectLevel += curiousItem.getEffectLevel(itemStack, repellingEffectTraitEffect);
                        if (mainHandItem.getItem() instanceof ModularItem item) {
                            float mainEffectLevel = item.getEffectLevel(mainHandItem, repellingEffectTraitEffect);
                            if (mainEffectLevel > 0) {
                                effectLevel += (int) mainEffectLevel;
                            }
                        }
                        if (offhandItem.getItem() instanceof ModularItem item) {
                            float offEffectLevel = item.getEffectLevel(offhandItem, repellingEffectTraitEffect);
                            if (offEffectLevel > 0) {
                                effectLevel += (int) offEffectLevel;
                            }
                        }
                        if (effectLevel > 0 && event.player.tickCount % 10 == 0) {
                            player.addEffect(new MobEffectInstance(MMTEffectsRegister.L2Repelling.get(), 100, effectLevel - 1, true, true, true));
                        }
                    }
            ));
        }
    }
}
