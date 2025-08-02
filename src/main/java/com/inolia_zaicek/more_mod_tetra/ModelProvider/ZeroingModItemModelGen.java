package com.inolia_zaicek.more_mod_tetra.ModelProvider;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZeroingModItemModelGen extends ItemModelProvider {
    //通用模型，让其他物品继承使用
    public static final String GENERATED = "item/generated";
    // 工具模型，通常用于工具
    public static final String HANDHELD = "item/handheld";

    //构造函数
    public ZeroingModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper){
        //第二个参数要填自己的modid
        super(output, MoreModTetra.MODID,existingFileHelper);
    }

    //重写该方法，在这个方法里面填写要生成数据的物品
    @Override
    protected void registerModels() {
        //遍历普通物品，使用 item/generated 模型
        for(RegistryObject<Item> itemsDeferredRegister: MoreModTetraItemRegister.CommonItem){
            if(itemsDeferredRegister.isPresent()){
                itemGenerateModel(itemsDeferredRegister.get(),resourceItem(itemName(itemsDeferredRegister.get())));
            }
        }

        // **** 结束新增的代码部分 ****

        // 关于“护甲穿戴模型”的说明：
        // ItemModelProvider 无法直接生成玩家穿戴时的3D模型。
        // 这些模型（通常是 JSON 格式，引用 OBJ 或其他模型）
        // 需要手动创建并放置在 resources/assets/yourmodid/models/armor/ 目录下。
        // 渲染这些3D模型通常在 ArmorRenderer（Minecraft 1.19+）或旧版本的 IItemRenderer 中完成。
        // 此处仅为护甲物品的物品栏图标生成模型。
    }

    //自定义方法，item表明我们要生成数据的物品，texture表明生成数据的贴图资源
    // 适用于通用物品 (item/generated)
    public void itemGenerateModel(Item item, ResourceLocation texture){
        //因为要继承通用模型，调用这个方法设置父模型为通用模型，返回这个类的对象调用texture方法设置贴图
        withExistingParent(itemName(item),GENERATED).texture("layer0",texture);
    }

    // 新增方法，用于生成工具物品模型 (item/handheld)
    public void itemHandheldModel(Item item, ResourceLocation texture){
        // 为工具设置父模型为 item/handheld，并设置贴图
        withExistingParent(itemName(item),HANDHELD).texture("layer0",texture);
    }

    //获取item的所在路径
    public String itemName(Item item){
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    //拼接路径寻找贴图资源
    public ResourceLocation resourceItem(String path){
        return new ResourceLocation(MoreModTetra.MODID,"item/"+path);
    }
}
