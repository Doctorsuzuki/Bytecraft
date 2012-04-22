package info.bytecraft.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This project was created under a custom license.
 * From here on out the term "author", refers to - Sabersamus -
 * the author of this project.
 * From here on out the term "plugin" or "project" refers to the Bukkit
 * plugin coded for the author's personal server Bytecraft.
 * You may use the code found in this project for personal use so long as 
 * you give credit the author of the class, somewhere within your code.
 * If you want to add onto this project, you must contact me at my email address
 * as shown in the java docs.
 * 
 * @author Sabersamus <rcatron10@gmail.com>
 * @deprecated 
 */
public interface IHome 
{
	@Deprecated
	public void setHome(Player player, Location loc);
	
	@Deprecated
	public Location getHome(Player player);

}
