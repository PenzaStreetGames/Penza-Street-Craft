package com.penzastreet.penzastreetcraft;

import com.penzastreet.penzastreetcraft.blocks.HematiteOre;
import com.penzastreet.penzastreetcraft.blocks.ModBlocks;
import com.penzastreet.penzastreetcraft.setup.ClientProxy;
import com.penzastreet.penzastreetcraft.setup.ServerProxy;
import com.penzastreet.penzastreetcraft.setup.IProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("penzastreetcraft")
public class PenzaStreetCraft
{
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    private static final Logger LOGGER = LogManager.getLogger();

    public PenzaStreetCraft() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.getClientWorld();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new HematiteOre());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new BlockItem(ModBlocks.HEMATITEORE, new Item.Properties()).setRegistryName("hematiteore"));
        }
    }
}
