package net.clockwork.cannibal.level.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class VoxelShapes {

    public static final VoxelShape SKULL_BLOCK = Stream.of(
            Block.box(4, 0, 4, 12, 8, 12),
            Block.box(7, 0, 7, 9, 4, 9),
            Block.box(8, 4, 7.5, 9, 6, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape BONE_TRAP = Stream.of(
            Block.box(1, 0, 1, 15, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

}
