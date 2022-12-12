package com.enething.portalgun.item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, com.enething.portalgun.PortalGun.MOD_ID);

    public static final RegistryObject<Item> PortalGun = ITEMS.register("portalgun",
            () -> new ItemPortalGun(new Item.Properties().
                    tab(CreativeModeTab.TAB_TOOLS).
                    rarity(Rarity.UNCOMMON).
                    stacksTo(1).
                    setNoRepair()
            ));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
