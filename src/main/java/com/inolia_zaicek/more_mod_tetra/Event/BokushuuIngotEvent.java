package com.inolia_zaicek.more_mod_tetra.Event;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Register.MoreModTetraItemRegister;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = MoreModTetra.MODID)
public class BokushuuIngotEvent {
    @SubscribeEvent
    public static void event(PlayerInteractEvent.EntityInteractSpecific event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        //末地水晶
        if (target instanceof EndCrystal) {
            ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            ItemStack offHandStack = player.getItemInHand(InteractionHand.OFF_HAND);
            // 检查物品是否是海晶砂粒 (Prismarine Crystals) 并且数量大于等于 16
            if (offHandStack.is(Items.PRISMARINE_CRYSTALS) && offHandStack.getCount() >= 16
            &&mainHandStack.is(Items.NETHERITE_INGOT) && mainHandStack.getCount() >= 1) {
                offHandStack.shrink(16); // 消耗物品
                mainHandStack.shrink(1); // 消耗物品
                //给物品
                Level level = player.level();
                ItemEntity itementity = new ItemEntity(level, player.getX(),
                        player.getY(), player.getZ(),
                        MoreModTetraItemRegister.BokushuuIngot.get()
                                .getDefaultInstance());
                itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                        (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                level.addFreshEntity(itementity);
                event.setCanceled(true); // 阻止默认交互
            }
        }
    }
}