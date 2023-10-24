package net.clockwork.cannibal.level.item.client.model;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.custom.BoneTrapItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class BoneTrapItemModel extends GeoModel<BoneTrapItem> {

    @Override
    public ResourceLocation getModelResource(BoneTrapItem animatable) {
        return new ResourceLocation(Clockwork.MOD_ID, "geo/block/bone_trap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoneTrapItem animatable) {
        return new ResourceLocation(Clockwork.MOD_ID, "textures/block/bone_trap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BoneTrapItem animatable) {
        return new ResourceLocation(Clockwork.MOD_ID, "animations/block/bone_trap.animation.json");
    }
}
