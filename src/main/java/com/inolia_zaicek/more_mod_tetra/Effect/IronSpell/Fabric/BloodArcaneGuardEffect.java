package com.inolia_zaicek.more_mod_tetra.Effect.IronSpell.Fabric;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class BloodArcaneGuardEffect {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(bloodArcaneGuardEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                bloodArcaneGuardName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(bloodArcaneGuardTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }


    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        LivingEntity target = event.getAttacked();
        //基础攻击伤害量
        float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(target, bloodArcaneGuardEffect);
        if (effectLevel > 0) {
            //获取法抗属性
            float magic = (float) target.getAttributeValue(AttributeRegistry.BLOOD_MAGIC_RESIST.get());
            float resist = (float) target.getAttributeValue(AttributeRegistry.SPELL_RESIST.get());
            event.addNormalMulti((1 - (magic+resist-2) * (effectLevel / 100)  ));
        }
    }
}
