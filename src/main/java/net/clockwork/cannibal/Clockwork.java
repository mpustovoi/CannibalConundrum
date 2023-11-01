package net.clockwork.cannibal;

import com.mojang.logging.LogUtils;
import net.clockwork.cannibal.level.block.ModBlocks;
import net.clockwork.cannibal.level.block.entity.ModBlockEntities;
import net.clockwork.cannibal.level.effect.ModEffects;
import net.clockwork.cannibal.level.entity.ModEntity;
import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.sounds.ModSounds;
import net.clockwork.cannibal.level.tab.ModTabs;
import net.clockwork.cannibal.networking.ModMessages;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(Clockwork.MOD_ID)
public class Clockwork {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "cannibal";

    public Clockwork()
    {
        GeckoLib.initialize();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        ModItems.ITEMS.register(modEventBus);

        ModBlocks.BLOCKS.register(modEventBus);

        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        ModTabs.TABS.register(modEventBus);

        ModEffects.EFFECTS.register(modEventBus);

        ModSounds.SOUNDS.register(modEventBus);

        ModEntity.ENTITIES.register(modEventBus);

        if (FMLLoader.getDist() == Dist.CLIENT) { // TODO Hacky workaround.
            ModItems.loadAnimatedItemsClient();
        } else {
            ModItems.loadAnimatedItemsServer();
        }

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }

}
