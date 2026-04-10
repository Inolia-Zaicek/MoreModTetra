package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.BuffGive;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosStrength {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosStrengthEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosStrengthName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosStrengthTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.level().getGameTime() % 20L == 0) {
            if (MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, curiosStrengthEffect) > 0) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, curiosStrengthEffect);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, strength_buff_Effect) > 0) {
                int effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, strength_buff_Effect);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, effectLevel2 - 1));
            }
            /// 新生魔艺部分
            if (ModList.get().isLoaded("ars_nouveau")) {
                if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_spell_damage_Effect) > 0 ) {
                    int effectLevel3 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_spell_damage_Effect);
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "spell_damage")))
                            , 200, effectLevel3 - 1));
                }
                if(MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_mana_regen_Effect)>0) {
                    int effectLevel4 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_mana_regen_Effect);
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "mana_regen")))
                            , 200, effectLevel4 - 1));
                }
                //饰品（固定
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_ars_spell_damage_Effect)) {
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "spell_damage")))
                            , 200, 0));
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_ars_mana_regen_Effect)) {
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "mana_regen")))
                            , 200, 0));
                }
            }

            if (ModList.get().isLoaded("immortalers_delight")) {
                int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,immortalers_delight_moon_bright_Effect);
                if (effectLevel1 > 0 ) {
                    livingEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(
                            ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("immortalers_delight", "moon_bright")))
                            ,200,effectLevel1-1));
                }
                int effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,immortalers_delight_incandescence_Effect);
                if (effectLevel2 > 0 ) {
                    livingEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(
                            ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("immortalers_delight", "incandescence")))
                            ,200,effectLevel2-1));
                }
            }
            if (MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, curiosFireResistanceEffect)
                    + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, fieryEffectTraitEffect) > 0 ) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,100,0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,fire_resistance_buff_Effect) > 0 ) {
                int effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,fire_resistance_buff_Effect);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,100,effectLevel2-1));
            }
            int effectLevel3 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,invisibility_buff_Effect);
            if (effectLevel3 > 0 ) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,100,effectLevel3-1));
            }
            int effectLevel4 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,slow_falling_buff_Effect);
            if (effectLevel4 > 0 ) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,100,effectLevel4-1));
            }
            int effectLevel5 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,dolphins_grace_buff_Effect);
            if (effectLevel5 > 0 ) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE,100,effectLevel5-1));
            }
            /// 铁魔法
            if ( ModList.get().isLoaded("irons_spellbooks")) {
                if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_oakskin_Effect) > 0) {
                    int effectLevel51 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_oakskin_Effect);
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "oakskin")))
                            , 200, effectLevel51 - 1));
                }
                if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_irons_spellbooks_oakskin_Effect) ) {
                    livingEntity.addEffect(new MobEffectInstance(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "oakskin")))
                            , 200, 0));
                }
            }
            /// 诡厄
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_climbing_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "climbing")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_climbing_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_climbing_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "climbing")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_explosive_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "explosive")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_explosive_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_explosive_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "explosive")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swift_swim_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swift_swim")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swift_swim_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_swift_swim_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swift_swim")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_flame_hands_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flame_hands")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_flame_hands_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_flame_hands_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flame_hands")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_venomous_hands_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "venomous_hands")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_venomous_hands_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_venomous_hands_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "venomous_hands")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_repulsive_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "repulsive")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_repulsive_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_repulsive_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "repulsive")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fiery_aura_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fiery_aura")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fiery_aura_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_fiery_aura_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fiery_aura")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_frosty_aura_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "frosty_aura")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_frosty_aura_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_frosty_aura_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "frosty_aura")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_photosynthesis_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "photosynthesis")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_photosynthesis_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_photosynthesis_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "photosynthesis")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_insight_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "insight")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_insight_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_insight_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "insight")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fortunate_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fortunate")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fortunate_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_fortunate_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fortunate")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_bottling_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "bottling")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_bottling_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_bottling_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "bottling")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_radiance_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "radiance")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_radiance_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && !ModList.get().isLoaded("goety_revelation") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_radiance_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "radiance")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && !ModList.get().isLoaded("goety_revelation") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_deflective_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "deflective")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_deflective_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && !ModList.get().isLoaded("goety_revelation") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_deflective_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "deflective")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_leeching_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "leeching")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_leeching_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swirling_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swirling")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swirling_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && !ModList.get().isLoaded("goety_revelation") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_radiance_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swirling")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_shielding_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "shielding")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_shielding_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_shielding_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "shielding")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_rallying_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "rallying")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_rallying_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_rallying_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "rallying")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("goety") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_gravity_pulse_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "gravity_pulse")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_gravity_pulse_Effect) - 1));
            }
            if (ModList.get().isLoaded("goety") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_gravity_pulse_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "gravity_pulse")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("quark") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, quark_resilience_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("quark", "resilience")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, quark_resilience_Effect) - 1));
            }
            if (ModList.get().isLoaded("quark") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_quark_resilience_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("quark", "resilience")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("cataclysm") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, cataclysm_blessing_of_amethyst_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blessing_of_amethyst")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, cataclysm_blessing_of_amethyst_Effect) - 1));
            }
            if (ModList.get().isLoaded("cataclysm") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_cataclysm_blessing_of_amethyst_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blessing_of_amethyst")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_bug_pheromones_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "bug_pheromones")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_bug_pheromones_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexsmobs_bug_pheromones_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "bug_pheromones")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_mosquito_repellent_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "mosquito_repellent")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_mosquito_repellent_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexsmobs_mosquito_repellent_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "mosquito_repellent")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_clinging_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "clinging")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_clinging_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexsmobs_clinging_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "clinging")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_knockback_resistance_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "knockback_resistance")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_knockback_resistance_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexsmobs_knockback_resistance_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "knockback_resistance")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_soulsteal_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "soulsteal")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexsmobs_soulsteal_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexsmobs") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexsmobs_soulsteal_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexsmobs", "soulsteal")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("alexscaves") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexscaves_deepsight_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "deepsight")))
                        , 400, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, alexscaves_deepsight_Effect) - 1));
            }
            if (ModList.get().isLoaded("alexscaves") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexscaves_deepsight_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "deepsight")))
                        , 400, 0));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_caffeinated_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "caffeinated")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_caffeinated_Effect) - 1));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_youkaishomecoming_caffeinated_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "caffeinated")))
                        , 200, 0));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_tea_polyphenols_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "tea_polyphenols")))
                        , 200, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_tea_polyphenols_Effect) - 1));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_youkaishomecoming_tea_polyphenols_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "tea_polyphenols")))
                        , 200, 0));
            }
        }
        if (livingEntity.level().getGameTime() % 200L == 0) {
            ;
            if (ModList.get().isLoaded("irons_spellbooks") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_true_invisibility_Effect) > 0) {
                int effectLevel6 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_true_invisibility_Effect);
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "true_invisibility")))
                        , 220, effectLevel6 - 1));
            }
            if (ModList.get().isLoaded("irons_spellbooks") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_irons_spellbooks_true_invisibility_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "true_invisibility")))
                        , 220, 0));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_unconscious_Effect) > 0) {
                int effectLevel7 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_unconscious_Effect);
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "unconscious")))
                        , 220, effectLevel7 - 1));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_youkaishomecoming_unconscious_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "unconscious")))
                        , 220, 0));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_youkaified_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "youkaified")))
                        , 220, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_youkaified_Effect) - 1));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_youkaishomecoming_youkaified_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "youkaified")))
                        , 220, 0));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_youkaifying_Effect) > 0) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "youkaifying")))
                        , 220, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, youkaishomecoming_youkaifying_Effect) - 1));
            }
            if (ModList.get().isLoaded("youkaishomecoming") && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_youkaishomecoming_youkaifying_Effect)) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("youkaishomecoming", "youkaifying")))
                        , 220, 0));
            }
        }
    }
}