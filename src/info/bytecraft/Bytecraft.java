package info.bytecraft;

import info.bytecraft.commands.*;
import info.bytecraft.manager.Manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import info.bytecraft.configuration.HomesDatabase;
import info.bytecraft.configuration.TeleportBlock;
import info.bytecraft.configuration.Warps;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import info.bytecraft.listeners.LPlayerJoin;
import info.bytecraft.listeners.LPlayerKick;

/**
 * This project was created under a custom license.
 * From here on out the term "author", refers to - Sabersamus -
 * the author of this project.
 * From here on out the term "plugin" or "project" refers to the Bukkit
 * plugin coded for the author's personal server Bytecraft.
 * You may use the code found in this project for personal use so long as 
 * you give credit the author of the class, somewhere within your code.
 * If you want to add onto this project, you must contact me at my email address
 * as shown in the java docs. This project also contains code from another plugin called "Bytes".
 * Certain methods, such as those in the {@link BUser} and the {@link User} classes use methods created
 * in the dependant plugin.
 * 
 * @author Sabersamus <rcatron10@gmail.com>
 */
@SuppressWarnings("deprecation")
public class Bytecraft extends JavaPlugin 
{
    @Override
    public void onDisable()
    {

    }

    @Override
    public void onEnable()
    {
    	this.setupHomeDatabase();
    	this.setupTeleportDatabase();
    	this.setupWarpsDatabase();
        
        this.registerCommands();
        this.registerEvents();
        
    }

    /**
     * @deprecated - using MySQL tables from now on.
     */
    public Manager getManager()
    {
        return new Manager(this);
    }

    private void registerCommands()
    {        
        this.getCommand("tpblock").setExecutor(new CTpBlock(this));
        this.getCommand("tp").setExecutor(new CTeleport(this));
    	
        this.getCommand("warp").setExecutor(new CWarp(this));
        this.getCommand("home").setExecutor(new CHome(this));
        this.getCommand("pos").setExecutor(new CPosition(this));
    }
    
    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new LPlayerKick(), this);
        pm.registerEvents(new LPlayerJoin(), this);
    }
    	
    private void setupHomeDatabase() {
        try {
           getDatabase().find(HomesDatabase.class).findRowCount();
        }catch (PersistenceException ex) {
            installDDL();
        }
    }
    
    private void setupWarpsDatabase(){
    	try{
    		getDatabase().find(Warps.class).findRowCount();
    	}catch(PersistenceException ex){
    		installDDL();
    	}
    }
    
    private void setupTeleportDatabase(){
    	try{
    		getDatabase().find(TeleportBlock.class).findRowCount();
    	}catch(PersistenceException ex){
    		installDDL();
    	}
    }
    
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(HomesDatabase.class);
        list.add(Warps.class);
        list.add(TeleportBlock.class);
        return list;
    }
}
