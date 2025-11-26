package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
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

public class DiamondGuard {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(diamond_guard_Effect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                diamond_guard_Name, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(diamond_guard_Tooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            //挨打的是玩家
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
                float effectLevel = MMTEffectHelper.getInstance().getAllEffectLevel(player, cursium_legs_Effect);
                if (effectLevel > 0) {
                    float mhp =player.getMaxHealth();
                    float damage =event.getAmount();
                    //最大扣血：最大生命值*（100%-比例)
                    float maxAmount=( mhp*(1- effectLevel /100) );
                    if(damage>maxAmount){
                        event.setAmount(maxAmount);
                    }
            }
        }
    }
}
