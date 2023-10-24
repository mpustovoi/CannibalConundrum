package net.clockwork.cannibal.level.entity.client.model;

import net.clockwork.cannibal.Clockwork;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public abstract class BiPedalModel<T extends LivingEntity & GeoAnimatable> extends DefaultGeoBiPedalModel<T> {

    public abstract String model(T animatable, int process);
    public abstract boolean hasAnimation();

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return new ResourceLocation(Clockwork.MOD_ID, "geo/entity/" + this.model(animatable, 1) + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return new ResourceLocation(Clockwork.MOD_ID, "textures/entity/" + this.model(animatable, 2) + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        if (!this.hasAnimation()) return null;
        return new ResourceLocation(Clockwork.MOD_ID, "animations/entity/animations_" + this.model(animatable, 3) + ".animation.json");
    }
}
