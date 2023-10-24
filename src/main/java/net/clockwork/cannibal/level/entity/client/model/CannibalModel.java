package net.clockwork.cannibal.level.entity.client.model;

import net.clockwork.cannibal.level.entity.custom.Cannibal;

public class CannibalModel extends BiPedalModel<Cannibal> {

    @Override
    public String model(Cannibal animatable, int process) {
        if (process == 2) {
            return "cannibal" + animatable.getTextureId();
        }
        return "cannibal";
    }

    @Override
    public boolean hasAnimation() {
        return false;
    }

    @Override
    public boolean shouldAnimateArms() {
        return true;
    }

    @Override
    public boolean shouldAnimateLegs() {
        return true;
    }
}
