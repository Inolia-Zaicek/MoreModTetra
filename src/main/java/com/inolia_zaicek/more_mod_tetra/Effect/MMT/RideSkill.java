package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.entity.LivingEntity;
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

public class RideSkill {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            //攻击
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                if (effectLevel > 0&&hp==mhp) {
                    float number = (float) effectLevel / 100;
                    event.setAmount(event.getAmount()*(1+number));
                    }
                } else if (event.getSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getEntity();
                int effectLevel = MMTEffectHelper.getInstance().getMainOffHandSumEffectLevel(livingEntity,assassinateEffect);
                float hp =mob.getHealth();
                float mhp =mob.getMaxHealth();
                //实体骑乘的东西非空
                if (effectLevel > 0&&hp==mhp&&livingEntity.getVehicle()!=null) {
                    float number = (float) effectLevel / 100;
                    event.setAmount(event.getAmount()*(1+number));
                }
            }
            }
        }

