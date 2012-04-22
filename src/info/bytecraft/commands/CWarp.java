package info.bytecraft.commands;

import info.bytecraft.Bytecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import info.bytecraft.configuration.Warps;

public class CWarp implements CommandExecutor {

    private static Bytecraft plugin;
    public CWarp(Bytecraft instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("warp")){
        	if(args.length == 1){
        		if(cs instanceof Player){
        			final Player player = (Player)cs;
        		String name = String.valueOf(args[0]).toLowerCase();
                final Warps warp = plugin.getDatabase().find(Warps.class).where().ieq("name", name).findUnique();
                if (warp == null) {
                    return true;
                }else{
                	player.sendMessage(ChatColor.DARK_AQUA + "Warping to " + ChatColor.GOLD + warp.getName() + ChatColor.DARK_AQUA + "....");
                   plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                	  @Override
                	  public void run(){
                		  player.teleport(warp.getLocation());
                		  player.sendMessage(ChatColor.AQUA + warp.getMessage());
                	  }
                   }, 100L);
                }
                return true;
        		}
        	}else{
        		return true;
        	}
        }
        return false;
    }
}
