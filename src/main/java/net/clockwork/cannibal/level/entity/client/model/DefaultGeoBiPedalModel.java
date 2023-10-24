package net.clockwork.cannibal.level.entity.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public abstract class DefaultGeoBiPedalModel<T extends LivingEntity & GeoAnimatable> extends GeoModel<T> {
    @Override
    public abstract ResourceLocation getModelResource(T animatable);

    @Override
    public abstract ResourceLocation getTextureResource(T animatable);

    @Override
    public abstract ResourceLocation getAnimationResource(T animatable);

    public abstract boolean shouldAnimateArms();

    public abstract boolean shouldAnimateLegs();

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone rightArm = getAnimationProcessor().getBone("right_arm");
        CoreGeoBone leftArm = getAnimationProcessor().getBone("left_arm");
        CoreGeoBone leftLeg = getAnimationProcessor().getBone("left_leg");
        CoreGeoBone rightLeg = getAnimationProcessor().getBone("right_leg");
        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }

        float partialTick = Minecraft.getInstance().getPartialTick();
        float speed = 0.0F;
        float position = 0.0F;

        if (animatable.isAlive()) {
            speed = animatable.walkAnimation.speed(partialTick);
            position = animatable.walkAnimation.position(partialTick);
            if (speed > 1.0F) speed = 1.0F;
        }
        if (leftArm != null && rightArm != null && this.shouldAnimateArms()) {
            if (animatable.swinging) {
                rightArm.updateRotation(0.0F, -0.15707964F, 0.0F);
                rightArm.updateRotation(-0.0F + Mth.cos(animatable.attackAnim * 0.19F) * 0.5F, 0.0F, 0.0F);
            } else {
                leftArm.updateRotation(Mth.cos(position * 0.6662F + (float) Math.PI) * 1.4F * speed * 0.5F, 0.0F, 0.0F);
                rightArm.updateRotation(Mth.cos(position * 0.6662F) * 1.4F * speed * 0.5F, 0.0F, 0.0F);
            }
        }
        if (leftLeg != null && rightLeg != null && this.shouldAnimateLegs()) {
            leftLeg.updateRotation(Mth.cos(position * 0.6662F) * 1.4F * speed * 0.5F, 0.0F, 0.0F);
            rightLeg.updateRotation(Mth.cos(position * 0.6662F + (float) Math.PI) * 1.4F * speed * 0.5F, 0.0F, 0.0F);
        }
    }

}
