package com.inolia_zaicek.more_mod_tetra.Effect.BornInChaos;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SoulStratification {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(born_in_chaos_v1_soul_stratification_Effect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                born_in_chaos_v1_soul_stratification_Name, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(born_in_chaos_v1_soul_stratification_Tooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("born_in_chaos_v1")) {
            //攻击者是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player &&event.getAttacked()!=null) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,born_in_chaos_v1_soul_stratification_Effect);
                if (effectLevel > 0 && event.getAttacked() != null) {
                    var mob = event.getAttacked();
                    var map = mob.getActiveEffectsMap();
                    //没有buff
                    if(!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))))) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))), 200, effectLevel - 1));
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))), 200, effectLevel - 1));
                    }
                }
            }else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player &&event.getAttacked()!=null) {
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,born_in_chaos_v1_soul_stratification_Effect);
                if (effectLevel > 0 && event.getAttacked() != null) {
                    var mob = event.getAttacked();
                    var map = mob.getActiveEffectsMap();
                    //没有buff
                    if(!mob.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))))) {
                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))), 200, effectLevel - 1));
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification")))
                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "soul_stratification"))), 200, effectLevel - 1));
                    }
                }
            }
        }
    }
}
