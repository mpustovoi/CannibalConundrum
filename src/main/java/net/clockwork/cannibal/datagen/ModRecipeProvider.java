package net.clockwork.cannibal.datagen;

import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BONE_TRAP.get(), 1)
                .define('R', Items.REDSTONE).define('B', Items.BONE)
                .pattern("BRB").unlockedBy("has_bone", has(Items.BONE)).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.FRESH_CHILI.get(), 1)
                .define('B', Items.BOWL).define('F', ModItems.FRESH_FLESH.get()).define('M', Items.BROWN_MUSHROOM)
                .pattern("FFM")
                .pattern(" B ").unlockedBy("has_flesh", has(ModItems.FRESH_FLESH.get())).save(pWriter);

    }
}
