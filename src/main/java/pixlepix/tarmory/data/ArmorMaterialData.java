package pixlepix.tarmory.data;

import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.tools.ToolMaterial;

import java.util.HashMap;

/**
 * Created by pixlepix on 7/21/15.
 */
public class ArmorMaterialData {
    
    public static HashMap<ToolMaterial, ArmorMaterial> materials = new HashMap();
    
    public static void init(){
        addMaterial("Wood", 0, 0, 0, 6, 0);
        addMaterial("Stone", 4, 1, 0, 0, 0);
        addMaterial("Iron", 15, 1, 0, 0, 0);
        addMaterial("Flint", 2, 0, 5, 0, 0);
        addMaterial("Cactus", 0, 2, 0, 0, 0);
        addMaterial("Bone", 6, 0, 3, 0, 5);
        addMaterial("Obsidian", 0, 3, 0, 0, 0);
        addMaterial("Netherrack", 4, 0, 0, 3, 3);
        addMaterial("Slime", 6, 0, 3, 8, 3);
        addMaterial("Paper", 1, 0, 7, 0, 6);
        addMaterial("Cobalt", 15, 1, 0, 4, 2);
        addMaterial("Ardite", 12, 0, 0, 12, 2);
        addMaterial("Manyullyn", 15, 1, 0, 8, 2);
        addMaterial("Copper", 14, 1, 1, 2, 0);
        addMaterial("Bronze", 12, 1, 0, 4, 4);
        addMaterial("Alumite", 15, 1, 0, 0, 0);
        addMaterial("Steel", 13, 0, 0, 2, 2);
        addMaterial("BlueSlime", 6, 0, 6, 6, 3);
        addMaterial("PigIron", 10, 0, 0, 5, 5);
        addMaterial("Thaumium", 15, 0, 0, 0, 8);
    }

    public static void addMaterial(String materialId, int protection, int resistance, int agility, int absorption, int warding){
        addMaterial(materialId, new ArmorMaterial(protection, resistance, agility, absorption, warding));
    }
    
    public static void addMaterial(String materialId, ArmorMaterial data){
        materials.put(TConstructRegistry.getMaterial(materialId), data);
    }
    
    public static ArmorMaterial getArmorProperties(ToolMaterial material){
        return materials.get(material);
        
    }
    public static class ArmorMaterial{

        public final int protection;
        public final int resistance;
        public final int agility;
        public final int absorption;
        public final int warding;

        public ArmorMaterial(int protection, int resistance, int agility, int absorption, int warding){

            this.protection = protection;
            this.resistance = resistance;
            this.agility = agility;
            this.absorption = absorption;
            this.warding = warding;
        }
        
    }
    
}
