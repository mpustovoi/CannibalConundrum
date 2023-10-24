package net.clockwork.cannibal.datagen;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.effect.ModEffects;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

import javax.swing.text.html.parser.Entity;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Clockwork.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
            if (item.get() instanceof BlockItem) continue;
            add(item.get(), WordUtils.capitalize(item.get().getDescriptionId().replace("item." + Clockwork.MOD_ID + ".", "").replace("_", " ")));
        }

        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            add(block.get(), WordUtils.capitalize(block.get().getDescriptionId().replace("block." + Clockwork.MOD_ID + ".", "").replace("_", " ")));
        }

        for (RegistryObject<MobEffect> mobEffect : ModEffects.EFFECTS.getEntries()) {
            add(mobEffect.get(), WordUtils.capitalize(mobEffect.get().getDescriptionId().replace("effect." + Clockwork.MOD_ID + ".", "").replace("_", " ")));
        }

        for (RegistryObject<EntityType<?>> mobEffect : ModEntity.ENTITIES.getEntries()) {
            add(mobEffect.get(), WordUtils.capitalize(mobEffect.get().getDescriptionId().replace("entity." + Clockwork.MOD_ID + ".", "").replace("_", " ")));
        }

        add("creativemodetab.clockwork", "Cannibal Conundrum");
    }
}
