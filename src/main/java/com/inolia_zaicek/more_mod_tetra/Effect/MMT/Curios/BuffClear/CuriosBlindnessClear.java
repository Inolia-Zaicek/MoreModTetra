package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.BuffClear;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.StatsHelper;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterInteger;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class CuriosBlindnessClear {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        var statGetter = new StatGetterEffectLevel(curiosBlindnessClearEffect, 1);
        GuiStatBar statBar = new GuiStatBar(0, 0, StatsHelper.barLength,
                curiosBlindnessClearName, 0, 1, false, false, false,
                statGetter, LabelGetterBasic.integerLabel,
                new TooltipGetterInteger(curiosBlindnessClearTooltip, statGetter)
        );

        WorkbenchStatsGui.addBar(statBar);
        HoloStatsGui.addBar(statBar);
    }
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (ModList.get().isLoaded("curios")) {
            CuriosApi.getCuriosInventory(livingEntity).ifPresent(inv -> inv.findCurios
                    (itemStack -> itemStack.getItem() instanceof IModularItem).forEach(
                    slotResult -> {
                        slotResult.stack();
                        ItemStack itemStack = slotResult.stack();
                        IModularItem item = (IModularItem) itemStack.getItem();
                        int effectLevel = item.getEffectLevel(itemStack, curiosBlindnessClearEffect);
                        if (effectLevel > 0 && livingEntity.hasEffect(MobEffects.BLINDNESS)) {
                            livingEntity.removeEffect(MobEffects.BLINDNESS);
                        }
                    }
            ));
        }
        /// 莱特兰——清除
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_curse_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_curse_clear_Effect) )
                && ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "curse")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "curse"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_flame_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_flame_clear_Effect) )
                && ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "flame")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "flame"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, l2complements_frozen_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_l2complements_frozen_clear_Effect) )
                && ModList.get().isLoaded("l2complements") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "frozen")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("l2complements", "frozen"))));
        }
        /// 诡厄
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_cursed_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_cursed_clear_Effect) )
                && ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "cursed"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_burn_hex_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_burn_hex_clear_Effect) )
                && ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "burn_hex")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "burn_hex"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_sapped_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_sapped_clear_Effect) )
                && ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "sapped"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, goety_freezing_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_goety_freezing_clear_Effect) )
                && ModList.get().isLoaded("goety") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("goety", "freezing"))));
        }
        /// A C
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, alexscaves_magnetizing_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexscaves_magnetizing_clear_Effect) )
                && ModList.get().isLoaded("alexscaves") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "magnetizing")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "magnetizing"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, alexscaves_irradiated_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_alexscaves_irradiated_clear_Effect) )
                && ModList.get().isLoaded("alexscaves") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "irradiated")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("alexscaves", "irradiated"))));
        }
        //天境
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, aether_inebriation_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_aether_inebriation_clear_Effect) )
                && ModList.get().isLoaded("aether") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("aether", "inebriation")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("aether", "inebriation"))));
        }
        //灾变
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, cataclysm_blazing_brand_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_cataclysm_blazing_brand_clear_Effect) )
                && ModList.get().isLoaded("cataclysm") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "blazing_brand"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, cataclysm_curse_of_desert_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_cataclysm_curse_of_desert_clear_Effect) )
                && ModList.get().isLoaded("cataclysm") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "curse_of_desert")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("cataclysm", "curse_of_desert"))));
        }
        //生于混沌
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_bone_fracture_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_bone_fracture_clear_Effect) )
                && ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "bone_fracture")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "bone_fracture"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_intoxicating_decoction_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_intoxicating_decoction_clear_Effect) )
                && ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "intoxicating_decoction")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "intoxicating_decoction"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_magic_depletion_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_magic_depletion_clear_Effect) )
                && ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "magic_depletion")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "magic_depletion"))));
        }
        if ( (MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, born_in_chaos_v1_myiasis_clear_Effect) || MMTCuriosHelper.getInstance().hasCuriosEffectLevel(livingEntity, curios_born_in_chaos_v1_myiasis_clear_Effect) )
                && ModList.get().isLoaded("born_in_chaos_v1") && livingEntity.hasEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "myiasis")))) ) {
            livingEntity.removeEffect(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("born_in_chaos_v1", "myiasis"))));
        }
    }
}