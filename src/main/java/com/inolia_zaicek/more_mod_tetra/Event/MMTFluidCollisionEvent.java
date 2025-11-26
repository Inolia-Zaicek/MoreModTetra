package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
@Cancelable
public class MMTFluidCollisionEvent extends LivingEvent {
    private final FluidState fluid;

    public MMTFluidCollisionEvent(LivingEntity entity, FluidState fluid) {
        super(entity);
        this.fluid = fluid;
    }

    public FluidState getFluid() {
        return this.fluid;
    }
}
