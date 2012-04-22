package info.bytecraft.commands;

import info.bytecraft.Bytecraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CPosition implements CommandExecutor {
	protected static Bytecraft plugin;
	public CPosition(Bytecraft instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("pos") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				ChatColor green = ChatColor.DARK_GREEN;
				ChatColor white = ChatColor.WHITE;
				Location loc = player.getLocation();
				player.sendMessage(green + "World: " + white + loc.getWorld().getName());
				player.sendMessage(green + "X: " + white + loc.getX());
				player.sendMessage(green + "Y: " + white + loc.getY());
				player.sendMessage(green + "Z: " + white + loc.getZ());
				player.sendMessage(green + "Yaw: " + white + loc.getYaw());
				player.sendMessage(green + "Pitch: " + white + loc.getPitch());
				return true;
			}
		}
		return false;
	}

}
