package com.inolia_zaicek.more_mod_tetra.Effect.Goety;

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
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;
import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class GoetyCursed {
        @OnlyIn(Dist.CLIENT)
        public static void init() {
            //双数据的显示代码要改
            var statGetter1 = new StatGetterEffectLevel(goety_cursed_Effect, 1);
            var statGetter2 = new StatGetterEffectEfficiency(goety_cursed_Effect, 1);
            IStatGetter[] statGetters = { statGetter1,statGetter2};
            IStatFormat[] statFormats = {StatFormat.noDecimal,StatFormat.noDecimal};
            GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                    goety_cursed_Name, 0, 100, false, false, false,
                    statGetter1, LabelGetterBasic.integerLabel,
                    new TooltipGetterMultiValue(goety_cursed_Tooltip, statGetters,statFormats)
            );
            WorkbenchStatsGui.addBar(statBar);
            HoloStatsGui.addBar(statBar);
        }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("goety")) {
            Random random = new Random();
            //攻击者是玩家
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity player && event.getAttacked() != null) {
                float chance = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_cursed_Effect);
                float time = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(player,goety_cursed_Effect);
                if (chance > 0 && event.getAttacked() != null && time>0 && random.nextInt(100) <= chance) {
                    var mob = event.getAttacked();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")))
                            , (int) (time*20), 0));
                    if(!mob.hasEffect(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")
                            )))) {
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new
                        ResourceLocation("goety","cursed")))
                                ,new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("goety", "cursed"))),(int) (time*20),0));
                    }
                }
            } else if (event.hurtEvent.getSource().getDirectEntity() instanceof LivingEntity player && event.getAttacked() != null) {
                float chance = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,goety_cursed_Effect);
                float time = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(player,goety_cursed_Effect);
                if (chance > 0 && event.getAttacked() != null && time>0 && random.nextInt(100) <= chance) {
                    var mob = event.getAttacked();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")))
                            , (int) (time*20), 0));
                    if(!mob.hasEffect(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")
                            )))) {
                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new
                                        ResourceLocation("goety","cursed")))
                                ,new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(
                                        new ResourceLocation("goety", "cursed"))),(int) (time*20),0));
                    }
                }
            }
        }
    }
}
