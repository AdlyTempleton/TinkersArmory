package pixlepix.tarmory.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import pixlepix.tarmory.registry.ITTinkererItem;
import pixlepix.tarmory.registry.ThaumicTinkererRecipe;
import tconstruct.library.armor.ArmorCore;
import tconstruct.library.armor.ArmorPart;
import tconstruct.library.modifier.IModifyable;
import tconstruct.library.tools.ToolCore;

import java.util.ArrayList;

/**
 * Created by pixlepix on 7/18/15.
 */
public class ArmorBase extends ArmorCore implements ITTinkererItem {
    public ArmorBase(int baseProtection, ArmorPart part, String type, String textureFolder, String textureName) {
        super(baseProtection, part, type, textureFolder, textureName);
    }
    
    public ArmorBase(){
        this(ArmorPart.Feet);
    }
    public ArmorBase(ArmorPart part){
        super(0, part, "clothing", "", "");
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        ArrayList result = new ArrayList();
        result.add(ArmorPart.Legs);
        result.add(ArmorPart.Chest);
        result.add(ArmorPart.Head);
        return result;
    }

    @Override
    public String getItemName() {
        return "armor" + armorPart.getPartId();
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
        return 0;
    }

    @Override
    public ItemStack getRepairMaterial(ItemStack input) {
        return null;
    }

    @Override
    protected double getBaseDefense() {
        return 0;
    }

    @Override
    protected double getMaxDefense() {
        return 0;
    }

    @Override
    protected int getDurability() {
        return 0;
    }
}
