package com.inolia_zaicek.more_mod_tetra.Damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static com.inolia_zaicek.more_mod_tetra.MoreModTetra.MODID;

//source的create方法
public class MMTTickZero {
    private static ResourceKey<DamageType> create(String name){
        return ResourceKey.create(Registries.DAMAGE_TYPE,new ResourceLocation(MODID,name));
    }
    public MMTTickZero(){}
    //生成source
    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing){
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct,causing);
    }
    //有源
    public static DamageSource hasSource(Level level, ResourceKey<DamageType> type, @Nullable Entity entity){
        return source(level,type,entity, entity);
    }
    //无源
    public static DamageSource source(Level level, ResourceKey<DamageType> type){
        return source(level,type,null, null);
    }
    //DamageType注册
    //注册项（type和tag是两个东西【参考注册物品就行
    //真伤
    public static final ResourceKey<DamageType> TRUEDAMAGE = create("truedamage");
    //dot
    public static final ResourceKey<DamageType> DOTDAMAGE = create("dotdamage");
    //不造成无敌帧or无视无敌帧（物理伤害
    public static final ResourceKey<DamageType> TICKAMAGE = create("tickdamage");
    //不造成无敌帧or无视无敌帧的法伤
    public static final ResourceKey<DamageType> TICKMAMAGE = create("tickmdamage");
    }
