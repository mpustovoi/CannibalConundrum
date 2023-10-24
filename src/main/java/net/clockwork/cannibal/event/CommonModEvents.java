package net.clockwork.cannibal.event;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.level.block.entity.client.renderer.BoneTrapRenderer;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.entity.custom.Cannibal;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        BlockEntityRenderers.register(ModBlockEntities.BONE_TRAP_ENTITY.get(), (renderer) -> new BoneTrapRenderer());
    }

}
