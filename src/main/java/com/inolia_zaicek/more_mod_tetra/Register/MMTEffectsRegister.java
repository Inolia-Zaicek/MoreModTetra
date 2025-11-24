package com.inolia_zaicek.more_mod_tetra.Register;

import com.inolia_zaicek.more_mod_tetra.Register.Effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID;

public class MMTEffectsRegister {
    public static final DeferredRegister<MobEffect> INOEFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,MODID);
    //决斗
    public static final RegistryObject<MobEffect> BeneficialReverDuel = INOEFFECT.register("beneficial_rever_duel", com.inolia_zaicek.more_mod_tetra.Register.Effect.BeneficialReverDuel::new);
    public static final RegistryObject<MobEffect> NeutralReverDuel = INOEFFECT.register("neutral_rever_duel", com.inolia_zaicek.more_mod_tetra.Register.Effect.NeutralReverDuel::new);
    public static final RegistryObject<MobEffect> NitenIchiryuKatana = INOEFFECT.register("niten_ichiryu_katana", com.inolia_zaicek.more_mod_tetra.Register.Effect.NitenIchiryuKatana::new);
    public static final RegistryObject<MobEffect> NitenIchiryuWakizashi = INOEFFECT.register("niten_ichiryu_wakizashi", com.inolia_zaicek.more_mod_tetra.Register.Effect.NitenIchiryuWakizashi::new);
    public static final RegistryObject<MobEffect> Powerless = INOEFFECT.register("powerless", com.inolia_zaicek.more_mod_tetra.Register.Effect.Powerless::new);
    public static final RegistryObject<MobEffect> StormEye = INOEFFECT.register("storm_eye", StormEye::new);
    public static final RegistryObject<MobEffect> BanHeal = INOEFFECT.register("ban_heal", BanHeal::new);
    public static final RegistryObject<MobEffect> ConstantFlux = INOEFFECT.register("constant_flux", ConstantFlux::new);
    public static final RegistryObject<MobEffect> Unstun = INOEFFECT.register("unstun", Unstun::new);
    public static final RegistryObject<MobEffect> DragonIce = INOEFFECT.register("dragon_ice", DragonIce::new);
    public static final RegistryObject<MobEffect> HideBlade = INOEFFECT.register("hide_blade", HideBlade::new);
    public static final RegistryObject<MobEffect> HideBladeMax = INOEFFECT.register("hide_blade_max", HideBladeMax::new);
    public static final RegistryObject<MobEffect> IndustrialProtection = INOEFFECT.register("industrial_protection", IndustrialProtection::new);
    public static final RegistryObject<MobEffect> UnIndustrialProtection = INOEFFECT.register("unindustrial_protection", IndustrialProtection::new);
    public static final RegistryObject<MobEffect> L2Speedy = INOEFFECT.register("l2_speedy", L2Speedy::new);
    public static final RegistryObject<MobEffect> L2Tanky = INOEFFECT.register("l2_tanky", L2Tanky::new);
    public static final RegistryObject<MobEffect> L2Repelling = INOEFFECT.register("l2_repelling", L2Repelling::new);
    public static final RegistryObject<MobEffect> RitualOfHolyGuard = INOEFFECT.register("ritual_of_holy_guard", RitualOfHolyGuard::new);
    public static final RegistryObject<MobEffect> RuinationTime = INOEFFECT.register("ruination_time", RuinationTime::new);
    public static final RegistryObject<MobEffect> MarchingTime = INOEFFECT.register("marching_time", MarchingTime::new);
    public static final RegistryObject<MobEffect> PatriotRenascenceCoolingTime = INOEFFECT.register("patriot_renascence_cooling_time", PatriotRenascenceCoolingTime::new);
    public static final RegistryObject<MobEffect> ThrowingTheHalberd = INOEFFECT.register("throwing_the_halberd", HideBlade::new);
    public static final RegistryObject<MobEffect> ThrowingTheHalberdMax = INOEFFECT.register("throwing_the_halberd_max", HideBladeMax::new);
    public static final RegistryObject<MobEffect> Dominion = INOEFFECT.register("dominion", Dominion::new);
    public static final RegistryObject<MobEffect> FakeInsatiable = INOEFFECT.register("fake_insatiable", FakeInsatiable::new);
    public static final RegistryObject<MobEffect> LetThePeopleRejoice = INOEFFECT.register("let_the_people_rejoice", LetThePeopleRejoice::new);
    public static final RegistryObject<MobEffect> StarBurstStream = INOEFFECT.register("star_burst_stream", StarBurstStream::new);
    public static final RegistryObject<MobEffect> Eclipse = INOEFFECT.register("eclipse", Eclipse::new);
    public static final RegistryObject<MobEffect> EclipseStarBurstStream = INOEFFECT.register("eclipse_star_burst_stream", EclipseStarBurstStream::new);
    public static final RegistryObject<MobEffect> SanctuaryOfMooncocoon = INOEFFECT.register("sanctuary_of_mooncocoon", SanctuaryOfMooncocoon::new);
    public static final RegistryObject<MobEffect> SanctuaryOfMooncocoonCooldown = INOEFFECT.register("sanctuary_of_mooncocoon_cooldown", SanctuaryOfMooncocoonCooldown::new);
    public static final RegistryObject<MobEffect> Enderference = INOEFFECT.register("enderference", Enderference::new);
    public static final RegistryObject<MobEffect> BokushuuSDesire = INOEFFECT.register("bokushuu_s_desire", BokushuuSDesireBuff::new);
}
