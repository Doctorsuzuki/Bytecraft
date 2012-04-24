package info.bytecraft.commands;

import info.bytecraft.Bytecraft;

import info.bytecraft.configuration.HomesDatabase;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/** @author Sabersamus <rcatron10@gmail.com> */
public class CHome implements CommandExecutor {
	private static Bytecraft plugin;

	public CHome(Bytecraft instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home") && cs instanceof Player) {
			final Player player = (Player)cs;
			if(args.length == 0){
				final HomesDatabase home = plugin.getDatabase().find(HomesDatabase.class).where().ieq("playerName", player.getName()).findUnique();
				if(home == null){
					return true;
				}else{
					player.sendMessage(ChatColor.AQUA + "The stars have aligned, allowing you to teleport to your home");
                    /*
                        Don't mind the message, really i know its stupid.
                    */
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run(){
							player.teleport(home.getLocation());
						}
					}, 100L);
					return true;
				}
			}else if(args.length == 1){
				String usage = String.valueOf(args[0]);
				if(usage.equalsIgnoreCase("save")){
					HomesDatabase home = plugin.getDatabase().find(HomesDatabase.class).where().ieq("playerName", player.getName()).findUnique();
					if(home == null){
						home = new HomesDatabase();
						home.setPlayer(player);
						home.setLocation(player.getLocation());
						plugin.getDatabase().save(home);
					}else{
						Location loc = player.getLocation();
						home.setLocation(loc);
						plugin.getDatabase().save(home);
					}
					player.sendMessage(ChatColor.DARK_AQUA + "Your home has been saved successfully");
					return true;
				}
			}
		}
		return false;
	}

}
