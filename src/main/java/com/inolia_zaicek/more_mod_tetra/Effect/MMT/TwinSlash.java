package com.inolia_zaicek.more_mod_tetra.Effect.MMT;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class TwinSlash {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(twinSlashEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                twinSlashName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(twinSlashTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //挨打
        if (event.getEntity()!=null) {
            LivingEntity player = event.getEntity();
            var mob = event.getEntity();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offhandItem = player.getOffhandItem();
            if (mainHandItem.getItem() instanceof ItemModularHandheld item&&mainHandItem.getItem() instanceof ItemModularHandheld item2) {
                int mainEffectLevel = item.getEffectLevel(mainHandItem, twinSlashEffect);
                int offEffectLevel = item2.getEffectLevel(offhandItem, twinSlashEffect);
                //双等级＞0
                if (mainEffectLevel > 0&&offEffectLevel>0) {
                    int effectLevel = Math.max(1, (Math.min(mainEffectLevel,offEffectLevel)) );
                    float damage = event.getAmount();
                    if (player.hasEffect(MMTEffectsRegister.EclipseStarBurstStream.get())) {
                        int buffLevel = mob.getEffect(MMTEffectsRegister.EclipseStarBurstStream.get()).getAmplifier();
                        event.setAmount(damage * (1 - (float) (effectLevel * buffLevel) / 100));
                    } else if (player.hasEffect(MMTEffectsRegister.Eclipse.get())) {
                        int buffLevel = mob.getEffect(MMTEffectsRegister.Eclipse.get()).getAmplifier();
                        event.setAmount(damage * (1 - (float) (effectLevel * buffLevel) / 100));
                    } else if (player.hasEffect(MMTEffectsRegister.StarBurstStream.get())) {
                        int buffLevel = mob.getEffect(MMTEffectsRegister.StarBurstStream.get()).getAmplifier();
                        event.setAmount(damage * (1 - (float) (effectLevel * buffLevel) / 100));
                    }
                }
            }
        }
    }
    //如果两个buff都没有

    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //星爆气流斩
        if (livingEntity.hasEffect(MMTEffectsRegister.StarBurstStream.get()) &&
                !(
                        livingEntity.getMainHandItem().getItem() instanceof ItemModularHandheld item1 &&
                                livingEntity.getOffhandItem().getItem() instanceof ItemModularHandheld item2 &&
                                item1.getEffectLevel(livingEntity.getMainHandItem(), starBurstStreamEffect) > 0 &&
                                item2.getEffectLevel(livingEntity.getOffhandItem(), starBurstStreamEffect) > 0
                )) {
            livingEntity.removeEffect(MMTEffectsRegister.StarBurstStream.get());
        }
        //日蚀
        if (livingEntity.hasEffect(MMTEffectsRegister.Eclipse.get()) &&
                !(
                        livingEntity.getMainHandItem().getItem() instanceof ItemModularHandheld item1 &&
                                livingEntity.getOffhandItem().getItem() instanceof ItemModularHandheld item2 &&
                                item1.getEffectLevel(livingEntity.getMainHandItem(), eclipseEffect) > 0 &&
                                item2.getEffectLevel(livingEntity.getOffhandItem(), eclipseEffect) > 0
                )) {
            livingEntity.removeEffect(MMTEffectsRegister.Eclipse.get());
        }
        if (livingEntity.hasEffect(MMTEffectsRegister.EclipseStarBurstStream.get()) &&
                !(
                        livingEntity.getMainHandItem().getItem() instanceof ItemModularHandheld item1 &&
                                livingEntity.getOffhandItem().getItem() instanceof ItemModularHandheld item2 &&
                                item1.getEffectLevel(livingEntity.getMainHandItem(), eclipseStarBurstStreamEffect) > 0 &&
                                item2.getEffectLevel(livingEntity.getOffhandItem(), eclipseStarBurstStreamEffect) > 0
                )) {
            livingEntity.removeEffect(MMTEffectsRegister.EclipseStarBurstStream.get());
        }
    }
}
