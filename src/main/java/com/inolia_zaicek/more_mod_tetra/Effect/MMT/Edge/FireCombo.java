package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;
import static com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge.DragonBreathEdge.attack_combo;

public class FireCombo {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fireComboEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fireComboName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fireComboTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                var mob = event.getEntity();
                if (player.getPersistentData().getInt(attack_combo) == 0) {
                    player.getPersistentData().putInt(attack_combo, (int) (0.1F * 20 * 2));
                    float damage = event.getAmount();
                    float fireComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, fireComboEffect);
                    if (fireComboEffectLevel > 0) {
                        float number = (float) fireComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LAVA, player), damage * number);
                    }
                    float freezeComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,freezeComboEffect);
                    if (freezeComboEffectLevel > 0) {
                        float number = (float) freezeComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.FREEZE, player), damage * number);
                    }
                    float lightningComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,lightningComboEffect);
                    if (lightningComboEffectLevel > 0) {
                        float number = (float) lightningComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LIGHTNING_BOLT, player), damage * number);
                    }
                    //
                    float magicComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,magicComboEffect);
                    if (magicComboEffectLevel > 0) {
                        float number = (float) magicComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.MAGIC, player), damage * number);
                    }
                    float witherComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,witherComboEffect);
                    if (witherComboEffectLevel > 0) {
                        float number = (float) witherComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.WITHER, player), damage * number);
                    }
                    float dragonBreathComboEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,dragonBreathComboEffect);
                    if (dragonBreathComboEffectLevel > 0) {
                        float number = (float) dragonBreathComboEffectLevel / 100;
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.DRAGON_BREATH, player), damage * number);
                    }
                    //冰火
                    float fireDragonSteelMaterialEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, fireDragonsteelMaterialEffect);
                    float iceDragonSteelMaterialEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, iceDragonsteelMaterialEffect);
                    float lightningDragonSteelMaterialEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, lightningDragonsteelMaterialEffect);
                    if(fireDragonSteelMaterialEffectLevel>0) {
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LAVA, player), damage * fireDragonSteelMaterialEffectLevel/100);
                    }
                    if(iceDragonSteelMaterialEffectLevel>0) {
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.FREEZE, player), damage * iceDragonSteelMaterialEffectLevel/100);
                    }
                    if(lightningDragonSteelMaterialEffectLevel>0) {
                        mob.invulnerableTime = 0;
                        mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LIGHTNING_BOLT, player), damage * lightningDragonSteelMaterialEffectLevel/100);
                    }
                }
            }
        }
    }
}