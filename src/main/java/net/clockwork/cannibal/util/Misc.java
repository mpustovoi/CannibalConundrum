package net.clockwork.cannibal.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

import java.util.List;

public class Misc {

    public static final RandomSource random = RandomSource.create();

    public static DamageSource damageSource(ResourceKey<DamageType> damageType, Level level) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType));
    }

    public static <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

}
