package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Fabric;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
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

public class EnderArcaneGuardEffect {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(enderArcaneGuardEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                enderArcaneGuardName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(enderArcaneGuardTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        //基础攻击伤害量
        float baseAmount = event.getAmount();
        ItemStack mainHandItem = target.getMainHandItem();
        ItemStack offhandItem = target.getOffhandItem();
        if (mainHandItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(mainHandItem, enderArcaneGuardEffect);
            if (level > 0) {
                //获取法强属性
                float magic = (float) target.getAttributeValue(AttributeRegistry.ENDER_MAGIC_RESIST.get());
                float resist = (float) target.getAttributeValue(AttributeRegistry.SPELL_RESIST.get());
                //结算
                float finish = 1-Math.min(0.5f,magic+resist-2);
                event.setAmount(baseAmount*finish);
            }
        }else if (offhandItem.getItem() instanceof IModularItem item) {
            float level = item.getEffectLevel(offhandItem, enderArcaneGuardEffect);
            if (level > 0) {
                //获取法强属性
                float magic = (float) target.getAttributeValue(AttributeRegistry.ENDER_MAGIC_RESIST.get());
                float resist = (float) target.getAttributeValue(AttributeRegistry.SPELL_RESIST.get());
                //结算
                float finish = 1-Math.min(0.5f,magic+resist-2);
                event.setAmount(baseAmount*finish);
            }
        }
    }
}
