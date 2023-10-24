package net.clockwork.cannibal.level.item.custom;

import net.clockwork.cannibal.level.item.client.renderer.BoneTrapItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;

public class BoneTrapItem extends AnimatedBlockItem {

    public BoneTrapItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties, Minecraft.getInstance() == null ? null : new BoneTrapItemRenderer()); // Crash on runData fix. Ignore IntelliJ warning.
    }

}
