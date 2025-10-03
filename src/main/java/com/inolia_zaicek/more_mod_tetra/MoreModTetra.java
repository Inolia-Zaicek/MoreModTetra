package com.inolia_zaicek.more_mod_tetra;


import com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.*;
import com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core.MagneticField;
import com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core.RadiationAbsorption;
import com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core.RadiationCore;
import com.inolia_zaicek.more_mod_tetra.Effect.AlexCave.Core.Underocean;
import com.inolia_zaicek.more_mod_tetra.Effect.Aquamirae.RuneOfTheStorm;
import com.inolia_zaicek.more_mod_tetra.Effect.Ars.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Biomancy.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Botania.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Botania.AncientWill.*;
import com.inolia_zaicek.more_mod_tetra.Effect.CaerulaArbor.SanityHurt;
import com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Core.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Storm.UnceasingStorm;
import com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Witherite.WitheriteA;
import com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Witherite.WitheriteB;
import com.inolia_zaicek.more_mod_tetra.Effect.Duel;
import com.inolia_zaicek.more_mod_tetra.Effect.EidolonRepraised.ShadowGem;
import com.inolia_zaicek.more_mod_tetra.Effect.EidolonRepraised.SoulShard;
import com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy.EtheriumGuard;
import com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy.EtheriumIngotMaterial;
import com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy.EvilCurse;
import com.inolia_zaicek.more_mod_tetra.Effect.Enigmaticlegacy.EvilIngotMaterial;
import com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany.ManaForce;
import com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany.ShadowiumPower;
import com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany.ShiningPower;
import com.inolia_zaicek.more_mod_tetra.Effect.Extrabotany.SpeedForce;
import com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct.FakeInsatiable;
import com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct.FakeMomentum;
import com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct.FakeTasty;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect.ColdWave;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect.Freeze;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.FrostNovaIngotEffect.FreezeRing;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.GunKnightPatriotIngotEffect.ExhortationOfGunKnightPatriot;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.GunKnightPatriotIngotEffect.RitualOfExhortation;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.GunKnightPatriotIngotEffect.RitualOfHolyGuard;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.IngotDrop.MMTIngotDrop;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect.MarchingTimeAndRuinationTime;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect.MarchingTimeHurt;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PatriotIngotEffect.SacrificeAndThrowingTheHalberd;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PursuerIngotEffect.CollapsingFear;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PursuerIngotEffect.Dominion;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.RosmontisIngotEffect.ExpandedCognition;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.RosmontisIngotEffect.NociceptorInhibition;
import com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.RosmontisIngotEffect.Notes;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.FireDragonSteel.FireDragonBloodCoating;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.FireDragonSteel.FireDragonPower;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.FireDragonSteel.FireDragonsteelMaterial;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.IceDragonSteel.IceDragonBloodCoating;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.IceDragonSteel.IceDragonPower;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.IceDragonSteel.IceDragonsteelMaterial;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.LightningDragonSteel.LightningDragonBloodCoating;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.LightningDragonSteel.LightningDragonPower;
import com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.LightningDragonSteel.LightningDragonsteelMaterial;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.AF.AFIronSpellTetraActualImpl;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.AF.SoundArcaneGuardEffect;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.AF.SoundStaffSocket;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Fabric.*;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Finish.*;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.IronSpellCastingEvent;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.IronSpellTetraActualImpl;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Metal.*;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.AF.SoundArcaneEdgeEffect;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.TO.AquaArcaneEdgeEffect;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.TO.AquaArcaneGuardEffect;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.TO.AquaStaffSocket;
import com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.TO.TOIronSpellTetraActualImpl;
import com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.*;
import com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.BuffClear.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.BuffGive.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.CuriosAllDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.CuriosMagicDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.CuriosMeleeDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.CuriosProjectileDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron.AFCuriosMagicDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron.IronCuriosMagicDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron.TOCuriosMagicDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Tacz.TaczCuriosProjectileDamageUp;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.DeepDarkFantasy;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.IndustrialProtection.IndustrialProtection;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.NitenIchiryu.NitenIchiryuKatana;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.NitenIchiryu.NitenIchiryuWakizashi;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Shield.ShieldSkillAutomaticDefense;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Shield.ShieldSkillAutomaticProtection;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Shield.ShieldSkillCooldownDown;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Slash.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan.*;
import com.inolia_zaicek.more_mod_tetra.Effect.MMTClientGuiRegistry;
import com.inolia_zaicek.more_mod_tetra.Effect.Malum.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Malum.TotemicRunes.*;
import com.inolia_zaicek.more_mod_tetra.Effect.StarMeowCraft.SMCFrost;
import com.inolia_zaicek.more_mod_tetra.Effect.StarMeowCraft.SMCFrostBurst;
import com.inolia_zaicek.more_mod_tetra.Event.MMTFluidCollisionEvent;
import com.inolia_zaicek.more_mod_tetra.Network.GhostSwordChannel;
import com.inolia_zaicek.more_mod_tetra.Network.TerraRayChannel;
import com.inolia_zaicek.more_mod_tetra.Network.MMTNetworkHandler;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraTab;
import com.inolia_zaicek.more_mod_tetra.Util.MMTScrollHelper;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.TetraRegistries;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"all", "removal"})
@Mod.EventBusSubscriber(modid = com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@Mod(com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID)
public class MoreModTetra {

    public static final String MODID = "more_mod_tetra";
    public static final Logger LOGGER = LogUtils.getLogger();
    public MoreModTetra() {
        init();
    }
    public void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //注册卷轴
        bus.addListener(this::buildCreativeTab);
        MoreModTetraTab.register(bus);
        MoreModTetraItemRegister.register(bus);
        MMTEffectsRegister.INOEFFECT.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        // 注册 FML 生命周期事件
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        //监听事件
        //        MinecraftForge.EVENT_BUS.register(new Duel());
        MinecraftForge.EVENT_BUS.register(Duel.class);
        MinecraftForge.EVENT_BUS.register(Wither.class);
        //铁魔法
        if(ModList.get().isLoaded("irons_spellbooks")) {
            MinecraftForge.EVENT_BUS.register(FireArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(IceArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(LightningArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(EvocationArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(HolyArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(BloodArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(EnderArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(NatureArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(EldritchArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(ProtectionArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(CooldownArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(ArcaneArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(FireArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(IceArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(LightningArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(EvocationArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(HolyArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(BloodArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(EnderArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(NatureArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(EldritchArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(FireStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(IceStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(LightningStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(EvocationStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(HolyStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(BloodStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(EnderStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(NatureStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(EldritchStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(IronCuriosMagicDamageUp.class);
        }
        if(ModList.get().isLoaded("traveloptics")) {
            MinecraftForge.EVENT_BUS.register(AquaArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(AquaArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(AquaStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(TOCuriosMagicDamageUp.class);
        }
        if(ModList.get().isLoaded("alshanex_familiars")) {
            MinecraftForge.EVENT_BUS.register(SoundArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(SoundArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(SoundStaffSocket.class);
            MinecraftForge.EVENT_BUS.register(AFCuriosMagicDamageUp.class);
        }
        if(ModList.get().isLoaded("malum")) {
            MinecraftForge.EVENT_BUS.register(StabilizingEffect.class);
            MinecraftForge.EVENT_BUS.register(RuneOfIgneousSolace.class);
            MinecraftForge.EVENT_BUS.register(RuneOfTwinnedDuration.class);
            MinecraftForge.EVENT_BUS.register(RuneOfTheHeretic.class);
            MinecraftForge.EVENT_BUS.register(RuneOfVolatileDistortion.class);
            MinecraftForge.EVENT_BUS.register(RuneOfReactiveShielding.class);
            MinecraftForge.EVENT_BUS.register(RuneOfAlimentCleansing.class);
            MinecraftForge.EVENT_BUS.register(RuneOfSacrificialEmpowerment.class);
        }
        if(ModList.get().isLoaded("eidolon")) {
            MinecraftForge.EVENT_BUS.register(SoulShard.class);
            MinecraftForge.EVENT_BUS.register(ShadowGem.class);
        }
        if(ModList.get().isLoaded("biomancy")) {
            MinecraftForge.EVENT_BUS.register(DespoilSickle.class);
            MinecraftForge.EVENT_BUS.register(AbsorptionBoost.class);
            MinecraftForge.EVENT_BUS.register(CleansingSerum.class);
            MinecraftForge.EVENT_BUS.register(CorrosiveAdditive.class);
            MinecraftForge.EVENT_BUS.register(FrenzySerum.class);
            MinecraftForge.EVENT_BUS.register(DespoilSickle.class);
            MinecraftForge.EVENT_BUS.register(HealingAdditive.class);
            MinecraftForge.EVENT_BUS.register(UnstableCompound.class);
        }
        if(ModList.get().isLoaded("cataclysm")) {
            MinecraftForge.EVENT_BUS.register(AbyssalCurse.class);
            MinecraftForge.EVENT_BUS.register(BlazingBrand.class);
            MinecraftForge.EVENT_BUS.register(BoneFracture.class);
            MinecraftForge.EVENT_BUS.register(CataclysmStun.class);
            MinecraftForge.EVENT_BUS.register(GhostForm.class);
            MinecraftForge.EVENT_BUS.register(WitheriteB.class);

            MinecraftForge.EVENT_BUS.register(AbyssalFinish.class);
            MinecraftForge.EVENT_BUS.register(Analysis.class);
            MinecraftForge.EVENT_BUS.register(BlazingAbsorb.class);
            MinecraftForge.EVENT_BUS.register(LavaDriver.class);
            MinecraftForge.EVENT_BUS.register(OverPostmortal.class);

            MinecraftForge.EVENT_BUS.register(TearsOfThunder.class);
            MinecraftForge.EVENT_BUS.register(IAmStorm.class);
            MinecraftForge.EVENT_BUS.register(UnceasingStorm.class);
        }

        if(ModList.get().isLoaded("iceandfire")) {
            MinecraftForge.EVENT_BUS.register(FireDragonsteelMaterial.class);
            MinecraftForge.EVENT_BUS.register(IceDragonsteelMaterial.class);
            MinecraftForge.EVENT_BUS.register(LightningDragonsteelMaterial.class);

            MinecraftForge.EVENT_BUS.register(FireDragonBloodCoating.class);
            MinecraftForge.EVENT_BUS.register(IceDragonBloodCoating.class);
            MinecraftForge.EVENT_BUS.register(LightningDragonBloodCoating.class);

            MinecraftForge.EVENT_BUS.register(FireDragonPower.class);
            MinecraftForge.EVENT_BUS.register(IceDragonPower.class);
            MinecraftForge.EVENT_BUS.register(LightningDragonPower.class);
            //幻灵
            MinecraftForge.EVENT_BUS.register(UndeadHydra.class);
            MinecraftForge.EVENT_BUS.register(GhostSword.class);
        }
        //明日方舟mrfz
        if(ModList.get().isLoaded("torchesbecomesunlight")) {
            MinecraftForge.EVENT_BUS.register(MMTIngotDrop.class);
            MinecraftForge.EVENT_BUS.register(ExpandedCognition.class);
            MinecraftForge.EVENT_BUS.register(NociceptorInhibition.class);
            MinecraftForge.EVENT_BUS.register(Notes.class);

            MinecraftForge.EVENT_BUS.register(ColdWave.class);
            MinecraftForge.EVENT_BUS.register(Freeze.class);
            MinecraftForge.EVENT_BUS.register(FreezeRing.class);

            MinecraftForge.EVENT_BUS.register(RitualOfHolyGuard.class);
            MinecraftForge.EVENT_BUS.register(RitualOfExhortation.class);

            MinecraftForge.EVENT_BUS.register(MarchingTimeAndRuinationTime.class);
            MinecraftForge.EVENT_BUS.register(MarchingTimeHurt.class);
            MinecraftForge.EVENT_BUS.register(SacrificeAndThrowingTheHalberd.class);

            MinecraftForge.EVENT_BUS.register(CollapsingFear.class);
            MinecraftForge.EVENT_BUS.register(Dominion.class);
        }
        if(ModList.get().isLoaded("enigmaticlegacy")) {
            MinecraftForge.EVENT_BUS.register(EvilIngotMaterial.class);
            MinecraftForge.EVENT_BUS.register(EtheriumIngotMaterial.class);

            MinecraftForge.EVENT_BUS.register(EtheriumGuard.class);
            MinecraftForge.EVENT_BUS.register(EvilCurse.class);
        }
        if(ModList.get().isLoaded("extrabotany")) {
            MinecraftForge.EVENT_BUS.register(ManaForce.class);
            MinecraftForge.EVENT_BUS.register(ShadowiumPower.class);
            MinecraftForge.EVENT_BUS.register(ShiningPower.class);
            MinecraftForge.EVENT_BUS.register(SpeedForce.class);
        }
        if(ModList.get().isLoaded("botania")) {
            MinecraftForge.EVENT_BUS.register(PixieSummon.class);
            MinecraftForge.EVENT_BUS.register(ManaAbsorption.class);

            MinecraftForge.EVENT_BUS.register(AncientWillAhrim.class);
            MinecraftForge.EVENT_BUS.register(AncientWillDharok.class);
            MinecraftForge.EVENT_BUS.register(AncientWillGuthan.class);
            MinecraftForge.EVENT_BUS.register(AncientWillKaril.class);
            MinecraftForge.EVENT_BUS.register(AncientWillTorag.class);
            MinecraftForge.EVENT_BUS.register(AncientWillVerac.class);
        }
        if(ModList.get().isLoaded("alexscaves")) {
            MinecraftForge.EVENT_BUS.register(ScarletMagnetizingPole.class);
            MinecraftForge.EVENT_BUS.register(RadioactiveMaterial.class);
            MinecraftForge.EVENT_BUS.register(MagnetizingMetal.class);
            MinecraftForge.EVENT_BUS.register(LavaMob.class);
            MinecraftForge.EVENT_BUS.register(IntoBubbled.class);
            MinecraftForge.EVENT_BUS.register(AbyssalOceanEcho.class);
            MinecraftForge.EVENT_BUS.register(Underocean.class);
        }
        if(ModList.get().isLoaded("l2complements")) {
            MinecraftForge.EVENT_BUS.register(Recovery.class);
            MinecraftForge.EVENT_BUS.register(ObsessionOfWarden.class);
            MinecraftForge.EVENT_BUS.register(HidingInShulkerShell.class);
            MinecraftForge.EVENT_BUS.register(EterniumDurability.class);
            MinecraftForge.EVENT_BUS.register(HostilityControl.class);
            //词条
            MinecraftForge.EVENT_BUS.register(UndyingEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(FieryEffectTraitHurt.class);
            MinecraftForge.EVENT_BUS.register(ReflectEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(StrayEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(WeaknessEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(GrenadeEffectTraitHurt.class);
            MinecraftForge.EVENT_BUS.register(RepellingEffectTraitHurt.class);
            MinecraftForge.EVENT_BUS.register(CurseEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(DementorEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(DispellEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(SoulBurnerEffectTrait.class);
            MinecraftForge.EVENT_BUS.register(FreezingEffectTrait.class);
        }
        if(ModList.get().isLoaded("aquamirae")) {
            MinecraftForge.EVENT_BUS.register(RuneOfTheStorm.class);
        }
        if(ModList.get().isLoaded("caerula_arbor")) {
            MinecraftForge.EVENT_BUS.register(SanityHurt.class);
        }
        if(ModList.get().isLoaded("tacz")) {
            MinecraftForge.EVENT_BUS.register(TaczCuriosProjectileDamageUp.class);
        }
        MinecraftForge.EVENT_BUS.register(Assassinate.class);
        MinecraftForge.EVENT_BUS.register(ConstantFlux.class);
        MinecraftForge.EVENT_BUS.register(DefeatDemons.class);
        MinecraftForge.EVENT_BUS.register(OverSlash.class);
        MinecraftForge.EVENT_BUS.register(UltimateSlash.class);
        MinecraftForge.EVENT_BUS.register(FinalSlash.class);
        MinecraftForge.EVENT_BUS.register(RisingSlash.class);
        MinecraftForge.EVENT_BUS.register(ExceedSlash.class);
        MinecraftForge.EVENT_BUS.register(BanHeal.class);
        MinecraftForge.EVENT_BUS.register(BanHealEvent.class);
        MinecraftForge.EVENT_BUS.register(Powerless.class);
        MinecraftForge.EVENT_BUS.register(PowerlessEvent.class);
        MinecraftForge.EVENT_BUS.register(Poison.class);
        MinecraftForge.EVENT_BUS.register(NitenIchiryuKatana.class);
        MinecraftForge.EVENT_BUS.register(NitenIchiryuWakizashi.class);
        MinecraftForge.EVENT_BUS.register(ReverseMirrorHurt.class);
        MinecraftForge.EVENT_BUS.register(ReverseMirrorHurtCurious.class);
        MinecraftForge.EVENT_BUS.register(HideBladeCurious.class);
        MinecraftForge.EVENT_BUS.register(HideBlade.class);
        MinecraftForge.EVENT_BUS.register(SmallShulker.class);
        MinecraftForge.EVENT_BUS.register(SmallShulkerHurtCurious.class);
        MinecraftForge.EVENT_BUS.register(IndustrialProtection.class);
        MinecraftForge.EVENT_BUS.register(FoodAcquisition.class);
        MinecraftForge.EVENT_BUS.register(InvulnerabilityBlade.class);
        MinecraftForge.EVENT_BUS.register(DevouringStone.class);
        MinecraftForge.EVENT_BUS.register(Growing.class);
        MinecraftForge.EVENT_BUS.register(RipeningHalo.class);
        MinecraftForge.EVENT_BUS.register(BlessingsOfWater.class);
        MinecraftForge.EVENT_BUS.register(DeepDarkFantasy.class);
        MinecraftForge.EVENT_BUS.register(FakeInsatiable.class);
        MinecraftForge.EVENT_BUS.register(FakeMomentum.class);
        MinecraftForge.EVENT_BUS.register(Fragile.class);
        MinecraftForge.EVENT_BUS.register(ReverselSmite.class);
        MinecraftForge.EVENT_BUS.register(Necrotic.class);
        MinecraftForge.EVENT_BUS.register(LetThePeopleRejoice.class);
        MinecraftForge.EVENT_BUS.register(MagicEdge.class);
        MinecraftForge.EVENT_BUS.register(MagicProficiency.class);
        MinecraftForge.EVENT_BUS.register(LightningEdge.class);
        MinecraftForge.EVENT_BUS.register(LightningProficiency.class);
        MinecraftForge.EVENT_BUS.register(FireEdge.class);
        MinecraftForge.EVENT_BUS.register(FireProficiency.class);
        MinecraftForge.EVENT_BUS.register(FreezeEdge.class);
        MinecraftForge.EVENT_BUS.register(FreezeProficiency.class);
        MinecraftForge.EVENT_BUS.register(ShieldSkillAutomaticDefense.class);
        MinecraftForge.EVENT_BUS.register(ShieldSkillAutomaticProtection.class);
        MinecraftForge.EVENT_BUS.register(ShieldSkillCooldownDown.class);
        MinecraftForge.EVENT_BUS.register(Puncture.class);
        MinecraftForge.EVENT_BUS.register(HeavyChop.class);
        MinecraftForge.EVENT_BUS.register(StarBurstStream.class);
        MinecraftForge.EVENT_BUS.register(Eclipse.class);
        MinecraftForge.EVENT_BUS.register(EclipseStarBurstStream.class);
        MinecraftForge.EVENT_BUS.register(InvulnerableTimeDown.class);
        MinecraftForge.EVENT_BUS.register(HeavyHit.class);
        MinecraftForge.EVENT_BUS.register(OriginSlash.class);
        MinecraftForge.EVENT_BUS.register(ToolBlocking.class);
        MinecraftForge.EVENT_BUS.register(TwinSlash.class);;
        MinecraftForge.EVENT_BUS.register(Beheading.class);
        MinecraftForge.EVENT_BUS.register(Glowing.class);
        MinecraftForge.EVENT_BUS.register(ShootingSun.class);
        MinecraftForge.EVENT_BUS.register(TheCenturyGate.class);
        MinecraftForge.EVENT_BUS.register(SirenicSerenade.class);
        MinecraftForge.EVENT_BUS.register(FirstLightHealsTheWorld.class);
        MinecraftForge.EVENT_BUS.register(JackpotForTheTaking.class);
        MinecraftForge.EVENT_BUS.register(EverythingIsInEverything.class);
        MinecraftForge.EVENT_BUS.register(TorchTheLawsOfOld.class);
        MinecraftForge.EVENT_BUS.register(SanctuaryOfMooncocoon.class);
        MinecraftForge.EVENT_BUS.register(ToEvernightsStars.class);
        MinecraftForge.EVENT_BUS.register(ThoughWorldsApart.class);
        MinecraftForge.EVENT_BUS.register(PyricCorpus.class);
        MinecraftForge.EVENT_BUS.register(Levitation.class);
        MinecraftForge.EVENT_BUS.register(Slowness.class);
        MinecraftForge.EVENT_BUS.register(Ignite.class);
        MinecraftForge.EVENT_BUS.register(FlameKiller.class);
        MinecraftForge.EVENT_BUS.register(FlamelessKiller.class);
        MinecraftForge.EVENT_BUS.register(FireCombo.class);
        MinecraftForge.EVENT_BUS.register(FreezeCombo.class);
        MinecraftForge.EVENT_BUS.register(LightningCombo.class);
        MinecraftForge.EVENT_BUS.register(MagicCombo.class);
        MinecraftForge.EVENT_BUS.register(WitherCombo.class);
        MinecraftForge.EVENT_BUS.register(WitherEdge.class);
        MinecraftForge.EVENT_BUS.register(WitherProficiency.class);
        MinecraftForge.EVENT_BUS.register(DragonBreathCombo.class);
        MinecraftForge.EVENT_BUS.register(DragonBreathEdge.class);
        MinecraftForge.EVENT_BUS.register(DragonBreathProficiency.class);
        MinecraftForge.EVENT_BUS.register(Enderference.class);
        MinecraftForge.EVENT_BUS.register(EnderferenceEvent.class);
        MinecraftForge.EVENT_BUS.register(PiglinKiller.class);
        MinecraftForge.EVENT_BUS.register(PhantomKiller.class);
        MinecraftForge.EVENT_BUS.register(EndermanKiller.class);
        MinecraftForge.EVENT_BUS.register(EmeraldPillage.class);
        MinecraftForge.EVENT_BUS.register(SMCFrostBurst.class);
        MinecraftForge.EVENT_BUS.register(SMCFrost.class);
        MinecraftForge.EVENT_BUS.register(ExtraAquaticProducts.class);
        MinecraftForge.EVENT_BUS.register(HealthPower.class);
        MinecraftForge.EVENT_BUS.register(Vampiric.class);
        MinecraftForge.EVENT_BUS.register(Capturing.class);
        MinecraftForge.EVENT_BUS.register(MagicOscillation.class);
        MinecraftForge.EVENT_BUS.register(AwakeningSlash.class);
        MinecraftForge.EVENT_BUS.register(TrueSlash.class);
        MinecraftForge.EVENT_BUS.register(CuriosArmorPenetration.class);
        MinecraftForge.EVENT_BUS.register(CuriosKamui.class);
        MinecraftForge.EVENT_BUS.register(CuriosCriticalStrike.class);
        MinecraftForge.EVENT_BUS.register(CuriosJank.class);
        MinecraftForge.EVENT_BUS.register(CuriosFeatherFalling.class);
        MinecraftForge.EVENT_BUS.register(CuriosProtect.class);
        MinecraftForge.EVENT_BUS.register(CuriosAllDamageUp.class);
        MinecraftForge.EVENT_BUS.register(CuriosMagicDamageUp.class);
        MinecraftForge.EVENT_BUS.register(CuriosMeleeDamageUp.class);
        MinecraftForge.EVENT_BUS.register(CuriosProjectileDamageUp.class);
        MinecraftForge.EVENT_BUS.register(CuriosMagmaWalker.class);
        MinecraftForge.EVENT_BUS.register(CuriosWaterWalker.class);
        MinecraftForge.EVENT_BUS.register(CuriosFly.class);
        MinecraftForge.EVENT_BUS.register(MMTFluidCollisionEvent.class);
        MinecraftForge.EVENT_BUS.register(CuriosTotemEvent.class);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        MMTNetworkHandler.init();
        if(ModList.get().isLoaded("iceandfire")) {
            //幻灵
            GhostSwordChannel.init();
        }
        if(ModList.get().isLoaded("botania")) {
            TerraRayChannel.init();
        }
    }

    // 客户端设置事件，用于注册渲染器和GUI屏幕
    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosWaterBreathing.init();
            CuriosStrength.init();
            CuriosSpeed.init();
            CuriosSaturation.init();
            CuriosResistance.init();
            CuriosRegeneration.init();
            CuriosNightVision.init();
            CuriosLuck.init();
            CuriosJumpBoost.init();
            CuriosHeroOfTheVillage.init();
            CuriosHealthBoost.init();
            CuriosHaste.init();
            CuriosFireResistance.init();
            CuriosTotemEvent.init();
            CuriosFly.init();
            CuriosWaterWalker.init();
            CuriosMagmaWalker.init();
            CuriosProjectileDamageUp.init();
            CuriosMeleeDamageUp.init();
            CuriosMagicDamageUp.init();
            CuriosAllDamageUp.init();
            CuriosJump.init();
            CuriosProtect.init();
            CuriosFeatherFalling.init();
            CuriosJank.init();
            CuriosCriticalStrike.init();
            CuriosFireClear.init();
            CuriosBlindnessClear.init();
            CuriosDarknessClear.init();
            CuriosHungerClear.init();
            CuriosLevitationClear.init();
            CuriosMiningFatigueClear.init();
            CuriosPoisonClear.init();
            CuriosSlownessClear.init();
            CuriosWeaknessClear.init();
            CuriosWitherClear.init();
            MMTClientGuiRegistry.registerAllBars();
            CuriosMaterialsEffectInvalidation.init();
            CuriosKamui.init();
            CuriosArmorPenetration.init();
            TrueSlash.init();
            AwakeningSlash.init();
            MagicOscillation.init();
            Capturing.init();
            Vampiric.init();
            HealthPower.init();
            ExtraAquaticProducts.init();
            SMCFrostBurst.init();
            SMCFrost.init();
            EmeraldPillage.init();
            EmeraldPillage.init();
            PhantomKiller.init();
            EndermanKiller.init();
            PiglinKiller.init();
            Enderference.init();
            DragonBreathCombo.init();
            DragonBreathEdge.init();
            DragonBreathProficiency.init();
            WitherCombo.init();
            WitherEdge.init();
            WitherProficiency.init();
            FireCombo.init();
            FreezeCombo.init();
            LightningCombo.init();
            MagicCombo.init();
            FlamelessKiller.init();
            FlameKiller.init();
            Ignite.init();
            Slowness.init();
            Levitation.init();
            PyricCorpus.init();
            ThoughWorldsApart.init();
            ToEvernightsStars.init();
            SanctuaryOfMooncocoon.init();
            TorchTheLawsOfOld.init();
            EverythingIsInEverything.init();
            JackpotForTheTaking.init();
            FirstLightHealsTheWorld.init();
            SirenicSerenade.init();
            TheCenturyGate.init();
            ShootingSun.init();
            Glowing.init();
            Beheading.init();
            TwinSlash.init();
            ToolBlocking.init();
            HeavyHit.init();
            OriginSlash.init();
            InvulnerableTimeDown.init();
            EclipseStarBurstStream.init();
            Eclipse.init();
            StarBurstStream.init();
            HeavyChop.init();
            Puncture.init();
            ShieldSkillCooldownDown.init();
            ShieldSkillAutomaticProtection.init();
            ShieldSkillAutomaticDefense.init();
            FreezeProficiency.init();
            FreezeEdge.init();
            FireProficiency.init();
            FireEdge.init();
            LightningProficiency.init();
            LightningEdge.init();
            MagicProficiency.init();
            MagicEdge.init();
            LetThePeopleRejoice.init();
            Necrotic.init();
            ReverselSmite.init();
            Fragile.init();
            FakeInsatiable.init();
            FakeMomentum.init();
            FakeTasty.init();
            Growing.init();
            RipeningHalo.init();
            DevouringStone.init();
            InvulnerabilityBlade.init();
            FoodAcquisition.init();
            IndustrialProtection.init();
            HideBladeCurious.init();
            HideBlade.init();
            NitenIchiryuKatana.init();
            NitenIchiryuWakizashi.init();
            Poison.init();
            Powerless.init();
            BanHeal.init();
            Duel.init();
            Wither.init();
            MinerLantern.init();
            Assassinate.init();
            ConstantFlux.init();
            DefeatDemons.init();
            OverSlash.init();
            UltimateSlash.init();
            FinalSlash.init();
            RisingSlash.init();
            ExceedSlash.init();
            ReverseMirror.init();
            UnderwaterOperations.init();
            EmergencyRescue.init();
            WorkWorkWork.init();
            EntityResonance.init();
            SmallShulker.init();
            BlessingsOfWater.init();
            if(ModList.get().isLoaded("caerula_arbor")) {
                SanityHurt.init();
            }
            if(ModList.get().isLoaded("torchesbecomesunlight")) {
                ExpandedCognition.init();
                NociceptorInhibition.init();
                Notes.init();

                ColdWave.init();
                Freeze.init();
                FreezeRing.init();

                RitualOfHolyGuard.init();
                RitualOfExhortation.init();
                ExhortationOfGunKnightPatriot.init();

                MarchingTimeAndRuinationTime.init();
                MarchingTimeHurt.init();
                SacrificeAndThrowingTheHalberd.init();

                CollapsingFear.init();
                Dominion.init();
            }
            if(ModList.get().isLoaded("aquamirae")) {
                RuneOfTheStorm.init();
            }
            if(ModList.get().isLoaded("l2complements")) {
                RepellingEffectTraitHurt.init();
                WeaknessEffectTrait.init();
                GrenadeEffectTraitHurt.init();
                FreezingEffectTrait.init();
                Recovery.init();
                ObsessionOfWarden.init();
                HidingInShulkerShell.init();
                EterniumDurability.init();
                MiracleAndMagic.init();
                HostilityControl.init();
                //词条
                SoulBurnerEffectTrait.init();
                DispellEffectTrait.init();
                DementorEffectTrait.init();
                CurseEffectTrait.init();
                UndyingEffectTrait.init();
                SpeedyEffectTrait.init();
                TankyEffectTrait.init();
                FieryEffectTrait.init();
                ProtectedEffectTrait.init();
                RegeneratingEffectTrait.init();
                ReflectEffectTrait.init();
                KillerAuraEffectTrait.init();
                StrayEffectTrait.init();
            }
            if(ModList.get().isLoaded("alexscaves")) {
                Underocean.init();
                ScarletMagnetizingPole.init();
                RadioactiveMaterial.init();
                MagnetizingMetal.init();
                LavaMob.init();
                OceanPearl.init();
                AzureMagnetizingPole.init();
                RadiationCore.init();
                MagneticField.init();
                DeepDarkFantasy.init();
                IntoBubbled.init();
                AbyssalOceanEcho.init();
                RadiationAbsorption.init();
            }
            if(ModList.get().isLoaded("botania")) {
                ManaAbsorption.init();
                ManaRegeneration.init();
                PixieSummon.init();
                TerraRay.init();
                WillOfGaia.init();

                AncientWillAhrim.init();
                AncientWillDharok.init();
                AncientWillGuthan.init();
                AncientWillKaril.init();
                AncientWillTorag.init();
                AncientWillVerac.init();
            }
            if(ModList.get().isLoaded("enigmaticlegacy")) {
                EvilIngotMaterial.init();
                EtheriumIngotMaterial.init();

                EtheriumGuard.init();
                EvilCurse.init();
            }
            if(ModList.get().isLoaded("irons_spellbooks")) {
                IronSpellCastingEvent.init();
                IronSpellTetraActualImpl.initClient();
                ProtectionArcaneEdgeEffect.init();
                CooldownArcaneEdgeEffect.init();
                ArcaneArcaneEdgeEffect.init();
                FireArcaneEdgeEffect.init();
                IceArcaneEdgeEffect.init();
                LightningArcaneEdgeEffect.init();
                EvocationArcaneEdgeEffect.init();
                HolyArcaneEdgeEffect.init();
                BloodArcaneEdgeEffect.init();
                EnderArcaneEdgeEffect.init();
                NatureArcaneEdgeEffect.init();
                EldritchArcaneEdgeEffect.init();

                FireArcaneGuardEffect.init();
                IceArcaneGuardEffect.init();
                LightningArcaneGuardEffect.init();
                EvocationArcaneGuardEffect.init();
                HolyArcaneGuardEffect.init();
                BloodArcaneGuardEffect.init();
                EnderArcaneGuardEffect.init();
                NatureArcaneGuardEffect.init();
                EldritchArcaneGuardEffect.init();

                FireStaffSocket.init();
                IceStaffSocket.init();
                LightningStaffSocket.init();
                EvocationStaffSocket.init();
                HolyStaffSocket.init();
                BloodStaffSocket.init();
                EnderStaffSocket.init();
                NatureStaffSocket.init();
                EldritchStaffSocket.init();
            }
            if(ModList.get().isLoaded("traveloptics")) {
                AquaArcaneEdgeEffect.init();
                AquaArcaneGuardEffect.init();
                AquaStaffSocket.init();
                TOIronSpellTetraActualImpl.initClient();
            }
            if(ModList.get().isLoaded("alshanex_familiars")) {
                SoundArcaneEdgeEffect.init();
                SoundArcaneGuardEffect.init();
                SoundStaffSocket.init();
                AFIronSpellTetraActualImpl.initClient();
            }
                    if(ModList.get().isLoaded("ars_nouveau")) {
                        //ArsTetraActualImpl.initClient();
                        ThreadDepths.init();
                        ThreadFeather.init();
                        ThreadHeights.init();
                        ThreadRepairing.init();
                    }
            if(ModList.get().isLoaded("malum")) {
                StabilizingEffect.init();
                RuneOfIdleRestoration.init();
                RuneOfIgneousSolace.init();
                RuneOfTwinnedDuration.init();
                RuneOfTheHeretic.init();
                RuneOfVolatileDistortion.init();
                RuneOfReactiveShielding.init();
                RuneOfAlimentCleansing.init();
                RuneOfFervor.init();
                RuneOfSacrificialEmpowerment.init();

                RuneOfHaste.init();
                RuneOfLoyalty.init();
                RuneOfMotion.init();
                RuneOfTheAether.init();
                RuneOfTheArena.init();
                RuneOfTheHells.init();
                RuneOfTheSeas.init();
                RuneOfWarding.init();
            }
                    if(ModList.get().isLoaded("eidolon")) {
                        SoulShard.init();
                        ShadowGem.init();
                    }
            if(ModList.get().isLoaded("biomancy")) {
                DespoilSickle.init();
                AbsorptionBoost.init();
                CleansingSerum.init();
                CorrosiveAdditive.init();
                FrenzySerum.init();
                DespoilSickle.init();
                HealingAdditive.init();
                OrganicCompound.init();
                UnstableCompound.init();
            }
            if(ModList.get().isLoaded("cataclysm")) {
                AbyssalCurse.init();
                BlazingBrand.init();
                BoneFracture.init();
                CataclysmStun.init();
                GhostForm.init();
                Monstrous.init();
                BlessingOfAmethyst.init();
                WitheriteA.init();

                AbyssalFinish.init();
                Analysis.init();
                BlazingAbsorb.init();
                LavaDriver.init();
                OverPostmortal.init();

                TearsOfThunder.init();
                IAmStorm.init();
                UnceasingStorm.init();
            }
            if(ModList.get().isLoaded("extrabotany")) {
                ManaForce.init();
                ShadowiumPower.init();
                ShiningPower.init();
                SpeedForce.init();
            }
            if(ModList.get().isLoaded("iceandfire")) {
                FireDragonsteelMaterial.init();
                IceDragonsteelMaterial.init();
                LightningDragonsteelMaterial.init();

                FireDragonBloodCoating.init();
                IceDragonBloodCoating.init();
                LightningDragonBloodCoating.init();
                FireDragonPower.init();
                IceDragonPower.init();
                LightningDragonPower.init();

                WeaknessRealm.init();
                WitherRealm.init();
                UndeadHydra.init();
                WaterPower.init();
                //幻灵
                GhostSword.init();UnlimitedPhantasmalBladeWorks.init();
            }
        });
    }

    //注册掉落物
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
        }
    }

    public static ResourceLocation prefix(String name){
        return new ResourceLocation(MODID,name.toLowerCase(Locale.ROOT));
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("more_mod_tetra", id);
    }
    //卷轴
    public void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        // This method is called for every registered Creative Tab added to Minecraft, so we only want to run this code
        // whenever it's called for the Tetra creative tab (which is registered under "tetra:default"
        if (event.getTabKey().location().equals(new ResourceLocation("tetra:default"))) {
            LOGGER.info("Registering MMT schematics with Tetra creative tab");
            //武士刀
            event.accept(this.setupSchematic("sword/mmt_katana", "more_mod_tetra",new String[]{"sword/mmt_katana"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    16777215, 9, 3, 6, 2));
            event.accept(this.setupSchematic("bow/mmt_bow_improvements", "more_mod_tetra",new String[]{"bow/mmt_bow_improvements"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    3407667, 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/mmt_critical_strike_improvements", "more_mod_tetra",
                    new String[]{"shared/mmt_critical_strike_improvements"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    65280, 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/mmt_upgraded_netherite", "more_mod_tetra",
                    new String[]{"shared/mmt_upgraded_netherite"},
                    false,
                    //材料数量影响卷轴本体颜色
                    1,
                    //rgb十进制代码
                    6914367, 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/mmt_more_improvements", "more_mod_tetra",
                    new String[]{"shared/mmt_more_improvements"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    0, 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/mmt_over_improvements", "more_mod_tetra",
                    new String[]{"shared/mmt_over_improvements"}, false, 2,//材料数量影响卷轴本体颜色
                    16777215//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/mmt_skill_improvements", "more_mod_tetra",
                    new String[]{"shared/mmt_skill_improvements"}, false, 2,//材料数量影响卷轴本体颜色
                    10066329//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shield/mmt_shield_skill", "more_mod_tetra",
                    new String[]{"shield/mmt_shield_skill"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    16777215, 9, 3, 6, 2));
            //十二泰坦
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_sky_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_sky_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    65280//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_earth_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_earth_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    16776960//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_ocean_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_ocean_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    3302399//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_romance_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_romance_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    6431426//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_worldbearing_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_worldbearing_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    4079231//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_reason_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_reason_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    65453//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_trickery_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_trickery_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    4419965//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_strife_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_strife_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    16766976//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_death_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_death_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    10040127//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_time_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_time_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    16738751//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_law_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_law_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    3670015//rgb十进制代码
                    , 9, 3, 6, 2));
            event.accept(this.setupSchematic("shared/the_legend_scroll_of_passage_titan", "more_mod_tetra",
                    new String[]{"shared/the_legend_scroll_of_passage_titan"}, false, 2,//材料数量影响卷轴本体颜色
                    16711680//rgb十进制代码
                    , 9, 3, 6, 2));
            //附属
            if(ModList.get().isLoaded("iceandfire")) {
                event.accept(this.setupSchematic("iceandfire/mmt_iceandfire_dragon_scroll", "more_mod_tetra",
                        new String[]{"iceandfire/mmt_iceandfire_dragon_scroll"}, false, 1,//材料数量影响卷轴本体颜色
                        11711154//rgb十进制代码
                        , 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("biomancy")) {
                event.accept(this.setupSchematic("sword/mmt_biomancy_despoil_sickle", "more_mod_tetra",
                        new String[]{"sword/mmt_biomancy_despoil_sickle"}, false, 1,//材料数量影响卷轴本体颜色
                        16711982//rgb十进制代码
                        , 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("botania")) {
                event.accept(this.setupSchematic("shared/mmt_botania_scroll", "more_mod_tetra",
                        new String[]{"shared/mmt_botania_scroll"}, false, 1,//材料数量影响卷轴本体颜色
                        24991//rgb十进制代码
                        , 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("eidolon")) {
                event.accept(this.setupSchematic("sword/mmt_eidolon_reaper_scythe", "more_mod_tetra",
                        new String[]{"sword/mmt_eidolon_reaper_scythe"}, false, 1,//材料数量影响卷轴本体颜色
                        16777215//rgb十进制代码
                        , 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("malum")) {
                event.accept(this.setupSchematic("sword/mmt_malum_scythe", "more_mod_tetra",
                        new String[]{"sword/mmt_malum_scythe"}, false, 1,//材料数量影响卷轴本体颜色
                        13079231//rgb十进制代码
                        , 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("irons_spellbooks")) {
                //原版
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_staff"}, false, 1,//材料数量影响卷轴本体颜色
                        5603126/*rgb十进制代码*/, 9, 3, 6, 2));
                //各系法杖
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_blood_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_blood_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        15185609/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_eldritch_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_eldritch_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        358234/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_ender_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_ender_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        11584323/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_evocation_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_evocation_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        5612322/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_fire_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_fire_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        16344064/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_holy_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_holy_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        15789906/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_ice_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_ice_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        7734316/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_lightning_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_lightning_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        8583392/*rgb十进制代码*/, 9, 3, 6, 2));
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_nature_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_nature_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        11592809/*rgb十进制代码*/, 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("traveloptics")) {
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_aqua_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_aqua_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        5614843/*rgb十进制代码*/, 9, 3, 6, 2));
            }
            if(ModList.get().isLoaded("alshanex_familiars")) {
                event.accept(this.setupSchematic("single/more_mod_tetra_iron_spell_sound_staff", "more_mod_tetra",
                        new String[]{"single/more_mod_tetra_iron_spell_sound_staff"}, false, 2,//材料数量影响卷轴本体颜色
                        8084219/*rgb十进制代码*/, 9, 3, 6, 2));
            }
        }
    }
    private static final RegistryObject<Item> tetraScroll = RegistryObject.create(new ResourceLocation("tetra:scroll_rolled"),
            TetraRegistries.items.getRegistryName(), TetraMod.MOD_ID);
    private ItemStack setupSchematic(String key, String details, String[] schematics, boolean isIntricate, int material, int tint, Integer... glyphs) {
        MMTScrollHelper data = new MMTScrollHelper(key, Optional.ofNullable(details), isIntricate, material, tint, Arrays.asList(glyphs),
                Arrays.stream(schematics).map((s) -> new ResourceLocation(TetraMod.MOD_ID, s)).collect(Collectors.toList()), Collections.emptyList());
        ItemStack itemStack = new ItemStack(tetraScroll.get());
        data.write(itemStack);
        return itemStack;
    }

}
