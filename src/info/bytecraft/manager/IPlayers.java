package info.bytecraft.manager;

import org.bukkit.*;
import org.bukkit.entity.*;

/**
 * @deprecated 
 */
public interface IPlayers 
{
	public boolean hasHome(Player player);
	
	public Location getHome(Player player);
	
	public void setHome(Player player, Location loc);
	
	public boolean getTelportBlock(Player player);
	
	public void setTeleportBlock(Player player, boolean bln);
	
}
