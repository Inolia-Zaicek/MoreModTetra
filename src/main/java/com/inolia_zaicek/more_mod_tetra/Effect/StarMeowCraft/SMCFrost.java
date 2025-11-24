package com.inolia_zaicek.more_mod_tetra.Effect.StarMeowCraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class SMCFrost {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(smcFrostEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                smcFrostName, 0, 25  , false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(smcFrostTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("smc")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof LivingEntity player  &&event.getEntity()!=null) {
                int effectLevel = 0;
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = player.getMainHandItem();
                                    ItemStack offhandItem = player.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof IModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, smcFrostEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof IModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, smcFrostEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0&&event.getEntity()!=null) {
                                        var mob = event.getEntity();
                                        var map = mob.getActiveEffectsMap();
                                        //通过id直接给buff
                                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost"))), 20 * effectLevel, 0));
                                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost")))
                                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost"))), 20 * effectLevel, 0));
                                    }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity player &&event.getEntity()!=null) {
                int effectLevel = 0;
                //获取一下玩家主副手
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, smcFrostEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, smcFrostEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0 && event.getEntity() != null) {
                    var mob = event.getEntity();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost"))), 20 * effectLevel, 0));
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost")))
                            , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("smc", "frost"))), 20 * effectLevel, 0));
                }
            }
        }
    }
}
