package info.bytecraft.commands;

import info.bytecraft.Bytecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import info.bytecraft.configuration.TeleportBlock;

public class CTpBlock implements CommandExecutor {
	private static Bytecraft plugin;
	public CTpBlock(Bytecraft instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tpblock") && args.length == 1){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(player.hasPermission("bytecraft.tpblock")){
					String usage = args[0];
					TeleportBlock block = plugin.getDatabase().find(TeleportBlock.class).where().ieq("playerName", player.getName()).findUnique();
					if(usage.equalsIgnoreCase("on")){
						if(block == null){
							block = new TeleportBlock();
							block.setPlayer(player);
							block.setBlock(true);
							plugin.getDatabase().save(block);
						}else{
							block.setBlock(true);
							plugin.getDatabase().save(block);
						}
						player.sendMessage(ChatColor.DARK_AQUA + "Your tpblock has been activated");
						return true;
					}else if(usage.equalsIgnoreCase("off")){
						if(block == null){
							block = new TeleportBlock();
							block.setPlayer(player);
							block.setBlock(false);
							plugin.getDatabase().save(block);
						}else{
							block.setBlock(false);
							plugin.getDatabase().save(block);
						}
						player.sendMessage(ChatColor.DARK_AQUA + "Your tpblock has been de-activated");
						return true;
					}else if(usage.equalsIgnoreCase("status")){
						if(block == null || !block.isBlock()){
							player.sendMessage(ChatColor.DARK_AQUA + "Your tpblock is: off");
						}else{
							player.sendMessage(ChatColor.DARK_AQUA + "Your tpblock is: on");
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
