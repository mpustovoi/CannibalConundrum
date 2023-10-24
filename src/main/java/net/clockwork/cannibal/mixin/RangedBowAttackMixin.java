package net.clockwork.cannibal.mixin;

import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RangedBowAttackGoal.class)
public abstract class RangedBowAttackMixin<T extends net.minecraft.world.entity.Mob & RangedAttackMob> extends Goal {

    @Shadow
    @Final
    private T mob;

    @Shadow public abstract void stop();

    @Inject(at = @At("HEAD"), method = "canContinueToUse", cancellable = true)
    public void canContinueInject(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob instanceof AbstractSkeleton && this.mob.getTarget() != null) {
            if (this.mob.getTarget().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.BONE_MASK.get())) {
                this.stop();
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canUse", cancellable = true)
    public void canInject(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob instanceof AbstractSkeleton && this.mob.getTarget() != null) {
            if (this.mob.getTarget().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.BONE_MASK.get())) {
                this.stop();
                cir.setReturnValue(false);
            }
        }
    }

}
