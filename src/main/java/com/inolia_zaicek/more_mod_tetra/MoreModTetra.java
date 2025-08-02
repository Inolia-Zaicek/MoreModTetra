package com.inolia_zaicek.more_mod_tetra;

// import com.inolia_zaicek.daily_delight.Curios.BentoBox.WoodenBentoBoxCurios; // This class might be removed or adapted if you fully replace it



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
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious.HideBladeCurious;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious.ReverseMirrorHurtCurious;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curious.SmallShulkerHurtCurious;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.IndustrialProtection.IndustrialProtection;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.NitenIchiryu.NitenIchiryuKatana;
import com.inolia_zaicek.more_mod_tetra.Effect.MMT.NitenIchiryu.NitenIchiryuWakizashi;
import com.inolia_zaicek.more_mod_tetra.Effect.Malum.*;
import com.inolia_zaicek.more_mod_tetra.Effect.Malum.TotemicRunes.*;
import com.inolia_zaicek.more_mod_tetra.Network.GhostSwordChannel;
import com.inolia_zaicek.more_mod_tetra.Network.TerraRayChannel;
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
        }
        if(ModList.get().isLoaded("traveloptics")) {
            MinecraftForge.EVENT_BUS.register(AquaArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(AquaArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(AquaStaffSocket.class);
        }
        if(ModList.get().isLoaded("alshanex_familiars")) {
            MinecraftForge.EVENT_BUS.register(SoundArcaneEdgeEffect.class);
            MinecraftForge.EVENT_BUS.register(SoundArcaneGuardEffect.class);
            MinecraftForge.EVENT_BUS.register(SoundStaffSocket.class);
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
            MinecraftForge.EVENT_BUS.register(UndeadHydra.class);MinecraftForge.EVENT_BUS.register(GhostSword.class);
        }
        if(ModList.get().isLoaded("enigmaticlegacy")) {
            MinecraftForge.EVENT_BUS.register(EvilIngotMaterial.class);
            MinecraftForge.EVENT_BUS.register(EtheriumIngotMaterial.class);

            MinecraftForge.EVENT_BUS.register(EtheriumGuard.class);
            MinecraftForge.EVENT_BUS.register(EvilCurse.class);
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
            MinecraftForge.EVENT_BUS.register(DeepDarkFantasy.class);
            MinecraftForge.EVENT_BUS.register(IntoBubbled.class);
            MinecraftForge.EVENT_BUS.register(AbyssalOceanEcho.class);
            MinecraftForge.EVENT_BUS.register(Underocean.class);
        }
        if(ModList.get().isLoaded("l2complements")) {
            MinecraftForge.EVENT_BUS.register(Recovery.class);
            MinecraftForge.EVENT_BUS.register(ObsessionOfWarden.class);
            MinecraftForge.EVENT_BUS.register(HidingInShulkerShell.class);
            MinecraftForge.EVENT_BUS.register(EterniumDurability.class);
            MinecraftForge.EVENT_BUS.register(BlessingsOfWater.class);
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
        //明日方舟

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
        if(ModList.get().isLoaded("caerula_arbor")) {
            MinecraftForge.EVENT_BUS.register(SanityHurt.class);
        }
        MinecraftForge.EVENT_BUS.register(Assassinate.class);
        MinecraftForge.EVENT_BUS.register(ConstantFlux.class);
        MinecraftForge.EVENT_BUS.register(DefeatDemons.class);
        MinecraftForge.EVENT_BUS.register(OverSlash.class);
        MinecraftForge.EVENT_BUS.register(UltimateSlash.class);
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
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
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
            ReverseMirror.init();
            UnderwaterOperations.init();
            EmergencyRescue.init();
            WorkWorkWork.init();
            EntityResonance.init();
            SmallShulker.init();
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
                BlessingsOfWater.init();
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
            event.accept(this.setupSchematic("shared/mmt_critical_strike_improvements", "more_mod_tetra",
                    new String[]{"shared/mmt_critical_strike_improvements"},
                    false,
                    //材料数量影响卷轴本体颜色
                    2,
                    //rgb十进制代码
                    65280, 9, 3, 6, 2));
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
