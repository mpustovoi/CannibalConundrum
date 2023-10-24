package net.clockwork.cannibal.level.block.entity.client.model;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.block.entity.custom.BoneTrapEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class BoneTrapModel extends DefaultedBlockGeoModel<BoneTrapEntity> {

    public BoneTrapModel() {
        super(new ResourceLocation(Clockwork.MOD_ID, "bone_trap"));
    }

}
