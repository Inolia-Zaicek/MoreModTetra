package com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class FakeInsatiable {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fakeInsatiableEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fakeInsatiableName, 0, 20, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fakeInsatiableTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                var map = mob.getActiveEffectsMap();
                ItemStack mainHandItem = livingEntity.getMainHandItem();
                ItemStack offhandItem = livingEntity.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, fakeInsatiableEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, fakeInsatiableEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    if (livingEntity.hasEffect(MMTEffectsRegister.FakeInsatiable.get())) {
                        int buffLevel = livingEntity.getEffect(MMTEffectsRegister.FakeInsatiable.get()).getAmplifier();
                        int buffTime = livingEntity.getEffect(MMTEffectsRegister.FakeInsatiable.get()).getDuration();
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.FakeInsatiable.get(),100, Math.min(effectLevel-1,buffLevel+1) ));
                        event.setAmount(event.getAmount()+ ( (buffLevel-1)*2 ) );
                    }else{
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.FakeInsatiable.get(),100,0));
                        event.setAmount(event.getAmount()+2);
                }
            }
        }
    }
}
