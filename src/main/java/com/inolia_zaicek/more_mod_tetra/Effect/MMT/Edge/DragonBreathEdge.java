package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Edge;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class DragonBreathEdge {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(dragonBreathEdgeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                dragonBreathEdgeName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(dragonBreathEdgeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            float damage = event.getAmount();
            float dragonBreathEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, dragonBreathEdgeEffect);
            float freezeEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,freezeEdgeEffect);
            float fireEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,fireEdgeEffect);
            float lightningEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,lightningEdgeEffect);
            float magicEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,magicEdgeEffect);
            float witherEdgeEffectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,witherEdgeEffect);
            //基础伤害减少
            float number = (float) (dragonBreathEdgeEffectLevel+freezeEdgeEffectLevel+fireEdgeEffectLevel+lightningEdgeEffectLevel
                    +magicEdgeEffectLevel);
            //任意词条》0
            if (number>0&&player.getPersistentData().getInt(attack_edge) == 0) {
                player.getPersistentData().putInt(attack_edge, (int) (0.1F * 20 * 2));
                event.setAmount(Math.max(0, damage * (1 - (number / 100) )));
                //龙息
                if (dragonBreathEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.DRAGON_BREATH, player), damage * dragonBreathEdgeEffectLevel/100);
                }
                //冰霜
                if (freezeEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.FREEZE, player), damage * freezeEdgeEffectLevel/100);
                }
                //火焰
                if (fireEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LAVA, player), damage * fireEdgeEffectLevel/100);
                }
                //雷霆
                if (lightningEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.LIGHTNING_BOLT, player), damage * lightningEdgeEffectLevel/100);
                }
                //凋零
                if (witherEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.WITHER, player), damage * witherEdgeEffectLevel/100);
                }
                //魔法
                if (magicEdgeEffectLevel > 0) {
                    mob.invulnerableTime = 0;
                    mob.hurt(MMTTickZero.hasSource(player.level(), DamageTypes.MAGIC, player), damage * magicEdgeEffectLevel/100);
                }
            }
        }
    }
    public static final String attack_edge = MoreModTetra.MODID + ":attack_edge";
    public static final String attack_combo = MoreModTetra.MODID + ":attack_combo";
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        if (!event.getEntity().isAlive())
            return;
        LivingEntity livingEntity = event.getEntity();
        if(livingEntity.getPersistentData().getInt(attack_edge)>0){
            livingEntity.getPersistentData().putInt(attack_edge,
                    livingEntity.getPersistentData().getInt(attack_edge)-1);
        }
        if(livingEntity.getPersistentData().getInt(attack_combo)>0){
            livingEntity.getPersistentData().putInt(attack_combo,
                    livingEntity.getPersistentData().getInt(attack_combo)-1);
        }
    }
}