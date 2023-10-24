package net.clockwork.cannibal.level.block.entity;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.block.entity.custom.BoneTrapEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Clockwork.MOD_ID);

    public static final RegistryObject<BlockEntityType<BoneTrapEntity>> BONE_TRAP_ENTITY = register("bone_trap_entity", ModBlocks.BONE_TRAP, BoneTrapEntity::new);

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, RegistryObject<? extends Block> registryObject, BlockEntityType.BlockEntitySupplier<? extends BlockEntity> factory) {
        RegistryObject<? extends BlockEntityType<? extends BlockEntity>> ret = BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(factory, registryObject.get()).build(null));
        return (RegistryObject<BlockEntityType<T>>) ret;
    }

}
