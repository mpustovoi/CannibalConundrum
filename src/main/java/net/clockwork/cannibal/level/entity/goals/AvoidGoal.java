package net.clockwork.cannibal.level.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class AvoidGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

    public AvoidGoal(PathfinderMob pMob, Class<T> pEntityClassToAvoid, float pMaxDistance) {
        super(pMob, pEntityClassToAvoid, pMaxDistance, 0.0F, 0.0F);
    }

    @Override
    public void start() {
        this.pathNav.moveTo(this.path, this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED) + 0.2F);
    }

    @Override
    public void tick() {
    }
}
