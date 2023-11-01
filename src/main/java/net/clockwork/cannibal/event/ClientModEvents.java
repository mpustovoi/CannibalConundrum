package net.clockwork.cannibal.event;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.client.ClientData;
import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.level.block.entity.client.renderer.BoneTrapRenderer;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.entity.client.renderer.CannibalButcherRenderer;
import net.clockwork.cannibal.level.entity.client.renderer.CannibalRenderer;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Clockwork.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.CANNIBAL.get(), CannibalRenderer::new);
        event.registerEntityRenderer(ModEntity.CANNIBAL_BUTCHER.get(), CannibalButcherRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BONE_TRAP_ENTITY.get(), (r) -> new BoneTrapRenderer());
    }

}
