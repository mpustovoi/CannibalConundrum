package net.clockwork.cannibal.level.sounds;

import net.clockwork.cannibal.Clockwork;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Clockwork.MOD_ID);

    public static final RegistryObject<SoundEvent> STONE_SAW_REV_1 = registerSoundEvent("stonesaw_rev1");
    public static final RegistryObject<SoundEvent> STONE_SAW_REV_2 = registerSoundEvent("stonesaw_rev2");
    public static final RegistryObject<SoundEvent> STONE_SAW_REV_3 = registerSoundEvent("stonesaw_rev3");
    public static final RegistryObject<SoundEvent> STONE_SAW_REV_4 = registerSoundEvent("stonesaw_rev4");
    public static final RegistryObject<SoundEvent> STONE_SAW_REVED = registerSoundEvent("stonesaw_reved");
    public static final RegistryObject<SoundEvent> STONE_SAW_REVED_LOOP = registerSoundEvent("stonesaw_reved_loop");
    public static final RegistryObject<SoundEvent> STONE_SAW_SWING_1 = registerSoundEvent("stonesaw_swing1");
    public static final RegistryObject<SoundEvent> STONE_SAW_SWING_2 = registerSoundEvent("stonesaw_swing2");
    public static final RegistryObject<SoundEvent> BONE_TRAP_SNAP = registerSoundEvent("bone_trap_snap");
    public static final RegistryObject<SoundEvent> CANNIBAL_HURT1 = registerSoundEvent("cannibal_hurt1");
    public static final RegistryObject<SoundEvent> CANNIBAL_HURT2 = registerSoundEvent("cannibal_hurt2");
    public static final RegistryObject<SoundEvent> CANNIBAL_HURT3 = registerSoundEvent("cannibal_hurt3");
    public static final RegistryObject<SoundEvent> CANNIBAL_DEATH = registerSoundEvent("cannibal_death");
    public static final RegistryObject<SoundEvent> CANNIBAL_IDLE1 = registerSoundEvent("cannibal_idle1");
    public static final RegistryObject<SoundEvent> CANNIBAL_IDLE2 = registerSoundEvent("cannibal_idle2");
    public static final RegistryObject<SoundEvent> CANNIBAL_IDLE3 = registerSoundEvent("cannibal_idle3");
    public static final RegistryObject<SoundEvent> CANNIBAL_IDLE4 = registerSoundEvent("cannibal_idle4");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_HURT1 = registerSoundEvent("cannibal_butcher_hurt1");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_HURT2 = registerSoundEvent("cannibal_butcher_hurt2");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_HURT3 = registerSoundEvent("cannibal_butcher_hurt3");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_DEATH = registerSoundEvent("cannibal_butcher_death");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_IDLE1 = registerSoundEvent("cannibal_butcher_idle1");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_IDLE2 = registerSoundEvent("cannibal_butcher_idle2");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_IDLE3 = registerSoundEvent("cannibal_butcher_idle3");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_IDLE4 = registerSoundEvent("cannibal_butcher_idle4");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_IDLE5 = registerSoundEvent("cannibal_butcher_idle5");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_SPOT_TARGET = registerSoundEvent("cannibal_butcher_spot_target");
    public static final RegistryObject<SoundEvent> CANNIBAL_BUTCHER_START_SPRINT = registerSoundEvent("cannibal_butcher_start_sprint");



    private static RegistryObject<SoundEvent> registerDistanceSoundEvent(String name, float distance) {
        return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Clockwork.MOD_ID, name), distance));
    }

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Clockwork.MOD_ID, name)));
    }

}
