package com.inolia_zaicek.more_mod_tetra.Effect.L2hostility.Trait;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MMTEffectsRegister;
import com.inolia_zaicek.more_mod_tetra.Util.MMTCuriosHelper;
import com.inolia_zaicek.more_mod_tetra.Util.MMTEffectHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.inolia_zaicek.more_mod_tetra.Effect.EffectGuiStats.repellingEffectTraitEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class RepellingEffectTrait {
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        ;
        float effectLevel = MMTCuriosHelper.getInstance().getCuriosEffectLevel(livingEntity, repellingEffectTraitEffect)
                + MMTEffectHelper.getInstance().getMainOffHandMaxEffectLevel(livingEntity, repellingEffectTraitEffect);
        if (effectLevel > 0 && livingEntity.tickCount % 10 == 0) {
            livingEntity.addEffect(new MobEffectInstance(MMTEffectsRegister.L2Repelling.get(), 100, (int) (effectLevel - 1), true, true, true));
        }
    }
}
