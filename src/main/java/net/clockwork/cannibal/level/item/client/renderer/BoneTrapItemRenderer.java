package net.clockwork.cannibal.level.item.client.renderer;

import net.clockwork.cannibal.level.item.client.model.BoneTrapItemModel;
import net.clockwork.cannibal.level.item.custom.BoneTrapItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BoneTrapItemRenderer extends GeoItemRenderer<BoneTrapItem> {

    public BoneTrapItemRenderer() {
        super(new BoneTrapItemModel());
    }

}
