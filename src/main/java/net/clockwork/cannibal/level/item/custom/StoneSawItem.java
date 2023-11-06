package net.clockwork.cannibal.level.item.custom;

import net.clockwork.cannibal.level.item.client.renderer.StoneSawRenderer;
import net.clockwork.cannibal.level.sounds.ModSounds;
import net.clockwork.cannibal.networking.ModMessages;
import net.clockwork.cannibal.networking.S2C.PlayChainsawSoundS2CPacket;
import net.clockwork.cannibal.util.Misc;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class StoneSawItem extends AxeItem implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final List<SoundEvent> REV_SOUNDS = List.of(ModSounds.STONE_SAW_REV_1.get(), ModSounds.STONE_SAW_REV_2.get(), ModSounds.STONE_SAW_REV_3.get(), ModSounds.STONE_SAW_REV_4.get());
    public static final List<SoundEvent> SWING_SOUNDS = List.of(ModSounds.STONE_SAW_SWING_1.get(), ModSounds.STONE_SAW_SWING_2.get());

    public StoneSawItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private StoneSawRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    renderer = new StoneSawRenderer();
                }
                return this.renderer;
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("rev") || pUsedHand.equals(InteractionHand.OFF_HAND))
            return InteractionResultHolder.fail(stack);
        if (!tag.contains("rev_count")) {
            tag.putInt("rev_count", 0);
        }
        if (tag.getInt("rev_count") < 3) {
            tag.putInt("rev_count", tag.getInt("rev_count") + 1);
            pPlayer.getCooldowns().addCooldown(stack.getItem(), 20);
            if (pPlayer.level() instanceof ServerLevel serverLevel) {
                SoundEvent event = REV_SOUNDS.get(tag.getInt("rev_count"));
                serverLevel.playSound(null, pPlayer.getOnPos().above(), event, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        } else {
            this.finishUsingItem(stack, pLevel, pPlayer);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (!tag.contains("rev")) {
            tag.putBoolean("rev", true);
            tag.putUUID("player", pLivingEntity.getUUID());
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("rev")) {
            return true;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (pEntity instanceof LivingEntity livingEntity) {
            if (livingEntity.getItemInHand(InteractionHand.OFF_HAND) == pStack) {
                resetRev(pStack);
            }
        }
        if (tag.contains("rev") && !pIsSelected) {
            resetRev(pStack);
        } else if (tag.contains("rev") && pIsSelected) {
            if (pEntity instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.getRandom().nextFloat() > 0.95F) {
                    BlockPos pos = BlockPos.containing(serverPlayer.getEyePosition());
                    double xRot = -Mth.sin(serverPlayer.getYRot() * ((float) Math.PI / 180F)) / 2;
                    double zRot = Mth.cos(serverPlayer.getYRot() * ((float) Math.PI / 180F)) / 2;
                    serverPlayer.serverLevel().sendParticles(ParticleTypes.LARGE_SMOKE, pos.getX() + xRot, pos.getY(), pos.getZ() + zRot, 0, xRot, 0.0D, zRot, 0.0D);
                }
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        CompoundTag tag = stack.getOrCreateTag();

        if (!tag.contains("rev")) return false;
        if (!tag.contains("cooldown")) tag.putInt("cooldown", 0);
        if (tag.getInt("cooldown") > entity.tickCount) return false;

        tag.putInt("cooldown", entity.tickCount + 20);
        SoundEvent event = Misc.getRandom(SWING_SOUNDS);
        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity.getOnPos().above(), event, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return super.onEntitySwing(stack, entity);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        resetRev(item);
        return super.onDroppedByPlayer(item, player);
    }

    public static void resetRev(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("rev")) {
            tag.remove("rev");
        }
        if (tag.contains("rev_count")) {
            tag.remove("rev_count");
        }
        if (tag.contains("cooldown")) {
            tag.remove("cooldown");
        }
        if (tag.contains("player")) {
            tag.remove("player");
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return InteractionResult.PASS;
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return !pStack.getOrCreateTag().contains("rev") ? 0.0F : pState.is(BlockTags.MINEABLE_WITH_AXE) ? 30.0F : 0.0F;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

}
