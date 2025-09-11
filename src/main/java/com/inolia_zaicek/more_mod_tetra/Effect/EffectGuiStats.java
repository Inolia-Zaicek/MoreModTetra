package com.inolia_zaicek.more_mod_tetra.Effect;


import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.effect.ItemEffect;

import java.util.Objects;


public class EffectGuiStats {
    //双刀
    public static final ItemEffect eclipseStarBurstStreamEffect = ItemEffect.get(MoreModTetra.MODID + ":eclipse_star_burst_stream");
    public static final String eclipseStarBurstStreamName = MoreModTetra.MODID + ".effect.eclipse_star_burst_stream.name";
    public static final String eclipseStarBurstStreamTooltip = MoreModTetra.MODID + ".effect.eclipse_star_burst_stream.tooltip";

    public static final ItemEffect starBurstStreamEffect = ItemEffect.get(MoreModTetra.MODID + ":star_burst_stream");
    public static final String starBurstStreamName = MoreModTetra.MODID + ".effect.star_burst_stream.name";
    public static final String starBurstStreamTooltip = MoreModTetra.MODID + ".effect.star_burst_stream.tooltip";

    public static final ItemEffect eclipseEffect = ItemEffect.get(MoreModTetra.MODID + ":eclipse");
    public static final String eclipseName = MoreModTetra.MODID + ".effect.eclipse.name";
    public static final String eclipseTooltip = MoreModTetra.MODID + ".effect.eclipse.tooltip";

