package net.clockwork.cannibal.level.item.client.model;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StoneSawModel extends GeoModel<StoneSawItem> {

    ResourceLocation modelLocation = new ResourceLocation(Clockwork.MOD_ID, "geo/item/stone_saw.geo.json");

    @Override
    public ResourceLocation getModelResource(StoneSawItem animatable) {
        return this.modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(StoneSawItem animatable) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(StoneSawItem animatable) {
        return null;
    }

}
