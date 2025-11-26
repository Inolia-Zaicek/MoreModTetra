package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import com.inolia_zaicek.more_mod_tetra.Event.MMTFluidCollisionEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosMagmaWalker {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosMagmaWalkerEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosMagmaWalkerName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosMagmaWalkerTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void onLivingAttack(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity()!=null) {
                LivingEntity player = event.getEntity();
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem iModularItem = (IModularItem) itemStack.getItem();
                            int effectLevel = iModularItem.getEffectLevel(itemStack, curiosMagmaWalkerEffect);
                            if (effectLevel > 0 && event.getSource() == event.getEntity().level().damageSources().hotFloor()) {
                                // 则取消伤害事件，使玩家不受伤害。
                                event.setCanceled(true);
                            }
                        }
                ));
            }
        }
    }
    /**
     * 监听 FluidCollisionEvent 事件，处理实体与流体碰撞的逻辑。
     * 这是熔岩行走能力的核心触发器。
     * @param event 流体碰撞事件。
     */
    @SubscribeEvent
    public static void onFluidCollide(MMTFluidCollisionEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity()!=null) {
                LivingEntity player = event.getEntity();
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem iModularItem = (IModularItem) itemStack.getItem();
                            float effectLevel = iModularItem.getEffectLevel(itemStack, curiosMagmaWalkerEffect);
                            if (event.getFluid().is(FluidTags.LAVA) && !player.isShiftKeyDown()) {
                                // 取消流体碰撞事件，阻止玩家因接触熔岩而受到伤害或被移除。
                                if (effectLevel > 0) {
                                    event.setCanceled(true);
                                }
                            }
                        }
                ));
            }
        }
    }
}
