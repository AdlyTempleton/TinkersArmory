package pixlepix.tarmory.main.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import pixlepix.tarmory.data.ArmorCalc;

/**
 * Created by pixlepix on 12/16/14.
 */
public class EventHandler {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onDamage(LivingHurtEvent event){
        if(event.entity instanceof EntityPlayer){
            event.ammount = ArmorCalc.calculateDamage(event.ammount, (EntityPlayer) event.entity, event.source);
        }
    }
}
