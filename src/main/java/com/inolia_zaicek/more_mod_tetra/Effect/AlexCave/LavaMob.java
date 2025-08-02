package com.inolia_zaicek.more_mod_tetra.Effect.AlexCave;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import com.inolia_zaicek.more_mod_tetra.Damage.TickZero;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
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
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class LavaMob {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(lavaMobEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                lavaMobName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(lavaMobTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        Entity attackingEntity = event.getSource().getEntity();
        // 获取触发攻击的实体（攻击者）。event.getSource() 返回攻击来源，.getEntity() 获取来源的实体对象。

        LivingEntity target = event.getEntity();
        // 获取被攻击的生物实体（目标）。event.getEntity() 返回事件中的目标实体。

        if (attackingEntity instanceof LivingEntity attacker) {
            // 检查 attacker 是否是一个 LivingEntity。如果不是（例如箭矢射出的是一个 Entity，而不是 LivingEntity），则不处理。
            ItemStack heldStack = attacker.getMainHandItem();
            if (heldStack.getItem() instanceof ModularItem item) {
                float level = item.getEffectLevel(heldStack, lavaMobEffect);
                if (level > 0) {
                    //基础攻击伤害量
                    float baseAmount = event.getAmount();
                    //额外法伤+基础伤害
                    float magicBonusDamage = baseAmount+getDecimalPercentage(level, baseAmount);
                    //结算
                    float finish =magicBonusDamage*(1+level/100);
                    //获取怪物之前的无敌时间
                    int time = target.invulnerableTime;
                    target.invulnerableTime=0;
                    //获取伤害类型
                    var FIRE_MAGIC_DAMAGE = TickZero.source(attacker.level(), DamageTypes.ON_FIRE);
                    target.hurt(FIRE_MAGIC_DAMAGE, finish);
                    if(attacker instanceof Player player) {
                        target.setLastHurtByPlayer(player);
                    }else{
                        target.setLastHurtByMob(target);
                    }
                    // target.damageSources().magic() 创建一个表示魔法伤害来源的 DamageSource。
                    event.setAmount(0);
                    target.invulnerableTime=time;
                }
            }
        }
    }
}