package net.clockwork.cannibal.mixin;

import net.clockwork.cannibal.client.ClientData;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin extends Input {

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tickInject(boolean pIsSneaking, float pSneakingSpeedMultiplier, CallbackInfo ci) {
        if (!ClientData.enabledMovement) {
            this.jumping = false;
            this.shiftKeyDown = false;
            this.up = false;
            this.down = false;
            this.right = false;
            this.left = false;
            this.forwardImpulse = 0.0F;
            this.leftImpulse = 0.0F;
            ci.cancel();
        }
    }

}
