package net.clockwork.cannibal.level.item.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.item.client.model.StoneSawModel;
import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StoneSawRenderer extends GeoItemRenderer<StoneSawItem> {

    private ItemStack stack = ItemStack.EMPTY;
    private final ResourceLocation textureLocation1 = new ResourceLocation(Clockwork.MOD_ID, "textures/item/stone_saw_1.png");
    private final ResourceLocation textureLocation2 = new ResourceLocation(Clockwork.MOD_ID, "textures/item/stone_saw_2.png");

    public StoneSawRenderer() {
        super(new StoneSawModel());
    }

    @Override
    public ResourceLocation getTextureLocation(StoneSawItem animatable) {
        if (stack.getItem() == animatable) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.contains("rev")) {
                if ((int) animatable.getTick(animatable) % 3 == 0) {
                    return this.textureLocation1;
                }
                return this.textureLocation2;
            }
        }
        return this.textureLocation1;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        this.stack = stack;
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }

}
