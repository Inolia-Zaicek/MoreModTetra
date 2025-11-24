package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.FE;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FantasyShadow {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fantasy_shadowEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fantasy_shadowName, 0, 5  , false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fantasy_shadowTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("fantasy_ending")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity &&event.getEntity()!=null) {
                int effectLevel = 0;
                                    //获取一下玩家主副手
                                    ItemStack mainHandItem = livingEntity.getMainHandItem();
                                    ItemStack offhandItem = livingEntity.getOffhandItem();
                                    if (mainHandItem.getItem() instanceof IModularItem item) {
                                        float mainEffectLevel = item.getEffectLevel(mainHandItem, fantasy_shadowEffect);
                                        if (mainEffectLevel > 0) {
                                            effectLevel += (int) mainEffectLevel;
                                        }
                                    }
                                    if (offhandItem.getItem() instanceof IModularItem item) {
                                        float offEffectLevel = item.getEffectLevel(offhandItem, fantasy_shadowEffect);
                                        if (offEffectLevel > 0) {
                                            effectLevel += (int) offEffectLevel;
                                        }
                                    }
                                    if (effectLevel > 0&&event.getEntity()!=null) {
                                        var mob = event.getEntity();
                                        var map = mob.getActiveEffectsMap();
                                        //通过id直接给buff
                                        mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,20*effectLevel,1));
                                        map.put(MobEffects.WEAKNESS,
                                                new MobEffectInstance(MobEffects.WEAKNESS,20*effectLevel,1));
                                        mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing"))), 20 * effectLevel, 7));
                                        map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing")))
                                                , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing"))), 20 * effectLevel, 7));
                                    }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity &&event.getEntity()!=null) {
                int effectLevel = 0;
                //获取一下玩家主副手
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fantasy_shadowEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, fantasy_shadowEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0 && event.getEntity() != null) {
                    var mob = event.getEntity();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,20*effectLevel,1));
                    map.put(MobEffects.WEAKNESS,
                            new MobEffectInstance(MobEffects.WEAKNESS,20*effectLevel,1));
                    mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing"))), 20 * effectLevel, 7));
                    map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing")))
                            , new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("fantasy_ending", "ban_healing"))), 20 * effectLevel, 7));
                }
            }
        }
    }
}
