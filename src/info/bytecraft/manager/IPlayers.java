package info.bytecraft.manager;

import org.bukkit.*;
import org.bukkit.entity.*;

@Deprecated
public interface IPlayers 
{
	@Deprecated
	public boolean hasHome(Player player);
	
	@Deprecated
	public Location getHome(Player player);
	
	@Deprecated
	public void setHome(Player player, Location loc);
	
	@Deprecated
	public boolean getTelportBlock(Player player);
	
	@Deprecated
	public void setTeleportBlock(Player player, boolean bln);
	
}
