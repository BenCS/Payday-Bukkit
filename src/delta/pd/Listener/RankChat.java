package delta.pd.Listener;

import java.sql.SQLException;
import java.util.IllegalFormatException;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import delta.pd.Main;
import delta.pd.sql.stats.PlayerMoney;

public class RankChat implements Listener {
    
	
	@EventHandler
	public void onPlayerChatPD(AsyncPlayerChatEvent e) throws IllegalFormatException, ClassNotFoundException, NullPointerException, SQLException {
		
		double money = PlayerMoney.money;
		
		if(Main.getInstance().doesPlayerExist(e.getPlayer().getName())) {
		
			e.setFormat(ChatColor.GOLD + "[" + money + "] " + e.getFormat());
		
		} else {
			
			e.setFormat(ChatColor.GOLD + "[NO MONEY] " + e.getFormat());
			
		}
		
	}
	
}
