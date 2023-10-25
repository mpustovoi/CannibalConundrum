package net.clockwork.cannibal.level.block.custom;

import net.clockwork.cannibal.level.block.VoxelShapes;
import net.clockwork.cannibal.level.block.custom.base.AnimatedItem;
import net.clockwork.cannibal.level.block.custom.base.BaseHorizontalEntityBlock;
import net.clockwork.cannibal.level.block.entity.custom.BoneTrapEntity;
import net.clockwork.cannibal.level.damage.ModDamageTypes;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.clockwork.cannibal.level.sounds.ModSounds;
import net.clockwork.cannibal.util.Misc;
import net.clockwork.cannibal.util.MovementUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BoneTrap extends BaseHorizontalEntityBlock implements AnimatedItem {

    public BoneTrap(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pLevel.isClientSide) return;
        BoneTrapEntity trapEntity = (BoneTrapEntity) pLevel.getBlockEntity(pPos);
        if (!(pEntity instanceof LivingEntity livingEntity)) return;
        if (trapEntity == null) return;
        if (!trapEntity.occupied) {
            if (pEntity.position().y <= pPos.getY() + 0.1F && !trapEntity.occupied && trapEntity.timeSinceRelease >= 40) {
                trapEntity.occupied = true;
                trapEntity.occupiedBy = pEntity;
                trapEntity.snareTotal++;
                trapEntity.setChanged();
                BlockState state = pLevel.getBlockState(pPos);
                pLevel.sendBlockUpdated(pPos, state, state, Block.UPDATE_ALL);
                if (livingEntity instanceof Player player) {
                    MovementUtil.enableMovement(player, false);
                } else if (!(livingEntity instanceof CannibalButcher)){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 10, false, false, false));
                }
                livingEntity.teleportTo(pPos.getX() + 0.5F, pPos.getY(), pPos.getZ() + 0.5F);
                livingEntity.hurt(Misc.damageSource(ModDamageTypes.BONE_TRAP, pLevel), 5.0F);
                pLevel.playSound(null, pPos, ModSounds.BONE_TRAP_SNAP.get(), SoundSource.BLOCKS);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) return InteractionResult.FAIL;
        BoneTrapEntity trapEntity = (BoneTrapEntity) pLevel.getBlockEntity(pPos);
        if (trapEntity == null) return InteractionResult.FAIL;

        ItemStack used = pPlayer.getItemInHand(pHand);

        if (used.is(Items.STICK)) {
            used.shrink(1);
            trapEntity.occupied = true;
            trapEntity.setChanged();
            pLevel.sendBlockUpdated(pPos, pState, pState, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof BoneTrapEntity boneTrap) {
            if (boneTrap.occupiedBy instanceof Player player) {
                MovementUtil.enableMovement(player, true);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return VoxelShapes.BONE_TRAP;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BoneTrapEntity(pPos, pState);
    }

}
