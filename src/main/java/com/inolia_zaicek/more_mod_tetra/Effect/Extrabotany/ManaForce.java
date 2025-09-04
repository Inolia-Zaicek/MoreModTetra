package com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import vazkii.botania.api.mana.ManaItemHandler;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ManaForce {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(manaForceEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                manaForceName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(manaForceTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, manaForceEffect);
                if (mainEffectLevel > 0) {
                    effectLevel +=  mainEffectLevel;
                    //200w--满额
                    if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,200*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount()*(1+number));
                    }
                    //150w--85%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,150*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount()*(1+number*0.85f));
                    }
                    //100w--70%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,100*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount()*(1+number*0.7f));
                    }
                    //50w--55%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,50*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount()*(1+number*0.7f));
                    }
                    //不满足50w--40%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,50*10000,false)){
                        float number = (float) effectLevel / 100;
                        event.setAmount(event.getAmount()*(1+number*0.7f));
                    }
                }
            }
            else if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, manaForceEffect);
                if (offEffectLevel > 0) {
                    effectLevel += (int) offEffectLevel;
                    if(ManaItemHandler.INSTANCE.requestManaExactForTool(offhandItem,player,5000,true)){

                    }
                }
            }
        }
    }
}
