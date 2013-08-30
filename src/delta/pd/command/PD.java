package delta.pd.command;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import delta.pd.Lobby;
import delta.pd.Main;
import delta.pd.sql.stats.StatSearch;

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
				
				else if(args[0].equalsIgnoreCase("help")) {
					
					p.sendMessage(g + "" + u + "--------------------" + ChatColor.GRAY + ChatColor.BOLD + "[ " + b + "PayDay" + g + ChatColor.BOLD + " ]" + u + "--------------------");
					p.sendMessage(b + "/pd -" + g + "Index Command");
					p.sendMessage(b + "/pd help -" + g + "Shows this screen");
					p.sendMessage(b + "/pd lobby - " + g + "Go to the lobby");
					//This plugin is only for the game, lobby is a completely separate plugin.
					
				}
				
				else if(args[0].equalsIgnoreCase("stats")) {
					
					try {
						StatSearch.getPlayerData(p, p.getName());
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return true;
					
				}
				
			}
			
			/*
			 * End Args1
			 * 
			 * Begin Args2
			 * 
			 */
			
			if(args.length == 2) {
				
			}
			
		}
		
		p.sendMessage(prefix + "ERROR: Unknown arguments!");
		return false;
	}

}
