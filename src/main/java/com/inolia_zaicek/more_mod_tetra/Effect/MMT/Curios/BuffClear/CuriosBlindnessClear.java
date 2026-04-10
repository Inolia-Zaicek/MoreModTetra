package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.BuffClear;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.resources.ResourceLocation;
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
public class CuriosBlindnessClear {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosBlindnessClearEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosBlindnessClearName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosBlindnessClearTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if ( livingEntity.hasEffect(MobEffects.BLINDNESS) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosBlindnessClearEffect)) {
            livingEntity.removeEffect(MobEffects.BLINDNESS);
        }
        if ( livingEntity.hasEffect(MobEffects.WITHER) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosWitherClearEffect)) {
            livingEntity.removeEffect(MobEffects.WITHER);
        }
        if ( livingEntity.hasEffect(MobEffects.DARKNESS) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosDarknessClearEffect)) {
            livingEntity.removeEffect(MobEffects.DARKNESS);
        }
        if ( livingEntity.getRemainingFireTicks()>0 && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosFireClearEffect)) {
            livingEntity.setRemainingFireTicks(0);
        }
        if ( livingEntity.hasEffect(MobEffects.HUNGER) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosHungerClearEffect)) {
            livingEntity.removeEffect(MobEffects.HUNGER);
        }
        if ( livingEntity.hasEffect(MobEffects.LEVITATION) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosLevitationClearEffect)) {
            livingEntity.removeEffect(MobEffects.LEVITATION);
        }
        if ( livingEntity.hasEffect(MobEffects.DIG_SLOWDOWN) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosMiningFatigueClearEffect)) {
            livingEntity.removeEffect(MobEffects.DIG_SLOWDOWN);
        }
        if ( livingEntity.hasEffect(MobEffects.POISON) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosPoisonClearEffect)) {
            livingEntity.removeEffect(MobEffects.POISON);
        }
        if ( livingEntity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosSlownessClearEffect)) {
            livingEntity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        if ( livingEntity.hasEffect(MobEffects.WEAKNESS) && MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curiosWeaknessClearEffect)) {
            livingEntity.removeEffect(MobEffects.WEAKNESS);
        }
        //勇三
        if ( ModList.get().isLoaded("chapter_of_yuusha_3_core") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("chapter_of_yuusha_3_core", "voidification")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, chapter_of_yuusha_3_core_voidification_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_chapter_of_yuusha_3_core_voidification_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("chapter_of_yuusha_3_core", "voidification"))));
        }
        /// 莱特兰——清除
        if ( ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "curse")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_curse_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_curse_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "curse"))));
        }
        if ( ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "flame")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_flame_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_flame_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "flame"))));
        }
        if ( ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "frozen")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_frozen_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_frozen_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "frozen"))));
        }
        /// 诡厄
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_cursed_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_cursed_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed"))));
        }
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "plunge")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_plunge_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_plunge_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "plunge"))));
        }
        if (!ModList.get().isLoaded("goety_revelation") && ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "crippled")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_crippled_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_crippled_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "crippled"))));
        }
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "burn_hex")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_burn_hex_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_burn_hex_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "burn_hex"))));
        }
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_sapped_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_sapped_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped"))));
        }
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_freezing_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_freezing_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing"))));
        }
        if ( ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "doom")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_doom_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_doom_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "doom"))));
        }
        /// A C
        if ( ModList.get().isLoaded("alexscaves") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "magnetizing")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, alexscaves_magnetizing_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexscaves_magnetizing_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "magnetizing"))));
        }
        if ( ModList.get().isLoaded("alexscaves") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "irradiated")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, alexscaves_irradiated_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexscaves_irradiated_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "irradiated"))));
        }
        //天境
        if ( ModList.get().isLoaded("aether") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("aether", "inebriation")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, aether_inebriation_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_aether_inebriation_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("aether", "inebriation"))));
        }
        //灾变
        if ( ModList.get().isLoaded("cataclysm") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, cataclysm_blazing_brand_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_cataclysm_blazing_brand_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand"))));
        }
        if ( ModList.get().isLoaded("cataclysm") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "curse_of_desert")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, cataclysm_curse_of_desert_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_cataclysm_curse_of_desert_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "curse_of_desert"))));
        }
        //生于混沌
        if ( ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "bone_fracture")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_bone_fracture_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_bone_fracture_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "bone_fracture"))));
        }
        if ( ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "intoxication")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_intoxicating_decoction_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_intoxicating_decoction_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "intoxication"))));
        }
        if ( ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "magic_depletion")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_magic_depletion_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_magic_depletion_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "magic_depletion"))));
        }
        if ( ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "myiasis")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_myiasis_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_myiasis_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "myiasis"))));
        }
        if ( ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "rotten_smell")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_rotten_smell_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_rotten_smell_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "rotten_smell"))));
        }
        //神化
        if ( ModList.get().isLoaded("attributeslib") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "grievous")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, apotheosis_grievous_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_apotheosis_grievous_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "grievous"))));
        }
        if ( ModList.get().isLoaded("attributeslib") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "bleeding")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, apotheosis_bleeding_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_apotheosis_bleeding_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "bleeding"))));
        }
        if ( ModList.get().isLoaded("attributeslib") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "sundering")))) && (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, apotheosis_sundering_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_apotheosis_sundering_clear_Effect))
        ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("attributeslib", "sundering"))));
        }
    }
}