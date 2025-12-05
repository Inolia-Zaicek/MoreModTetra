package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Metal;

import com.inolia_zaicek.more_mod_tetra.Damage.MMTTickZero;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class NatureArcaneEdgeEffect {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(natureArcaneEdgeEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                natureArcaneEdgeName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(natureArcaneEdgeTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        // 声明了一个公共方法 onLivingAttackEvent，并使用 @SubscribeEvent 注解标记。
        // 这表示这个方法是一个事件监听器，会响应 LivingAttackEvent 事件。

        Entity attackingEntity = event.getSource().getEntity();
        // 获取触发攻击的实体（攻击者）。event.getSource() 返回攻击来源，.getEntity() 获取来源的实体对象。

        LivingEntity target = event.getEntity();
        // 获取被攻击的生物实体（目标）。event.getEntity() 返回事件中的目标实体。

        if (attackingEntity instanceof LivingEntity attacker) {
            // 检查 attacker 是否是一个 LivingEntity。如果不是（例如箭矢射出的是一个 Entity，而不是 LivingEntity），则不处理。
            ItemStack heldStack = attacker.getMainHandItem();
            // 获取攻击者主手持有的物品。
            if (heldStack.getItem() instanceof IModularItem item) {
                // 检查主手物品是否是 IModularItem 的实例。
                // 如果是，则将其转换为 IModularItem 类型，并赋值给变量 item。
                //获取词条数值
                float level = item.getEffectLevel(heldStack, natureArcaneEdgeEffect);
                // 只有当 "Esoteric Edge" 效果等级大于 0 时才执行以下操作。
                if (level > 0&& MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                    //基础攻击伤害量
                    float baseAmount = event.getAmount();
                    //额外法伤+基础伤害
                    float magicBonusDamage = baseAmount+getDecimalPercentage(level, baseAmount);
                    //获取法强属性
                    float fire = (float) attacker.getAttributeValue(AttributeRegistry.NATURE_SPELL_POWER.get());
                    float power = (float) attacker.getAttributeValue(AttributeRegistry.SPELL_POWER.get());
                    //结算
                    float finish =fire*power*magicBonusDamage;
                    //获取怪物之前的无敌时间
                    int time = target.invulnerableTime;
                    target.invulnerableTime=0;
                    //获取伤害类型
                    var MAGIC_DAMAGE = MMTTickZero.hasSource(attacker.level(), ISSDamageTypes.NATURE_MAGIC,attacker);
                    target.hurt(MAGIC_DAMAGE, finish);
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
