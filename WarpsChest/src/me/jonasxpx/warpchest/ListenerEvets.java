package me.jonasxpx.warpchest;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerEvets implements Listener{
	
	
	
	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent e){
		if(e.getInventory().getName().equals("Warp System")){
			int slot = e.getSlot();
			if(Metodos.Slot(e.getSlot()).isCmd()){
				Metodos.Slot(slot).execCommand(((Player)e.getWhoClicked()));
			}else{
				((Player)e.getWhoClicked()).teleport(Metodos.Slot(slot).getLocation());
			}
			((Player)e.getWhoClicked()).playSound(((Player)e.getWhoClicked()).getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 2.0F);
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
		}
	}

}
