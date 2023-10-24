package net.clockwork.cannibal.networking.S2C;

import net.clockwork.cannibal.level.sounds.custom.TickableChainsawSound;
import net.clockwork.cannibal.networking.Packet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class PlayTickableSoundS2CPacket extends Packet {

    private final ResourceLocation soundEvent;
    private final UUID uuid;
    private final boolean loop;

    public PlayTickableSoundS2CPacket(SoundEvent event, Player player, boolean loop) {
        this.soundEvent = event.getLocation();
        this.uuid = player.getUUID();
        this.loop = loop;
    }

    public PlayTickableSoundS2CPacket(FriendlyByteBuf buf) {
        this.soundEvent = buf.readResourceLocation();
        this.uuid = buf.readUUID();
        this.loop = buf.readBoolean();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.soundEvent);
        buf.writeUUID(this.uuid);
        buf.writeBoolean(this.loop);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            Player player = level.getPlayerByUUID(uuid);
            SoundEvent event = ForgeRegistries.SOUND_EVENTS.getValue(this.soundEvent);
            if (event == null || player == null) return;
            Minecraft.getInstance().getSoundManager().queueTickingSound(new TickableChainsawSound(event, player, this.loop));
        });
        context.setPacketHandled(true);
    }

}
