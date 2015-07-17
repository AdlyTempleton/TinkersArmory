package pixlepix.tarmory.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import pixlepix.tarmory.KeyBindings;
import pixlepix.tarmory.data.CoordTuple;
import pixlepix.tarmory.main.event.ClientEventHandler;

public class ClientProxy extends CommonProxy {


    @Override
    public World getWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        KeyBindings.init();

        ClientEventHandler clientEventHandler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(clientEventHandler);
        FMLCommonHandler.instance().bus().register(clientEventHandler);


    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public EffectRenderer getEffectRenderer() {
        return Minecraft.getMinecraft().effectRenderer;
    }


    public void addBlockDestroyEffects(CoordTuple tuple) {

        Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(tuple.getX(), tuple.getY(), tuple.getZ(), tuple.getBlock(Minecraft.getMinecraft().theWorld), tuple.getMeta(Minecraft.getMinecraft().theWorld));


    }

    @Override
    public void addEffectBypassingLimit(EntityFX entityFX) {
        if (Config.overrideMaxParticleLimit) {
            Minecraft.getMinecraft().effectRenderer.fxLayers[entityFX.getFXLayer()].add(entityFX);
        } else {
            Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entityFX);
        }
    }
}
