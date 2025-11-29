package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.*;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class MeleeAttackCombo {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter1 = new StatGetterEffectLevel(melee_attack_combo_Effect, 1);
        var statGetter2 = new StatGetterEffectEfficiency(melee_attack_combo_Effect, 1);
        IStatGetter[] statGetters = {statGetter1, statGetter2};
        IStatFormat[] statFormats = {StatFormat.noDecimal, StatFormat.noDecimal};
        GuiStatBar statBar1 = new GuiStatBar(0, 0, StatsHelper.barLength,
                melee_attack_combo_Name, 0, 100, false, false, false,
                statGetter1, LabelGetterBasic.integerLabel,
                new TooltipGetterMultiValue(melee_attack_combo_Tooltip, statGetters, statFormats)
        );
        WorkbenchStatsGui.addBar(statBar1);
        HoloStatsGui.addBar(statBar1);
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
        if (MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, melee_attack_combo_Effect);
                int effectLevel2 = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(livingEntity, melee_attack_combo_Effect);
                if (effectLevel1 > 0) {
                    float number = (float) effectLevel1 / 100;
                    float damage = event.getAmount();
                    var DamageType = MMTTickZero.hasSource(livingEntity.level(), MMTTickZero.TICKAMAGE, livingEntity);
                    if(effectLevel2>=1) {
                        //实际范围：range*2-1
                        var mobList = MMTUtil.mobList(effectLevel2,mob);
                        for (Mob mobs:mobList){
                            if(mobs!=null) {
                                //获取伤害类型
                                mobs.invulnerableTime=0;
                                if(livingEntity instanceof Player player) {
                                    mobs.setLastHurtByPlayer(player);
                                }
                                mob.hurt(DamageType, damage * number);
                                if(livingEntity instanceof Player player) {
                                    mobs.setLastHurtByPlayer(player);
                                }
                            }
                        }
                    }else{
                        mob.invulnerableTime = 0;
                        if (livingEntity instanceof Player player1) {
                            mob.setLastHurtByPlayer(player1);
                        }
                        mob.hurt(DamageType, damage * number);
                    }
                }
            }
        }
    }
}
