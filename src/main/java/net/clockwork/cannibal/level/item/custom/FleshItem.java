package net.clockwork.cannibal.level.item.custom;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.effect.ModEffects;
import net.clockwork.cannibal.level.item.interfaces.CannibalFoodItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FleshItem extends Item implements CannibalFoodItem {
    public FleshItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {

        if (!pLivingEntity.hasEffect(ModEffects.CANNIBALISM.get())) {
            pLivingEntity.addEffect(new MobEffectInstance(ModEffects.CANNIBALISM.get(), 12000));
        }

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }


}
