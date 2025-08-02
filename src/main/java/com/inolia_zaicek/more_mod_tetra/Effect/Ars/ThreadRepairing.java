package com.inolia_zaicek.more_mod_tetra.Effect.Ars;

import com.hollingsworth.arsnouveau.setup.registry.CapabilityRegistry;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class ThreadRepairing {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(ThreadRepairingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                ThreadRepairingName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(ThreadRepairingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if(ModList.get().isLoaded("ars_nouveau")) {
            Player player = event.player;
            ItemStack mainStack = player.getMainHandItem();
            ItemStack offStack = player.getOffhandItem();
            ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
            if (mainStack.getItem() instanceof ModularItem item) {
                float mainLevel = item.getEffectLevel(mainStack, ThreadRepairingEffect);
                float offLevel = item.getEffectLevel(offStack, ThreadRepairingEffect);
                float headLevel = item.getEffectLevel(head, ThreadRepairingEffect);
                float chestLevel = item.getEffectLevel(chest, ThreadRepairingEffect);
                float legsLevel = item.getEffectLevel(legs, ThreadRepairingEffect);
                float feetLevel = item.getEffectLevel(feet, ThreadRepairingEffect);
                int Durability=0;
                if (mainLevel > 0 || offLevel > 0 || headLevel > 0 || chestLevel > 0 || legsLevel > 0 || feetLevel > 0) {
                    if(!mainStack.isEmpty() && mainStack.getItem().isDamageable(mainStack)){
                        int currentDurability = mainStack.getDamageValue();
                        int maxDurability = mainStack.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    if(!offStack.isEmpty() && offStack.getItem().isDamageable(offStack)){
                        int currentDurability = offStack.getDamageValue();
                        int maxDurability = offStack.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    if(!head.isEmpty() && head.getItem().isDamageable(head)){
                        int currentDurability = head.getDamageValue();
                        int maxDurability = head.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    if(!chest.isEmpty() && chest.getItem().isDamageable(chest)){
                        int currentDurability = chest.getDamageValue();
                        int maxDurability = chest.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    if(!legs.isEmpty() && legs.getItem().isDamageable(legs)){
                        int currentDurability = legs.getDamageValue();
                        int maxDurability = legs.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    if(!feet.isEmpty() && feet.getItem().isDamageable(feet)){
                        int currentDurability = feet.getDamageValue();
                        int maxDurability = feet.getMaxDamage();
                        Durability += maxDurability-currentDurability;
                    }
                    int finalDurability = Durability;
                    CapabilityRegistry.getMana(player).ifPresent(mana -> {
                        if (mana.getCurrentMana() > 10 && finalDurability >0) {
                            mana.removeMana(20);
                            //如果物品的损坏值小于 4（例如损坏值为 0 或 1），那么它会返回物品的损坏值本身（即 0 或 1）。
                            //如果物品的损坏值大于等于 4（例如损坏值为 5、10、50 等），那么它会返回 4。
                            if(!mainStack.isEmpty() && mainStack.getItem().isDamageable(mainStack)) {
                                mainStack.setDamageValue(mainStack.getDamageValue() - Math.min(mainStack.getDamageValue(), 4));
                            }
                            if(!offStack.isEmpty() && offStack.getItem().isDamageable(offStack)) {
                                offStack.setDamageValue(offStack.getDamageValue() - Math.min(offStack.getDamageValue(), 4));
                            }
                            if(!head.isEmpty() && head.getItem().isDamageable(head)) {
                                head.setDamageValue(head.getDamageValue() - Math.min(head.getDamageValue(), 4));
                            }
                            if(!chest.isEmpty() && chest.getItem().isDamageable(chest)) {
                                chest.setDamageValue(chest.getDamageValue() - Math.min(chest.getDamageValue(), 4));
                            }
                            if(!legs.isEmpty() && legs.getItem().isDamageable(legs)) {
                                legs.setDamageValue(legs.getDamageValue() - Math.min(legs.getDamageValue(), 4));
                            }
                            if(!feet.isEmpty() && feet.getItem().isDamageable(feet)) {
                                feet.setDamageValue(feet.getDamageValue() - Math.min(feet.getDamageValue(), 4));
                            }
                        }
                    });
                }
            }
        }
    }
}
