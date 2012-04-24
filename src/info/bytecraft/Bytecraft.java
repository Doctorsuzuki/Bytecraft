package info.bytecraft;

import info.bytecraft.commands.*;
import info.bytecraft.listeners.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import info.bytecraft.configuration.HomesDatabase;
import info.bytecraft.configuration.TeleportBlock;
import info.bytecraft.configuration.Warps;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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

    private void registerCommands()
    {        
        this.getCommand("tpblock").setExecutor(new CTpBlock(this));
        this.getCommand("tpblock").setPermissionMessage("");
        this.getCommand("tp").setExecutor(new CTeleport(this));
        this.getCommand("tp").setPermissionMessage("");
    	
        this.getCommand("warp").setExecutor(new CWarp(this));
        this.getCommand("warp").setPermissionMessage("");
        this.getCommand("home").setExecutor(new CHome(this));
        this.getCommand("pos").setExecutor(new CPosition(this));
    }
    
    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new LPlayerKick(), this);
        pm.registerEvents(new LPlayerJoin(this), this);
        pm.registerEvents(new SignWarp(this), this);
        pm.registerEvents(new LCompass(), this);
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
