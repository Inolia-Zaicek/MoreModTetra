package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class    KillerAuraEffectTrait {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(killerAuraEffectTraitEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                killerAuraEffectTraitName, 0, 3, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(killerAuraEffectTraitTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        ;
        if (ModList.get().isLoaded("l2complements")) {
            float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, killerAuraEffectTraitEffect)
                    + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, killerAuraEffectTraitEffect);
            if (effectLevel > 0 && livingEntity.tickCount % 20 == 0) {
                var mobList = MMTUtil.mobList(7, livingEntity);
                for (Mob mobs : mobList) {
                    if (mobs != null) {
                        //获取伤害类型
                        mobs.invulnerableTime = 0;
                        if (livingEntity instanceof Player player) {
                            mobs.setLastHurtByPlayer(player);
                        }
                        float atk = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
                        mobs.hurt(mobs.damageSources().mobAttack(livingEntity), atk * effectLevel * 0.15f);
                        if (livingEntity instanceof Player player) {
                            mobs.setLastHurtByPlayer(player);
                        }
                    }
                }
            }
        }
    }
}
