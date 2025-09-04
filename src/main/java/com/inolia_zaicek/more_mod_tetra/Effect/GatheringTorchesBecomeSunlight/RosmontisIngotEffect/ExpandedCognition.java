package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.RosmontisIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class ExpandedCognition {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(expandedCognitionEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                expandedCognitionName, 0, 180, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(expandedCognitionTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {
            float damage=event.getAmount();
                var mob = event.getEntity();
                ItemStack mainHandItem = player.getMainHandItem();
                ItemStack offhandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof IModularItem item) {
                    float mainEffectLevel = item.getEffectLevel(mainHandItem, expandedCognitionEffect);
                    if (mainEffectLevel > 0) {
                        effectLevel +=  mainEffectLevel;
                    }
                }
                if (offhandItem.getItem() instanceof IModularItem item) {
                    float offEffectLevel = item.getEffectLevel(offhandItem, expandedCognitionEffect);
                    if (offEffectLevel > 0) {
                        effectLevel += (int) offEffectLevel;
                    }
                }
                if (effectLevel > 0) {
                    mob.invulnerableTime=0;
                    mob.setLastHurtByPlayer(player);
                    float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    mob.hurt(mob.damageSources().magic(),(atk*effectLevel/100));
                    mob.setLastHurtByPlayer(player);
            }
                //二技能
            int effectLevel2 = 0;
            if (mainHandItem.getItem() instanceof IModularItem item) {
                float mainEffectLevel = item.getEffectLevel(mainHandItem, nociceptorInhibitionEffect);
                if (mainEffectLevel > 0) {
                    effectLevel2 += (int) mainEffectLevel;
                }
            }
            if (offhandItem.getItem() instanceof IModularItem item) {
                float offEffectLevel = item.getEffectLevel(offhandItem, nociceptorInhibitionEffect);
                if (offEffectLevel > 0) {
                    effectLevel2 += (int) offEffectLevel;
                }
            }
            if (effectLevel2 > 0) {
                if(MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                    var mobList = MMTUtil.mobList(3,mob);
                    float number=1+ (float) effectLevel2 /100;
                    for (Mob mobs:mobList){
                        if(mobs!=null) {
                            //获取伤害类型
                            mobs.invulnerableTime=0;
                            mobs.setLastHurtByPlayer(player);
                            float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().magic(),atk*number);
                            mobs.setLastHurtByPlayer(player);
                            //眩晕
                            Random random = new Random();
                            if(random.nextInt(100)<(effectLevel2) ) {
                                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 3));
                                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 1));
                            }
                        }
                    }
                    //非近战
                }else{
                    var mobList = MMTUtil.mobList(3,mob);
                    float number=1+ (float) effectLevel2 /50;
                    for (Mob mobs:mobList){
                        if(mobs!=null) {
                            //获取伤害类型
                            mobs.invulnerableTime=0;
                            mobs.setLastHurtByPlayer(player);
                            float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mobs.hurt(mobs.damageSources().magic(),atk*number);
                            mobs.setLastHurtByPlayer(player);
                            //眩晕
                            Random random = new Random();
                            if(random.nextInt(100)<(2*effectLevel2) ) {
                                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 3));
                                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 1));
                            }
                        }
                    }
                }
            }
        }


    }
}