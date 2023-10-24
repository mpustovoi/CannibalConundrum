package net.clockwork.cannibal.level.sounds.custom;

import net.clockwork.cannibal.level.item.ModItems;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class TickableChainsawSound extends AbstractTickableSoundInstance {

    private final LivingEntity livingEntity;
    public boolean isPlaying;

    public TickableChainsawSound(SoundEvent soundEvent, LivingEntity livingEntity, boolean loop) {
        super(soundEvent, SoundSource.HOSTILE, livingEntity.getRandom());
        this.livingEntity = livingEntity;
        this.looping = loop;
    }

    @Override
    public void tick() {
        if (this.livingEntity.isRemoved() || (this.livingEntity instanceof Player && this.livingEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() != ModItems.STONE_SAW.get())) {
            this.stop();
            this.isPlaying = false;
            return;
        }
        this.isPlaying = true;
        this.x = this.livingEntity.getX();
        this.y = this.livingEntity.getY();
        this.z = this.livingEntity.getZ();
    }

}
