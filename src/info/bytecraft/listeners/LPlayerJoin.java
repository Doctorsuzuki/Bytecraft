package info.bytecraft.listeners;

import info.bytecraft.Bytecraft;
import info.bytecraft.basiccommands.Players;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LPlayerJoin implements Listener {
	private static Bytecraft plugin;
	public LPlayerJoin(Bytecraft instance){
		plugin = instance;
	}
	
	ChatColor white = ChatColor.WHITE;
	ChatColor gold = ChatColor.GOLD;
	ChatColor blue = ChatColor.BLUE;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor red = ChatColor.RED;
	ChatColor dgreen = ChatColor.DARK_GREEN;
	ChatColor green = ChatColor.DARK_GREEN;
	ChatColor purple = ChatColor.DARK_PURPLE;
	private String admin = "bytecraft.admin";
	private String guardian = "bytecraft.guardian";
	private String builder = "bytecraft.builder";
	private String donor = "bytecraft.donator";
	private String member = "bytecraft.member";
	private String child = "bytecraft.child";
	
	@EventHandler
	public void onJoin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		if(player.hasPermission(admin)){
			player.setDisplayName(red + name + white);
			player.setPlayerListName(red + name);
			return;
		}
		if(player.hasPermission(guardian)){
			player.setDisplayName(blue + name + white);
			player.setPlayerListName(blue + name);
			return;
		}
		if(player.hasPermission(builder)){
			player.setDisplayName(purple + name + white);
			player.setPlayerListName(purple + name);
			return;
		}
		if(player.hasPermission(donor)){
			player.setDisplayName(yellow + name + white);
			player.setPlayerListName(yellow + name + white);
			return;
		}
		if(player.hasPermission(member)){
			player.setDisplayName(dgreen + name + white);
			player.setPlayerListName(dgreen + name);
			return;
		}
		if(player.hasPermission(child)){
			player.setDisplayName(green + name + white);
			player.setPlayerListName(green + name);
			return;
		}
		
	}
	
	@EventHandler
	public void onEnter(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Players players = plugin.getServer().getPluginManager().getPlugin("Basic commands").getDatabase().find(Players.class).where().ieq("playerName", player.getName()).findUnique();
		if(players != null && players.isVanished()){
			event.setJoinMessage(null);
			return;
		}
		event.setJoinMessage(ChatColor.DARK_AQUA + "Please welcome " + event.getPlayer().getDisplayName() + ChatColor.DARK_AQUA + " to bytecraft");
	}

}
