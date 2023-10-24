package net.clockwork.cannibal.util;

import net.clockwork.cannibal.networking.ModMessages;
import net.clockwork.cannibal.networking.S2C.DisableMovementS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class MovementUtil {

    public static void enableMovement(Player player, boolean enabled) {
        if (player instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new DisableMovementS2CPacket(enabled), serverPlayer);
        }
    }

}
