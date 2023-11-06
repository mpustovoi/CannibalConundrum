package net.clockwork.cannibal.networking.S2C;

import net.clockwork.cannibal.client.ClientData;
import net.clockwork.cannibal.networking.Packet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class DisableMovementS2CPacket extends Packet {
    private final boolean enabled;

    public DisableMovementS2CPacket(boolean enabled) {
        this.enabled = enabled;
    }

    public DisableMovementS2CPacket(FriendlyByteBuf buf) {
        this.enabled = buf.readBoolean();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.enabled);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ClientData.enabledMovement = this.enabled;
        });
        context.setPacketHandled(true);
    }
}
