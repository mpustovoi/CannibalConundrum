package net.clockwork.cannibal.level.effect;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.effect.custom.Cannibalism;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Clockwork.MOD_ID);

    public static final RegistryObject<MobEffect> CANNIBALISM = EFFECTS.register("cannibalism", () -> new Cannibalism(MobEffectCategory.NEUTRAL, new Color(112, 18, 17, 255).getRGB()));

}
