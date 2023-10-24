package net.clockwork.cannibal.level.item.client.renderer;

import net.clockwork.cannibal.level.item.client.model.BoneMaskModel;
import net.clockwork.cannibal.level.item.custom.BoneMaskItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BoneMaskRenderer extends GeoArmorRenderer<BoneMaskItem> {
    public BoneMaskRenderer() {
        super(new BoneMaskModel());
    }

}
