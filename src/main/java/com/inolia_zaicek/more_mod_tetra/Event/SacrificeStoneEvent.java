package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class SacrificeStoneEvent {
    @SubscribeEvent
    public static void event(BabyEntitySpawnEvent event) {
        if (event.getChild() != null) {
            Random random = new Random();
            if (random.nextInt(100) <= (2)) {
                LivingEntity livingEntity = event.getParentA();
                Level level = livingEntity.level();
                ItemEntity itementity = new ItemEntity(level, livingEntity.getX(),
                        livingEntity.getY(), livingEntity.getZ(),
                        MoreModTetraItemRegister.SacrificeStone.get()
                                .getDefaultInstance());
                itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity);
            }
        }
    }
}