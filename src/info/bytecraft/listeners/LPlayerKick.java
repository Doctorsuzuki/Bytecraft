package info.bytecraft.listeners;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/** @author Sabersamus <rcatron10@gmail.com> */
public class LPlayerKick implements Listener{
    
    @EventHandler
    public void onKick(PlayerKickEvent event){
        event.setLeaveMessage(null);
        event.setReason(null);
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Random random = new Random();
        String dname = event.getPlayer().getDisplayName();
        int x = random.nextInt(10);
        if(x==1){
            event.setQuitMessage(ChatColor.YELLOW + "-Quit-" + ChatColor.AQUA + " Goodbye " + event.getPlayer().getDisplayName() + ChatColor.AQUA + " hope to see you later");
        }else if(x==2){
             event.setQuitMessage(ChatColor.YELLOW + "-Quit-" + dname + ChatColor.DARK_GREEN + " has left the server");
        }else if(x==3){
            event.setQuitMessage(ChatColor.YELLOW + "-Quit-" + dname + ChatColor.BLUE + " will be back, don't worry!");
        }else if(x==4){
            event.setQuitMessage(ChatColor.YELLOW + "-Quit-" + dname + ChatColor.YELLOW + " smashed their face on the keyboard just a little too hard");
        }else{
            event.setQuitMessage(ChatColor.YELLOW + "-Quit-" + ChatColor.AQUA + " Goodbye " + event.getPlayer().getDisplayName() + ChatColor.AQUA + " hope to see you later");
        }
    }
}
