// package com.inolia_zaicek.zeroing_story.network.s2c; // 假设的网络包路径

package com.inolia_zaicek.more_mod_tetra.Network.Packet;

import com.inolia_zaicek.more_mod_tetra.Client.MMTFlightHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public record MMTPlayerFlyPacketS2C(int maxFlyTicks, double flySpeed) {

    public MMTPlayerFlyPacketS2C(int maxFlyTicks, double flySpeed) {
        this.maxFlyTicks = maxFlyTicks;
        this.flySpeed = flySpeed;
    }

    public static void encode(MMTPlayerFlyPacketS2C packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.maxFlyTicks);
        friendlyByteBuf.writeDouble(packet.flySpeed);
    }

    public static MMTPlayerFlyPacketS2C decode(FriendlyByteBuf friendlyByteBuf) {
        return new MMTPlayerFlyPacketS2C(friendlyByteBuf.readInt(), friendlyByteBuf.readDouble());
    }

    public static void handle(MMTPlayerFlyPacketS2C packet, Supplier<NetworkEvent.Context> ctx) {
        ((NetworkEvent.Context)ctx.get()).enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () ->
                        () -> MMTFlightHandler.handleFlyPacket(packet, ctx)
                )
        );
        ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
    }

    @Override
    public int maxFlyTicks() {
        return this.maxFlyTicks;
    }

    @Override
    public double flySpeed() {
        return this.flySpeed;
    }
}
