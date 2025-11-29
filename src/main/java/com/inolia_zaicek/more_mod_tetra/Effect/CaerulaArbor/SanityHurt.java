package com.inolia_zaicek.more_mod_tetra.Effect.CaerulaArbor;

import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.LivingEntity;
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

public class SanityHurt {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(sanityHurtEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                sanityHurtName, 0, 20 , false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(sanityHurtTooltip, statGetter)
        );
        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("caerula_arbor")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity &&event.getEntity()!=null) {
                float effectLevel = MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity,sanityHurtEffect);
                if (effectLevel > 0&&event.getEntity()!=null) {
                    var mob = event.getEntity();
                    var map = mob.getActiveEffectsMap();
                    //通过id直接给buff
                    //map.put(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("caerula_arbor", "instant_sanity"))),new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("caerula_arbor", "instant_sanity"))), 20, effectLevel - 1));
                    //mob.addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("caerula_arbor", "instant_sanity"))), 1, effectLevel - 1));
                    //CauseSanityProcedure.execute(mob.level(), mob.getX(), mob.getY(), mob.getZ(), mob);
                    DeductPlayerSanityProcedure.execute(mob, 120*effectLevel);
                }
            }
        }
    }
}
