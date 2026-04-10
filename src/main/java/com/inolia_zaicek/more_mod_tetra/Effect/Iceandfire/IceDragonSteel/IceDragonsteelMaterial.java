package com.inolia_zaicek.more_mod_tetra.Effect.Iceandfire.IceDragonSteel;

import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class IceDragonsteelMaterial {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(iceDragonsteelMaterialEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                iceDragonsteelMaterialName, 0, 100, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(iceDragonsteelMaterialTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(EffectLevelEvent event) {
        if (ModList.get().isLoaded("iceandfire")) {
            if (event.hurtEvent.getSource().getEntity() instanceof LivingEntity livingEntity) {
                var mob = event.getAttacked();
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, iceDragonsteelMaterialEffect);
                if (effectLevel > 0) {
                    float number = (float) effectLevel / 100;
                    float numberA = (float) effectLevel / 200;
                    if (mob instanceof EntityFireDragon || mob instanceof EntityLightningDragon) {
                        event.addNormalMulti((number));
                    }
                    if (mob instanceof EntityIceDragon) {
                        event.addNormalMulti((numberA));
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            var mob = event.getEntity();
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, iceDragonsteelMaterialEffect);
            if (effectLevel > 0) {
                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
            }
        }
        //挨打
        if (event.getEntity() != null && !(event.getSource().getEntity() instanceof Player)) {
            LivingEntity livingEntity = event.getEntity();
            float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, iceDragonsteelMaterialEffect);
            if (effectLevel > 0 && livingEntity.getLastAttacker() != null) {
                float number = (float) effectLevel / 100;
                float numberA = (float) effectLevel / 200;
                //冰龙
                if (livingEntity.getLastAttacker() instanceof EntityFireDragon || livingEntity.getLastAttacker() instanceof EntityLightningDragon) {
                    event.setAmount(event.getAmount() * (1 - number));
                }
                if (livingEntity.getLastAttacker() instanceof EntityIceDragon) {
                    event.setAmount(event.getAmount() * (1 - numberA));
                }
            }
        }
    }
}
