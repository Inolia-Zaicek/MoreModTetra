package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.Event.Post.EffectLevelEvent;
import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class MMTDamageCalculate {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        //攻击
        LivingEntity livingEntity;
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            livingEntity = entity;
        } else if (event.getSource().getDirectEntity() instanceof LivingEntity entity) {
            livingEntity = entity;
        } else return;
        var mob = event.getEntity();
        EffectLevelEvent effectLevel = new EffectLevelEvent(livingEntity, mob, event);
        MinecraftForge.EVENT_BUS.post(effectLevel);
        Event.Result result = effectLevel.getResult();

        if (Objects.requireNonNull(result) == Event.Result.DENY) {
            event.setAmount(0);
        } else {
            float fixedDamage = effectLevel.getFixedDamage();
            List<Float> independentMulti = effectLevel.getIndependentMulti();
            float normalMulti = effectLevel.getNormalMulti();
            float originalDamage = event.getAmount();
            float newDamage = (originalDamage + fixedDamage) * (normalMulti + 1.0f);
            for (Float multi : independentMulti) {
                newDamage *= multi;
            }
            event.setAmount(Math.max(newDamage, 0));
        }
    }

}
