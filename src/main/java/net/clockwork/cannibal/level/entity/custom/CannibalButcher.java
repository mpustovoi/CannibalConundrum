package net.clockwork.cannibal.level.entity.custom;

import net.clockwork.cannibal.level.item.custom.StoneSawItem;
import net.clockwork.cannibal.level.sounds.ModSounds;
import net.clockwork.cannibal.level.sounds.custom.TickableChainsawSound;
import net.clockwork.cannibal.util.Misc;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class CannibalButcher extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final EntityDataAccessor<Boolean> IS_CHAINSAW_SPRINTING = SynchedEntityData.defineId(CannibalButcher.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SPOTTED_ENEMY = SynchedEntityData.defineId(CannibalButcher.class, EntityDataSerializers.BOOLEAN);
    public int raiseTick = 0;
    public int chainsawSprint = 0;
    public final AttributeModifier chainsawSprintModifier = new AttributeModifier("chainsaw sprint", 0.2D, AttributeModifier.Operation.ADDITION);
    public TickableChainsawSound sound = new TickableChainsawSound(ModSounds.STONE_SAW_REVED_LOOP.get(), this, true);
    public static final List<SoundEvent> HURT_SOUNDS = List.of(ModSounds.CANNIBAL_BUTCHER_HURT1.get(), ModSounds.CANNIBAL_BUTCHER_HURT2.get(), ModSounds.CANNIBAL_BUTCHER_HURT3.get());
    public static final List<SoundEvent> IDLE_SOUNDS = List.of(ModSounds.CANNIBAL_BUTCHER_IDLE1.get(), ModSounds.CANNIBAL_BUTCHER_IDLE2.get(), ModSounds.CANNIBAL_BUTCHER_IDLE3.get(), ModSounds.CANNIBAL_BUTCHER_IDLE4.get(), ModSounds.CANNIBAL_BUTCHER_IDLE5.get());

    public CannibalButcher(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0F, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, true));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.setPersistenceRequired();
    }

    @Override
    public void checkDespawn() {

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.32F)
                .add(Attributes.ATTACK_DAMAGE, 13.0F)
                .add(Attributes.ATTACK_SPEED, 0.5F)
                .add(Attributes.MAX_HEALTH, 175.0F)
                .add(Attributes.FOLLOW_RANGE, 32.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CHAINSAW_SPRINTING, false);
        this.entityData.define(SPOTTED_ENEMY, false);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Misc.getRandom(IDLE_SOUNDS);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean flag = super.hurt(pSource, pAmount);
        if (flag) {
            this.playSound(Misc.getRandom(HURT_SOUNDS));
        }
        return flag;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CANNIBAL_BUTCHER_DEATH.get();
    }

    public boolean isChainsawSprinting() {
        return this.entityData.get(IS_CHAINSAW_SPRINTING);
    }

    public void setChainsawSprint(boolean isChainsawSprinting) {
        if (!isChainsawSprinting) {
            if (this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(this.chainsawSprintModifier)) {
                this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(this.chainsawSprintModifier);
            }
        } else {
            if (!this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(this.chainsawSprintModifier)) {
                this.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(this.chainsawSprintModifier);
            }
            this.playSound(ModSounds.CANNIBAL_BUTCHER_START_SPRINT.get());
        }
        this.entityData.set(IS_CHAINSAW_SPRINTING, isChainsawSprinting);
    }

    public boolean spottedEnemy() {
        return this.entityData.get(SPOTTED_ENEMY);
    }

    public void setSpottedEnemy(boolean hasEnemy) {
        if (hasEnemy && this.getTarget() == null) {
            this.raiseTick = 80;
            this.playSound(ModSounds.CANNIBAL_BUTCHER_SPOT_TARGET.get());
        }
        this.entityData.set(SPOTTED_ENEMY, hasEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.spottedEnemy()) {
            this.raiseTick--;
            if (raiseTick <= 0) {
                this.raiseTick = 0;
                this.setSpottedEnemy(false);
            }
        } else if (!this.level().isClientSide && this.getTarget() != null) {
            if (this.getRandom().nextFloat() > 0.991F && !this.isChainsawSprinting()) {
                this.chainsawSprint = 40;
                this.setChainsawSprint(true);
                this.playSound(ModSounds.CANNIBAL_BUTCHER_START_SPRINT.get());
            }
            if (this.chainsawSprint <= 0) {
                this.setChainsawSprint(false);
            } else {
                this.chainsawSprint--;
            }
        }
    }

    @Override
    public void setTarget(@Nullable LivingEntity pTarget) {
        this.setSpottedEnemy(pTarget != null);
        if (pTarget == null) {
            this.setChainsawSprint(false);
        }
        super.setTarget(pTarget);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity pEntity) {
        SoundEvent swingSound = Misc.getRandom(StoneSawItem.SWING_SOUNDS);
        this.playSound(swingSound);
        return super.doHurtTarget(pEntity);
    }

    public static RawAnimation CHAINSAW_SPRINT = RawAnimation.begin().thenLoop("stone_saw_sprint");
    public static RawAnimation CHAINSAW_RAISE = RawAnimation.begin().thenLoop("stone_saw_raise");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 0, state -> {

            if (this.spottedEnemy()) {
                state.setAndContinue(CHAINSAW_RAISE);
                return PlayState.CONTINUE;
            }

            if (this.isChainsawSprinting()) {
                state.setAndContinue(CHAINSAW_SPRINT);
            } else {
                return PlayState.STOP;
            }

            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
