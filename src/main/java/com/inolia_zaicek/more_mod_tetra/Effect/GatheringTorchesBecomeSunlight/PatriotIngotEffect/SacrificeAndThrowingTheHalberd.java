package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SacrificeAndThrowingTheHalberd {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(SacrificeAndThrowingTheHalberdEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                SacrificeAndThrowingTheHalberdName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(SacrificeAndThrowingTheHalberdTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,SacrificeAndThrowingTheHalberdEffect);
                if (effectLevel > 0) {
                    //是近战
                    if(MMTDamageSourceHelper.isMeleeAttack(event.hurtEvent.getSource())) {
                        if(livingEntity.hasEffect(MMTEffectsRegister.ThrowingTheHalberdMax.get())){
                            event.addNormalMulti(( (float) effectLevel /100));
                        }
                        else{
                            livingEntity.removeEffect(MMTEffectsRegister.ThrowingTheHalberd.get());
                            livingEntity.removeEffect(MMTEffectsRegister.ThrowingTheHalberdMax.get());
                        }
                    }
                    //不是近战
                    else{
                        livingEntity.removeEffect(MMTEffectsRegister.ThrowingTheHalberd.get());
                        livingEntity.removeEffect(MMTEffectsRegister.ThrowingTheHalberdMax.get());
                    }
                }
            }
        }
    }
