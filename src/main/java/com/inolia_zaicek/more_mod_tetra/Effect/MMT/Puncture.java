package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Puncture {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(punctureEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                punctureName, 0, 200, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(punctureTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof Player player&&event.getEntity()!=null) {
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                float armorPenetrationLevel=0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, punctureEffect);
                    float armorPenetrationMainEffectLevel = item.getEffectLevel(mainHandItem, ItemEffect.get("armorPenetration"));
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                        armorPenetrationLevel += armorPenetrationMainEffectLevel;
                    }
                }
                int armor = (int) event.getEntity().getAttributeValue(Attributes.ARMOR);
                if (effectLevel > 0) {
                    if(!(armor>0)||armorPenetrationLevel>=100||
                            //护甲*穿甲比例小于等于0
                            !( ((int)( armor* (1-armorPenetrationLevel/100) )) > 0)) {
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount() * (1 + number));
                    }
                    }
                }
            }
        }

