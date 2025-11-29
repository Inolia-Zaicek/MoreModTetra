package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Random;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class FieryPalpitation {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(fieryPalpitationEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                fieryPalpitationName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(fieryPalpitationTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity player = event.getEntity();
        int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,fieryPalpitationEffect);
        if (effectLevel > 0 && player.level().getGameTime() % 40L == 0) {
            var mobList = MMTUtil.mobList(6, player);
            float number = (float) effectLevel / 100;
            for (LivingEntity mobs : mobList) {
                //getOwnerUUID防止主人不在线
                //随从判断
                if (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null) {
                    mobs.heal(mobs.getMaxHealth() * number);
                }
            }
            var playerList = MMTUtil.PlayerList(6, player);
            for (Player players : playerList) {
                players.heal(players.getMaxHealth() * number);
            }
        }
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof Player player) {
            int effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(player,fieryPalpitationEffect);
            if (effectLevel > 0) {
                var mobList = MMTUtil.mobList(6, player);
                Random random = new Random();
                if (random.nextInt(100)<effectLevel) {
                    for (LivingEntity mobs : mobList) {
                        //getOwnerUUID防止主人不在线
                        if (mobs instanceof OwnableEntity ownableEntity && ownableEntity.getOwnerUUID() != null) {
                            mobs.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
                        }
                    }
                    var playerList = MMTUtil.PlayerList(6, player);
                    for (Player players : playerList) {
                        if (players != player) {
                            players.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
                        }
                    }
                }
            }
        }
    }
}