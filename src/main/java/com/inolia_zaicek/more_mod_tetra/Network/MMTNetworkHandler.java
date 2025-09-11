// package com.inolia_zaicek.zeroing_story.network;

package com.inolia_zaicek.more_mod_tetra.Network;

import com.inolia_zaicek.more_mod_tetra.MoreModTetra;
import com.inolia_zaicek.more_mod_tetra.Network.Packet.MMTPlayerFlyPacketS2C;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class MMTNetworkHandler {
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MoreModTetra.MODID, "mmt_fly"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int id = 0;

    public static void init() {
        CHANNEL.<MMTPlayerFlyPacketS2C>registerMessage(
                id++,
                MMTPlayerFlyPacketS2C.class,
                MMTPlayerFlyPacketS2C::encode,
                MMTPlayerFlyPacketS2C::decode,
                MMTPlayerFlyPacketS2C::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );
    }
}
