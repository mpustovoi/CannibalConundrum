package net.clockwork.cannibal.level.block.custom;

import net.clockwork.cannibal.level.block.custom.base.BaseHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorationalBlock extends BaseHorizontalBlock {

    final VoxelShape voxelShape;

    public DecorationalBlock(Properties pProperties, VoxelShape voxelShape) {
        super(pProperties);
        this.voxelShape = voxelShape;
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return voxelShape;
    }


}
