package pixlepix.tarmory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import pixlepix.tarmory.registry.BlockRegistry;
import pixlepix.tarmory.registry.ITTinkererItem;
import pixlepix.tarmory.registry.ThaumicTinkererRecipe;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.armor.ArmorCore;
import tconstruct.library.armor.ArmorPart;
import tconstruct.library.tools.CustomMaterial;
import tconstruct.library.tools.ToolMaterial;

import java.util.ArrayList;

/**
 * Created by pixlepix on 7/18/15.
 */
public class ArmorBase extends ArmorCore implements ITTinkererItem {

    public static final String NBT_TAG_MAT = "ARMOR_MATERIAL";

    public tconstruct.library.tools.ToolMaterial getMaterial(ItemStack stack){
        if(stack.stackTagCompound != null){
            return TConstructRegistry.getMaterial(stack.stackTagCompound.getInteger(NBT_TAG_MAT));
        }
        return null;
    }

    public static ArmorBase getArmor(int i){
        for(Item item: BlockRegistry.getItemFromClass(ArmorBase.class)){
            if(item instanceof ArmorBase && ((ArmorBase)item).armorPart == ArmorPart.values()[i]){
                return (ArmorBase) item;
            }
        }
        return null;
    }

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

    public int getMaterialID (ItemStack stack)
    {
        if (TConstructRegistry.toolMaterials.keySet().contains(stack.getItemDamage()))
            return stack.getItemDamage();

        return -1;
    }

    @Override
    public String getItemStackDisplayName (ItemStack stack) {
        String material = "";
        String matName = "";

        //if (customMaterialClass == null)
        //{
        tconstruct.library.tools.ToolMaterial toolmat = TConstructRegistry.getMaterial(getMaterialID(stack));
        if(toolmat == null) {
            return super.getItemStackDisplayName(stack);
        }
        

        material = toolmat.localizationString.substring(9); // :(
        matName = toolmat.prefixName();
        /*}
        else
        {
            CustomMaterial customMaterial = TConstructRegistry.getCustomMaterial(getMaterialID(stack), customMaterialClass);
            if (customMaterial == null)
                return super.getItemStackDisplayName(stack);

            material = "";
            if (customMaterial.input != null)
            {
                material = customMaterial.input.getUnlocalizedName();
                int firstPeriodIndex = material.indexOf('.');
                if (firstPeriodIndex >= 0)
                    material = material.substring(firstPeriodIndex+1);
                matName = customMaterial.input.getDisplayName();
            }
            else
            {
                material = customMaterial.oredict;
                matName = customMaterial.oredict;
            }
        }

        */
        
        String partName = armorPart.name();
        // custom name
        if (StatCollector.canTranslate("toolpart." + partName + "." + material))
        {
            return StatCollector.translateToLocal("toolpart." + partName + "." + material);
        }
        // general name
        else
        {
            // specific material name for materials?
            if(StatCollector.canTranslate("toolpart.material." + material))
                matName = StatCollector.translateToLocal("toolpart.material." + material);

            return StatCollector.translateToLocal("toolpart." + partName).replaceAll("%%material", matName);
        }
    }
}
