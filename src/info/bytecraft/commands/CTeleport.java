package info.bytecraft.commands;

import info.bytecraft.Bytecraft;
import info.bytecraft.configuration.TeleportBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CTeleport implements CommandExecutor{
	private static Bytecraft plugin;
	public CTeleport(Bytecraft instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tp")){
			if(cs instanceof Player){
				final Player player = (Player)cs;
			if(args.length == 1){
				final Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					TeleportBlock block = plugin.getDatabase().find(TeleportBlock.class).where().ieq("playerName", target.getName()).findUnique();
						if(block != null && block.isBlock()){
							if(!player.hasPermission("bytecraft.teleport.override")){
								player.sendMessage(ChatColor.RED + "I'm sorry, " + target.getDisplayName() + ChatColor.RED + " doesn't allow teleporting");
								target.sendMessage(ChatColor.RED + "LOL "+ player.getDisplayName() + ChatColor.RED + " failed at teleporting to you!");
								return true;
							}else{
								if(player.hasPermission("bytecraft.teleport.silent")){
									player.sendMessage(ChatColor.AQUA + "Teleporting to " + target.getDisplayName());
									player.teleport(target.getLocation());
									return true;
								}else{
									player.sendMessage(ChatColor.AQUA + "Teleporting to " + target.getDisplayName());
									target.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has teleported to you");
									player.teleport(target.getLocation());
								}
							}
						}else if(block == null || !block.isBlock()){
							if(!player.hasPermission("bytecraft.teleport.admin")){
								player.sendMessage(ChatColor.AQUA + "Teleporting to " + target.getDisplayName());
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									@Override
									public void run(){
										player.teleport(target.getLocation());
										target.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has teleported to you");
									}
								}, 100L);
								return true;
							}else{
								player.sendMessage(ChatColor.AQUA + "Teleporting to " + target.getDisplayName());
								player.teleport(target.getLocation());
								return true;
							}
						}
					}else{
						return true;
					}
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
		return false;
	}

}
