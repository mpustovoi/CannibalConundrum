package net.clockwork.cannibal.level.entity.client.model;

import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.minecraft.client.Minecraft;

public class CannibalButcherModel extends BiPedalModel<CannibalButcher> {

    @Override
    public String model(CannibalButcher animatable, int process) {
        if (process == 2) {
            if (!animatable.sound.isPlaying) {
                Minecraft.getInstance().getSoundManager().queueTickingSound(animatable.sound);
            }
            return "cannibal_butcher" + (animatable.tickCount % 3 == 0 ? 1 : 2);
        }
        return "cannibal_butcher";
    }

    @Override
    public boolean hasAnimation() {
        return true;
    }

    @Override
    public boolean shouldAnimateArms() {
        return false;
    }

    @Override
    public boolean shouldAnimateLegs() {
        return true;
    }
}