    public static final ItemEffect twinSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":twin_slash");
    public static final String twinSlashName = MoreModTetra.MODID + ".effect.twin_slash.name";
    public static final String twinSlashTooltip = MoreModTetra.MODID + ".effect.twin_slash.tooltip";

    public static final ItemEffect invulnerableTimeDownEffect = ItemEffect.get(MoreModTetra.MODID + ":invulnerable_time_down");
    public static final String invulnerableTimeDownName = MoreModTetra.MODID + ".effect.invulnerable_time_down.name";
    public static final String invulnerableTimeDownTooltip = MoreModTetra.MODID + ".effect.invulnerable_time_down.tooltip";
    //十二泰坦
    public static final ItemEffect thoughWorldsApartEffect = ItemEffect.get(MoreModTetra.MODID + ":though_worlds_apart");
    public static final String thoughWorldsApartName = MoreModTetra.MODID + ".effect.though_worlds_apart.name";
    public static final String thoughWorldsApartTooltip = MoreModTetra.MODID + ".effect.though_worlds_apart.tooltip";

    public static final ItemEffect toEvernightsStarsEffect = ItemEffect.get(MoreModTetra.MODID + ":to_evernights_stars");
    public static final String toEvernightsStarsName = MoreModTetra.MODID + ".effect.to_evernights_stars.name";
    public static final String toEvernightsStarsTooltip = MoreModTetra.MODID + ".effect.to_evernights_stars.tooltip";

    public static final ItemEffect firstLightHealsTheWorldEffect = ItemEffect.get(MoreModTetra.MODID + ":first_light_heals_the_world");
    public static final String firstLightHealsTheWorldName = MoreModTetra.MODID + ".effect.first_light_heals_the_world.name";
    public static final String firstLightHealsTheWorldTooltip = MoreModTetra.MODID + ".effect.first_light_heals_the_world.tooltip";

    public static final ItemEffect pyricCorpusEffect = ItemEffect.get(MoreModTetra.MODID + ":pyric_corpus");
    public static final String pyricCorpusName = MoreModTetra.MODID + ".effect.pyric_corpus.name";
    public static final String pyricCorpusTooltip = MoreModTetra.MODID + ".effect.pyric_corpus.tooltip";

    public static final ItemEffect everythingIsInEverythingEffect = ItemEffect.get(MoreModTetra.MODID + ":everything_is_in_everything");
    public static final String everythingIsInEverythingName = MoreModTetra.MODID + ".effect.everything_is_in_everything.name";
    public static final String everythingIsInEverythingTooltip = MoreModTetra.MODID + ".effect.everything_is_in_everything.tooltip";

    public static final ItemEffect jackpotForTheTakingEffect = ItemEffect.get(MoreModTetra.MODID + ":jackpot_for_the_taking");
    public static final String jackpotForTheTakingName = MoreModTetra.MODID + ".effect.jackpot_for_the_taking.name";
    public static final String jackpotForTheTakingTooltip = MoreModTetra.MODID + ".effect.jackpot_for_the_taking.tooltip";

    public static final ItemEffect sanctuaryOfMooncocoonEffect = ItemEffect.get(MoreModTetra.MODID + ":sanctuary_of_mooncocoon");
    public static final String sanctuaryOfMooncocoonName = MoreModTetra.MODID + ".effect.sanctuary_of_mooncocoon.name";
    public static final String sanctuaryOfMooncocoonTooltip = MoreModTetra.MODID + ".effect.sanctuary_of_mooncocoon.tooltip";

    public static final ItemEffect torchTheLawsOfOldEffect = ItemEffect.get(MoreModTetra.MODID + ":torch_the_laws_of_old");
    public static final String torchTheLawsOfOldName = MoreModTetra.MODID + ".effect.torch_the_laws_of_old.name";
    public static final String torchTheLawsOfOldTooltip = MoreModTetra.MODID + ".effect.torch_the_laws_of_old.tooltip";

    public static final ItemEffect sirenicSerenadeEffect = ItemEffect.get(MoreModTetra.MODID + ":sirenic_serenade");
    public static final String sirenicSerenadeName = MoreModTetra.MODID + ".effect.sirenic_serenade.name";
    public static final String sirenicSerenadeTooltip = MoreModTetra.MODID + ".effect.sirenic_serenade.tooltip";

    public static final ItemEffect theCenturyGateEffect = ItemEffect.get(MoreModTetra.MODID + ":the_century_gate");
    public static final String theCenturyGateName = MoreModTetra.MODID + ".effect.the_century_gate.name";
    public static final String theCenturyGateTooltip = MoreModTetra.MODID + ".effect.the_century_gate.tooltip";
    //CURIOS
    public static final ItemEffect curiosAllDamageUpEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_all_damage_up");
    public static final String curiosAllDamageUpName = MoreModTetra.MODID + ".effect.curios_all_damage_up.name";
    public static final String curiosAllDamageUpTooltip = MoreModTetra.MODID + ".effect.curios_all_damage_up.tooltip";

    public static final ItemEffect curiosMeleeDamageUpEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_melee_damage_up");
    public static final String curiosMeleeDamageUpName = MoreModTetra.MODID + ".effect.curios_melee_damage_up.name";
    public static final String curiosMeleeDamageUpTooltip = MoreModTetra.MODID + ".effect.curios_melee_damage_up.tooltip";

    public static final ItemEffect curiosProjectileDamageUpEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_projectile_damage_up");
    public static final String curiosProjectileDamageUpName = MoreModTetra.MODID + ".effect.curios_projectile_damage_up.name";
    public static final String curiosProjectileDamageUpTooltip = MoreModTetra.MODID + ".effect.curios_projectile_damage_up.tooltip";

    public static final ItemEffect curiosMagicDamageUpEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_magic_damage_up");
    public static final String curiosMagicDamageUpName = MoreModTetra.MODID + ".effect.curios_magic_damage_up.name";
    public static final String curiosMagicDamageUpTooltip = MoreModTetra.MODID + ".effect.curios_magic_damage_up.tooltip";
    //其他
    public static final ItemEffect glowingEffect = ItemEffect.get(MoreModTetra.MODID + ":glowing");
    public static final String glowingName = MoreModTetra.MODID + ".effect.glowing.name";
    public static final String glowingTooltip = MoreModTetra.MODID + ".effect.glowing.tooltip";

    public static final ItemEffect curiosArmorPenetrationEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_armor_penetration");
    public static final String curiosArmorPenetrationName = MoreModTetra.MODID + ".effect.curios_armor_penetration.name";
    public static final String curiosArmorPenetrationTooltip = MoreModTetra.MODID + ".effect.curios_armor_penetration.tooltip";

    public static final ItemEffect curiosFeatherFallingEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_feather_falling");
    public static final String curiosFeatherFallingName = MoreModTetra.MODID + ".effect.curios_feather_falling.name";
    public static final String curiosFeatherFallingTooltip = MoreModTetra.MODID + ".effect.curios_feather_falling.tooltip";

    public static final ItemEffect curiosCriticalStrikeEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_critical_strike");
    public static final String curiosCriticalStrikeName = MoreModTetra.MODID + ".effect.curios_critical_strike.name";
    public static final String curiosCriticalStrikeTooltip = MoreModTetra.MODID + ".effect.curios_critical_strike.tooltip";

    public static final ItemEffect curiosJankEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_jank");
    public static final String curiosJankName = MoreModTetra.MODID + ".effect.curios_jank.name";
    public static final String curiosJankTooltip = MoreModTetra.MODID + ".effect.curios_jank.tooltip";

    public static final ItemEffect curiosFlyEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_fly");
    public static final String curiosFlyName = MoreModTetra.MODID + ".effect.curios_fly.name";
    public static final String curiosFlyTooltip = MoreModTetra.MODID + ".effect.curios_fly.tooltip";

    public static final ItemEffect curiosJumpEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_jump");
    public static final String curiosJumpName = MoreModTetra.MODID + ".effect.curios_jump.name";
    public static final String curiosJumpTooltip = MoreModTetra.MODID + ".effect.curios_jump.tooltip";

    public static final ItemEffect curiosWaterWalkerEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_water_walker");
    public static final String curiosWaterWalkerName = MoreModTetra.MODID + ".effect.curios_water_walker.name";
    public static final String curiosWaterWalkerTooltip = MoreModTetra.MODID + ".effect.curios_water_walker.tooltip";

    public static final ItemEffect curiosProjectileTrackingEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_projectile_tracking");
    public static final String curiosProjectileTrackingName = MoreModTetra.MODID + ".effect.curios_projectile_tracking.name";
    public static final String curiosProjectileTrackingTooltip = MoreModTetra.MODID + ".effect.curios_projectile_tracking.tooltip";

    public static final ItemEffect curiosMagmaWalkerEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_magma_walker");
    public static final String curiosMagmaWalkerName = MoreModTetra.MODID + ".effect.curios_magma_walker.name";
    public static final String curiosMagmaWalkerTooltip = MoreModTetra.MODID + ".effect.curios_magma_walker.tooltip";

    public static final ItemEffect curiosProtectEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_protect");
    public static final String curiosProtectName = MoreModTetra.MODID + ".effect.curios_protect.name";
    public static final String curiosProtectTooltip = MoreModTetra.MODID + ".effect.curios_protect.tooltip";

    public static final ItemEffect curiosKamuiEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_kamui");
    public static final String curiosKamuiName = MoreModTetra.MODID + ".effect.curios_kamui.name";
    public static final String curiosKamuiTooltip = MoreModTetra.MODID + ".effect.curios_kamui.tooltip";

    public static final ItemEffect beheadingEffect = ItemEffect.get(MoreModTetra.MODID + ":beheading");
    public static final String beheadingName = MoreModTetra.MODID + ".effect.beheading.name";
    public static final String beheadingTooltip = MoreModTetra.MODID + ".effect.beheading.tooltip";

    public static final ItemEffect vampiricEffect = ItemEffect.get(MoreModTetra.MODID + ":vampiric");
    public static final String vampiricName = MoreModTetra.MODID + ".effect.vampiric.name";
    public static final String vampiricTooltip = MoreModTetra.MODID + ".effect.vampiric.tooltip";

    public static final ItemEffect capturingEffect = ItemEffect.get(MoreModTetra.MODID + ":capturing");
    public static final String capturingName = MoreModTetra.MODID + ".effect.capturing.name";
    public static final String capturingTooltip = MoreModTetra.MODID + ".effect.capturing.tooltip";

    public static final ItemEffect necroticEffect = ItemEffect.get(MoreModTetra.MODID + ":necrotic");
    public static final String necroticName = MoreModTetra.MODID + ".effect.necrotic.name";
    public static final String necroticTooltip = MoreModTetra.MODID + ".effect.necrotic.tooltip";

    public static final ItemEffect smcFrostBurstEffect = ItemEffect.get(MoreModTetra.MODID + ":smc_frost_burst");
    public static final String smcFrostBurstName = MoreModTetra.MODID + ".effect.smc_frost_burst.name";
    public static final String smcFrostBurstTooltip = MoreModTetra.MODID + ".effect.smc_frost_burst.tooltip";

    public static final ItemEffect healthPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":health_power");
    public static final String healthPowerName = MoreModTetra.MODID + ".effect.health_power.name";
    public static final String healthPowerTooltip = MoreModTetra.MODID + ".effect.health_power.tooltip";

    public static final ItemEffect extraAquaticProductsEffect = ItemEffect.get(MoreModTetra.MODID + ":extra_aquatic_products");
    public static final String extraAquaticProductsName = MoreModTetra.MODID + ".effect.extra_aquatic_products.name";
    public static final String extraAquaticProductsTooltip = MoreModTetra.MODID + ".effect.extra_aquatic_products.tooltip";

    public static final ItemEffect smcFrostEffect = ItemEffect.get(MoreModTetra.MODID + ":smc_frost");
    public static final String smcFrostName = MoreModTetra.MODID + ".effect.smc_frost.name";
    public static final String smcFrostTooltip = MoreModTetra.MODID + ".effect.smc_frost.tooltip";

    public static final ItemEffect toolBlockingEffect = ItemEffect.get(MoreModTetra.MODID + ":tool_blocking");
    public static final String toolBlockingName = MoreModTetra.MODID + ".effect.tool_blocking.name";
    public static final String toolBlockingTooltip = MoreModTetra.MODID + ".effect.tool_blocking.tooltip";

    public static final ItemEffect shootingSunEffect = ItemEffect.get(MoreModTetra.MODID + ":shooting_sun");
    public static final String shootingSunName = MoreModTetra.MODID + ".effect.shooting_sun.name";
    public static final String shootingSunTooltip = MoreModTetra.MODID + ".effect.shooting_sun.tooltip";

    public static final ItemEffect magicOscillationEffect = ItemEffect.get(MoreModTetra.MODID + ":magic_oscillation");
    public static final String magicOscillationName = MoreModTetra.MODID + ".effect.magic_oscillation.name";
    public static final String magicOscillationTooltip = MoreModTetra.MODID + ".effect.magic_oscillation.tooltip";

    public static final ItemEffect heavyChopEffect = ItemEffect.get(MoreModTetra.MODID + ":heavy_chop");
    public static final String heavyChopName = MoreModTetra.MODID + ".effect.heavy_chop.name";
    public static final String heavyChopTooltip = MoreModTetra.MODID + ".effect.heavy_chop.tooltip";

    public static final ItemEffect originSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":origin_slash");
    public static final String originSlashName = MoreModTetra.MODID + ".effect.origin_slash.name";
    public static final String originSlashTooltip = MoreModTetra.MODID + ".effect.origin_slash.tooltip";

    public static final ItemEffect heavyHitEffect = ItemEffect.get(MoreModTetra.MODID + ":heavy_hit");
    public static final String heavyHitName = MoreModTetra.MODID + ".effect.heavy_hit.name";
    public static final String heavyHitTooltip = MoreModTetra.MODID + ".effect.heavy_hit.tooltip";

    public static final ItemEffect punctureEffect = ItemEffect.get(MoreModTetra.MODID + ":puncture");
    public static final String punctureName = MoreModTetra.MODID + ".effect.puncture.name";
    public static final String punctureTooltip = MoreModTetra.MODID + ".effect.puncture.tooltip";

    public static final ItemEffect fragileEffect = ItemEffect.get(MoreModTetra.MODID + ":fragile");
    public static final String fragileName = MoreModTetra.MODID + ".effect.fragile.name";
    public static final String fragileTooltip = MoreModTetra.MODID + ".effect.fragile.tooltip";

    public static final ItemEffect shieldSkillAutomaticDefenseEffect = ItemEffect.get(MoreModTetra.MODID + ":shield_skill_automatic_defense");
    public static final String shieldSkillAutomaticDefenseName = MoreModTetra.MODID + ".effect.shield_skill_automatic_defense.name";
    public static final String shieldSkillAutomaticDefenseTooltip = MoreModTetra.MODID + ".effect.shield_skill_automatic_defense.tooltip";

    public static final ItemEffect shieldSkillAutomaticProtectionEffect = ItemEffect.get(MoreModTetra.MODID + ":shield_skill_automatic_protection");
    public static final String shieldSkillAutomaticProtectionName = MoreModTetra.MODID + ".effect.shield_skill_automatic_protection.name";
    public static final String shieldSkillAutomaticProtectionTooltip = MoreModTetra.MODID + ".effect.shield_skill_automatic_protection.tooltip";

    public static final ItemEffect shieldSkillCooldownDownEffect = ItemEffect.get(MoreModTetra.MODID + ":shield_skill_cooldown_down");
    public static final String shieldSkillCooldownDownName = MoreModTetra.MODID + ".effect.shield_skill_cooldown_down.name";
    public static final String shieldSkillCooldownDownTooltip = MoreModTetra.MODID + ".effect.shield_skill_cooldown_down.tooltip";

    public static final ItemEffect reverselSmiteEffect = ItemEffect.get(MoreModTetra.MODID + ":reversel_smite");
    public static final String reverselSmiteName = MoreModTetra.MODID + ".effect.reversel_smite.name";
    public static final String reverselSmiteTooltip = MoreModTetra.MODID + ".effect.reversel_smite.tooltip";

    public static final ItemEffect magicEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":magic_edge");
    public static final String magicEdgeName = MoreModTetra.MODID + ".effect.magic_edge.name";
    public static final String magicEdgeTooltip = MoreModTetra.MODID + ".effect.magic_edge.tooltip";

    public static final ItemEffect magicProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":magic_proficiency");
    public static final String magicProficiencyName = MoreModTetra.MODID + ".effect.magic_proficiency.name";
    public static final String magicProficiencyTooltip = MoreModTetra.MODID + ".effect.magic_proficiency.tooltip";

    public static final ItemEffect magicComboEffect = ItemEffect.get(MoreModTetra.MODID + ":magic_combo");
    public static final String magicComboName = MoreModTetra.MODID + ".effect.magic_combo.name";
    public static final String magicComboTooltip = MoreModTetra.MODID + ".effect.magic_combo.tooltip";

    public static final ItemEffect freezeEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":freeze_edge");
    public static final String freezeEdgeName = MoreModTetra.MODID + ".effect.freeze_edge.name";
    public static final String freezeEdgeTooltip = MoreModTetra.MODID + ".effect.freeze_edge.tooltip";

    public static final ItemEffect freezeProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":freeze_proficiency");
    public static final String freezeProficiencyName = MoreModTetra.MODID + ".effect.freeze_proficiency.name";
    public static final String freezeProficiencyTooltip = MoreModTetra.MODID + ".effect.freeze_proficiency.tooltip";

    public static final ItemEffect freezeComboEffect = ItemEffect.get(MoreModTetra.MODID + ":freeze_combo");
    public static final String freezeComboName = MoreModTetra.MODID + ".effect.freeze_combo.name";
    public static final String freezeComboTooltip = MoreModTetra.MODID + ".effect.freeze_combo.tooltip";

    public static final ItemEffect lightningEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_edge");
    public static final String lightningEdgeName = MoreModTetra.MODID + ".effect.lightning_edge.name";
    public static final String lightningEdgeTooltip = MoreModTetra.MODID + ".effect.lightning_edge.tooltip";

    public static final ItemEffect lightningProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_proficiency");
    public static final String lightningProficiencyName = MoreModTetra.MODID + ".effect.lightning_proficiency.name";
    public static final String lightningProficiencyTooltip = MoreModTetra.MODID + ".effect.lightning_proficiency.tooltip";

    public static final ItemEffect lightningComboEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_combo");
    public static final String lightningComboName = MoreModTetra.MODID + ".effect.lightning_combo.name";
    public static final String lightningComboTooltip = MoreModTetra.MODID + ".effect.lightning_combo.tooltip";

    public static final ItemEffect fireEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_edge");
    public static final String fireEdgeName = MoreModTetra.MODID + ".effect.fire_edge.name";
    public static final String fireEdgeTooltip = MoreModTetra.MODID + ".effect.fire_edge.tooltip";

    public static final ItemEffect fireProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_proficiency");
    public static final String fireProficiencyName = MoreModTetra.MODID + ".effect.fire_proficiency.name";
    public static final String fireProficiencyTooltip = MoreModTetra.MODID + ".effect.fire_proficiency.tooltip";

    public static final ItemEffect fireComboEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_combo");
    public static final String fireComboName = MoreModTetra.MODID + ".effect.fire_combo.name";
    public static final String fireComboTooltip = MoreModTetra.MODID + ".effect.fire_combo.tooltip";

    public static final ItemEffect witherEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":wither_edge");
    public static final String witherEdgeName = MoreModTetra.MODID + ".effect.wither_edge.name";
    public static final String witherEdgeTooltip = MoreModTetra.MODID + ".effect.wither_edge.tooltip";

    public static final ItemEffect witherProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":wither_proficiency");
    public static final String witherProficiencyName = MoreModTetra.MODID + ".effect.wither_proficiency.name";
    public static final String witherProficiencyTooltip = MoreModTetra.MODID + ".effect.wither_proficiency.tooltip";

    public static final ItemEffect witherComboEffect = ItemEffect.get(MoreModTetra.MODID + ":wither_combo");
    public static final String witherComboName = MoreModTetra.MODID + ".effect.wither_combo.name";
    public static final String witherComboTooltip = MoreModTetra.MODID + ".effect.wither_combo.tooltip";

    public static final ItemEffect dragonBreathEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":dragon_breath_edge");
    public static final String dragonBreathEdgeName = MoreModTetra.MODID + ".effect.dragon_breath_edge.name";
    public static final String dragonBreathEdgeTooltip = MoreModTetra.MODID + ".effect.dragon_breath_edge.tooltip";

    public static final ItemEffect dragonBreathProficiencyEffect = ItemEffect.get(MoreModTetra.MODID + ":dragon_breath_proficiency");
    public static final String dragonBreathProficiencyName = MoreModTetra.MODID + ".effect.dragon_breath_proficiency.name";
    public static final String dragonBreathProficiencyTooltip = MoreModTetra.MODID + ".effect.dragon_breath_proficiency.tooltip";

    public static final ItemEffect dragonBreathComboEffect = ItemEffect.get(MoreModTetra.MODID + ":dragon_breath_combo");
    public static final String dragonBreathComboName = MoreModTetra.MODID + ".effect.dragon_breath_combo.name";
    public static final String dragonBreathComboTooltip = MoreModTetra.MODID + ".effect.dragon_breath_combo.tooltip";

    public static final ItemEffect letThePeopleRejoiceEffect = ItemEffect.get(MoreModTetra.MODID + ":let_the_people_rejoice");
    public static final String letThePeopleRejoiceName = MoreModTetra.MODID + ".effect.let_the_people_rejoice.name";
    public static final String letThePeopleRejoiceTooltip = MoreModTetra.MODID + ".effect.let_the_people_rejoice.tooltip";
    //伪造匠魂
    public static final ItemEffect fakeInsatiableEffect = ItemEffect.get(MoreModTetra.MODID + ":fake_insatiable");
    public static final String fakeInsatiableName = MoreModTetra.MODID + ".effect.fake_insatiable.name";
    public static final String fakeInsatiableTooltip = MoreModTetra.MODID + ".effect.fake_insatiable.tooltip";

    public static final ItemEffect fakeMomentumEffect = ItemEffect.get(MoreModTetra.MODID + ":fake_momentum");
    public static final String fakeMomentumName = MoreModTetra.MODID + ".effect.fake_momentum.name";
    public static final String fakeMomentumTooltip = MoreModTetra.MODID + ".effect.fake_momentum.tooltip";

    public static final ItemEffect fakeTastyEffect = ItemEffect.get(MoreModTetra.MODID + ":fake_tasty");
    public static final String fakeTastyName = MoreModTetra.MODID + ".effect.fake_tasty.name";
    public static final String fakeTastyTooltip = MoreModTetra.MODID + ".effect.fake_tasty.tooltip";
    //额外植物学
    public static final ItemEffect shiningPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":shining_power");
    public static final String shiningPowerName = MoreModTetra.MODID + ".effect.shining_power.name";
    public static final String shiningPowerTooltip = MoreModTetra.MODID + ".effect.shining_power.tooltip";

    public static final ItemEffect shadowiumPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":shadowium_power");
    public static final String shadowiumPowerName = MoreModTetra.MODID + ".effect.shadowium_power.name";
    public static final String shadowiumPowerTooltip = MoreModTetra.MODID + ".effect.shadowium_power.tooltip";

    public static final ItemEffect speedForceEffect = ItemEffect.get(MoreModTetra.MODID + ":speed_force");
    public static final String speedForceName = MoreModTetra.MODID + ".effect.speed_force.name";
    public static final String speedForceTooltip = MoreModTetra.MODID + ".effect.speed_force.tooltip";

    public static final ItemEffect manaForceEffect = ItemEffect.get(MoreModTetra.MODID + ":mana_force");
    public static final String manaForceName = MoreModTetra.MODID + ".effect.mana_force.name";
    public static final String manaForceTooltip = MoreModTetra.MODID + ".effect.mana_force.tooltip";
    //恶意词条
    public static final ItemEffect cursedEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":cursed_effect_trait");
    public static final String cursedEffectTraitName = MoreModTetra.MODID + ".effect.cursed_effect_trait.name";
    public static final String cursedEffectTraitTooltip = MoreModTetra.MODID + ".effect.cursed_effect_trait.tooltip";

    public static final ItemEffect undyingEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":undying_effect_trait");
    public static final String undyingEffectTraitName = MoreModTetra.MODID + ".effect.undying_effect_trait.name";
    public static final String undyingEffectTraitTooltip = MoreModTetra.MODID + ".effect.undying_effect_trait.tooltip";

    public static final ItemEffect weaknessEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":weakness_effect_trait");
    public static final String weaknessEffectTraitName = MoreModTetra.MODID + ".effect.weakness_effect_trait.name";
    public static final String weaknessEffectTraitTooltip = MoreModTetra.MODID + ".effect.weakness_effect_trait.tooltip";

    public static final ItemEffect tankyEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":tanky_effect_trait");
    public static final String tankyEffectTraitName = MoreModTetra.MODID + ".effect.tanky_effect_trait.name";
    public static final String tankyEffectTraitTooltip = MoreModTetra.MODID + ".effect.tanky_effect_trait.tooltip";

    public static final ItemEffect speedyEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":speedy_effect_trait");
    public static final String speedyEffectTraitName = MoreModTetra.MODID + ".effect.speedy_effect_trait.name";
    public static final String speedyEffectTraitTooltip = MoreModTetra.MODID + ".effect.speedy_effect_trait.tooltip";

    public static final ItemEffect protectedEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":protected_effect_trait");
    public static final String protectedEffectTraitName = MoreModTetra.MODID + ".effect.protected_effect_trait.name";
    public static final String protectedEffectTraitTooltip = MoreModTetra.MODID + ".effect.protected_effect_trait.tooltip";

    public static final ItemEffect fieryEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":fiery_effect_trait");
    public static final String fieryEffectTraitName = MoreModTetra.MODID + ".effect.fiery_effect_trait.name";
    public static final String fieryEffectTraitTooltip = MoreModTetra.MODID + ".effect.fiery_effect_trait.tooltip";

    public static final ItemEffect grenadeEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":grenade_effect_trait");
    public static final String grenadeEffectTraitName = MoreModTetra.MODID + ".effect.grenade_effect_trait.name";
    public static final String grenadeEffectTraitTooltip = MoreModTetra.MODID + ".effect.grenade_effect_trait.tooltip";

    public static final ItemEffect repellingEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":repelling_effect_trait");
    public static final String repellingEffectTraitName = MoreModTetra.MODID + ".effect.repelling_effect_trait.name";
    public static final String repellingEffectTraitTooltip = MoreModTetra.MODID + ".effect.repelling_effect_trait.tooltip";

    public static final ItemEffect regeneratingEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":regenerating_effect_trait");
    public static final String regeneratingEffectTraitName = MoreModTetra.MODID + ".effect.regenerating_effect_trait.name";
    public static final String regeneratingEffectTraitTooltip = MoreModTetra.MODID + ".effect.regenerating_effect_trait.tooltip";

    public static final ItemEffect reflectEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":reflect_effect_trait");
    public static final String reflectEffectTraitName = MoreModTetra.MODID + ".effect.reflect_effect_trait.name";
    public static final String reflectEffectTraitTooltip = MoreModTetra.MODID + ".effect.reflect_effect_trait.tooltip";

    public static final ItemEffect killerAuraEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":killer_aura_effect_trait");
    public static final String killerAuraEffectTraitName = MoreModTetra.MODID + ".effect.killer_aura_effect_trait.name";
    public static final String killerAuraEffectTraitTooltip = MoreModTetra.MODID + ".effect.killer_aura_effect_trait.tooltip";

    public static final ItemEffect strayEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":stray_effect_trait");
    public static final String strayEffectTraitName = MoreModTetra.MODID + ".effect.stray_effect_trait.name";
    public static final String strayEffectTraitTooltip = MoreModTetra.MODID + ".effect.stray_effect_trait.tooltip";

    public static final ItemEffect dementorEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":dementor_effect_trait");
    public static final String dementorEffectTraitName = MoreModTetra.MODID + ".effect.dementor_effect_trait.name";
    public static final String dementorEffectTraitTooltip = MoreModTetra.MODID + ".effect.dementor_effect_trait.tooltip";

    public static final ItemEffect dispellEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":dispell_effect_trait");
    public static final String dispellEffectTraitName = MoreModTetra.MODID + ".effect.dispell_effect_trait.name";
    public static final String dispellEffectTraitTooltip = MoreModTetra.MODID + ".effect.dispell_effect_trait.tooltip";

    public static final ItemEffect freezingEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":freezing_effect_trait");
    public static final String freezingEffectTraitName = MoreModTetra.MODID + ".effect.freezing_effect_trait.name";
    public static final String freezingEffectTraitTooltip = MoreModTetra.MODID + ".effect.freezing_effect_trait.tooltip";

    public static final ItemEffect soulBurnerEffectTraitEffect = ItemEffect.get(MoreModTetra.MODID + ":soul_burner_effect_trait");
    public static final String soulBurnerEffectTraitName = MoreModTetra.MODID + ".effect.soul_burner_effect_trait.name";
    public static final String soulBurnerEffectTraitTooltip = MoreModTetra.MODID + ".effect.soul_burner_effect_trait.tooltip";
    //潜影贝吊坠
    public static final ItemEffect smallShulkerEffect = ItemEffect.get(MoreModTetra.MODID + ":small_shulker");
    public static final String smallShulkerName = MoreModTetra.MODID + ".effect.small_shulker.name";
    public static final String smallShulkerTooltip = MoreModTetra.MODID + ".effect.small_shulker.tooltip";

    public static final ItemEffect industrialProtectionEffect = ItemEffect.get(MoreModTetra.MODID + ":industrial_protection");
    public static final String industrialProtectionName = MoreModTetra.MODID + ".effect.industrial_protection.name";
    public static final String industrialProtectionTooltip = MoreModTetra.MODID + ".effect.industrial_protection.tooltip";

    public static final ItemEffect foodAcquisitionEffect = ItemEffect.get(MoreModTetra.MODID + ":food_acquisition");
    public static final String foodAcquisitionName = MoreModTetra.MODID + ".effect.food_acquisition.name";
    public static final String foodAcquisitionTooltip = MoreModTetra.MODID + ".effect.food_acquisition.tooltip";


    //莱特兰恶意
    public static final ItemEffect hostilityControlEffect = ItemEffect.get(MoreModTetra.MODID + ":hostility_control");
    public static final String hostilityControlName = MoreModTetra.MODID + ".effect.hostility_control.name";
    public static final String hostilityControlTooltip = MoreModTetra.MODID + ".effect.hostility_control.tooltip";

    public static final ItemEffect miracleAndMagicEffect = ItemEffect.get(MoreModTetra.MODID + ":miracle_and_magic");
    public static final String miracleAndMagicName = MoreModTetra.MODID + ".effect.miracle_and_magic.name";
    public static final String miracleAndMagicTooltip = MoreModTetra.MODID + ".effect.miracle_and_magic.tooltip";

    public static final ItemEffect recoveryEffect = ItemEffect.get(MoreModTetra.MODID + ":recovery");
    public static final String recoveryName = MoreModTetra.MODID + ".effect.recovery.name";
    public static final String recoveryTooltip = MoreModTetra.MODID + ".effect.recovery.tooltip";

    public static final ItemEffect hidingInShulkerShellEffect = ItemEffect.get(MoreModTetra.MODID + ":hiding_in_shulker_shell");
    public static final String hidingInShulkerShellName = MoreModTetra.MODID + ".effect.hiding_in_shulker_shell.name";
    public static final String hidingInShulkerShellTooltip = MoreModTetra.MODID + ".effect.hiding_in_shulker_shell.tooltip";

    public static final ItemEffect blessingsOfWaterEffect = ItemEffect.get(MoreModTetra.MODID + ":blessings_of_water");
    public static final String blessingsOfWaterName = MoreModTetra.MODID + ".effect.blessings_of_water.name";
    public static final String blessingsOfWaterTooltip = MoreModTetra.MODID + ".effect.blessings_of_water.tooltip";

    public static final ItemEffect eterniumDurabilityEffect = ItemEffect.get(MoreModTetra.MODID + ":eternium_durability");
    public static final String eterniumDurabilityName = MoreModTetra.MODID + ".effect.eternium_durability.name";
    public static final String eterniumDurabilityTooltip = MoreModTetra.MODID + ".effect.eternium_durability.tooltip";

    public static final ItemEffect obsessionOfWardenEffect = ItemEffect.get(MoreModTetra.MODID + ":obsession_of_warden");
    public static final String obsessionOfWardenName = MoreModTetra.MODID + ".effect.obsession_of_warden.name";
    public static final String obsessionOfWardenTooltip = MoreModTetra.MODID + ".effect.obsession_of_warden.tooltip";

    //铁魔法
    public static final ItemEffect ironSpellCastingEffect = ItemEffect.get(MoreModTetra.MODID + ":iron_spell_casting");
    public static final String ironSpellCastingName = MoreModTetra.MODID + ".effect.iron_spell_casting.name";
    public static final String ironSpellCastingTooltip = MoreModTetra.MODID + ".effect.iron_spell_casting.tooltip";

    public static final ItemEffect fireArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_arcane_edge");
    public static final String fireArcaneEdgeName = MoreModTetra.MODID + ".effect.fire_arcane_edge.name";
    public static final String fireArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.fire_arcane_edge.tooltip";

    public static final ItemEffect iceArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_arcane_edge");
    public static final String iceArcaneEdgeName = MoreModTetra.MODID + ".effect.ice_arcane_edge.name";
    public static final String iceArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.ice_arcane_edge.tooltip";

    public static final ItemEffect lightningArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_arcane_edge");
    public static final String lightningArcaneEdgeName = MoreModTetra.MODID + ".effect.lightning_arcane_edge.name";
    public static final String lightningArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.lightning_arcane_edge.tooltip";

    public static final ItemEffect evocationArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":evocation_arcane_edge");
    public static final String evocationArcaneEdgeName = MoreModTetra.MODID + ".effect.evocation_arcane_edge.name";
    public static final String evocationArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.evocation_arcane_edge.tooltip";

    public static final ItemEffect holyArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":holy_arcane_edge");
    public static final String holyArcaneEdgeName = MoreModTetra.MODID + ".effect.holy_arcane_edge.name";
    public static final String holyArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.holy_arcane_edge.tooltip";

    public static final ItemEffect bloodArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":blood_arcane_edge");
    public static final String bloodArcaneEdgeName = MoreModTetra.MODID + ".effect.blood_arcane_edge.name";
    public static final String bloodArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.blood_arcane_edge.tooltip";

    public static final ItemEffect enderArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":ender_arcane_edge");
    public static final String enderArcaneEdgeName = MoreModTetra.MODID + ".effect.ender_arcane_edge.name";
    public static final String enderArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.ender_arcane_edge.tooltip";

    public static final ItemEffect natureArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":nature_arcane_edge");
    public static final String natureArcaneEdgeName = MoreModTetra.MODID + ".effect.nature_arcane_edge.name";
    public static final String natureArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.nature_arcane_edge.tooltip";

    public static final ItemEffect eldritchArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":eldritch_arcane_edge");
    public static final String eldritchArcaneEdgeName = MoreModTetra.MODID + ".effect.eldritch_arcane_edge.name";
    public static final String eldritchArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.eldritch_arcane_edge.tooltip";

    public static final ItemEffect arcaneArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":arcane_arcane_edge");
    public static final String arcaneArcaneEdgeName = MoreModTetra.MODID + ".effect.arcane_arcane_edge.name";
    public static final String arcaneArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.arcane_arcane_edge.tooltip";

    public static final ItemEffect protectionArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":protection_arcane_edge");
    public static final String protectionArcaneEdgeName = MoreModTetra.MODID + ".effect.protection_arcane_edge.name";
    public static final String protectionArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.protection_arcane_edge.tooltip";

    public static final ItemEffect cooldownArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":cooldown_arcane_edge");
    public static final String cooldownArcaneEdgeName = MoreModTetra.MODID + ".effect.cooldown_arcane_edge.name";
    public static final String cooldownArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.cooldown_arcane_edge.tooltip";

    public static final ItemEffect soundArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":sound_arcane_edge");
    public static final String soundArcaneEdgeName = MoreModTetra.MODID + ".effect.sound_arcane_edge.name";
    public static final String soundArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.sound_arcane_edge.tooltip";

    public static final ItemEffect aquaArcaneEdgeEffect = ItemEffect.get(MoreModTetra.MODID + ":aqua_arcane_edge");
    public static final String aquaArcaneEdgeName = MoreModTetra.MODID + ".effect.aqua_arcane_edge.name";
    public static final String aquaArcaneEdgeTooltip = MoreModTetra.MODID + ".effect.aqua_arcane_edge.tooltip";
    //铁魔法 法抗
    public static final ItemEffect fireArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_arcane_guard");
    public static final String fireArcaneGuardName = MoreModTetra.MODID + ".effect.fire_arcane_guard.name";
    public static final String fireArcaneGuardTooltip = MoreModTetra.MODID + ".effect.fire_arcane_guard.tooltip";

    public static final ItemEffect iceArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_arcane_guard");
    public static final String iceArcaneGuardName = MoreModTetra.MODID + ".effect.ice_arcane_guard.name";
    public static final String iceArcaneGuardTooltip = MoreModTetra.MODID + ".effect.ice_arcane_guard.tooltip";

    public static final ItemEffect lightningArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_arcane_guard");
    public static final String lightningArcaneGuardName = MoreModTetra.MODID + ".effect.lightning_arcane_guard.name";
    public static final String lightningArcaneGuardTooltip = MoreModTetra.MODID + ".effect.lightning_arcane_guard.tooltip";

    public static final ItemEffect evocationArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":evocation_arcane_guard");
    public static final String evocationArcaneGuardName = MoreModTetra.MODID + ".effect.evocation_arcane_guard.name";
    public static final String evocationArcaneGuardTooltip = MoreModTetra.MODID + ".effect.evocation_arcane_guard.tooltip";

    public static final ItemEffect holyArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":holy_arcane_guard");
    public static final String holyArcaneGuardName = MoreModTetra.MODID + ".effect.holy_arcane_guard.name";
    public static final String holyArcaneGuardTooltip = MoreModTetra.MODID + ".effect.holy_arcane_guard.tooltip";

    public static final ItemEffect bloodArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":blood_arcane_guard");
    public static final String bloodArcaneGuardName = MoreModTetra.MODID + ".effect.blood_arcane_guard.name";
    public static final String bloodArcaneGuardTooltip = MoreModTetra.MODID + ".effect.blood_arcane_guard.tooltip";

    public static final ItemEffect enderArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":ender_arcane_guard");
    public static final String enderArcaneGuardName = MoreModTetra.MODID + ".effect.ender_arcane_guard.name";
    public static final String enderArcaneGuardTooltip = MoreModTetra.MODID + ".effect.ender_arcane_guard.tooltip";

    public static final ItemEffect natureArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":nature_arcane_guard");
    public static final String natureArcaneGuardName = MoreModTetra.MODID + ".effect.nature_arcane_guard.name";
    public static final String natureArcaneGuardTooltip = MoreModTetra.MODID + ".effect.nature_arcane_guard.tooltip";

    public static final ItemEffect eldritchArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":eldritch_arcane_guard");
    public static final String eldritchArcaneGuardName = MoreModTetra.MODID + ".effect.eldritch_arcane_guard.name";
    public static final String eldritchArcaneGuardTooltip = MoreModTetra.MODID + ".effect.eldritch_arcane_guard.tooltip";

    public static final ItemEffect soundArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":sound_arcane_guard");
    public static final String soundArcaneGuardName = MoreModTetra.MODID + ".effect.sound_arcane_guard.name";
    public static final String soundArcaneGuardTooltip = MoreModTetra.MODID + ".effect.sound_arcane_guard.tooltip";

    public static final ItemEffect aquaArcaneGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":aqua_arcane_guard");
    public static final String aquaArcaneGuardName = MoreModTetra.MODID + ".effect.aqua_arcane_guard.name";
    public static final String aquaArcaneGuardTooltip = MoreModTetra.MODID + ".effect.aqua_arcane_guard.tooltip";
    //铁魔法 结算增伤
    public static final ItemEffect fireStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_staff_socket");
    public static final String fireStaffSocketName = MoreModTetra.MODID + ".effect.fire_staff_socket.name";
    public static final String fireStaffSocketTooltip = MoreModTetra.MODID + ".effect.fire_staff_socket.tooltip";

    public static final ItemEffect iceStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_staff_socket");
    public static final String iceStaffSocketName = MoreModTetra.MODID + ".effect.ice_staff_socket.name";
    public static final String iceStaffSocketTooltip = MoreModTetra.MODID + ".effect.ice_staff_socket.tooltip";

    public static final ItemEffect lightningStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_staff_socket");
    public static final String lightningStaffSocketName = MoreModTetra.MODID + ".effect.lightning_staff_socket.name";
    public static final String lightningStaffSocketTooltip = MoreModTetra.MODID + ".effect.lightning_staff_socket.tooltip";

    public static final ItemEffect evocationStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":evocation_staff_socket");
    public static final String evocationStaffSocketName = MoreModTetra.MODID + ".effect.evocation_staff_socket.name";
    public static final String evocationStaffSocketTooltip = MoreModTetra.MODID + ".effect.evocation_staff_socket.tooltip";

    public static final ItemEffect holyStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":holy_staff_socket");
    public static final String holyStaffSocketName = MoreModTetra.MODID + ".effect.holy_staff_socket.name";
    public static final String holyStaffSocketTooltip = MoreModTetra.MODID + ".effect.holy_staff_socket.tooltip";

    public static final ItemEffect bloodStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":blood_staff_socket");
    public static final String bloodStaffSocketName = MoreModTetra.MODID + ".effect.blood_staff_socket.name";
    public static final String bloodStaffSocketTooltip = MoreModTetra.MODID + ".effect.blood_staff_socket.tooltip";

    public static final ItemEffect enderStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":ender_staff_socket");
    public static final String enderStaffSocketName = MoreModTetra.MODID + ".effect.ender_staff_socket.name";
    public static final String enderStaffSocketTooltip = MoreModTetra.MODID + ".effect.ender_staff_socket.tooltip";

    public static final ItemEffect natureStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":nature_staff_socket");
    public static final String natureStaffSocketName = MoreModTetra.MODID + ".effect.nature_staff_socket.name";
    public static final String natureStaffSocketTooltip = MoreModTetra.MODID + ".effect.nature_staff_socket.tooltip";

    public static final ItemEffect eldritchStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":eldritch_staff_socket");
    public static final String eldritchStaffSocketName = MoreModTetra.MODID + ".effect.eldritch_staff_socket.name";
    public static final String eldritchStaffSocketTooltip = MoreModTetra.MODID + ".effect.eldritch_staff_socket.tooltip";

    public static final ItemEffect soundStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":sound_staff_socket");
    public static final String soundStaffSocketName = MoreModTetra.MODID + ".effect.sound_staff_socket.name";
    public static final String soundStaffSocketTooltip = MoreModTetra.MODID + ".effect.sound_staff_socket.tooltip";

    public static final ItemEffect aquaStaffSocketEffect = ItemEffect.get(MoreModTetra.MODID + ":aqua_staff_socket");
    public static final String aquaStaffSocketName = MoreModTetra.MODID + ".effect.aqua_staff_socket.name";
    public static final String aquaStaffSocketTooltip = MoreModTetra.MODID + ".effect.aqua_staff_socket.tooltip";

    //新生魔艺
    public static final ItemEffect ThreadDepthsEffect = ItemEffect.get(MoreModTetra.MODID + ":thread_depths");
    public static final String ThreadDepthsName = MoreModTetra.MODID + ".effect.thread_depths.name";
    public static final String ThreadDepthsTooltip = MoreModTetra.MODID + ".effect.thread_depths.tooltip";

    public static final ItemEffect ThreadHeightsEffect = ItemEffect.get(MoreModTetra.MODID + ":thread_heights");
    public static final String ThreadHeightsName = MoreModTetra.MODID + ".effect.thread_heights.name";
    public static final String ThreadHeightsTooltip = MoreModTetra.MODID + ".effect.thread_heights.tooltip";

    public static final ItemEffect ThreadFeatherEffect = ItemEffect.get(MoreModTetra.MODID + ":thread_feather");
    public static final String ThreadFeatherName = MoreModTetra.MODID + ".effect.thread_feather.name";
    public static final String ThreadFeatherTooltip = MoreModTetra.MODID + ".effect.thread_feather.tooltip";

    public static final ItemEffect ThreadRepairingEffect = ItemEffect.get(MoreModTetra.MODID + ":thread_repairing");
    public static final String ThreadRepairingName = MoreModTetra.MODID + ".effect.thread_repairing.name";
    public static final String ThreadRepairingTooltip = MoreModTetra.MODID + ".effect.thread_repairing.tooltip";
    //灵灾
    public static final ItemEffect StabilizingEffect = ItemEffect.get(MoreModTetra.MODID + ":stabilizing");
    public static final String StabilizingName = MoreModTetra.MODID + ".effect.stabilizing.name";
    public static final String StabilizingTooltip = MoreModTetra.MODID + ".effect.stabilizing.tooltip";

    public static final ItemEffect runeOfIdleRestorationEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_idle_restoration");
    public static final String runeOfIdleRestorationName = MoreModTetra.MODID + ".effect.rune_of_idle_restoration.name";
    public static final String runeOfIdleRestorationTooltip = MoreModTetra.MODID + ".effect.rune_of_idle_restoration.tooltip";

    public static final ItemEffect runeOfIgneousSolaceEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_igneous_solace");
    public static final String runeOfIgneousSolaceName = MoreModTetra.MODID + ".effect.rune_of_igneous_solace.name";
    public static final String runeOfIgneousSolaceTooltip = MoreModTetra.MODID + ".effect.rune_of_igneous_solace.tooltip";

    public static final ItemEffect runeOfTwinnedDurationEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_twinned_duration");
    public static final String runeOfTwinnedDurationName = MoreModTetra.MODID + ".effect.rune_of_twinned_duration.name";
    public static final String runeOfTwinnedDurationTooltip = MoreModTetra.MODID + ".effect.rune_of_twinned_duration.tooltip";

    public static final ItemEffect runeOfTheHereticEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_heretic");
    public static final String runeOfTheHereticName = MoreModTetra.MODID + ".effect.rune_of_the_heretic.name";
    public static final String runeOfTheHereticTooltip = MoreModTetra.MODID + ".effect.rune_of_the_heretic.tooltip";

    public static final ItemEffect runeOfVolatileDistortionEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_volatile_distortion");
    public static final String runeOfVolatileDistortionName = MoreModTetra.MODID + ".effect.rune_of_volatile_distortion.name";
    public static final String runeOfVolatileDistortionTooltip = MoreModTetra.MODID + ".effect.rune_of_volatile_distortion.tooltip";

    public static final ItemEffect runeOfReactiveShieldingEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_reactive_shielding");
    public static final String runeOfReactiveShieldingName = MoreModTetra.MODID + ".effect.rune_of_reactive_shielding.name";
    public static final String runeOfReactiveShieldingTooltip = MoreModTetra.MODID + ".effect.rune_of_reactive_shielding.tooltip";

    public static final ItemEffect runeOfAlimentCleansingEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_aliment_cleansing");
    public static final String runeOfAlimentCleansingName = MoreModTetra.MODID + ".effect.rune_of_aliment_cleansing.name";
    public static final String runeOfAlimentCleansingTooltip = MoreModTetra.MODID + ".effect.rune_of_aliment_cleansing.tooltip";

    public static final ItemEffect runeOfFervorEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_fervor");
    public static final String runeOfFervorName = MoreModTetra.MODID + ".effect.rune_of_fervor.name";
    public static final String runeOfFervorTooltip = MoreModTetra.MODID + ".effect.rune_of_fervor.tooltip";

    public static final ItemEffect runeOfSacrificialEmpowermentEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_sacrificial_empowerment");
    public static final String runeOfSacrificialEmpowermentName = MoreModTetra.MODID + ".effect.rune_of_sacrificial_empowerment.name";
    public static final String runeOfSacrificialEmpowermentTooltip = MoreModTetra.MODID + ".effect.rune_of_sacrificial_empowerment.tooltip";
    //褐色符文
    public static final ItemEffect runeOfMotionEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_motion");
    public static final String runeOfMotionName = MoreModTetra.MODID + ".effect.rune_of_motion.name";
    public static final String runeOfMotionTooltip = MoreModTetra.MODID + ".effect.rune_of_motion.tooltip";

    public static final ItemEffect runeOfLoyaltyEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_loyalty");
    public static final String runeOfLoyaltyName = MoreModTetra.MODID + ".effect.rune_of_loyalty.name";
    public static final String runeOfLoyaltyTooltip = MoreModTetra.MODID + ".effect.rune_of_loyalty.tooltip";

    public static final ItemEffect runeOfWardingEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_warding");
    public static final String runeOfWardingName = MoreModTetra.MODID + ".effect.rune_of_warding.name";
    public static final String runeOfWardingTooltip = MoreModTetra.MODID + ".effect.rune_of_warding.tooltip";

    public static final ItemEffect runeOfHasteEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_haste");
    public static final String runeOfHasteName = MoreModTetra.MODID + ".effect.rune_of_haste.name";
    public static final String runeOfHasteTooltip = MoreModTetra.MODID + ".effect.rune_of_haste.tooltip";

    public static final ItemEffect runeOfTheAetherEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_aether");
    public static final String runeOfTheAetherName = MoreModTetra.MODID + ".effect.rune_of_the_aether.name";
    public static final String runeOfTheAetherTooltip = MoreModTetra.MODID + ".effect.rune_of_the_aether.tooltip";

    public static final ItemEffect runeOfTheSeasEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_seas");
    public static final String runeOfTheSeasName = MoreModTetra.MODID + ".effect.rune_of_the_seas.name";
    public static final String runeOfTheSeasTooltip = MoreModTetra.MODID + ".effect.rune_of_the_seas.tooltip";

    public static final ItemEffect runeOfTheArenaEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_arena");
    public static final String runeOfTheArenaName = MoreModTetra.MODID + ".effect.rune_of_the_arena.name";
    public static final String runeOfTheArenaTooltip = MoreModTetra.MODID + ".effect.rune_of_the_arena.tooltip";

    public static final ItemEffect runeOfTheHellsEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_hells");
    public static final String runeOfTheHellsName = MoreModTetra.MODID + ".effect.rune_of_the_hells.name";
    public static final String runeOfTheHellsTooltip = MoreModTetra.MODID + ".effect.rune_of_the_hells.tooltip";
    //幻梦
    public static final ItemEffect soulShardEffect = ItemEffect.get(MoreModTetra.MODID + ":soul_shard");
    public static final String soulShardName = MoreModTetra.MODID + ".effect.soul_shard.name";
    public static final String soulShardTooltip = MoreModTetra.MODID + ".effect.soul_shard.tooltip";

    public static final ItemEffect shadowGemEffect = ItemEffect.get(MoreModTetra.MODID + ":shadow_gem");
    public static final String shadowGemName = MoreModTetra.MODID + ".effect.shadow_gem.name";
    public static final String shadowGemTooltip = MoreModTetra.MODID + ".effect.shadow_gem.tooltip";
    //血肉
    public static final ItemEffect despoilSickleEffect = ItemEffect.get(MoreModTetra.MODID + ":despoil_sickle");
    public static final String despoilSickleName = MoreModTetra.MODID + ".effect.despoil_sickle.name";
    public static final String despoilSickleTooltip = MoreModTetra.MODID + ".effect.despoil_sickle.tooltip";

    public static final ItemEffect absorptionBoostEffect = ItemEffect.get(MoreModTetra.MODID + ":absorption_boost");
    public static final String absorptionBoostName = MoreModTetra.MODID + ".effect.absorption_boost.name";
    public static final String absorptionBoostTooltip = MoreModTetra.MODID + ".effect.absorption_boost.tooltip";

    public static final ItemEffect cleansingSerumEffect = ItemEffect.get(MoreModTetra.MODID + ":cleansing_serum");
    public static final String cleansingSerumName = MoreModTetra.MODID + ".effect.cleansing_serum.name";
    public static final String cleansingSerumTooltip = MoreModTetra.MODID + ".effect.cleansing_serum.tooltip";

    public static final ItemEffect corrosiveAdditiveEffect = ItemEffect.get(MoreModTetra.MODID + ":corrosive_additive");
    public static final String corrosiveAdditiveName = MoreModTetra.MODID + ".effect.corrosive_additive.name";
    public static final String corrosiveAdditiveTooltip = MoreModTetra.MODID + ".effect.corrosive_additive.tooltip";

    public static final ItemEffect frenzySerumEffect = ItemEffect.get(MoreModTetra.MODID + ":frenzy_serum");
    public static final String frenzySerumName = MoreModTetra.MODID + ".effect.frenzy_serum.name";
    public static final String frenzySerumTooltip = MoreModTetra.MODID + ".effect.frenzy_serum.tooltip";

    public static final ItemEffect healingAdditiveEffect = ItemEffect.get(MoreModTetra.MODID + ":healing_additive");
    public static final String healingAdditiveName = MoreModTetra.MODID + ".effect.healing_additive.name";
    public static final String healingAdditiveTooltip = MoreModTetra.MODID + ".effect.healing_additive.tooltip";

    public static final ItemEffect organicCompoundEffect = ItemEffect.get(MoreModTetra.MODID + ":organic_compound");
    public static final String organicCompoundName = MoreModTetra.MODID + ".effect.organic_compound.name";
    public static final String organicCompoundTooltip = MoreModTetra.MODID + ".effect.organic_compound.tooltip";

    public static final ItemEffect unstableCompoundEffect = ItemEffect.get(MoreModTetra.MODID + ":unstable_compound");
    public static final String unstableCompoundName = MoreModTetra.MODID + ".effect.unstable_compound.name";
    public static final String unstableCompoundTooltip = MoreModTetra.MODID + ".effect.unstable_compound.tooltip";
    //本体
    public static final ItemEffect growingEffect = ItemEffect.get(MoreModTetra.MODID + ":growing");
    public static final String growingName = MoreModTetra.MODID + ".effect.growing.name";
    public static final String growingTooltip = MoreModTetra.MODID + ".effect.growing.tooltip";

    public static final ItemEffect duelEffect = ItemEffect.get(MoreModTetra.MODID + ":duel");
    public static final String duelName = MoreModTetra.MODID + ".effect.duel.name";
    public static final String duelTooltip = MoreModTetra.MODID + ".effect.duel.tooltip";

    public static final ItemEffect invulnerabilityBladeEffect = ItemEffect.get(MoreModTetra.MODID + ":invulnerability_blade");
    public static final String invulnerabilityBladeName = MoreModTetra.MODID + ".effect.invulnerability_blade.name";
    public static final String invulnerabilityBladeTooltip = MoreModTetra.MODID + ".effect.invulnerability_blade.tooltip";

    public static final ItemEffect devouringStoneEffect = ItemEffect.get(MoreModTetra.MODID + ":devouring_stone");
    public static final String devouringStoneName = MoreModTetra.MODID + ".effect.devouring_stone.name";
    public static final String devouringStoneTooltip = MoreModTetra.MODID + ".effect.devouring_stone.tooltip";
    //灾变
    public static final ItemEffect blazingBrandEffect = ItemEffect.get(MoreModTetra.MODID + ":blazing_brand");
    public static final String blazingBrandName = MoreModTetra.MODID + ".effect.blazing_brand.name";
    public static final String blazingBrandTooltip = MoreModTetra.MODID + ".effect.blazing_brand.tooltip";

    public static final ItemEffect abyssalCurseEffect = ItemEffect.get(MoreModTetra.MODID + ":abyssal_curse");
    public static final String abyssalCurseName = MoreModTetra.MODID + ".effect.abyssal_curse.name";
    public static final String abyssalCurseTooltip = MoreModTetra.MODID + ".effect.abyssal_curse.tooltip";

    public static final ItemEffect boneFractureEffect = ItemEffect.get(MoreModTetra.MODID + ":bone_fracture");
    public static final String boneFractureName = MoreModTetra.MODID + ".effect.bone_fracture.name";
    public static final String boneFractureTooltip = MoreModTetra.MODID + ".effect.bone_fracture.tooltip";

    public static final ItemEffect cataclysmStunEffect = ItemEffect.get(MoreModTetra.MODID + ":cataclysm_stun");
    public static final String cataclysmStunName = MoreModTetra.MODID + ".effect.cataclysm_stun.name";
    public static final String cataclysmStunTooltip = MoreModTetra.MODID + ".effect.cataclysm_stun.tooltip";

    public static final ItemEffect ghostFormEffect = ItemEffect.get(MoreModTetra.MODID + ":ghost_form");
    public static final String ghostFormName = MoreModTetra.MODID + ".effect.ghost_form.name";
    public static final String ghostFormTooltip = MoreModTetra.MODID + ".effect.ghost_form.tooltip";

    public static final ItemEffect blessingOfAmethystEffect = ItemEffect.get(MoreModTetra.MODID + ":blessing_of_amethyst");
    public static final String blessingOfAmethystName = MoreModTetra.MODID + ".effect.blessing_of_amethyst.name";
    public static final String blessingOfAmethystTooltip = MoreModTetra.MODID + ".effect.blessing_of_amethyst.tooltip";

    public static final ItemEffect monstrousEffect = ItemEffect.get(MoreModTetra.MODID + ":monstrous");
    public static final String monstrousName = MoreModTetra.MODID + ".effect.monstrous.name";
    public static final String monstrousTooltip = MoreModTetra.MODID + ".effect.monstrous.tooltip";

    public static final ItemEffect abyssalFinishEffect = ItemEffect.get(MoreModTetra.MODID + ":abyssal_finish");
    public static final String abyssalFinishName = MoreModTetra.MODID + ".effect.abyssal_finish.name";
    public static final String abyssalFinishTooltip = MoreModTetra.MODID + ".effect.abyssal_finish.tooltip";

    public static final ItemEffect witheriteEffect = ItemEffect.get(MoreModTetra.MODID + ":witherite");
    public static final String witheriteName = MoreModTetra.MODID + ".effect.witherite.name";
    public static final String witheriteTooltip = MoreModTetra.MODID + ".effect.witherite.tooltip";

    public static final ItemEffect blazingAbsorbEffect = ItemEffect.get(MoreModTetra.MODID + ":blazing_absorb");
    public static final String blazingAbsorbName = MoreModTetra.MODID + ".effect.blazing_absorb.name";
    public static final String blazingAbsorbTooltip = MoreModTetra.MODID + ".effect.blazing_absorb.tooltip";

    public static final ItemEffect analysisEffect = ItemEffect.get(MoreModTetra.MODID + ":analysis");
    public static final String analysisName = MoreModTetra.MODID + ".effect.analysis.name";
    public static final String analysisTooltip = MoreModTetra.MODID + ".effect.analysis.tooltip";

    public static final ItemEffect overPostmortalEffect = ItemEffect.get(MoreModTetra.MODID + ":over_postmortal");
    public static final String overPostmortalName = MoreModTetra.MODID + ".effect.over_postmortal.name";
    public static final String overPostmortalTooltip = MoreModTetra.MODID + ".effect.over_postmortal.tooltip";

    public static final ItemEffect curiosTotemEffectEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_totem_effect");
    public static final String curiosTotemEffectName = MoreModTetra.MODID + ".effect.curios_totem_effect.name";
    public static final String curiosTotemEffectTooltip = MoreModTetra.MODID + ".effect.curios_totem_effect.tooltip";

    public static final ItemEffect curiosTotemCooldownEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_totem_cooldown");
    public static final String curiosTotemCooldownName = MoreModTetra.MODID + ".effect.curios_totem_cooldown.name";
    public static final String curiosTotemCooldownTooltip = MoreModTetra.MODID + ".effect.curios_totem_cooldown.tooltip";

    public static final ItemEffect curiosTotemHealthEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_totem_health");
    public static final String curiosTotemHealthName = MoreModTetra.MODID + ".effect.curios_totem_health.name";
    public static final String curiosTotemHealthTooltip = MoreModTetra.MODID + ".effect.curios_totem_health.tooltip";

    public static final ItemEffect lavaDriverEffect = ItemEffect.get(MoreModTetra.MODID + ":lava_driver");
    public static final String lavaDriverName = MoreModTetra.MODID + ".effect.lava_driver.name";
    public static final String lavaDriverTooltip = MoreModTetra.MODID + ".effect.lava_driver.tooltip";

    public static final ItemEffect tearsOfThunderEffect = ItemEffect.get(MoreModTetra.MODID + ":tears_of_thunder");
    public static final String tearsOfThunderName = MoreModTetra.MODID + ".effect.tears_of_thunder.name";
    public static final String tearsOfThunderTooltip = MoreModTetra.MODID + ".effect.tears_of_thunder.tooltip";

    public static final ItemEffect unceasingStormEffect = ItemEffect.get(MoreModTetra.MODID + ":unceasing_storm");
    public static final String unceasingStormName = MoreModTetra.MODID + ".effect.unceasing_storm.name";
    public static final String unceasingStormTooltip = MoreModTetra.MODID + ".effect.unceasing_storm.tooltip";

    public static final ItemEffect iAmStormEffect = ItemEffect.get(MoreModTetra.MODID + ":i_am_storm");
    public static final String iAmStormName = MoreModTetra.MODID + ".effect.i_am_storm.name";
    public static final String iAmStormTooltip = MoreModTetra.MODID + ".effect.i_am_storm.tooltip";
    //洞穴
    public static final ItemEffect oceanPearlEffect = ItemEffect.get(MoreModTetra.MODID + ":ocean_pearl");
    public static final String oceanPearlName = MoreModTetra.MODID + ".effect.ocean_pearl.name";
    public static final String oceanPearlTooltip = MoreModTetra.MODID + ".effect.ocean_pearl.tooltip";

    public static final ItemEffect radioactiveMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":radioactive_material");
    public static final String radioactiveMaterialName = MoreModTetra.MODID + ".effect.radioactive_material.name";
    public static final String radioactiveMaterialTooltip = MoreModTetra.MODID + ".effect.radioactive_material.tooltip";

    public static final ItemEffect deepDarkFantasyEffect = ItemEffect.get(MoreModTetra.MODID + ":deep_dark_fantasy");
    public static final String deepDarkFantasyName = MoreModTetra.MODID + ".effect.deep_dark_fantasy.name";
    public static final String deepDarkFantasyTooltip = MoreModTetra.MODID + ".effect.deep_dark_fantasy.tooltip";

    public static final ItemEffect intoBubbledEffect = ItemEffect.get(MoreModTetra.MODID + ":into_bubbled");
    public static final String intoBubbledName = MoreModTetra.MODID + ".effect.into_bubbled.name";
    public static final String intoBubbledTooltip = MoreModTetra.MODID + ".effect.into_bubbled.tooltip";

    public static final ItemEffect radiationCoreEffect = ItemEffect.get(MoreModTetra.MODID + ":radiation_core");
    public static final String radiationCoreName = MoreModTetra.MODID + ".effect.radiation_core.name";
    public static final String radiationCoreTooltip = MoreModTetra.MODID + ".effect.radiation_core.tooltip";

    public static final ItemEffect lavaMobEffect = ItemEffect.get(MoreModTetra.MODID + ":lava_mob");
    public static final String lavaMobName = MoreModTetra.MODID + ".effect.lava_mob.name";
    public static final String lavaMobTooltip = MoreModTetra.MODID + ".effect.lava_mob.tooltip";

    public static final ItemEffect extinctionEffect = ItemEffect.get(MoreModTetra.MODID + ":extinction");
    public static final String extinctionName = MoreModTetra.MODID + ".effect.extinction.name";
    public static final String extinctionTooltip = MoreModTetra.MODID + ".effect.extinction.tooltip";

    public static final ItemEffect magnetizingMetalEffect = ItemEffect.get(MoreModTetra.MODID + ":magnetizing_metal");
    public static final String magnetizingMetalName = MoreModTetra.MODID + ".effect.magnetizing_metal.name";
    public static final String magnetizingMetalTooltip = MoreModTetra.MODID + ".effect.magnetizing_metal.tooltip";

    public static final ItemEffect scarletMagnetizingPoleEffect = ItemEffect.get(MoreModTetra.MODID + ":scarlet_magnetizing_pole");
    public static final String scarletMagnetizingPoleName = MoreModTetra.MODID + ".effect.scarlet_magnetizing_pole.name";
    public static final String scarletMagnetizingPoleTooltip = MoreModTetra.MODID + ".effect.scarlet_magnetizing_pole.tooltip";

    public static final ItemEffect azureMagnetizingPoleEffect = ItemEffect.get(MoreModTetra.MODID + ":azure_magnetizing_pole");
    public static final String azureMagnetizingPoleName = MoreModTetra.MODID + ".effect.azure_magnetizing_pole.name";
    public static final String azureMagnetizingPoleTooltip = MoreModTetra.MODID + ".effect.azure_magnetizing_pole.tooltip";

    public static final ItemEffect magneticFieldEffect = ItemEffect.get(MoreModTetra.MODID + ":magnetic_field");
    public static final String magneticFieldName = MoreModTetra.MODID + ".effect.magnetic_field.name";
    public static final String magneticFieldTooltip = MoreModTetra.MODID + ".effect.magnetic_field.tooltip";

    public static final ItemEffect abyssalOceanEchoEffect = ItemEffect.get(MoreModTetra.MODID + ":abyssal_ocean_echo");
    public static final String abyssalOceanEchoName = MoreModTetra.MODID + ".effect.abyssal_ocean_echo.name";
    public static final String abyssalOceanEchoTooltip = MoreModTetra.MODID + ".effect.abyssal_ocean_echo.tooltip";

    public static final ItemEffect underoceanEffect = ItemEffect.get(MoreModTetra.MODID + ":underocean");
    public static final String underoceanName = MoreModTetra.MODID + ".effect.underocean.name";
    public static final String underoceanTooltip = MoreModTetra.MODID + ".effect.underocean.tooltip";

    public static final ItemEffect radiationAbsorptionEffect = ItemEffect.get(MoreModTetra.MODID + ":radiation_absorption");
    public static final String radiationAbsorptionName = MoreModTetra.MODID + ".effect.radiation_absorption.name";
    public static final String radiationAbsorptionTooltip = MoreModTetra.MODID + ".effect.radiation_absorption.tooltip";
    //冰火
    public static final ItemEffect fireDragonsteelMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_dragonsteel_material");
    public static final String fireDragonsteelMaterialName = MoreModTetra.MODID + ".effect.fire_dragonsteel_material.name";
    public static final String fireDragonsteelMaterialTooltip = MoreModTetra.MODID + ".effect.fire_dragonsteel_material.tooltip";
    public static final ItemEffect fireDragonPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_dragon_power");
    public static final String fireDragonPowerName = MoreModTetra.MODID + ".effect.fire_dragon_power.name";
    public static final String fireDragonPowerTooltip = MoreModTetra.MODID + ".effect.fire_dragon_power.tooltip";

    public static final ItemEffect iceDragonsteelMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_dragonsteel_material");
    public static final String iceDragonsteelMaterialName = MoreModTetra.MODID + ".effect.ice_dragonsteel_material.name";
    public static final String iceDragonsteelMaterialTooltip = MoreModTetra.MODID + ".effect.ice_dragonsteel_material.tooltip";
    public static final ItemEffect iceDragonPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_dragon_power");
    public static final String iceDragonPowerName = MoreModTetra.MODID + ".effect.ice_dragon_power.name";
    public static final String iceDragonPowerTooltip = MoreModTetra.MODID + ".effect.ice_dragon_power.tooltip";

    public static final ItemEffect lightningDragonsteelMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_dragonsteel_material");
    public static final String lightningDragonsteelMaterialName = MoreModTetra.MODID + ".effect.lightning_dragonsteel_material.name";
    public static final String lightningDragonsteelMaterialTooltip = MoreModTetra.MODID + ".effect.lightning_dragonsteel_material.tooltip";
    public static final ItemEffect lightningDragonPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_dragon_power");
    public static final String lightningDragonPowerName = MoreModTetra.MODID + ".effect.lightning_dragon_power.name";
    public static final String lightningDragonPowerTooltip = MoreModTetra.MODID + ".effect.lightning_dragon_power.tooltip";

    public static final ItemEffect fireDragonBloodCoatingEffect = ItemEffect.get(MoreModTetra.MODID + ":fire_dragon_blood_coating");
    public static final String fireDragonBloodCoatingName = MoreModTetra.MODID + ".effect.fire_dragon_blood_coating.name";
    public static final String fireDragonBloodCoatingTooltip = MoreModTetra.MODID + ".effect.fire_dragon_blood_coating.tooltip";

    public static final ItemEffect iceDragonBloodCoatingEffect = ItemEffect.get(MoreModTetra.MODID + ":ice_dragon_blood_coating");
    public static final String iceDragonBloodCoatingName = MoreModTetra.MODID + ".effect.ice_dragon_blood_coating.name";
    public static final String iceDragonBloodCoatingTooltip = MoreModTetra.MODID + ".effect.ice_dragon_blood_coating.tooltip";

    public static final ItemEffect lightningDragonBloodCoatingEffect = ItemEffect.get(MoreModTetra.MODID + ":lightning_dragon_blood_coating");
    public static final String lightningDragonBloodCoatingName = MoreModTetra.MODID + ".effect.lightning_dragon_blood_coating.name";
    public static final String lightningDragonBloodCoatingTooltip = MoreModTetra.MODID + ".effect.lightning_dragon_blood_coating.tooltip";
    //非龙
    public static final ItemEffect undeadHydraEffect = ItemEffect.get(MoreModTetra.MODID + ":undead_hydra");
    public static final String undeadHydraName = MoreModTetra.MODID + ".effect.undead_hydra.name";
    public static final String undeadHydraTooltip = MoreModTetra.MODID + ".effect.undead_hydra.tooltip";

    public static final ItemEffect weaknessRealmEffect = ItemEffect.get(MoreModTetra.MODID + ":weakness_realm");
    public static final String weaknessRealmName = MoreModTetra.MODID + ".effect.weakness_realm.name";
    public static final String weaknessRealmTooltip = MoreModTetra.MODID + ".effect.weakness_realm.tooltip";

    public static final ItemEffect witherRealmEffect = ItemEffect.get(MoreModTetra.MODID + ":wither_realm");
    public static final String witherRealmName = MoreModTetra.MODID + ".effect.wither_realm.name";
    public static final String witherRealmTooltip = MoreModTetra.MODID + ".effect.wither_realm.tooltip";

    public static final ItemEffect waterPowerEffect = ItemEffect.get(MoreModTetra.MODID + ":water_power");
    public static final String waterPowerName = MoreModTetra.MODID + ".effect.water_power.name";
    public static final String waterPowerTooltip = MoreModTetra.MODID + ".effect.water_power.tooltip";

    public static final ItemEffect ghostSwordEffect = ItemEffect.get(MoreModTetra.MODID + ":ghost_sword");
    public static final String ghostSwordName = MoreModTetra.MODID + ".effect.ghost_sword.name";
    public static final String ghostSwordTooltip = MoreModTetra.MODID + ".effect.ghost_sword.tooltip";

    public static final ItemEffect unlimitedPhantasmalBladeWorksEffect = ItemEffect.get(MoreModTetra.MODID + ":unlimited_phantasmal_blade_works");
    public static final String unlimitedPhantasmalBladeWorksName = MoreModTetra.MODID + ".effect.unlimited_phantasmal_blade_works.name";
    public static final String unlimitedPhantasmalBladeWorksTooltip = MoreModTetra.MODID + ".effect.unlimited_phantasmal_blade_works.tooltip";
    //神秘遗物
    public static final ItemEffect evilIngotMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":evil_ingot_material");
    public static final String evilIngotMaterialName = MoreModTetra.MODID + ".effect.evil_ingot_material.name";
    public static final String evilIngotMaterialTooltip = MoreModTetra.MODID + ".effect.evil_ingot_material.tooltip";

    public static final ItemEffect etheriumIngotMaterialEffect = ItemEffect.get(MoreModTetra.MODID + ":etherium_ingot_material");
    public static final String etheriumIngotMaterialName = MoreModTetra.MODID + ".effect.etherium_ingot_material.name";
    public static final String etheriumIngotMaterialTooltip = MoreModTetra.MODID + ".effect.etherium_ingot_material.tooltip";

    public static final ItemEffect evilCurseEffect = ItemEffect.get(MoreModTetra.MODID + ":evil_curse");
    public static final String evilCurseName = MoreModTetra.MODID + ".effect.evil_curse.name";
    public static final String evilCurseTooltip = MoreModTetra.MODID + ".effect.evil_curse.tooltip";

    public static final ItemEffect etheriumGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":etherium_guard");
    public static final String etheriumGuardName = MoreModTetra.MODID + ".effect.etherium_guard.name";
    public static final String etheriumGuardTooltip = MoreModTetra.MODID + ".effect.etherium_guard.tooltip";
    //植物魔法
    public static final ItemEffect manaRegenerationEffect = ItemEffect.get(MoreModTetra.MODID + ":mana_regeneration");
    public static final String manaRegenerationName = MoreModTetra.MODID + ".effect.mana_regeneration.name";
    public static final String manaRegenerationTooltip = MoreModTetra.MODID + ".effect.mana_regeneration.tooltip";

    public static final ItemEffect pixieSummonEffect = ItemEffect.get(MoreModTetra.MODID + ":pixie_summon");
    public static final String pixieSummonName = MoreModTetra.MODID + ".effect.pixie_summon.name";
    public static final String pixieSummonTooltip = MoreModTetra.MODID + ".effect.pixie_summon.tooltip";

    public static final ItemEffect terraRayEffect = ItemEffect.get(MoreModTetra.MODID + ":terra_ray");
    public static final String terraRayName = MoreModTetra.MODID + ".effect.terra_ray.name";
    public static final String terraRayTooltip = MoreModTetra.MODID + ".effect.terra_ray.tooltip";

    public static final ItemEffect willOfGaiaEffect = ItemEffect.get(MoreModTetra.MODID + ":will_of_gaia");
    public static final String willOfGaiaName = MoreModTetra.MODID + ".effect.will_of_gaia.name";
    public static final String willOfGaiaTooltip = MoreModTetra.MODID + ".effect.will_of_gaia.tooltip";

    public static final ItemEffect manaAbsorptionEffect = ItemEffect.get(MoreModTetra.MODID + ":mana_absorption");
    public static final String manaAbsorptionName = MoreModTetra.MODID + ".effect.mana_absorption.name";
    public static final String manaAbsorptionTooltip = MoreModTetra.MODID + ".effect.mana_absorption.tooltip";
    //盖亚众魂
    public static final ItemEffect ancientWillAhrimEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_ahrim");
    public static final String ancientWillAhrimName = MoreModTetra.MODID + ".effect.ancient_will_ahrim.name";
    public static final String ancientWillAhrimTooltip = MoreModTetra.MODID + ".effect.ancient_will_ahrim.tooltip";

    public static final ItemEffect ancientWillDharokEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_dharok");
    public static final String ancientWillDharokName = MoreModTetra.MODID + ".effect.ancient_will_dharok.name";
    public static final String ancientWillDharokTooltip = MoreModTetra.MODID + ".effect.ancient_will_dharok.tooltip";

    public static final ItemEffect ancientWillGuthanEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_guthan");
    public static final String ancientWillGuthanName = MoreModTetra.MODID + ".effect.ancient_will_guthan.name";
    public static final String ancientWillGuthanTooltip = MoreModTetra.MODID + ".effect.ancient_will_guthan.tooltip";

    public static final ItemEffect ancientWillToragEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_torag");
    public static final String ancientWillToragName = MoreModTetra.MODID + ".effect.ancient_will_torag.name";
    public static final String ancientWillToragTooltip = MoreModTetra.MODID + ".effect.ancient_will_torag.tooltip";

    public static final ItemEffect ancientWillVeracEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_verac");
    public static final String ancientWillVeracName = MoreModTetra.MODID + ".effect.ancient_will_verac.name";
    public static final String ancientWillVeracTooltip = MoreModTetra.MODID + ".effect.ancient_will_verac.tooltip";

    public static final ItemEffect ancientWillKarilEffect = ItemEffect.get(MoreModTetra.MODID + ":ancient_will_karil");
    public static final String ancientWillKarilName = MoreModTetra.MODID + ".effect.ancient_will_karil.name";
    public static final String ancientWillKarilTooltip = MoreModTetra.MODID + ".effect.ancient_will_karil.tooltip";


    //原版
    public static final ItemEffect poisonEffect = ItemEffect.get(MoreModTetra.MODID + ":poison");
    public static final String poisonName = MoreModTetra.MODID + ".effect.poison.name";
    public static final String poisonTooltip = MoreModTetra.MODID + ".effect.poison.tooltip";

    public static final ItemEffect levitationEffect = ItemEffect.get(MoreModTetra.MODID + ":levitation");
    public static final String levitationName = MoreModTetra.MODID + ".effect.levitation.name";
    public static final String levitationTooltip = MoreModTetra.MODID + ".effect.levitation.tooltip";

    public static final ItemEffect igniteEffect = ItemEffect.get(MoreModTetra.MODID + ":ignite");
    public static final String igniteName = MoreModTetra.MODID + ".effect.ignite.name";
    public static final String igniteTooltip = MoreModTetra.MODID + ".effect.ignite.tooltip";

    public static final ItemEffect slownessEffect = ItemEffect.get(MoreModTetra.MODID + ":slowness");
    public static final String slownessName = MoreModTetra.MODID + ".effect.slowness.name";
    public static final String slownessTooltip = MoreModTetra.MODID + ".effect.slowness.tooltip";

    public static final ItemEffect witherEffect = ItemEffect.get(MoreModTetra.MODID + ":wither");
    public static final String witherName = MoreModTetra.MODID + ".effect.wither.name";
    public static final String witherTooltip = MoreModTetra.MODID + ".effect.wither.tooltip";

    public static final ItemEffect endermanKillerEffect = ItemEffect.get(MoreModTetra.MODID + ":enderman_killer");
    public static final String endermanKillerName = MoreModTetra.MODID + ".effect.enderman_killer.name";
    public static final String endermanKillerTooltip = MoreModTetra.MODID + ".effect.enderman_killer.tooltip";

    public static final ItemEffect emeraldPillageEffect = ItemEffect.get(MoreModTetra.MODID + ":emerald_pillage");
    public static final String emeraldPillageName = MoreModTetra.MODID + ".effect.emerald_pillage.name";
    public static final String emeraldPillageTooltip = MoreModTetra.MODID + ".effect.emerald_pillage.tooltip";

    public static final ItemEffect phantomKillerEffect = ItemEffect.get(MoreModTetra.MODID + ":phantom_killer");
    public static final String phantomKillerName = MoreModTetra.MODID + ".effect.phantom_killer.name";
    public static final String phantomKillerTooltip = MoreModTetra.MODID + ".effect.phantom_killer.tooltip";

    public static final ItemEffect piglinKillerEffect = ItemEffect.get(MoreModTetra.MODID + ":piglin_killer");
    public static final String piglinKillerName = MoreModTetra.MODID + ".effect.piglin_killer.name";
    public static final String piglinKillerTooltip = MoreModTetra.MODID + ".effect.piglin_killer.tooltip";

    public static final ItemEffect flameKillerEffect = ItemEffect.get(MoreModTetra.MODID + ":flame_killer");
    public static final String flameKillerName = MoreModTetra.MODID + ".effect.flame_killer.name";
    public static final String flameKillerTooltip = MoreModTetra.MODID + ".effect.flame_killer.tooltip";

    public static final ItemEffect flamelessKillerEffect = ItemEffect.get(MoreModTetra.MODID + ":flameless_killer");
    public static final String flamelessKillerName = MoreModTetra.MODID + ".effect.flameless_killer.name";
    public static final String flamelessKillerTooltip = MoreModTetra.MODID + ".effect.flameless_killer.tooltip";

    public static final ItemEffect minerLanternEffect = ItemEffect.get(MoreModTetra.MODID + ":miner_lantern");
    public static final String minerLanternName = MoreModTetra.MODID + ".effect.miner_lantern.name";
    public static final String minerLanternTooltip = MoreModTetra.MODID + ".effect.miner_lantern.tooltip";

    public static final ItemEffect overSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":over_slash");
    public static final String overSlashName = MoreModTetra.MODID + ".effect.over_slash.name";
    public static final String overSlashTooltip = MoreModTetra.MODID + ".effect.over_slash.tooltip";

    public static final ItemEffect ultimateSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":ultimate_slash");
    public static final String ultimateSlashName = MoreModTetra.MODID + ".effect.ultimate_slash.name";
    public static final String ultimateSlashTooltip = MoreModTetra.MODID + ".effect.ultimate_slash.tooltip";

    public static final ItemEffect finalSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":final_slash");
    public static final String finalSlashName = MoreModTetra.MODID + ".effect.final_slash.name";
    public static final String finalSlashTooltip = MoreModTetra.MODID + ".effect.final_slash.tooltip";

    public static final ItemEffect awakeningSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":awakening_slash");
    public static final String awakeningSlashName = MoreModTetra.MODID + ".effect.awakening_slash.name";
    public static final String awakeningSlashTooltip = MoreModTetra.MODID + ".effect.awakening_slash.tooltip";

    public static final ItemEffect trueSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":true_slash");
    public static final String trueSlashName = MoreModTetra.MODID + ".effect.true_slash.name";
    public static final String trueSlashTooltip = MoreModTetra.MODID + ".effect.true_slash.tooltip";

    public static final ItemEffect exceedSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":exceed_slash");
    public static final String exceedSlashName = MoreModTetra.MODID + ".effect.exceed_slash.name";
    public static final String exceedSlashTooltip = MoreModTetra.MODID + ".effect.exceed_slash.tooltip";

    public static final ItemEffect risingSlashEffect = ItemEffect.get(MoreModTetra.MODID + ":rising_slash");
    public static final String risingSlashName = MoreModTetra.MODID + ".effect.rising_slash.name";
    public static final String risingSlashTooltip = MoreModTetra.MODID + ".effect.rising_slash.tooltip";

    public static final ItemEffect constantFluxEffect = ItemEffect.get(MoreModTetra.MODID + ":constant_flux");
    public static final String constantFluxName = MoreModTetra.MODID + ".effect.constant_flux.name";
    public static final String constantFluxTooltip = MoreModTetra.MODID + ".effect.constant_flux.tooltip";

    public static final ItemEffect defeatDemonsEffect = ItemEffect.get(MoreModTetra.MODID + ":defeat_demons");
    public static final String defeatDemonsName = MoreModTetra.MODID + ".effect.defeat_demons.name";
    public static final String defeatDemonsTooltip = MoreModTetra.MODID + ".effect.defeat_demons.tooltip";

    public static final ItemEffect enderferenceEffect = ItemEffect.get(MoreModTetra.MODID + ":enderference");
    public static final String enderferenceName = MoreModTetra.MODID + ".effect.enderference.name";
    public static final String enderferenceTooltip = MoreModTetra.MODID + ".effect.enderference.tooltip";

    public static final ItemEffect assassinateEffect = ItemEffect.get(MoreModTetra.MODID + ":assassinate");
    public static final String assassinateName = MoreModTetra.MODID + ".effect.assassinate.name";
    public static final String assassinateTooltip = MoreModTetra.MODID + ".effect.assassinate.tooltip";

    public static final ItemEffect banHealEffect = ItemEffect.get(MoreModTetra.MODID + ":ban_heal");
    public static final String banHealName = MoreModTetra.MODID + ".effect.ban_heal.name";
    public static final String banHealTooltip = MoreModTetra.MODID + ".effect.ban_heal.tooltip";

    public static final ItemEffect powerlessEffect = ItemEffect.get(MoreModTetra.MODID + ":powerless");
    public static final String powerlessName = MoreModTetra.MODID + ".effect.powerless.name";
    public static final String powerlessTooltip = MoreModTetra.MODID + ".effect.powerless.tooltip";

    public static final ItemEffect underwaterOperationsEffect = ItemEffect.get(MoreModTetra.MODID + ":underwater_operations");
    public static final String underwaterOperationsName = MoreModTetra.MODID + ".effect.underwater_operations.name";
    public static final String underwaterOperationsTooltip = MoreModTetra.MODID + ".effect.underwater_operations.tooltip";

    public static final ItemEffect reverseMirrorEffect = ItemEffect.get(MoreModTetra.MODID + ":reverse_mirror");
    public static final String reverseMirrorName = MoreModTetra.MODID + ".effect.reverse_mirror.name";
    public static final String reverseMirrorTooltip = MoreModTetra.MODID + ".effect.reverse_mirror.tooltip";

    public static final ItemEffect emergencyRescueEffect = ItemEffect.get(MoreModTetra.MODID + ":emergency_rescue");
    public static final String emergencyRescueName = MoreModTetra.MODID + ".effect.emergency_rescue.name";
    public static final String emergencyRescueTooltip = MoreModTetra.MODID + ".effect.emergency_rescue.tooltip";

    public static final ItemEffect workWorkWorkEffect = ItemEffect.get(MoreModTetra.MODID + ":work_work_work");
    public static final String workWorkWorkName = MoreModTetra.MODID + ".effect.work_work_work.name";
    public static final String workWorkWorkTooltip = MoreModTetra.MODID + ".effect.work_work_work.tooltip";

    public static final ItemEffect curiosMaterialsEffectInvalidationEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_materials_effect_invalidation");
    public static final String curiosMaterialsEffectInvalidationName = MoreModTetra.MODID + ".effect.curios_materials_effect_invalidation.name";
    public static final String curiosMaterialsEffectInvalidationTooltip = MoreModTetra.MODID + ".effect.curios_materials_effect_invalidation.tooltip";

    public static final ItemEffect curiosSlownessClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_slowness_clear");
    public static final String curiosSlownessClearName = MoreModTetra.MODID + ".effect.curios_slowness_clear.name";
    public static final String curiosSlownessClearTooltip = MoreModTetra.MODID + ".effect.curios_slowness_clear.tooltip";

    public static final ItemEffect curiosWeaknessClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_weakness_clear");
    public static final String curiosWeaknessClearName = MoreModTetra.MODID + ".effect.curios_weakness_clear.name";
    public static final String curiosWeaknessClearTooltip = MoreModTetra.MODID + ".effect.curios_weakness_clear.tooltip";

    public static final ItemEffect curiosMiningFatigueClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_mining_fatigue_clear");
    public static final String curiosMiningFatigueClearName = MoreModTetra.MODID + ".effect.curios_mining_fatigue_clear.name";
    public static final String curiosMiningFatigueClearTooltip = MoreModTetra.MODID + ".effect.curios_mining_fatigue_clear.tooltip";

    public static final ItemEffect curiosHungerClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_hunger_clear");
    public static final String curiosHungerClearName = MoreModTetra.MODID + ".effect.curios_hunger_clear.name";
    public static final String curiosHungerClearTooltip = MoreModTetra.MODID + ".effect.curios_hunger_clear.tooltip";

    public static final ItemEffect curiosFireClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_fire_clear");
    public static final String curiosFireClearName = MoreModTetra.MODID + ".effect.curios_fire_clear.name";
    public static final String curiosFireClearTooltip = MoreModTetra.MODID + ".effect.curios_fire_clear.tooltip";

    public static final ItemEffect curiosDarknessClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_darkness_clear");
    public static final String curiosDarknessClearName = MoreModTetra.MODID + ".effect.curios_darkness_clear.name";
    public static final String curiosDarknessClearTooltip = MoreModTetra.MODID + ".effect.curios_darkness_clear.tooltip";

    public static final ItemEffect curiosBlindnessClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_blindness_clear");
    public static final String curiosBlindnessClearName = MoreModTetra.MODID + ".effect.curios_blindness_clear.name";
    public static final String curiosBlindnessClearTooltip = MoreModTetra.MODID + ".effect.curios_blindness_clear.tooltip";

    public static final ItemEffect curiosLevitationClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_levitation_clear");
    public static final String curiosLevitationClearName = MoreModTetra.MODID + ".effect.curios_levitation_clear.name";
    public static final String curiosLevitationClearTooltip = MoreModTetra.MODID + ".effect.curios_levitation_clear.tooltip";

    public static final ItemEffect curiosPoisonClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_poison_clear");
    public static final String curiosPoisonClearName = MoreModTetra.MODID + ".effect.curios_poison_clear.name";
    public static final String curiosPoisonClearTooltip = MoreModTetra.MODID + ".effect.curios_poison_clear.tooltip";

    public static final ItemEffect curiosWitherClearEffect = ItemEffect.get(MoreModTetra.MODID + ":curios_wither_clear");
    public static final String curiosWitherClearName = MoreModTetra.MODID + ".effect.curios_wither_clear.name";
    public static final String curiosWitherClearTooltip = MoreModTetra.MODID + ".effect.curios_wither_clear.tooltip";

    public static final ItemEffect hideBladeEffect = ItemEffect.get(MoreModTetra.MODID + ":hide_blade");
    public static final String hideBladeName = MoreModTetra.MODID + ".effect.hide_blade.name";
    public static final String hideBladeTooltip = MoreModTetra.MODID + ".effect.hide_blade.tooltip";

    public static final ItemEffect ripeningHaloEffect = ItemEffect.get(MoreModTetra.MODID + ":ripening_halo");
    public static final String ripeningHaloName = MoreModTetra.MODID + ".effect.ripening_halo.name";
    public static final String ripeningHaloTooltip = MoreModTetra.MODID + ".effect.ripening_halo.tooltip";

    public static final ItemEffect entityResonanceEffect = ItemEffect.get(MoreModTetra.MODID + ":entity_resonance");
    public static final String entityResonanceName = MoreModTetra.MODID + ".effect.entity_resonance.name";
    public static final String entityResonanceTooltip = MoreModTetra.MODID + ".effect.entity_resonance.tooltip";
    //二天一流
    public static final ItemEffect NitenIchiryuKatanaEffect = ItemEffect.get(MoreModTetra.MODID + ":niten_ichiryu_katana");
    public static final String NitenIchiryuKatanaName = MoreModTetra.MODID + ".effect.niten_ichiryu_katana.name";
    public static final String NitenIchiryuKatanaTooltip = MoreModTetra.MODID + ".effect.niten_ichiryu_katana.tooltip";

    public static final ItemEffect NitenIchiryuWakizashiEffect = ItemEffect.get(MoreModTetra.MODID + ":niten_ichiryu_wakizashi");
    public static final String NitenIchiryuWakizashiName = MoreModTetra.MODID + ".effect.niten_ichiryu_wakizashi.name";
    public static final String NitenIchiryuWakizashiTooltip = MoreModTetra.MODID + ".effect.niten_ichiryu_wakizashi.tooltip";
    //风暴符文
    public static final ItemEffect runeOfTheStormEffect = ItemEffect.get(MoreModTetra.MODID + ":rune_of_the_storm");
    public static final String runeOfTheStormName = MoreModTetra.MODID + ".effect.rune_of_the_storm.name";
    public static final String runeOfTheStormTooltip = MoreModTetra.MODID + ".effect.rune_of_the_storm.tooltip";
    //明日方舟
    public static final ItemEffect expandedCognitionEffect = ItemEffect.get(MoreModTetra.MODID + ":expanded_cognition");
    public static final String expandedCognitionName = MoreModTetra.MODID + ".effect.expanded_cognition.name";
    public static final String expandedCognitionTooltip = MoreModTetra.MODID + ".effect.expanded_cognition.tooltip";

    public static final ItemEffect nociceptorInhibitionEffect = ItemEffect.get(MoreModTetra.MODID + ":nociceptor_inhibition");
    public static final String nociceptorInhibitionName = MoreModTetra.MODID + ".effect.nociceptor_inhibition.name";
    public static final String nociceptorInhibitionTooltip = MoreModTetra.MODID + ".effect.nociceptor_inhibition.tooltip";

    public static final ItemEffect notesEffect = ItemEffect.get(MoreModTetra.MODID + ":notes");
    public static final String notesName = MoreModTetra.MODID + ".effect.notes.name";
    public static final String notesTooltip = MoreModTetra.MODID + ".effect.notes.tooltip";

    public static final ItemEffect freezeEffect = ItemEffect.get(MoreModTetra.MODID + ":freeze");
    public static final String freezeName = MoreModTetra.MODID + ".effect.freeze.name";
    public static final String freezeTooltip = MoreModTetra.MODID + ".effect.freeze.tooltip";

    public static final ItemEffect freezeRingEffect = ItemEffect.get(MoreModTetra.MODID + ":freeze_ring");
    public static final String freezeRingName = MoreModTetra.MODID + ".effect.freeze_ring.name";
    public static final String freezeRingTooltip = MoreModTetra.MODID + ".effect.freeze_ring.tooltip";

    public static final ItemEffect coldWaveEffect = ItemEffect.get(MoreModTetra.MODID + ":cold_wave");
    public static final String coldWaveName = MoreModTetra.MODID + ".effect.cold_wave.name";
    public static final String coldWaveTooltip = MoreModTetra.MODID + ".effect.cold_wave.tooltip";

    public static final ItemEffect ritualOfHolyGuardEffect = ItemEffect.get(MoreModTetra.MODID + ":ritual_of_holy_guard");
    public static final String ritualOfHolyGuardName = MoreModTetra.MODID + ".effect.ritual_of_holy_guard.name";
    public static final String ritualOfHolyGuardTooltip = MoreModTetra.MODID + ".effect.ritual_of_holy_guard.tooltip";

    public static final ItemEffect ritualOfExhortationEffect = ItemEffect.get(MoreModTetra.MODID + ":ritual_of_exhortation");
    public static final String ritualOfExhortationName = MoreModTetra.MODID + ".effect.ritual_of_exhortation.name";
    public static final String ritualOfExhortationTooltip = MoreModTetra.MODID + ".effect.ritual_of_exhortation.tooltip";

    public static final ItemEffect exhortationOfGunKnightPatriotEffect = ItemEffect.get(MoreModTetra.MODID + ":exhortation_of_gun_knight_patriot");
    public static final String exhortationOfGunKnightPatriotName = MoreModTetra.MODID + ".effect.exhortation_of_gun_knight_patriot.name";
    public static final String exhortationOfGunKnightPatriotTooltip = MoreModTetra.MODID + ".effect.exhortation_of_gun_knight_patriot.tooltip";

    public static final ItemEffect ruinationTimeEffect = ItemEffect.get(MoreModTetra.MODID + ":ruination_time");
    public static final String ruinationTimeName = MoreModTetra.MODID + ".effect.ruination_time.name";
    public static final String ruinationTimeTooltip = MoreModTetra.MODID + ".effect.ruination_time.tooltip";

    public static final ItemEffect marchingTimeEffect = ItemEffect.get(MoreModTetra.MODID + ":marching_time");
    public static final String marchingTimeName = MoreModTetra.MODID + ".effect.marching_time.name";
    public static final String marchingTimeTooltip = MoreModTetra.MODID + ".effect.marching_time.tooltip";

    public static final ItemEffect SacrificeAndThrowingTheHalberdEffect = ItemEffect.get(MoreModTetra.MODID + ":sacrifice_and_throwing_the_halberd");
    public static final String SacrificeAndThrowingTheHalberdName = MoreModTetra.MODID + ".effect.sacrifice_and_throwing_the_halberd.name";
    public static final String SacrificeAndThrowingTheHalberdTooltip = MoreModTetra.MODID + ".effect.sacrifice_and_throwing_the_halberd.tooltip";

    public static final ItemEffect collapsingFearEffect = ItemEffect.get(MoreModTetra.MODID + ":collapsing_fear");
    public static final String collapsingFearName = MoreModTetra.MODID + ".effect.collapsing_fear.name";
    public static final String collapsingFearTooltip = MoreModTetra.MODID + ".effect.collapsing_fear.tooltip";

    public static final ItemEffect dominionEffect = ItemEffect.get(MoreModTetra.MODID + ":dominion");
    public static final String dominionName = MoreModTetra.MODID + ".effect.dominion.name";
    public static final String dominionTooltip = MoreModTetra.MODID + ".effect.dominion.tooltip";
    //水月
    public static final ItemEffect sanityHurtEffect = ItemEffect.get(MoreModTetra.MODID + ":sanity_hurt");
    public static final String sanityHurtName = MoreModTetra.MODID + ".effect.sanity_hurt.name";
    public static final String sanityHurtTooltip = MoreModTetra.MODID + ".effect.sanity_hurt.tooltip";
    //判断boss
    public static boolean isBossEntity(EntityType<?> entity) {
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("more_mod_tetra", "bosses"))
        ).contains(entity);
    }
    public static boolean isWaterEntity(EntityType<?> entity) {
        return Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("more_mod_tetra", "bosses"))
        ).contains(entity);
    }

    //用于计算百分比
    public static float getDecimalPercentage(float percentage, float base) {
        float totalPercentage = (base * (percentage / 100));

        return totalPercentage;
    }
    //将一个值直接加到基数上。
    public static float getExactPercentage(float base, float percentage) {
        float totalPercentage = base + percentage;

        return totalPercentage;
    }
}