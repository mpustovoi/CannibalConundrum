package net.clockwork.cannibal.networking;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.networking.S2C.DisableMovementS2CPacket;
import net.clockwork.cannibal.networking.S2C.PlayChainsawSoundS2CPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Clockwork.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        registerPacket(PlayChainsawSoundS2CPacket.class, NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(DisableMovementS2CPacket.class, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.with(() -> null), message);
    }

    private static <P extends Packet> void registerPacket(Class<P> msgClass, NetworkDirection direction) {
        INSTANCE.messageBuilder(msgClass, id(), direction)
                .decoder(byteBuf -> {
                    try {
                        return msgClass.getConstructor(FriendlyByteBuf.class).newInstance(byteBuf);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .encoder(Packet::toBytes)
                .consumerMainThread((msg, supplier) -> msg.handle(supplier.get()))
                .add();
    }

}
