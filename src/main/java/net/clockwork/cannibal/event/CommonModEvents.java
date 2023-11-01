package net.clockwork.cannibal.event;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.level.block.entity.client.renderer.BoneTrapRenderer;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.entity.custom.Cannibal;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Clockwork.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.CANNIBAL.get(), Cannibal.createAttributes().build());
        event.put(ModEntity.CANNIBAL_BUTCHER.get(), CannibalButcher.createAttributes().build());
    }

}
