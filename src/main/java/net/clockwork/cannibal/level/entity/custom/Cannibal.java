package net.clockwork.cannibal.level.entity.custom;

import net.clockwork.cannibal.level.item.ModItems;
import net.clockwork.cannibal.level.sounds.ModSounds;
import net.clockwork.cannibal.util.Misc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class Cannibal extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final EntityDataAccessor<Integer> TEXTURE = SynchedEntityData.defineId(Cannibal.class, EntityDataSerializers.INT);

    public static final List<SoundEvent> HURT_SOUNDS = List.of(ModSounds.CANNIBAL_HURT1.get(), ModSounds.CANNIBAL_HURT2.get(), ModSounds.CANNIBAL_HURT3.get());
    public static final List<SoundEvent> IDLE_SOUNDS = List.of(ModSounds.CANNIBAL_IDLE1.get(), ModSounds.CANNIBAL_IDLE2.get(), ModSounds.CANNIBAL_IDLE3.get(), ModSounds.CANNIBAL_IDLE4.get());

    public Cannibal(EntityType<? extends Monster> pEntityType, Level pLevel) {
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
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, false));
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        ((GroundPathNavigation) this.getNavigation()).setCanPassDoors(true);
        this.setPersistenceRequired();
    }

    @Override
    public void checkDespawn() {

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.ATTACK_DAMAGE, 5.0F)
                .add(Attributes.ATTACK_SPEED, 0.1F)
                .add(Attributes.MAX_HEALTH, 22.0F)
                .add(Attributes.FOLLOW_RANGE, 32.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TEXTURE, 1);
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
        return ModSounds.CANNIBAL_DEATH.get();
    }

    public int getTextureId() {
        return this.entityData.get(TEXTURE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("texture", this.getTextureId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("texture")) {
            this.entityData.set(TEXTURE, pCompound.getInt("texture"));
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.BONE_SWORD.get()));
        this.handDropChances[0] = 0.5F;
        this.entityData.set(TEXTURE, this.getRandom().nextInt(1, 4));
        return pSpawnData;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
