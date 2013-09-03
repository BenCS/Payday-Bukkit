package delta.pd.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockLoot implements Listener {

	@EventHandler
	public void onPlayerInteractLOOT(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		
		if(!e.getClickedBlock().getType().equals(Material.GOLD_BLOCK)) return;
		
		ItemStack loot = new ItemStack(Material.GOLD_BLOCK, 1);
		ItemMeta lm = loot.getItemMeta();
		lm.setDisplayName(ChatColor.GOLD + "GOLD");
		loot.setItemMeta(lm);
		
		if(!p.getInventory().contains(loot)) {
			
			p.getInventory().addItem(loot);
			
			p.setWalkSpeed(.04F);
		
		} else {
			
			p.sendMessage(ChatColor.RED + "You can only carry one at a time!");
			
		}
		
	}
	
}
