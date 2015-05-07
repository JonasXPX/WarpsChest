package me.jonasxpx.warpchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SlotConfig {

	private int SlotID;
	private JavaPlugin jp;
	
	public SlotConfig(JavaPlugin jp, int SlotID){
		this.jp = jp;
		this.SlotID = SlotID;
	}

	public int getItemID(){
		return this.jp.getConfig().getInt("Slot."+SlotID+".ItemID");
	}
	
	public boolean isCmd(){
		if(this.jp.getConfig().getString("Slot."+SlotID+".Config").equalsIgnoreCase("tele")){
			return false;
		}else{
			return true;
		}
	}
	
	public Location getLocation(){
		String st[] = this.jp.getConfig().getString("Slot."+SlotID+".Teleport").split(";");
		return new Location(Bukkit.getWorld(st[0]),
				Double.parseDouble(st[1]),
				Double.parseDouble(st[2]),
				Double.parseDouble(st[3]),
				Float.parseFloat(st[4]),
				Float.parseFloat(st[5]));
	}
	
	public void execCommand(Player player){
		System.out.println(SlotID);
		String pcmd[] = this.jp.getConfig().getString("Slot."+SlotID+".PlayerCommands").split(",");
		String opcmd[] = this.jp.getConfig().getString("Slot."+SlotID+".OpCommands").split(",");
		for(int x = 0; x <= pcmd.length-1; x++){
			if(!(pcmd[x].equalsIgnoreCase("null") || pcmd[x].equalsIgnoreCase("cmd"))){
				Bukkit.dispatchCommand(player, pcmd[x]);
			}
		}
		for (int i = 0; i < opcmd.length-1; i++) {
			if(!(opcmd[i].equalsIgnoreCase("null") || opcmd[i].equalsIgnoreCase("cmd"))){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), opcmd[i]);
			}
		}
	}
	
	/**
	 * @return retorna o Nome do item customisado da config.yml
	 */
	public String getName(){
		return this.jp.getConfig().getString("Slot."+SlotID+".Name").replaceAll("&", "§");
	}
	
}
