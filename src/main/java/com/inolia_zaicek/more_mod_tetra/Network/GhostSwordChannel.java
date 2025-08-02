package com.inolia_zaicek.more_mod_tetra.Network;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Network.Packet.GhostSwordPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class GhostSwordChannel {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MoreModTetra.MODID,"ghost_sword"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    public static void init(){
        int packetID=0;
        CHANNEL.registerMessage(
                packetID++,
                GhostSwordPacket.class,
                GhostSwordPacket::encode,
                GhostSwordPacket::decode,
                GhostSwordPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}
