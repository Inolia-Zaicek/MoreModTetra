package com.inolia_zaicek.more_mod_tetra.Network.Packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

import java.util.function.Supplier;

public class TerraRayPacket {
    public static boolean handle(TerraRayPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        if(ModList.get().isLoaded("botania")) {
            //获取上下文
            var context = ctxSupplier.get();
            context.enqueueWork(() -> {
                //要做什么
                var player = context.getSender();

                ManaBurstEntity manaBurstEntity = TerraBladeItem.getBurst(player, new ItemStack(BotaniaItems.terraSword));
                player.level().addFreshEntity(manaBurstEntity);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1, 1);
            });
            context.setPacketHandled(true);
        }
            return true;
        }
        public static void encode (TerraRayPacket packet, FriendlyByteBuf buf){

        }
        public static TerraRayPacket decode (FriendlyByteBuf buf){
            if(ModList.get().isLoaded("botania")) {
                return new TerraRayPacket();
            }
            return null;
        }

}