package net.clockwork.cannibal.level.damage;

import net.clockwork.cannibal.Clockwork;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import java.util.HashMap;
import java.util.Map;

public class ModDamageTypes {
    private static final Map<String, ResourceKey<DamageType>> DAMAGE_TYPES = new HashMap<>();

    public static ResourceKey<DamageType> BONE_TRAP = register("bone_trap");

    public static void bootstrap(BootstapContext<DamageType> context) {
        DAMAGE_TYPES.forEach((name, damageTypeResourceKey) -> context.register(damageTypeResourceKey, new DamageType(Clockwork.MOD_ID + ".damage_source." + name, 1.0F)));
    }

    private static ResourceKey<DamageType> register(String name) {
        ResourceKey<DamageType> damageType = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Clockwork.MOD_ID, "damage_source." + name));
        DAMAGE_TYPES.put(name, damageType);
        return damageType;
    }

}
