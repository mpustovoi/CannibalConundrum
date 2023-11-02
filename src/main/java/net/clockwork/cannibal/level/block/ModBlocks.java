package net.clockwork.cannibal.level.block;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.custom.BoneTrap;
import net.clockwork.cannibal.level.block.custom.DecorationalBlock;
import net.clockwork.cannibal.level.block.custom.base.AnimatedItem;
import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.item.client.renderer.BoneTrapItemRenderer;
import net.clockwork.cannibal.level.item.custom.AnimatedBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import javax.annotation.Nullable;
import java.time.Clock;
import java.util.Arrays;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Clockwork.MOD_ID);


    public static final RegistryObject<Block> SKULL_LAMP = registerBlock("skull_lamp", () -> new DecorationalBlock(BlockBehaviour.Properties.of().strength(1.0F).lightLevel((blockState) -> 15), VoxelShapes.SKULL_BLOCK), false);

    public static final RegistryObject<Block> BONE_TRAP = registerBlock("bone_trap", () -> new BoneTrap(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion().noCollission()), true);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, boolean animatedBlock) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if (!animatedBlock) {
            registerBlockItem(name, toReturn);
        } else {
            if (FMLLoader.getDist() != Dist.CLIENT) {
                registerBlockItem(name, toReturn);
            }
        }
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
