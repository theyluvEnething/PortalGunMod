package com.enething.portalgun.entity;

import com.enething.portalgun.PortalGun;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, PortalGun.MOD_ID);

    public static final RegistryObject<EntityType<Entity>> PORTALPROJECTILE =
            ENTITY_TYPES.register("portalprojectile",
            () -> EntityType.Builder.of(EntityPortalProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build(new ResourceLocation(PortalGun.MOD_ID, "portalprojectile").toString()));

//   private static void registerProjectile(String name, int id, Class<? extends Entity> entity, Item item) {
//       EntityRegistry.register;
//   }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
