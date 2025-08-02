package com.inolia_zaicek.more_mod_tetra.ModelProvider;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

//这个类相当于对所有的json数据生成器的一个管理，都添加到generator身上即可
@Mod.EventBusSubscriber(modid = MoreModTetra.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ZeroingDataGenerators {

    //这个注释表明这个方法要被模组事件总线所监视，这个方法是事件触发时的回调
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        //创建一堆东西，照着写就行，以后有机会会介绍
        DataGenerator generator=event.getGenerator();
        PackOutput output=generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider>lookupProvider=event.getLookupProvider();
        ExistingFileHelper helper=event.getExistingFileHelper();


        //添加生成对应文件的生成器
        //第一个参数表示在客户端还是服务端，这里我们选择客户端，第二个传进一个生成的对象，就是我们之前自己写的类
        generator.addProvider(event.includeClient(),new ZeroingModItemModelGen(output,helper));
        generator.addProvider(event.includeClient(),new ZeroingModRecipesGen(output));
    }
}