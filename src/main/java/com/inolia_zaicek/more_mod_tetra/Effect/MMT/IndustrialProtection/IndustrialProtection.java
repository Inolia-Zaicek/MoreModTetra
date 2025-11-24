package com.inolia_zaicek.more_mod_tetra.Effect.MMT.IndustrialProtection;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class IndustrialProtection {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(industrialProtectionEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                industrialProtectionName, 0, 20, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(industrialProtectionTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            if (event.getEntity()!=null&&event.getSource().getEntity()==null
                    &&!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                LivingEntity player = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, industrialProtectionEffect);
                if (effectLevel > 0&&player.hasEffect(MMTEffectsRegister.IndustrialProtection.get())) {
                    int buffLevel = player.getEffect(MMTEffectsRegister.IndustrialProtection.get()).getAmplifier();
                    event.setAmount(  Math.max(0,event.getAmount()-buffLevel)  );
                    }
                }
        if (event.getEntity() instanceof Player player&&event.getSource().getEntity()!=null&&player.hasEffect(MMTEffectsRegister.IndustrialProtection.get())) {
            player.removeEffect(MMTEffectsRegister.IndustrialProtection.get());
            player.addEffect(new MobEffectInstance(MMTEffectsRegister.UnIndustrialProtection.get(),200,0));
        }
    }
}

