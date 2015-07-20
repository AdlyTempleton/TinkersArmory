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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import pixlepix.tarmory.TArmory;
import pixlepix.tarmory.data.CoordTuple;
import pixlepix.tarmory.item.ArmorBase;
import pixlepix.tarmory.item.MetalPatternArmor;
import pixlepix.tarmory.main.event.EventHandler;
import pixlepix.tarmory.network.PacketBurst;
import pixlepix.tarmory.registry.BlockRegistry;
import pixlepix.tarmory.registry.ModCreativeTab;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.FluidType;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.Smeltery;
import tconstruct.library.util.IPattern;
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

        
        //Cast-creating recipies
        Item armorCast = BlockRegistry.getFirstItemFromClass(MetalPatternArmor.class);
        for (FluidStack stack : new FluidStack[]{new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2)}){
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast), stack, new ItemStack(Items.iron_helmet), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 1), stack, new ItemStack(Items.iron_chestplate), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 2), stack, new ItemStack(Items.iron_leggings), false, 50);
            TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(armorCast, 1, 3), stack, new ItemStack(Items.iron_boots), false, 50);
        }
        int[] liquidDamage = new int[] { 2, 13, 10, 11, 12, 14, 15, 6, 16, 18 };
        LiquidCasting tableCasting = TConstructRegistry.instance.getTableCasting();
        
        //Armor casting recipies. Code from TinkerSmeltery
        int fluidAmount = 0;
        Fluid fs = null;

        for (int iter = 0; iter < 4; iter++) {
                ItemStack cast = new ItemStack(armorCast, 1, iter);

                tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new ItemStack(TinkerTools.patternOutputs[iter], 1, Short.MAX_VALUE), false, 50);
                tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2), new ItemStack(TinkerTools.patternOutputs[iter], 1, Short.MAX_VALUE), false, 50);

                for (int iterTwo = 0; iterTwo < TinkerSmeltery.liquids.length; iterTwo++) {
                    fs = TinkerSmeltery.liquids[iterTwo].getFluid();
                    fluidAmount = (7) * TConstruct.ingotLiquidValue / 2;

                    ItemStack metalCast = new ItemStack(ArmorBase.getArmor(iter), 1, liquidDamage[iterTwo]);
                    tableCasting.addCastingRecipe(metalCast, new FluidStack(fs, fluidAmount), cast, 50);
                    Smeltery.addMelting(FluidType.getFluidType(fs), metalCast, 0, fluidAmount);
                }
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
