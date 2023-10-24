package net.clockwork.cannibal.level.block.entity.client.renderer;

import net.clockwork.cannibal.level.block.entity.client.model.BoneTrapModel;
import net.clockwork.cannibal.level.block.entity.custom.BoneTrapEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BoneTrapRenderer extends GeoBlockRenderer<BoneTrapEntity> {
    public BoneTrapRenderer() {
        super(new BoneTrapModel());
    }
}
