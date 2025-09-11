package com.inolia_zaicek.more_mod_tetra.Register;

import com.inolia_zaicek.more_mod_tetra.ModularCurios.*;
import com.inolia_zaicek.more_mod_tetra.ModularCurios.Iron.ModularIronStaff;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Supplier;

import static com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID;


public class MoreModTetraItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> IronSpellITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> IronSpellToITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> IronSpellAfITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> CataclysmITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> IceAndFireITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> EnigmaticlegacyITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> AlexsCavesITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> L2ITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final DeferredRegister<Item> ArkNightsITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    // tag

    public static final TagKey<Item> malum_scythe_effect = TagKey.create(Registries.ITEM,new ResourceLocation("more_mod_tetra","malum_scythe_effect"));
    public static final TagKey<Item> iron_spell_casting = TagKey.create(Registries.ITEM,new ResourceLocation("more_mod_tetra","iron_spell_casting"));
    public static final TagKey<Item> stoneTag = TagKey.create(Registries.ITEM,new ResourceLocation("minecraft","stone_tool_materials"));
    public static final TagKey<Item> stoneTag2 = TagKey.create(Registries.ITEM,new ResourceLocation("forge","stone"));
    //饰品
    public static final RegistryObject<Item> MODULAR_Emblem = ZeroingITEM.register(ModularEmblem.identifier, ModularEmblem::new);
    public static final RegistryObject<Item> CuriosEmblem = registerCommonMaterials(ZeroingITEM,"curios_emblem", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Necklace = ZeroingITEM.register(ModularNecklace.identifier, ModularNecklace::new);
    public static final RegistryObject<Item> CuriosNecklace = registerCommonMaterials(ZeroingITEM,"curios_necklace", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Shoes = ZeroingITEM.register(ModularShoes.identifier, ModularShoes::new);
    public static final RegistryObject<Item> CuriosShoes = registerCommonMaterials(ZeroingITEM,"curios_shoes", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Ring = ZeroingITEM.register(ModularRing.identifier, ModularRing::new);
    public static final RegistryObject<Item> CuriosRing = registerCommonMaterials(ZeroingITEM,"curios_ring", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Crown = ZeroingITEM.register(ModularCrown.identifier, ModularCrown::new);
    public static final RegistryObject<Item> CuriosCrown = registerCommonMaterials(ZeroingITEM,"curios_crown", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Glove = ZeroingITEM.register(ModularGlove.identifier, ModularGlove::new);
    public static final RegistryObject<Item> CuriosGlove = registerCommonMaterials(ZeroingITEM,"curios_glove", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Amulet = ZeroingITEM.register(ModularAmulet.identifier, ModularAmulet::new);
    public static final RegistryObject<Item> CuriosAmulet = registerCommonMaterials(ZeroingITEM,"curios_amulet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Bracelet = ZeroingITEM.register(ModularBracelet.identifier, ModularBracelet::new);
    public static final RegistryObject<Item> CuriosBracelet = registerCommonMaterials(ZeroingITEM,"curios_bracelet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_HeartProtectingMirror = ZeroingITEM.register(ModularHeartProtectingMirror.identifier, ModularHeartProtectingMirror::new);
    public static final RegistryObject<Item> CuriosHeartProtectingMirror = registerCommonMaterials(ZeroingITEM,"curios_heart_protecting_mirror", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_Jetpack = ZeroingITEM.register(ModularJetpack.identifier, ModularJetpack::new);
    public static final RegistryObject<Item> CuriosJetpack = registerCommonMaterials(ZeroingITEM,"curios_jetpack", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    public static final RegistryObject<Item> MODULAR_TotemOfUndying = ZeroingITEM.register(ModularTotemOfUndying.identifier, ModularTotemOfUndying::new);
    public static final RegistryObject<Item> CuriosTotemOfUndying = registerCommonMaterials(ZeroingITEM,"curios_totem_of_undying", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    //原版
    public static final RegistryObject<Item> CopperLantern = registerCommonMaterials(ZeroingITEM,"copper_lantern", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ReverseMirror = registerCommonMaterials(ZeroingITEM,"reverse_mirror", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> OxygenTank = registerCommonMaterials(ZeroingITEM,"oxygen_tank", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ObsidianCore = registerCommonMaterials(ZeroingITEM,"obsidian_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HasteCore = registerCommonMaterials(ZeroingITEM,"haste_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AmethystRadar = registerCommonMaterials(ZeroingITEM,"amethyst_radar", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> Scabbord = registerCommonMaterials(ZeroingITEM,"scabbord", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ShulkerPendant = registerCommonMaterials(ZeroingITEM,"shulker_pendant", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RedstoneGenerator = registerCommonMaterials(ZeroingITEM,"redstone_generator", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EnderRod = registerCommonMaterials(ZeroingITEM,"ender_rod", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AmethystRod = registerCommonMaterials(ZeroingITEM,"amethyst_rod", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GoldBambooStick = registerCommonMaterials(ZeroingITEM,"gold_bamboo_stick", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FloatingThreads = registerCommonMaterials(ZeroingITEM,"floating_threads", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SpreadingRoots = registerCommonMaterials(ZeroingITEM,"spreading_roots", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GoldenSilk = registerCommonMaterials(ZeroingITEM,"golden_silk", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BoneIngot = registerCommonMaterials(ZeroingITEM,"bone_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DevouringStoneVine = registerCommonMaterials(ZeroingITEM,"devouring_stone_vine", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GlassShard = registerCommonMaterials(ZeroingITEM,"glass_shard", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RottenFleshIngot = registerCommonMaterials(ZeroingITEM,"rotten_flesh_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WitherBoneIngot = registerCommonMaterials(ZeroingITEM,"wither_bone_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TidalIngot = registerCommonMaterials(ZeroingITEM,"tidal_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    //伪·匠魂
    public static final RegistryObject<Item> FakeManyullynIngot = registerCommonMaterials(ZeroingITEM,"fake_manyullyn_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakeHepatizonIngot = registerCommonMaterials(ZeroingITEM,"fake_hepatizon_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakeRoseGoldIngot = registerCommonMaterials(ZeroingITEM,"fake_rose_gold_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakePigIronIngot = registerCommonMaterials(ZeroingITEM,"fake_pig_iron_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakeRoseGoldenSilk = registerCommonMaterials(ZeroingITEM,"fake_rose_golden_silk", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakeSilkyCloth = registerCommonMaterials(ZeroingITEM,"fake_silky_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FakeAlumiteIngot = registerCommonMaterials(ZeroingITEM,"fake_alumite_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    //钱币
    public static final RegistryObject<Item> CopperMoney = registerCommonMaterials(ZeroingITEM,"copper_money", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IronMoney = registerCommonMaterials(ZeroingITEM,"iron_money", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GoldMoney = registerCommonMaterials(ZeroingITEM,"gold_money", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DiamondMoney = registerCommonMaterials(ZeroingITEM,"diamond_money", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NetheriteMoney = registerCommonMaterials(ZeroingITEM,"netherite_money", () -> new Item(new Item.Properties().stacksTo(64)));
    //铁魔法法杖
    public static final RegistryObject<Item> MODULAR_IronStaff = IronSpellITEM.register(ModularIronStaff.identifier, ModularIronStaff::new);
    public static final RegistryObject<Item> TetraIronStaff = registerCommonMaterials(IronSpellITEM,"tetra_iron_staff", () -> new Item(new Item.Properties().stacksTo(1).fireResistant() ));
    //铁魔法-符文锭
    public static final RegistryObject<Item> EmptyArcaneIngot = registerCommonMaterials(IronSpellITEM,"empty_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FireArcaneIngot = registerCommonMaterials(IronSpellITEM,"fire_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IceArcaneIngot = registerCommonMaterials(IronSpellITEM,"ice_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LightningArcaneIngot = registerCommonMaterials(IronSpellITEM,"lightning_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EvocationArcaneIngot = registerCommonMaterials(IronSpellITEM,"evocation_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HolyArcaneIngot = registerCommonMaterials(IronSpellITEM,"holy_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BloodArcaneIngot = registerCommonMaterials(IronSpellITEM,"blood_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EnderArcaneIngot = registerCommonMaterials(IronSpellITEM,"ender_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NatureArcaneIngot = registerCommonMaterials(IronSpellITEM,"nature_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EldritchArcaneIngot = registerCommonMaterials(IronSpellITEM,"eldritch_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ArcaneArcaneIngot = registerCommonMaterials(IronSpellITEM,"arcane_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CooldownArcaneIngot = registerCommonMaterials(IronSpellITEM,"cooldown_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ProtectionArcaneIngot = registerCommonMaterials(IronSpellITEM,"protection_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SoundArcaneIngot = registerCommonMaterials(IronSpellAfITEM,"sound_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AquaArcaneIngot = registerCommonMaterials(IronSpellToITEM,"aqua_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    //奥术布匹
    public static final RegistryObject<Item> FireMagicCloth = registerCommonMaterials(IronSpellITEM,"fire_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IceMagicCloth = registerCommonMaterials(IronSpellITEM,"ice_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LightningMagicCloth = registerCommonMaterials(IronSpellITEM,"lightning_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EvocationMagicCloth = registerCommonMaterials(IronSpellITEM,"evocation_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HolyMagicCloth = registerCommonMaterials(IronSpellITEM,"holy_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BloodMagicCloth = registerCommonMaterials(IronSpellITEM,"blood_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EnderMagicCloth = registerCommonMaterials(IronSpellITEM,"ender_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NatureMagicCloth = registerCommonMaterials(IronSpellITEM,"nature_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EldritchMagicCloth = registerCommonMaterials(IronSpellITEM,"eldritch_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SoundMagicCloth = registerCommonMaterials(IronSpellAfITEM,"sound_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AquaMagicCloth = registerCommonMaterials(IronSpellToITEM,"aqua_magic_cloth", () -> new Item(new Item.Properties().stacksTo(64)));
    //灾变
    public static final RegistryObject<Item> AbyssalIngot = registerCommonMaterials(CataclysmITEM,"abyssal_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AbyssalCore = registerCommonMaterials(CataclysmITEM,"abyssal_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IgnitiumCore = registerCommonMaterials(CataclysmITEM,"ignitium_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CursiumCore = registerCommonMaterials(CataclysmITEM,"cursium_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HarbingerCore = registerCommonMaterials(CataclysmITEM,"harbinger_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> StormCore = registerCommonMaterials(CataclysmITEM,"storm_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> StormIngot = registerCommonMaterials(CataclysmITEM,"storm_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    //冰火
    public static final RegistryObject<Item> FireDragonsteelCore = registerCommonMaterials(IceAndFireITEM,"fire_dragonsteel_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IceDragonsteelCore = registerCommonMaterials(IceAndFireITEM,"ice_dragonsteel_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LightningDragonsteelCore = registerCommonMaterials(IceAndFireITEM,"lightning_dragonsteel_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FireDragonBone = registerCommonMaterials(IceAndFireITEM,"fire_dragonbone", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> IceDragonBone = registerCommonMaterials(IceAndFireITEM,"ice_dragonbone", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LightningDragonBone = registerCommonMaterials(IceAndFireITEM,"lightning_dragonbone", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GhostCore = registerCommonMaterials(IceAndFireITEM,"ghost_core", () -> new Item(new Item.Properties().stacksTo(64)));
    //神秘遗物
    public static final RegistryObject<Item> EvilCore = registerCommonMaterials(EnigmaticlegacyITEM,"evil_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EtheriumCore = registerCommonMaterials(EnigmaticlegacyITEM,"etherium_core", () -> new Item(new Item.Properties().stacksTo(64)));
    //洞穴
    public static final RegistryObject<Item> ExtinctionCore = registerCommonMaterials(AlexsCavesITEM,"extinction_core", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AbyssalOceanIngot = registerCommonMaterials(AlexsCavesITEM,"abyssal_ocean_ingot", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RadiationAbsorptionCore = registerCommonMaterials(AlexsCavesITEM,"radiation_absorption_core", () -> new Item(new Item.Properties().stacksTo(64)));
    //恶意
    public static final RegistryObject<Item> HostilityCore = registerCommonMaterials(L2ITEM,"hostility_core", () -> new Item(new Item.Properties().stacksTo(64)));
    //薪火（方舟
    public static final RegistryObject<Item> AttainmentPoint = registerCommonMaterials(ArkNightsITEM,"attainment_point", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RhodesIslandStandardIngot = registerCommonMaterials(ArkNightsITEM,"rhodes_island_standard_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RosmontisIngot = registerCommonMaterials(ArkNightsITEM,"rosmontis_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FrostNovaIngot = registerCommonMaterials(ArkNightsITEM,"frost_nova_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GunKnightPatriotIngot = registerCommonMaterials(ArkNightsITEM,"gun_knight_patriot_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PatriotIngot = registerCommonMaterials(ArkNightsITEM,"patriot_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PursuerIngot = registerCommonMaterials(ArkNightsITEM,"pursuer_ingot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    //方舟胜利之证
    public static final RegistryObject<Item> ProofOfVictoryRosmontis = registerCommonMaterials(ArkNightsITEM,"proof_of_victory_rosmontis", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ProofOfVictoryFrostNova = registerCommonMaterials(ArkNightsITEM,"proof_of_victory_frost_nova", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ProofOfVictoryGunKnightPatriot = registerCommonMaterials(ArkNightsITEM,"proof_of_victory_gun_knight_patriot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ProofOfVictoryPatriot = registerCommonMaterials(ArkNightsITEM,"proof_of_victory_patriot", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ProofOfVictoryPursuer = registerCommonMaterials(ArkNightsITEM,"proof_of_victory_pursuer", () -> new TooltipItem(new TooltipItem.Properties().stacksTo(64)));

    public MoreModTetraItemRegister(){
    }

    public static void register(IEventBus bus){
        ZeroingITEM.register(bus);
        if(ModList.get().isLoaded("torchesbecomesunlight")){
            ArkNightsITEM.register(bus);
        }
        if(ModList.get().isLoaded("irons_spellbooks")){
            IronSpellITEM.register(bus);
        }
        if(ModList.get().isLoaded("traveloptics")){
            IronSpellToITEM.register(bus);
        }
        if(ModList.get().isLoaded("alshanex_familiars")){
            IronSpellAfITEM.register(bus);
        }
        if(ModList.get().isLoaded("cataclysm")){
            CataclysmITEM.register(bus);
        }
        if(ModList.get().isLoaded("iceandfire")){
            IceAndFireITEM.register(bus);
        }
        if(ModList.get().isLoaded("enigmaticlegacy")){
            EnigmaticlegacyITEM.register(bus);
        }
        if(ModList.get().isLoaded("alexscaves")){
            AlexsCavesITEM.register(bus);
        }
        if(ModList.get().isLoaded("l2complements")){
            L2ITEM.register(bus);
        }
    }
}
