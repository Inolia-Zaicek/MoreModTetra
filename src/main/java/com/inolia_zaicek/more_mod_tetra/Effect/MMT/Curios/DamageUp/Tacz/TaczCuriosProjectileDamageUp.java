package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Tacz;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.tacz.guns.init.ModDamageTypes;
import net.minecraft.world.damagesource.DamageTypes;
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
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class TaczCuriosProjectileDamageUp {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosProjectileDamageUpEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosProjectileDamageUpName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosProjectileDamageUpTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("tacz")) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosProjectileDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(ModDamageTypes.BULLETS_TAG) || event.getSource().is(ModDamageTypes.BULLET)
                            || event.getSource().is(ModDamageTypes.BULLET_VOID) || event.getSource().is(ModDamageTypes.BULLET_IGNORE_ARMOR)
                            || event.getSource().is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR)) {
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        event.setAmount(finish);
                    }
                }
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosProjectileDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(ModDamageTypes.BULLETS_TAG) || event.getSource().is(ModDamageTypes.BULLET)
                            || event.getSource().is(ModDamageTypes.BULLET_VOID) || event.getSource().is(ModDamageTypes.BULLET_IGNORE_ARMOR)
                            || event.getSource().is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR)) {
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        event.setAmount(finish);
                    }
                }
            }
        }
    }
}
