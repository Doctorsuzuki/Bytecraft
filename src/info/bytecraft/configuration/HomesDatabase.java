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
 */
@Entity()
@Table(name="bytecraft_homes")
public class HomesDatabase 
{

    @Id
    private int id;

    @Length(max=30)
    @NotEmpty
    private String name;
    
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

    public String getName() {
        return name;
    }

    public void setName(Player player) {
        this.name = player.getName();
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public Player getPlayer(){
    	return Bukkit.getPlayer(name);
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
