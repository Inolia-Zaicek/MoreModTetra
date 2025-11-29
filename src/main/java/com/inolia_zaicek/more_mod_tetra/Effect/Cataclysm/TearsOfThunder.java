package com.inolia_zaicek.more_mod_tetra.Effect.Cataclysm;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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

public class TearsOfThunder {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(tearsOfThunderEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                tearsOfThunderName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(tearsOfThunderTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,tearsOfThunderEffect);
                if (effectLevel > 0) {
                    var mobList = MMTUtil.mobList(3,mob);
                    float number= effectLevel /100;
                    for (Mob mobs:mobList){
                        if(mobs!=null) {
                            //获取伤害类型
                            mobs.invulnerableTime=0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().lightningBolt(),atk*number);
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                }
            }else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,tearsOfThunderEffect);
                if (effectLevel > 0) {
                    var mobList = MMTUtil.mobList(3,mob);
                    float number= effectLevel /100;
                    for (Mob mobs:mobList){
                        if(mobs!=null) {
                            //获取伤害类型
                            mobs.invulnerableTime=0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().lightningBolt(),atk*number);
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                }
            }
        }
    }
