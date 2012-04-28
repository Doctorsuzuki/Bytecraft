package info.bytecraft.commands;

import java.util.List;

import info.bytecraft.Bytecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import info.bytecraft.configuration.Warp;

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
        		if(name.equalsIgnoreCase("list")){
        			List<Warp> warps = plugin.getDatabase().find(Warp.class).where().ieq("worldName", player.getWorld().getName()).findList();
        			StringBuilder m = new StringBuilder();
        			for(Warp warp: warps){
        				if(warps.size() > 1){
        					m.append(ChatColor.GOLD + warp.getName() + ChatColor.WHITE + ", ");
        				}else{
        					m.append(ChatColor.GOLD + warp.getName());
        				}
        			}
    				player.sendMessage(ChatColor.DARK_GREEN + "Warps in your world: " + m.toString());
    				return true;
        		}else{
                final Warp warp = plugin.getDatabase().find(Warp.class).where().ieq("name", name).findUnique();
                if (warp == null) {
                    return true;
                }else{
                	player.sendMessage(ChatColor.DARK_AQUA + "Warping to " + ChatColor.GOLD + warp.getName() + ChatColor.DARK_AQUA + "....");
                   plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                	  @Override
                	  public void run(){
                		  player.teleport(warp.getLocation());
                	  }
                   }, 100L);
                }
                return true;
        			}
        		}
        	}else{
        		return true;
        	}
        }
        return false;
    }
}
