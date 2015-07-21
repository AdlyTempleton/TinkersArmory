package pixlepix.tarmory.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import pixlepix.tarmory.TArmory;
import pixlepix.tarmory.item.ArmorBase;
import tconstruct.library.armor.ArmorPart;

import java.util.Random;

/**
 * Created by pixlepix on 7/21/15.
 */
public class ArmorCalc {
    
    public static double armorPercent(ArmorPart part){
        switch(part) {
            case Head:
                return .15D;
            case Chest:
                return .4D;
            case Legs:
                return .3D;
            case Feet:
                return .15D;
            default:
                return 0D;
        }
    }
    
    public static float calculateDamage(float initialDamage, EntityPlayer player, DamageSource type){
        double agility = 0D;
        double warding = 0D;
        double resistance = 0D;
        double protection = 0D;
        
        for(ArmorPart part:ArmorPart.values()){
            ItemStack stack = player.getCurrentArmor(part.getPartId());
            if(stack != null && stack.getItem() instanceof ArmorBase){
                ArmorMaterialData.ArmorMaterial material = ArmorBase.getArmorMaterial(stack);
                if(material != null){
                    agility += armorPercent(part) * material.agility;
                    warding += armorPercent(part) * material.warding;
                    resistance += armorPercent(part) * material.resistance;
                    protection += armorPercent(part) * material.protection;
                }else{
                    TArmory.log.error("Null value for armor material. This is a bug.");
                    
                }
            }
        }
        
        
        Random r = new Random();
        if(r.nextDouble() < .1D * agility){
            return 0F;
        }
        if(type.isExplosion() || type.isMagicDamage() || type == DamageSource.outOfWorld|| type.isProjectile()){
            return (float) (initialDamage * (1 - .08F * warding));
        }else{
            if(!type.isUnblockable()){
                initialDamage -= resistance;
                initialDamage *= (1 - .04F * protection);
                return initialDamage;
            }
        }
        return initialDamage;
    }
    
}
