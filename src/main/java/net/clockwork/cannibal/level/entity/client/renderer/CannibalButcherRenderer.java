package net.clockwork.cannibal.level.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.clockwork.cannibal.level.entity.client.model.CannibalButcherModel;
import net.clockwork.cannibal.level.entity.client.model.CannibalModel;
import net.clockwork.cannibal.level.entity.custom.Cannibal;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

import javax.annotation.Nullable;

import static net.minecraft.client.model.geom.PartNames.LEFT_HAND;
import static net.minecraft.client.model.geom.PartNames.RIGHT_HAND;

public class CannibalButcherRenderer extends GeoEntityRenderer<CannibalButcher> {

    public CannibalButcherRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CannibalButcherModel());
    }

}
