package info.bytecraft.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @deprecated 
 */
public interface IHome 
{
	@Deprecated
	public void setHome(Player player, Location loc);
	
	@Deprecated
	public Location getHome(Player player);

}
