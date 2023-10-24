package net.clockwork.cannibal.level.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties FRESH_FLESH = (new FoodProperties.Builder()).nutrition(3).saturationMod(5.0F).meat().build();
    public static final FoodProperties FRESH_CHILI = (new FoodProperties.Builder()).nutrition(10).saturationMod(10.0F).build();

}
