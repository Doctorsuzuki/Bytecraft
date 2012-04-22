package info.bytecraft.commands;

import info.bytecraft.Bytecraft;

import info.bytecraft.configuration.HomesDatabase;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import info.bytecraft.annotation.DataType;
import info.bytecraft.annotation.EntityData;

/**
 * This project was created under a custom license. From here on out the term
 * "author", refers to - Sabersamus - the author of this project. From here on
 * out the term "plugin" or "project" refers to the Bukkit plugin coded for the
 * author's personal server Bytecraft. You may use the code found in this
 * project for personal use so long as you give credit the author of the class,
 * somewhere within your code. If you want to add onto this project, you must
 * contact me at my email address as shown in the java docs.
 * 
 * @author Sabersamus <rcatron10@gmail.com>
 */
@EntityData(dataType = DataType.CLASS, entityType = EntityType.PLAYER)
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
				final HomesDatabase home = plugin.getDatabase().find(HomesDatabase.class).where().ieq("name", player.getName()).findUnique();
				if(home == null){
					return true;
				}else{
					player.sendMessage(ChatColor.AQUA + "The stars have aligned, allowing you to teleport to your home");
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
					HomesDatabase home = plugin.getDatabase().find(HomesDatabase.class).where().ieq("name", player.getName()).findUnique();
					if(home == null){
						home = new HomesDatabase();
						home.setName(player);
						home.setLocation(player.getLocation());
						plugin.getDatabase().save(home);
					}else{
						home.setLocation(player.getLocation());
						plugin.getDatabase().refresh(home);
					}
					player.sendMessage(ChatColor.DARK_AQUA + "Your home has been saved successfully");
					return true;
				}
			}
		}
		return false;
	}

}
