package net.clockwork.cannibal.datagen;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Clockwork.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handHeldItem(ModItems.BONE_SWORD);
        simpleItem(ModItems.FRESH_FLESH);
        simpleItem(ModItems.FRESH_CHILI);
        simpleItem(ModItems.BONE_MASK);

        for (RegistryObject<Item> registry : ModItems.ITEMS.getEntries()) {
            if (registry.get() instanceof ForgeSpawnEggItem) {
                spawnEgg(registry);
            }
        }

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Clockwork.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Clockwork.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }

}
