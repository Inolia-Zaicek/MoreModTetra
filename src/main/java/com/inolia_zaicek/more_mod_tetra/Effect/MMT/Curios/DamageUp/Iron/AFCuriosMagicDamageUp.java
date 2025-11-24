package com.inolia_zaicek.more_mod_tetra.Effect.MMT.Curios.DamageUp.Iron;

import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import net.alshanex.alshanex_familiars.datagen.AFDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import se.mickelus.tetra.items.modular.IModularItem;
import top.theillusivec4.curios.api.CuriosApi;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.curiosMagicDamageUpEffect;

public class AFCuriosMagicDamageUp {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (event.getSource().getEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(AFDamageTypes.SOUND_MAGIC)) {
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        event.setAmount(finish);
                    }
                }
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity player) {
                float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(player, curiosMagicDamageUpEffect);
                if (effectLevel > 0) {
                    if (event.getSource().is(AFDamageTypes.SOUND_MAGIC)) {
                        float finish = event.getAmount() * (1 + effectLevel / 100);
                        event.setAmount(finish);
                    }
                }
            }
        }
    }
}
