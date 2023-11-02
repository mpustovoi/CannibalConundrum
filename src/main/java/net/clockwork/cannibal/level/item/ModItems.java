package net.clockwork.cannibal.level.item;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.item.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Clockwork.MOD_ID);


    public static final RegistryObject<Item> BONE_SWORD = ITEMS.register("bone_sword", () -> new SwordItem(Tiers.GOLD, 6, -2.4F, new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BONE_MASK = ITEMS.register("bone_mask", () -> new BoneMaskItem(ArmorMaterials.GOLD, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> STONE_SAW = ITEMS.register("stone_saw", () -> new StoneSawItem(Tiers.DIAMOND, 9, -3.5F, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    /** Food **/
    public static final RegistryObject<Item> FRESH_FLESH = ITEMS.register("fresh_flesh", () -> new FleshItem(new Item.Properties().food(ModFoods.FRESH_FLESH)));
    public static final RegistryObject<Item> FRESH_CHILI = ITEMS.register("fresh_chili", () -> new FreshChili(new Item.Properties().food(ModFoods.FRESH_CHILI)));

    /** Spawn Eggs **/
    public static final RegistryObject<Item> CANNIBAL_SPAWN_EGG = registerEgg("cannibal_spawn_egg", ModEntity.CANNIBAL, new Color(174, 160, 149), new Color(171, 32, 46));
    public static final RegistryObject<Item> CANNIBAL_BUTCHER_SPAWN_EGG = registerEgg("cannibal_butcher_spawn_egg", ModEntity.CANNIBAL_BUTCHER, new Color(213, 210, 198), new Color(216, 168, 92));

    public static void loadAnimatedItems() {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ITEMS.register("bone_trap", () -> new BoneTrapItem(ModBlocks.BONE_TRAP.get(), new Item.Properties()));
        }
    }

    public static RegistryObject<Item> registerEgg(String name, Supplier<? extends EntityType<? extends Mob>> entityType, Color backgroundColor, Color highlightColor) {
        return ITEMS.register(name, () -> new ForgeSpawnEggItem(entityType, backgroundColor.getRGB(), highlightColor.getRGB(), new Item.Properties().stacksTo(16)));
    }

}
