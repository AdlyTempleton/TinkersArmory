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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import pixlepix.tarmory.TArmory;
import pixlepix.tarmory.data.CoordTuple;
import pixlepix.tarmory.item.MetalPatternArmor;
import pixlepix.tarmory.main.event.EventHandler;
import pixlepix.tarmory.network.PacketBurst;
import pixlepix.tarmory.registry.BlockRegistry;
import pixlepix.tarmory.registry.ModCreativeTab;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.tools.TinkerTools;

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

        eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }

    public void postInit(FMLPostInitializationEvent event) {
        registry.postInit();

        Item armorCast = BlockRegistry.getFirstItemFromClass(MetalPatternArmor.class);
        for (FluidStack stack : new FluidStack[]{new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2)}){
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast), stack, new ItemStack(Items.iron_helmet), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 1), stack, new ItemStack(Items.iron_chestplate), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 2), stack, new ItemStack(Items.iron_leggings), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 3), stack, new ItemStack(Items.iron_boots), false, 50);
        }
    }

    public void addBlockDestroyEffects(CoordTuple tuple) {
    }

    public EffectRenderer getEffectRenderer() {
        return null;
    }

    public void addEffectBypassingLimit(EntityFX entityFX) {

    }
}
