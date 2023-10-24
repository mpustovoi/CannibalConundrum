package net.clockwork.cannibal.mixin;

import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    @Inject(at = @At("HEAD"), method = "getArmPose", cancellable = true)
    private static void armInject(AbstractClientPlayer pPlayer, InteractionHand pHand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        if (pPlayer.getItemInHand(pHand).getItem() instanceof StoneSawItem)
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
    }

}
