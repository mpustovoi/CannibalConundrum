package net.clockwork.cannibal.level.entity;

import net.clockwork.cannibal.Clockwork;
import net.clockwork.cannibal.level.entity.custom.Cannibal;
import net.clockwork.cannibal.level.entity.custom.CannibalButcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Clockwork.MOD_ID);

    public static final RegistryObject<EntityType<Cannibal>> CANNIBAL = ENTITIES.register("cannibal",
            () -> EntityType.Builder.of(Cannibal::new, MobCategory.MONSTER).sized(0.5F, 1.8F).build(Clockwork.MOD_ID + ":cannibal"));

    public static final RegistryObject<EntityType<CannibalButcher>> CANNIBAL_BUTCHER = ENTITIES.register("cannibal_butcher",
            () -> EntityType.Builder.of(CannibalButcher::new, MobCategory.MONSTER).sized(0.7F, 2.5F).build(Clockwork.MOD_ID + ":cannibal_butcher"));

}
