package net.clockwork.cannibal.datagen;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Clockwork.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlockWithItem(ModBlocks.SKULL_LAMP);
        horizontalBlockWithItem(ModBlocks.BONE_TRAP);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void horizontalBlockWithItem(RegistryObject<? extends Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ModelFile.ExistingModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(Clockwork.MOD_ID, "block/" + block.getDescriptionId().replace("block." + Clockwork.MOD_ID + ".", "")), models().existingFileHelper);
        horizontalBlock(block, model);
        simpleBlockItem(block, model);
    }

}
