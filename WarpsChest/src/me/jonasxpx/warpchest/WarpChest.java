package me.jonasxpx.warpchest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpChest extends JavaPlugin{
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		Metodos.loadConfigYml(this);
		getServer().getPluginManager().registerEvents(new ListenerEvets(), this);
		getCommand("warp").setExecutor(this);
		getCommand("warpchest").setExecutor(new OutroComando());
		
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		String cmd = "";
		if(args.length == 0){
			((Player)sender).openInventory(Metodos.open());
			return true;
		}
		for(int x = 0; x <= args.length-1; x++){
			cmd = cmd + " " + args[x];
		}
		Bukkit.dispatchCommand(sender, "warps "+cmd.replaceFirst(" ", ""));
		return true;
	}
	public class OutroComando implements CommandExecutor{

		@Override
		public boolean onCommand(CommandSender sender, Command arg1, String arg2,
				String[] arg3) {
			if(arg3.length == 0){
				sender.sendMessage("§m§a-----------------[§6WarpChest§m§a]-----------------");
				sender.sendMessage("§a /warpchest setlocation (SLOT) - §b Marca a posiçao atual no slot informado");
				return true;
			}
			if(arg3.length == 2){
				if(arg3[0].equalsIgnoreCase("setlocation")){
					try{
					Metodos.setLocationTeleport((Player)sender, Integer.parseInt(arg3[1]));
					}catch(NumberFormatException e){sender.sendMessage("§cUse apenas numeros");}
					return true;
				}
			}
			return false;
		}
		
	}
}
