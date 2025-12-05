package com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
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
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.hurtEvent.getSource().getEntity() instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            int effectLevel = 0;
            if (mainHandItem.getItem() instanceof IModularItem item&&item.getEffectLevel(mainHandItem, manaForceEffect)>0) {
                    effectLevel +=  item.getEffectLevel(mainHandItem, manaForceEffect);
                    //200w--满额
                    if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,200*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.addNormalMulti((number));
                    }
                    //150w--85%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,150*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.addNormalMulti((number*0.85f));
                    }
                    //100w--70%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,100*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.addNormalMulti((number*0.7f));
                    }
                    //50w--55%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,50*10000,true)){
                        float number = (float) effectLevel / 100;
                        event.addNormalMulti((number*0.7f));
                    }
                    //不满足50w--40%加成
                    else if(ManaItemHandler.INSTANCE.requestManaExactForTool(mainHandItem,player,50*10000,false)) {
                        float number = (float) effectLevel / 100;
                        event.addNormalMulti((1 + number * 0.7f));
                    }
            }
            else if (offHandItem.getItem() instanceof IModularItem item&&item.getEffectLevel(offHandItem, manaForceEffect)>0) {
                effectLevel += item.getEffectLevel(offHandItem, manaForceEffect);
                //200w--满额
                if (ManaItemHandler.INSTANCE.requestManaExactForTool(offHandItem, player, 200 * 10000, true)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1 + number));
                }
                //150w--85%加成
                else if (ManaItemHandler.INSTANCE.requestManaExactForTool(offHandItem, player, 150 * 10000, true)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1 + number * 0.85f));
                }
                //100w--70%加成
                else if (ManaItemHandler.INSTANCE.requestManaExactForTool(offHandItem, player, 100 * 10000, true)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1 + number * 0.7f));
                }
                //50w--55%加成
                else if (ManaItemHandler.INSTANCE.requestManaExactForTool(offHandItem, player, 50 * 10000, true)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1 + number * 0.7f));
                }
                //不满足50w--40%加成
                else if (ManaItemHandler.INSTANCE.requestManaExactForTool(offHandItem, player, 50 * 10000, false)) {
                    float number = (float) effectLevel / 100;
                    event.addNormalMulti((1 + number * 0.7f));
                }
            }
        }
    }
}
