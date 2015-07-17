package pixlepix.tarmory.main;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import pixlepix.tarmory.block.tile.TileBookshelfCoordinator;
import pixlepix.tarmory.gui.ContainerCoordinator;
import pixlepix.tarmory.gui.GuiCoordinator;
import pixlepix.tarmory.lexicon.GuiLexicon;

/**
 * Created by pixlepix on 12/27/14.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        if (id == 1) {
            return new ContainerCoordinator(player.inventory, (TileBookshelfCoordinator) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            return GuiLexicon.currentOpenLexicon;
        }
        if (id == 1) {
            return new GuiCoordinator(player.inventory, (TileBookshelfCoordinator) world.getTileEntity(x, y, z));

        }
        return null;

    }
}