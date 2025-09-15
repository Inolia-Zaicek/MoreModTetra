package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.*;

public class IronCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.getSource().getEntity() instanceof Player player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                                    if (effectLevel > 0) {
                                        if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                                        ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                                            float finish =event.getAmount()*(1+effectLevel/100);
                                            event.setAmount(finish);
                                        }
                                    }
            }else if (event.getSource().getDirectEntity() instanceof Player player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                            ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)) {
                        float finish =event.getAmount()*(1+effectLevel/100);
                        event.setAmount(finish);
                    }
                }
            }
        }
    }
}
