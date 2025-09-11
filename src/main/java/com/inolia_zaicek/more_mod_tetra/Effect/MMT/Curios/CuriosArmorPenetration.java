package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;
import java.util.UUID;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class CuriosArmorPenetration {
    private static final UUID uuid = UUID.fromString("CFB465FC-9BF4-2283-E255-88864219DE97");
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosArmorPenetrationEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosArmorPenetrationName, 0, 10, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosArmorPenetrationTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                                (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                                slotResult -> {
                                    slotResult.stack();
                                    ItemStack itemStack = slotResult.stack();
                                    IModularItem item = (IModularItem) itemStack.getItem();
                                    int effectLevel = item.getEffectLevel(itemStack, curiosArmorPenetrationEffect);
                                    if (effectLevel > 0) {
                                        Optional.of(event.getEntity())
                                                .map(LivingEntity::getAttributes)
                                                .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                                                .map(manager -> manager.getInstance(Attributes.ARMOR))
                                                .filter(instance -> instance.getModifier(uuid) == null)
                                                .ifPresent(instance -> instance.addTransientModifier(
                                                        new AttributeModifier(uuid, "mmt_curios_armor_penetration", effectLevel * -1, AttributeModifier.Operation.ADDITION)));
                                    }
                                }
                        )
                );
            }
        }
    }
    public static void onLivingDamage(LivingDamageEvent event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getEntity() instanceof Player player) {
                CuriosApi.getCuriosInventory(player).ifPresent(inv -> inv.findCurios
                        (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                        slotResult -> {
                            slotResult.stack();
                            ItemStack itemStack = slotResult.stack();
                            IModularItem item = (IModularItem) itemStack.getItem();
                            int effectLevel = item.getEffectLevel(itemStack, curiosArmorPenetrationEffect);
                            if (effectLevel > 0) {
                                Optional.of(event.getEntity())
                                        .map(LivingEntity::getAttributes)
                                        .filter(manager -> manager.hasAttribute(Attributes.ARMOR))
                                        .map(manager -> manager.getInstance(Attributes.ARMOR))
                                        .ifPresent(instance -> instance.removeModifier(uuid));
                            }
                        }
                        )
                );
            }
        }
    }
}
