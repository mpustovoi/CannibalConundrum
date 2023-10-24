package net.clockwork.cannibal.mixin;

import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MeleeAttackGoal.class)
public abstract class MeleeAttackMixin extends Goal {

    @Shadow
    @Final
    protected PathfinderMob mob;

    @Inject(at = @At("HEAD"), method = "canContinueToUse", cancellable = true)
    public void canInject(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob instanceof AbstractSkeleton && this.mob.getTarget() != null) {
            if (this.mob.getTarget().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.BONE_MASK.get())) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getAttackReachSqr", cancellable = true)
    public void reachInject(LivingEntity pAttackTarget, CallbackInfoReturnable<Double> cir) {
        if (this.mob instanceof CannibalButcher) {
            cir.setReturnValue(7.00D);
        }
    }

}
