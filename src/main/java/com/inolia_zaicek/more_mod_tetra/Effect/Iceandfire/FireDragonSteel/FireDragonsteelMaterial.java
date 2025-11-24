package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.FireDragonSteel;

import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FireDragonsteelMaterial {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fireDragonsteelMaterialEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fireDragonsteelMaterialName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fireDragonsteelMaterialTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("iceandfire")) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fireDragonsteelMaterialEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, fireDragonsteelMaterialEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    mob.setRemainingFireTicks(mob.getRemainingFireTicks()+200);
                    float number = (float) effectLevel / 100;
                    float numberA = (float) effectLevel / 200;
                    float damage =event.getAmount();
                    event.setAmount(damage*(1-number));
                    //非火龙
                    if(mob instanceof EntityLightningDragon||mob instanceof EntityIceDragon) {
                        mob.invulnerableTime=0;
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        mob.hurt(mob.damageSources().onFire(),damage*number*(1+number));
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                    }
                    if(mob instanceof EntityFireDragon) {
                        mob.invulnerableTime=0;
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        mob.hurt(mob.damageSources().onFire(),damage*number*(1+numberA));
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                    }
                    //不是龙
                    if(!(mob instanceof EntityFireDragon)&&!(mob instanceof EntityIceDragon)&&!(mob instanceof EntityLightningDragon)){
                        mob.invulnerableTime=0;
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                        mob.hurt(mob.damageSources().onFire(),damage*number);
                        if(livingEntity instanceof Player player) {
                            mob.setLastHurtByPlayer(player);
                        }
                    }
                    }
                }
            //挨打
            if (event.getEntity()!=null&& !(event.getSource().getEntity() instanceof Player)) {
                LivingEntity livingEntity = event.getEntity();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fireDragonsteelMaterialEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, fireDragonsteelMaterialEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0&&livingEntity.getLastAttacker()!=null) {
                    float number = (float) effectLevel / 100;
                    float numberA = (float) effectLevel / 200;
                    //冰龙
                    if(livingEntity.getLastAttacker() instanceof EntityIceDragon||livingEntity.getLastAttacker() instanceof EntityLightningDragon) {
                        event.setAmount(event.getAmount()*(1-number));
                    }
                    if(livingEntity.getLastAttacker() instanceof EntityFireDragon) {
                        event.setAmount(event.getAmount()*(1-numberA));
                    }
                }
            }
            }
        }
    }
