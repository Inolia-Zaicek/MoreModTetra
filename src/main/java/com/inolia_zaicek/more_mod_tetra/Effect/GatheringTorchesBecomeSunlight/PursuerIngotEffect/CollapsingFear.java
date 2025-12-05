package com.inolia_zaicek.more_mod_tetra.Effect.GatheringTorchesBecomeSunlight.PursuerIngotEffect;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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

public class CollapsingFear {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(collapsingFearEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                collapsingFearName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(collapsingFearTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (event.getAttacked()!=null&&event.hurtEvent.getSource().getEntity() instanceof LivingEntity mob) {
            LivingEntity livingEntity = event.getAttacked();;
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,dominionEffect);
            if (effectLevel > 0&&mob.hasEffect(MMTEffectsRegister.Dominion.get())) {
                 event.addNormalMulti((1- (float) effectLevel /100));
            }
        }
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getEntity();
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,collapsingFearEffect);
            if (effectLevel > 0) {
                var mobList = MMTUtil.mobList(3,mob);
                float number= (float) effectLevel * 0.15f;
                float numberFinish= (float) effectLevel /100;
                for (Mob mobs:mobList) {
                    if (mobs != null) {
                        //获取伤害类型
                        mobs.invulnerableTime = 0;
                        if(livingEntity instanceof Player player) {
                            mobs.setLastHurtByPlayer(player);
                        }
                        float damage =event.getAmount();
                        mobs.hurt(mobs.damageSources().magic(), damage * number);
                        if(livingEntity instanceof Player player) {
                            mobs.setLastHurtByPlayer(player);
                        }
                        if( mobs.getHealth()<(mobs.getMaxHealth()*numberFinish) && mob.hasEffect(MMTEffectsRegister.Dominion.get())){
                            mobs.invulnerableTime = 0;
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                            mobs.hurt(mobs.damageSources().magic(), mobs.getHealth());
                            if(livingEntity instanceof Player player) {
                                mobs.setLastHurtByPlayer(player);
                            }
                        }
                    }
                }
            }
        }
    }
}
