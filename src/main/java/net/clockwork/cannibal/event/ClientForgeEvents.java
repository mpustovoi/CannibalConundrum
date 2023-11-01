package net.clockwork.cannibal.event;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.client.ClientData;
import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.level.block.entity.client.renderer.BoneTrapRenderer;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.entity.client.renderer.CannibalButcherRenderer;
import net.clockwork.cannibal.level.entity.client.renderer.CannibalRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Clockwork.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void clientLeave(ClientPlayerNetworkEvent.LoggingOut event) {
        ClientData.reset();
    }

}
