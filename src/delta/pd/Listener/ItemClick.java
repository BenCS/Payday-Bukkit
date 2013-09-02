package delta.pd.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import delta.pd.Main;
import delta.pd.Util.IconMenu;

public class ItemClick implements Listener {

	@EventHandler
	public void onPlayerInteractLeaveCompass(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) | e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		
			if(p.getItemInHand().getType().equals(Material.COMPASS) && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Leave Game"))  {
		
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
        		out.writeUTF("Connect");
        		out.writeUTF(Main.getInstance().getConfig().getString("Payday-Lobby-Server"));
        		p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
		
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractShopPD(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) | e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if(p.getItemInHand().getType().equals(Material.DIAMOND) && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Shop")) {
				
		        IconMenu menu = new IconMenu("Shop", 9, new IconMenu.OptionClickEventHandler() {
		            @Override
		            public void onOptionClick(IconMenu.OptionClickEvent event) {
		                event.setWillClose(true);
		                event.setWillDestroy(true);
		            }
		        }, Main.getInstance());
		        menu.setOption(4, new ItemStack(Material.BAKED_POTATO), ChatColor.DARK_RED + "" + ChatColor.BOLD + "RIPPED VAGINAS", ChatColor.GRAY + "No really ill do this later");
				menu.open(p);
			
			}
		}
		
	}
	
}
