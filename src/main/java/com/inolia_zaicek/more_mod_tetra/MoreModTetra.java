package com.inolia_zaicek.more_mod_tetra;

// import com.inolia_zaicek.daily_delight.Curios.BentoBox.WoodenBentoBoxCurios; // This class might be removed or adapted if you fully replace it



import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraTab;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.screens.MenuScreens; // For client-side screen registration
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID)
public class MoreModTetra {

    public static final String MODID = "more_mod_tetra";
    public static final Logger LOGGER = LogUtils.getLogger();
    public MoreModTetra() {
        init();
    }
    public void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册所有 DeferredRegister
        //DailyDelightItemRegister.register(bus); // Use ModItems for item registration
        MoreModTetraTab.register(bus);
        MoreModTetraItemRegister.register(bus);
        //DailyDelightBlockEntitiesRegister.register(bus);
        // ModCapabilities 在它自己的类中通过 @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD) 自动注册

        // 注册 FML 生命周期事件
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        //注册村民（事件有监听不需要
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册到 Forge Event Bus 的事件监听器
        // MinecraftForge.EVENT_BUS.register(this); // 如果你的主类中有 @SubscribeEvent 方法用于 Forge 事件，则需要注册
        // MinecraftForge.EVENT_BUS.register(WoodenBentoBoxCurios.class); // This line might need to be removed or adjusted if WoodenBentoBoxCurios is replaced
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            //CookingPotRecipes.register(registry -> {});
        });
    }

    // 客户端设置事件，用于注册渲染器和GUI屏幕
    private void clientSetup(FMLClientSetupEvent event) {

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
}
