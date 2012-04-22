package info.bytecraft.configuration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="bytecraft_teleport")
public class TeleportBlock {

	@Id
	public int id;
	
	@NotNull
	public String playerName;
	
	@NotNull
	public boolean block;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(playerName);
	}
	
	public void setPlayer(Player player){
		this.playerName = player.getName();
	}
	
	public boolean isBlock(){
		return this.block;
	}
	
	public void setBlock(boolean block){
		this.block = block;
	}
}
