package net.clockwork.cannibal.level.block.entity.custom;

import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.util.MovementUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class BoneTrapEntity extends BaseEntityBlock {
    public int releaseTicks = 0;
    public int timeSinceRelease = 0;
    public boolean occupied = true;
    public Entity occupiedBy = null;
    public int snareTotal = 0;

    public BoneTrapEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BONE_TRAP_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void tick() {
        if (this.getLevel() == null || this.getLevel().isClientSide) return;
        if (this.occupied && this.releaseTicks <= 60) {
            this.releaseTicks++;
            this.timeSinceRelease = 0;
            if (this.occupiedBy != null) {
                this.occupiedBy.teleportTo(this.getBlockPos().getX() + 0.5F, this.getBlockPos().getY(), this.getBlockPos().getZ() + 0.5F);
            }
        } else {
            this.releaseTicks = 0;
            this.timeSinceRelease++;
            if (this.occupiedBy instanceof Player player) {
                MovementUtil.enableMovement(player, true);
            }
            this.occupied = false;
            if (this.timeSinceRelease <= 5) {
                this.setChanged();
                this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            }
            this.occupiedBy = null;
            if (this.snareTotal > 4) {
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), Blocks.AIR.defaultBlockState());
            }
        }
    }

    public static final RawAnimation CLOSED = RawAnimation.begin().thenLoop("bone_trap_closed");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 0, state -> {
            if (this.occupied) {
                state.setAndContinue(CLOSED);
            } else {
                return PlayState.STOP;
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putBoolean("closed", this.occupied);
        pTag.putInt("snares", this.snareTotal);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("closed")) {
            this.occupied = pTag.getBoolean("closed");
        }
        if (pTag.contains("snares")) {
            this.snareTotal = pTag.getInt("snares");
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

}
