package me.jonasxpx.warpchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Metodos {
	
	private static JavaPlugin jp;
	
	public static void loadConfigYml(JavaPlugin javaplugin){
		jp = javaplugin;
		for(int x = 0; x <= jp.getConfig().getInt("Configuracao.QuantiaDeSlots")-1; x++){
			jp.getConfig().addDefault("Slot."+x+".SlotID", x);
			jp.getConfig().addDefault("Slot."+x+".ItemID", 102);
			jp.getConfig().addDefault("Slot."+x+".Name", "&6EndCraft");
			jp.getConfig().addDefault("Slot."+x+".Config", "cmd/tele");
			jp.getConfig().addDefault("Slot."+x+".PlayerCommands", "cmd");
			jp.getConfig().addDefault("Slot."+x+".OpCommands", "cmd");
			jp.getConfig().addDefault("Slot."+x+".Teleport", "0;0;0;0;0");
		}
		jp.saveConfig();
	}

	@SuppressWarnings("deprecation")
	public static Inventory open(){
		Inventory inv = Bukkit.createInventory(null, 27, "Warp System");
		ItemStack i;
		ItemMeta m;
		for(int x = 0; x <= 26; x++){
			i = new ItemStack(Slot(x).getItemID());
			m = i.getItemMeta();
			m.setDisplayName(Slot(x).getName());
			i.setItemMeta(m);
			inv.setItem(x, i);
		}
		return inv;
	}
	
	public static SlotConfig Slot(int slot){
		return new SlotConfig(jp, slot);
	}

	public static void setLocationTeleport(Player player, int slot){
		Location loc = player.getLocation();
		jp.getConfig().set("Slot."+slot+".Teleport", loc.getWorld().getName()+";"+loc.getX()+";"+loc.getY()+";"+loc.getZ()+";"+loc.getYaw()+";"+loc.getPitch());
		jp.saveConfig();
	}
}
