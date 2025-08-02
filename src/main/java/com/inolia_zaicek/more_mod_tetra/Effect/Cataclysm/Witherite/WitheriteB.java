package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm.Witherite;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.data.Main;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class WitheriteB {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if(ModList.get().isLoaded("cataclysm")) {
            if (event.getSource().getDirectEntity() instanceof Player player && !(event.getEntity() instanceof Player)) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, witheriteEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel += (int) mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof ModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, witheriteEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (mob.hasEffect(MobEffects.WITHER)) {
                        int buffLevel = mob.getEffect(MobEffects.WITHER).getAmplifier();
                        int buffTime = mob.getEffect(MobEffects.WITHER).getDuration();
                        map.put(MobEffects.WITHER,
                                new MobEffectInstance(MobEffects.WITHER, Math.min(20*60,Math.max(200,buffTime*2)), Math.min(4,buffLevel+effectLevel) ));
                        event.setAmount(event.getAmount()*( 1+ ((float) effectLevel /100)/(6-Math.min(5,buffLevel+1)) ) );
                    }else{
                        mob.addEffect(new MobEffectInstance(MobEffects.WITHER,200,effectLevel));
                    }
                }
            }
        }
    }
}
