package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static net.minecraft.tags.DamageTypeTags.IS_EXPLOSION;

public class GrenadeEffectTraitHurt {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(grenadeEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                grenadeEffectTraitName, 0, 5, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(grenadeEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("l2complements")) {
            if (event.getAttacked() != null) {
                LivingEntity livingEntity = event.getAttacked();;
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, dementorEffectTraitEffect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, dementorEffectTraitEffect);
                if (effectLevel > 0&&event.hurtEvent.getSource().is(IS_EXPLOSION)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            //攻击者是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity &&event.getAttacked()!=null
                    &&event.hurtEvent.getSource().is(DamageTypeTags.IS_EXPLOSION)) {

                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, dementorEffectTraitEffect)
                        + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, dementorEffectTraitEffect);
                if (effectLevel > 0) {
                    event.addNormalMulti((effectLevel * 0.5f));
                }

            }
        }
    }
}
