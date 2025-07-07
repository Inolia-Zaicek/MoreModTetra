package com.inolia_zaicek.more_mod_tetra.Register;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID;


public class MoreModTetraItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    // 物品
    public static final RegistryObject<Item> FireArcaneIngot = registerCommonMaterials(ZeroingITEM,"fire_arcane_ingot", () -> new Item(new Item.Properties().stacksTo(64)));

    public MoreModTetraItemRegister(){
    }

    public static void register(IEventBus bus){
        ZeroingITEM.register(bus);
    }
}
