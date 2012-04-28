package info.bytecraft.manager;

import info.bytecraft.configuration.Warp;

import org.bukkit.Location;

@Deprecated
public interface IWarp {
	@Deprecated
	public Location getLocation();
	@Deprecated
	public Location getLocation(Warp warp);
	@Deprecated
	public void deleteWarp(Warp warp);
	@Deprecated
	public void deleteWarp();

}
