package com.inolia_zaicek.more_mod_tetra.Effect.FakeTconstruct;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,fakeInsatiableEffect);
                if (effectLevel > 0) {
                    if (livingEntity.hasEffect(MMTEffectsRegister.FakeInsatiable.get())) {
                        int buffLevel = livingEntity.getEffect(MMTEffectsRegister.FakeInsatiable.get()).getAmplifier();
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.FakeInsatiable.get(),100, (int) Math.min(effectLevel-1,buffLevel+1)));
                        event.setAmount(event.getAmount()+ ( (buffLevel+1)*0.5f ) );
                    }else{
                        livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.FakeInsatiable.get(),100,0));
                        event.setAmount(event.getAmount()+0.5f);
                }
            }
        }
    }
}
