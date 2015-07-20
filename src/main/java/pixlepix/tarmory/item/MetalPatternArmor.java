package pixlepix.tarmory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import pixlepix.tarmory.registry.ITTinkererItem;
import pixlepix.tarmory.registry.ThaumicTinkererRecipe;
import tconstruct.TConstruct;
import tconstruct.library.armor.ArmorPart;
import tconstruct.smeltery.items.MetalPattern;
import tconstruct.tools.TinkerTools;
import tconstruct.tools.items.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixlepix on 7/19/15.
 */
public class MetalPatternArmor extends MetalPattern implements ITTinkererItem{
    public MetalPatternArmor() {
        super("armorCast", "materials/");

        textureNames = new String[]{"armorcast_helmet", "armorcast_chestplate", "armorcast_leggings", "armorcast_boots"};
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack);
    }

    public static String[] getPatternNameArmor (String partType)
    {
        String[] names = new String[patternNameArmor.length];
        for (int i = 0; i < patternNameArmor.length; i++)
            if (!(patternNameArmor[i].equals("")))
                names[i] = partType + patternNameArmor[i];
            else
                names[i] = "";
        return names;
    }

    private static final String[] patternNameArmor = new String[] { "head", "chest", "legs", "feet" };

    @Override
    public void getSubItems (Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        for (int i = 0; i < patternNameArmor.length; i++)
            if (!(patternNameArmor[i].equals("")))
                p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
    }
    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "armorPattern";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    @Override
    public int getCreativeTabPriority() {
        return 1;
    }
}
