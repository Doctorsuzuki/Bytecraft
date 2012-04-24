package info.bytecraft.configuration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="bytecraft_homes")
public class HomesDatabase 
{

    @Id
    private int id;

    @Length(max=30)
    @NotEmpty
    private String playerName;
    
    @NotNull
    private double x;

    @NotNull
    private double y;

    @NotNull
    private double z;

    @NotNull
    private float pitch;

    @NotNull
    private float yaw;

    @NotEmpty
    private String worldName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayer(Player player) {
        this.playerName = player.getName();
    }
    
    public void setPlayerName(String name){
    	this.playerName = name;
    }
    
    public Player getPlayer(){
    	return Bukkit.getPlayer(playerName);
    }

    public World getWorld(){
        return Bukkit.getWorld(worldName);
    }

    public String getWorldName(){
    	return this.worldName;
    }
    
    public void setWorld(World world){
    	this.worldName = world.getName();
    }
    
    public void setWorldName(String world){
    	this.worldName = world;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setLocation(Location loc){
    	this.setWorld(loc.getWorld());
    	this.setX(loc.getX());
    	this.setY(loc.getY());
    	this.setZ(loc.getZ());
    	this.setPitch(loc.getPitch());
    	this.setYaw(loc.getYaw());
    }

    public Location getLocation() {
        World world = Bukkit.getServer().getWorld(worldName);
        return new Location(world, x, y, z, yaw, pitch);
    }
}
