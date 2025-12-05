package com.inolia_zaicek.more_mod_tetra.Effect.MMT.NitenIchiryu;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class NitenIchiryuWakizashi {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(NitenIchiryuWakizashiEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                NitenIchiryuWakizashiName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(NitenIchiryuWakizashiTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player) {
                ItemStack mainHandItem = player.getMainHandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, NitenIchiryuWakizashiEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    //无天
                    if(!(player.hasEffect(MMTEffectsRegister.NitenIchiryuKatana.get()))) {
                        player.addEffect(new MobEffectInstance(MMTEffectsRegister.NitenIchiryuWakizashi.get(), 200, 0));
                        //有天
                    }else{
                        player.removeEffect(MMTEffectsRegister.NitenIchiryuWakizashi.get());
                        player.removeEffect(MMTEffectsRegister.NitenIchiryuKatana.get());
                        event.addNormalMulti(( (float) effectLevel /100));
                    }
                }
            }
        }
    }
