package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTDamageSourceHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
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
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class Eclipse {
    public static final InteractionHand INTERACTION_HAND = InteractionHand.OFF_HAND;

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(eclipseEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                eclipseName, 0, 27, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(eclipseTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity player) {
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
                int effectLevel = 0;
                if (mainHandItem.getItem() instanceof ItemModularHandheld item&&mainHandItem.getItem() instanceof ItemModularHandheld item2) {
                    int mainEffectLevel = item.getEffectLevel(mainHandItem, eclipseEffect);
                    int offEffectLevel = item2.getEffectLevel(offHandItem, eclipseEffect);
                    //双等级＞0，近战
                    if (mainEffectLevel > 0&&offEffectLevel>0&&MMTDamageSourceHelper.isMeleeAttack(event.getSource())) {
                        //上buff部分
                        if (!player.hasEffect(MMTEffectsRegister.Eclipse.get())) {
                            player.addEffect(new MobEffectInstance(MMTEffectsRegister.Eclipse.get(), 200, 0));
                        } else {
                            int buffLevel = player.getEffect(MMTEffectsRegister.Eclipse.get()).getAmplifier();
                            player.addEffect(new MobEffectInstance(MMTEffectsRegister.Eclipse.get(), 200, Math.min(26, buffLevel + 1)));
                            //根据星爆气流斩缩减无敌帧
                            var time =Math.max(0,(mob.invulnerableTime)*(1-buffLevel/50) );
                            mob.invulnerableTime=time;
                        }
                    }
            }
        }
    }
}
