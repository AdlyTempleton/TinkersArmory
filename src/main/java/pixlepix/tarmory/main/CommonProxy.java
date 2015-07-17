package pixlepix.tarmory.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import pixlepix.tarmory.TArmory;
import pixlepix.tarmory.data.CoordTuple;
import pixlepix.tarmory.data.recipe.ProcessorRecipeRegistry;
import pixlepix.tarmory.data.recipe.PylonRecipeRegistry;
import pixlepix.tarmory.main.event.EventHandler;
import pixlepix.tarmory.network.PacketBurst;
import pixlepix.tarmory.registry.BlockRegistry;
import pixlepix.tarmory.registry.ModCreativeTab;

public class CommonProxy {

    public static EventHandler eventHandler;

    public BlockRegistry registry;
    public SimpleNetworkWrapper networkWrapper;

    public void preInit(FMLPreInitializationEvent event) {
        Config.init(event);
        ModCreativeTab.INSTANCE = new ModCreativeTab();
        registry = new BlockRegistry();
        registry.preInit();


        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(ConstantMod.modId);
        networkWrapper.registerMessage(PacketBurst.class, PacketBurst.class, 0, Side.CLIENT);

    }

    public World getWorld() {
        return null;
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    public void init(FMLInitializationEvent event) {
        registry.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(TArmory.instance, new GuiHandler());

        PylonRecipeRegistry.init();
        ProcessorRecipeRegistry.init();
        eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }

    public void postInit(FMLPostInitializationEvent event) {
        registry.postInit();
    }

    public void setLexiconStack(ItemStack stack) {
    }

    public void addBlockDestroyEffects(CoordTuple tuple) {
    }

    public EffectRenderer getEffectRenderer() {
        return null;
    }

    public void addEffectBypassingLimit(EntityFX entityFX) {

    }
}
