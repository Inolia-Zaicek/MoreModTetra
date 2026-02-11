package com.inolia_zaicek.more_mod_tetra.Event.IngotDrop;

import com.freefish.torchesbecomesunlight.server.entity.dlc.GunKnightPatriot;
import com.freefish.torchesbecomesunlight.server.entity.dlc.PathfinderBallistarius;
import com.freefish.torchesbecomesunlight.server.entity.guerrillas.shield.Patriot;
import com.freefish.torchesbecomesunlight.server.entity.guerrillas.shield.ShieldGuard;
import com.freefish.torchesbecomesunlight.server.entity.guerrillas.snowmonster.FrostNova;
import com.freefish.torchesbecomesunlight.server.entity.guerrillas.snowmonster.YetiIcecleaver;
import com.freefish.torchesbecomesunlight.server.entity.rhodesIsland.rosmontis.Rosmontis;
import com.freefish.torchesbecomesunlight.server.entity.ursus.Pursuer;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class MMTIngotDrop {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void drop(LivingDropsEvent event) {
        if (ModList.get().isLoaded("torchesbecomesunlight")) {
            if (event.getEntity() instanceof Rosmontis rosmontis) {
                Level level = rosmontis.level();
                for (int i = 0; i < 4; i++) {
                        ItemEntity itementity = new ItemEntity(level, rosmontis.getX(),
                                rosmontis.getY(), rosmontis.getZ(),
                                MoreModTetraItemRegister.AttainmentPoint.get()
                                        .getDefaultInstance());
                        itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                        level.addFreshEntity(itementity);
                }
                ItemEntity itementity1 = new ItemEntity(level, rosmontis.getX(),
                        rosmontis.getY(), rosmontis.getZ(),
                        MoreModTetraItemRegister.ProofOfVictoryRosmontis.get()
                                .getDefaultInstance());
                itementity1.setDeltaMovement(itementity1.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity1);
            }
            if (event.getEntity() instanceof FrostNova frostNova) {
                Level level = frostNova.level();
                for (int i = 0; i < 4; i++) {
                    ItemEntity itementity = new ItemEntity(level, frostNova.getX(),
                            frostNova.getY(), frostNova.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
                ItemEntity itementity1 = new ItemEntity(level, frostNova.getX(),
                        frostNova.getY(), frostNova.getZ(),
                        MoreModTetraItemRegister.ProofOfVictoryFrostNova.get()
                                .getDefaultInstance());
                itementity1.setDeltaMovement(itementity1.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity1);
            }
            if (event.getEntity() instanceof GunKnightPatriot boss) {
                Level level = boss.level();
                for (int i = 0; i < 4; i++) {
                    ItemEntity itementity = new ItemEntity(level, boss.getX(),
                            boss.getY(), boss.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
                ItemEntity itementity1 = new ItemEntity(level, boss.getX(),
                        boss.getY(), boss.getZ(),
                        MoreModTetraItemRegister.ProofOfVictoryGunKnightPatriot.get()
                                .getDefaultInstance());
                itementity1.setDeltaMovement(itementity1.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity1);
            }
            if (event.getEntity() instanceof Patriot boss) {
                Level level = boss.level();
                for (int i = 0; i < 4; i++) {
                    ItemEntity itementity = new ItemEntity(level, boss.getX(),
                            boss.getY(), boss.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
                ItemEntity itementity1 = new ItemEntity(level, boss.getX(),
                        boss.getY(), boss.getZ(),
                        MoreModTetraItemRegister.ProofOfVictoryPatriot.get()
                                .getDefaultInstance());
                itementity1.setDeltaMovement(itementity1.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity1);
            }
            if (event.getEntity() instanceof Pursuer boss) {
                Level level = boss.level();
                for (int i = 0; i < 4; i++) {
                    ItemEntity itementity = new ItemEntity(level, boss.getX(),
                            boss.getY(), boss.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
                ItemEntity itementity1 = new ItemEntity(level, boss.getX(),
                        boss.getY(), boss.getZ(),
                        MoreModTetraItemRegister.ProofOfVictoryPursuer.get()
                                .getDefaultInstance());
                itementity1.setDeltaMovement(itementity1.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity1);
            }
            if (event.getEntity() instanceof YetiIcecleaver mob) {
                Level level = mob.level();
                for (int i = 0; i < 2; i++) {
                    ItemEntity itementity = new ItemEntity(level, mob.getX(),
                            mob.getY(), mob.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
            }
            if (event.getEntity() instanceof PathfinderBallistarius mob) {
                Level level = mob.level();
                for (int i = 0; i < 4; i++) {
                    ItemEntity itementity = new ItemEntity(level, mob.getX(),
                            mob.getY(), mob.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
            }
            if (event.getEntity() instanceof ShieldGuard mob) {
                Level level = mob.level();
                for (int i = 0; i < 2; i++) {
                    ItemEntity itementity = new ItemEntity(level, mob.getX(),
                            mob.getY(), mob.getZ(),
                            MoreModTetraItemRegister.AttainmentPoint.get()
                                    .getDefaultInstance());
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                            (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                    level.addFreshEntity(itementity);
                }
            }
        }
    }
}
