package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Titan;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class EverythingIsInEverything {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(everythingIsInEverythingEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                everythingIsInEverythingName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(everythingIsInEverythingTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void event(PlayerXpEvent.XpChange event){
        if (event.getEntity()!=null&&event.getAmount()>1) {
            LivingEntity player = event.getEntity();
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,everythingIsInEverythingEffect);
            if (effectLevel > 0) {
                event.setAmount((int) (event.getAmount()*(1+effectLevel/100)+1));
            }
        }
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        //攻击
        if (event.getAttacker() instanceof Player player) {
            int experienceLevel = player.experienceLevel;
            int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, experience_edge_Effect);
            int effectEfficiency1 = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(player, experience_edge_Effect);
            if (effectLevel1 > 0 && experienceLevel>0) {
                if(experienceLevel<effectEfficiency1) {
                    event.addNormalMulti((float) (effectLevel1 / 100) * ((float) experienceLevel / effectEfficiency1));
                } else{
                    event.addNormalMulti((float) effectLevel1 / 100);
                }
            }
            int effectLevel2 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player, enchanting_edge_Effect);
            int effectEfficiency2 = (int) MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(player, enchanting_edge_Effect);
            //附魔数量
            int enchantmentsAmount = MMTUtil.getTotalEnchantmentsAmount(player);
            if(effectLevel2>0&&enchantmentsAmount>0){
                //附魔数小于总数，乘以比例
                if(enchantmentsAmount<effectEfficiency2) {
                    event.addNormalMulti((float) (effectLevel2 / 200) * ((float) experienceLevel / effectEfficiency2));
                } else{
                    event.addNormalMulti((float) effectLevel2 / 200);
                }
            }
        }
        //缓伤
        if (event.hurtEvent.getAmount()>0&&!event.hurtEvent.getSource().is(MMTTickZero.TRUEDAMAGE)) {
            LivingEntity attacked = event.getAttacked();
            int effectLevel1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(attacked, damage_buffering_Effect);
            int effectLevel2 = MMTCuriosHelper.getInstance().getCuriosEffectMaxLevel(attacked, damage_buffering_Effect);
            float effectEfficiency1 = MMTEffectHelper.getInstance().getMainOffHandMaxEffectEfficiency(attacked, damage_buffering_Effect);
            float effectEfficiency2 = MMTCuriosHelper.getInstance().getCuriosEffectMaxEfficiency(attacked, damage_buffering_Effect);
            int effectLevel = Math.max(effectLevel1, effectLevel2);
            float effectEfficiency = Math.max(effectEfficiency1, effectEfficiency2);
            //开始缓伤
            if (effectLevel > 0 && effectEfficiency > 0) {
                float number = (float) effectLevel / 100;
                int time = (int) (effectEfficiency + 1) * 20+1;
                event.addNormalMulti((-(float) effectLevel / 100));
                //计算缓伤（*100
                int bufferingDamage = (int) (event.hurtEvent.getAmount()*number*100);
                CompoundTag compoundTag = attacked.getPersistentData();
                int bufferingDamageNumber = compoundTag.getInt(damage_buffering_Number_NBT);
                compoundTag.putInt(damage_buffering_Number_NBT, bufferingDamageNumber+bufferingDamage);
                //赋予buff
                var map = attacked.getActiveEffectsMap();
                map.put(MMTEffectsRegister.DamageBuffering.get(),
                        new MobEffectInstance(MMTEffectsRegister.DamageBuffering.get(), time,0));
            }
        }
    }
    private static final String damage_buffering_Number_NBT = MoreModTetra.MODID + ":damage_buffering_number";
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.hasEffect(MMTEffectsRegister.DamageBuffering.get()) && livingEntity.level().getGameTime() % 20L == 0) {
            CompoundTag compoundTag = livingEntity.getPersistentData();
            int bufferingDamageNumber = compoundTag.getInt(damage_buffering_Number_NBT);
            //伤害数额（nbt数额/【20*tick(时间)*100】
            float damage = (float) bufferingDamageNumber /(20* Objects.requireNonNull(livingEntity.getEffect(MMTEffectsRegister.NeutralReverDuel.get())).getDuration()*100);
            var DamageType = MMTTickZero.source(livingEntity.level(), MMTTickZero.TRUEDAMAGE);
            livingEntity.hurt(DamageType, damage);
            //NBT结算
            if((bufferingDamageNumber-damage*100)>0) {
                compoundTag.putInt(damage_buffering_Number_NBT, (int) (bufferingDamageNumber - damage * 100));
            }else{
                compoundTag.putInt(damage_buffering_Number_NBT,0);
            }
        }
    }
}