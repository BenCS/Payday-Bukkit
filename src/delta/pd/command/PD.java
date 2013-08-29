package delta.pd.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import delta.pd.Lobby;
import delta.pd.Main;

public class PD implements CommandExecutor {

	ChatColor g = ChatColor.GRAY;
	ChatColor b = ChatColor.BLUE;
	ChatColor r = ChatColor.RED;
	ChatColor u = ChatColor.STRIKETHROUGH;
	
	int id;
	int switchint = 1;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		final Player p = (Player) sender;
		
		String prefix = g + "[" + b + "PayDay" + g + "] ";
		
		if(label.equalsIgnoreCase("pd") || label.equalsIgnoreCase("payday")) {

			/*
			 * Begin Args0
			 */
			
			if(args.length == 0) {
				
				p.sendMessage(g + "" + u + "--------------------" + ChatColor.GRAY + ChatColor.BOLD + "[ " + b + "PayDay" + g + ChatColor.BOLD + " ]" + u + "--------------------");
				p.sendMessage(g + "Payday for MinecraftUniverse developed by " + r + "BenCS_ " + g + "and" );
				p.sendMessage(r + "LegendSight. " + g + "**CURRENTLY IN ALPHA!!!!** Report bugs on forums!");
				
				return true;
			
			}
			
			/*
			 * End Args0
			 * 
			 * Begin Args1
			 * 
			 */
			
			if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("lobby")) {
					
                    if (Lobby.getInstance().teleportToLobby(p)) {

                        p.sendMessage(prefix + ChatColor.GRAY + "Teleporting to lobby!");

                    } else if (!Lobby.getInstance().teleportToLobby(p)) {

                        p.sendMessage(prefix + ChatColor.RED + "ERROR: Lobby not found! Please tell server staff!");
                    }
                    
					return true;
					
				}
				
				else if(args[0].equalsIgnoreCase("setlobby")) {
					
					Lobby.getInstance().setLobby(p);
					p.sendMessage(prefix + "Main Lobby set!");
					
					return true;
					
				}
				
			}
			
			/*
			 * End Args1
			 */
			
		}
		
		p.sendMessage(prefix + "ERROR: Unknown arguments!");
		return false;
	}

}
