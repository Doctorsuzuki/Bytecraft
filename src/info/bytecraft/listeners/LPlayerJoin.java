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
	ChatColor green = ChatColor.GREEN;
	ChatColor purple = ChatColor.DARK_PURPLE;
	private String admin = "bytecraft.admin";
	private String guardian = "bytecraft.guardian";
	private String builder = "bytecraft.builder";
	private String donor = "bytecraft.donator";
	private String member = "bytecraft.member";
	private String child = "bytecraft.child";

	
	  @EventHandler
	  public void onJoin(PlayerLoginEvent event)
	  {
	    Player player = event.getPlayer();
	    String name = player.getName();
	    if (player.hasPermission(this.admin)) {
	      player.setDisplayName(this.red + name + this.white);
	      player.setPlayerListName(this.red + name);
	      return;
	    }
	    if (player.hasPermission(this.guardian)) {
	      player.setDisplayName(this.blue + name + this.white);
	      player.setPlayerListName(this.blue + name);
	      return;
	    }
	    if (player.hasPermission(this.builder)) {
	      player.setDisplayName(this.purple + name + this.white);
	      player.setPlayerListName(this.purple + name);
	      return;
	    }
	    if (player.hasPermission(this.donor)) {
	      player.setDisplayName(this.yellow + name + this.white);
	      player.setPlayerListName(this.yellow + name + this.white);
	      return;
	    }
	    if (player.hasPermission(this.member)) {
	      player.setDisplayName(this.dgreen + name + this.white);
	      player.setPlayerListName(this.dgreen + name);
	      return;
	    }
	    if (player.hasPermission(this.child)) {
	      player.setDisplayName(this.green + name + this.white);
	      player.setPlayerListName(this.green + name);
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
		event.setJoinMessage(ChatColor.DARK_AQUA + "Please welcome " + event.getPlayer().getDisplayName() + ChatColor.DARK_AQUA + " to bytecraft + from ");
	}

}
