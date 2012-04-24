package info.bytecraft.listeners;

import info.bytecraft.Bytecraft;
import info.bytecraft.bytes.Bytes;
import info.bytecraft.bytes.api.Economy;
import info.bytecraft.configuration.Warps;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignWarp implements Listener {
	private static Bytecraft plugin;
	public SignWarp(Bytecraft instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(event.getClickedBlock().getState() instanceof Sign){
				Sign sign = (Sign)event.getClickedBlock().getState();
				if(sign.getLines().length == 4){
					plugin.getLogger().info("Debug message four");
					if(sign.getLine(0).equalsIgnoreCase("[warp]")){
						plugin.getLogger().info("Debug message five");
						if(sign.getLine(2).equalsIgnoreCase("")){
						String name = sign.getLine(1).toLowerCase();
						Warps warps = plugin.getDatabase().find(Warps.class).where().ieq("name", name).findUnique();
						if(warps != null){
							plugin.getLogger().info("Debug message six");
							event.getPlayer().sendMessage(warps.getMessage());
							event.getPlayer().teleport(warps.getLocation());
						}else{
							event.getPlayer().sendMessage(ChatColor.RED + "No warp was found by the name of " + sign.getLine(1).toLowerCase());
							}
						}else{
							int amount = Integer.parseInt(sign.getLine(2));
							Economy eco = ((Bytes)plugin.getServer().getPluginManager().getPlugin("Bytes")).getEconomy();
							String name = sign.getLine(1).toLowerCase();
							Warps warps = plugin.getDatabase().find(Warps.class).where().ieq("name", name).findUnique();
							if(warps != null){
								plugin.getLogger().info("Debug message nine");
								if(Economy.hasEnough(event.getPlayer(), amount)){
									plugin.getLogger().info("Debug message ten");
								eco.subtractMoney(event.getPlayer(), amount);
								event.getPlayer().sendMessage(ChatColor.GOLD + "" + amount + ChatColor.AQUA + " bytes have been removed from your account");
								event.getPlayer().sendMessage(warps.getMessage());
								event.getPlayer().teleport(warps.getLocation());
								}
							}else{
								event.getPlayer().sendMessage(ChatColor.RED + "No warp was found by the name of " + sign.getLine(1).toLowerCase());
							}
						}
					}
				}/*else if(sign.getLines().length == 4){
					plugin.getLogger().info("Debug message seven");
					if(sign.getLine(0).equalsIgnoreCase("[warp]")){
						plugin.getLogger().info("Debug message eight");
						int amount = Integer.parseInt(sign.getLine(2));
						Economy eco = ((Bytes)plugin.getServer().getPluginManager().getPlugin("Bytes")).getEconomy();
						String name = sign.getLine(1).toLowerCase();
						Warps warps = plugin.getDatabase().find(Warps.class).where().ieq("name", name).findUnique();
						if(warps != null){
							plugin.getLogger().info("Debug message nine");
							if(Economy.hasEnough(event.getPlayer(), amount)){
								plugin.getLogger().info("Debug message ten");
							eco.subtractMoney(event.getPlayer(), amount);
							event.getPlayer().sendMessage(warps.getMessage());
							event.getPlayer().teleport(warps.getLocation());
							}
						}else{
							event.getPlayer().sendMessage(ChatColor.RED + "No warp was found by the name of " + sign.getLine(1).toLowerCase());
						}
					}
				}*/
			}
		}
	}
}
