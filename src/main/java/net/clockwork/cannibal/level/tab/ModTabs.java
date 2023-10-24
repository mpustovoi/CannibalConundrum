package net.clockwork.cannibal.level.tab;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.block.custom.BoneTrap;
import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.item.custom.BoneTrapItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Clockwork.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CANNIBAL_TAB = TABS.register("cc", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.clockwork")).icon(() ->
                    new ItemStack(ModItems.STONE_SAW.get())).displayItems((enabledFeatures, entries) -> {
                for (RegistryObject<Block> key : ModBlocks.BLOCKS.getEntries()) {
                    entries.accept(key.get());
                }

                for (RegistryObject<Item> key : ModItems.ITEMS.getEntries()) {
                    entries.accept(key.get());
                }
            }).build());


}
