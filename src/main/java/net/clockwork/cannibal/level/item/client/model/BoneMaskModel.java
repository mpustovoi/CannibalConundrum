package net.clockwork.cannibal.level.item.client.model;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.custom.BoneMaskItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BoneMaskModel extends GeoModel<BoneMaskItem> {

    ResourceLocation modelLocation = new ResourceLocation(Clockwork.MOD_ID, "geo/item/bone_mask.geo.json");
    ResourceLocation textureLocation = new ResourceLocation(Clockwork.MOD_ID, "textures/item/bone_mask_armor.png");

    @Override
    public ResourceLocation getModelResource(BoneMaskItem animatable) {
        return this.modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(BoneMaskItem animatable) {
        return this.textureLocation;
    }

    @Override
    public ResourceLocation getAnimationResource(BoneMaskItem animatable) {
        return null;
    }
}
