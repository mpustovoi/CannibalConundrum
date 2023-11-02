package net.clockwork.cannibal.event;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.clockwork.cannibal.util.MovementUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Clockwork.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void targetEvent(LivingChangeTargetEvent event) {
        if (event.getEntity() instanceof AbstractSkeleton) {
            if (event.getNewTarget() != null && event.getNewTarget().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.BONE_MASK.get())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            MovementUtil.enableMovement(serverPlayer, true);
        }
    }

    @SubscribeEvent
    public static void livingDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof AbstractSkeleton) {
            if (event.getEntity().getRandom().nextFloat() > 0.88F) {
                BlockPos pos = event.getEntity().getOnPos();
                ItemEntity entity = new ItemEntity(event.getEntity().level(), pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.BONE_MASK.get(), 1));
                event.getDrops().add(entity);
            }
        }
        if (event.getEntity() instanceof AbstractVillager) {
            if (event.getEntity().getRandom().nextFloat() > 0.85F) {
                BlockPos pos = event.getEntity().getOnPos();
                ItemEntity entity = new ItemEntity(event.getEntity().level(), pos.getX(), pos.getY() + 0.5F, pos.getZ(), new ItemStack(ModItems.FRESH_FLESH.get(), event.getEntity().getRandom().nextIntBetweenInclusive(1, 3)));
                event.getDrops().add(entity);
            }
        }
    }

    @SubscribeEvent
    public static void playerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        ItemStack stackMain = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack stackOff = player.getItemInHand(InteractionHand.OFF_HAND);
        StoneSawItem.resetRev(stackMain);
        StoneSawItem.resetRev(stackOff);
    }

}
