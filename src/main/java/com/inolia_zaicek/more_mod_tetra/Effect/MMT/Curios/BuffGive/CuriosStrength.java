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
        if (livingEntity.level().getGameTime() % 10L == 0) {
            float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, curiosStrengthEffect);
            if (effectLevel > 0 ) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
            }
            int effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, strength_buff_Effect);
            if (effectLevel2 > 0) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, effectLevel2 - 1));
            }
            /// 新生魔艺部分
            //工具
            int effectLevel3 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_spell_damage_Effect);
            if (effectLevel3 > 0&&ModList.get().isLoaded("ars_nouveau")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "spell_damage")))
                        , 100, effectLevel3 - 1));
            }
            int effectLevel4 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, ars_mana_regen_Effect);
            if (effectLevel4 > 0&&ModList.get().isLoaded("ars_nouveau")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "mana_regen")))
                        , 100, effectLevel4 - 1));
            }
            //饰品（固定
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_ars_spell_damage_Effect) && ModList.get().isLoaded("ars_nouveau")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "spell_damage")))
                        , 100, 0));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_ars_mana_regen_Effect) && ModList.get().isLoaded("ars_nouveau")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_nouveau", "mana_regen")))
                        , 100, 0));
            }
            /// 铁魔法
            int effectLevel5 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_oakskin_Effect);
            if (effectLevel5 > 0&&ModList.get().isLoaded("irons_spellbooks")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "oakskin")))
                        , 100, effectLevel5 - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_irons_spellbooks_oakskin_Effect) && ModList.get().isLoaded("irons_spellbooks")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "oakskin")))
                        , 100, 0));
            }
            int effectLevel6 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, irons_spellbooks_true_invisibility_Effect);
            if (effectLevel6 > 0&&ModList.get().isLoaded("irons_spellbooks")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "true_invisibility")))
                        , 100, effectLevel6 - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_irons_spellbooks_true_invisibility_Effect) && ModList.get().isLoaded("irons_spellbooks")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("irons_spellbooks", "true_invisibility")))
                        , 100, 0));
            }
            /// 诡厄
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_climbing_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "climbing")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_climbing_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_climbing_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "climbing")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_explosive_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "explosive")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_explosive_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_explosive_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "explosive")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swift_swim_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swift_swim")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swift_swim_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_swift_swim_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swift_swim")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_flame_hands_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flame_hands")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_flame_hands_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_flame_hands_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "flame_hands")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_venomous_hands_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "venomous_hands")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_venomous_hands_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_venomous_hands_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "venomous_hands")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_repulsive_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "repulsive")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_repulsive_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_repulsive_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "repulsive")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fiery_aura_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fiery_aura")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fiery_aura_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_fiery_aura_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fiery_aura")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_frosty_aura_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "frosty_aura")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_frosty_aura_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_frosty_aura_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "frosty_aura")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_photosynthesis_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "photosynthesis")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_photosynthesis_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_photosynthesis_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "photosynthesis")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_insight_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "insight")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_insight_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_insight_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "insight")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fortunate_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fortunate")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_fortunate_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_fortunate_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "fortunate")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_bottling_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "bottling")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_bottling_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_bottling_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "bottling")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_radiance_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "radiance")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_radiance_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_radiance_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "radiance")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_deflective_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "deflective")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_deflective_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_deflective_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "deflective")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_leeching_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "leeching")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_leeching_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_leeching_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "leeching")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swirling_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swirling")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_swirling_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_swirling_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "swirling")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_shielding_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "shielding")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_shielding_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_shielding_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "shielding")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_rallying_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "rallying")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_rallying_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_rallying_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "rallying")))
                        , 100, 0));
            }
            if (MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_gravity_pulse_Effect) > 0&&ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "gravity_pulse")))
                        , 100, MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, goety_gravity_pulse_Effect) - 1));
            }
            if (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_gravity_pulse_Effect) && ModList.get().isLoaded("goety")) {
                livingEntity.addEffect(new MobEffectInstance(
                        Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "gravity_pulse")))
                        , 100, 0));
            }
        }
    }
}