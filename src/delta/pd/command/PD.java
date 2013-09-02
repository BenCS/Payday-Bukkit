package delta.pd.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import delta.pd.Lobby;
import delta.pd.Main;
import delta.pd.Game.Game;
import delta.pd.Util.WorldEditUtility;

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
				p.sendMessage(g + "Payday for MinecraftUniverse developed by " + b + "BenCS_ " + g + "and" );
				p.sendMessage(b + "LegendSight. " + g + "**CURRENTLY IN ALPHA!!!!** Report bugs on forums!");
				
				return true;
			
			}
			
			/*
			 * End Args0
			 * 
			 * Begin Args1
			 * 
			 */
			
			if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("setlobby")) {
					
					if(p.hasPermission("pd.admin")) {
					
						Lobby.getInstance().setLobby(p);
						p.sendMessage(prefix + "Main Lobby set!");
					
						return true;
					}
					
				}
				
				else if(args[0].equalsIgnoreCase("setwin")) {
					
					if(p.hasPermission("pd.admin")) {
					
						Lobby.getInstance().setLobby(p);
						p.sendMessage(prefix + "Win Lobby set!");
					
						return true;
					}
					
				}
				
				else if(args[0].equalsIgnoreCase("setgamespawn")) {
					
					if(p.hasPermission("pd.admin")) {
					
						Lobby.getInstance().setGameSpawn(p);
						p.sendMessage(prefix + "Game spawn set!");
					
						return true;
					}
					
				}
				
				else if(args[0].equalsIgnoreCase("reload")) {
					
					if(p.hasPermission("pd.admin")) {
						
						Main.getInstance().reloadConfig();
						p.sendMessage(ChatColor.GREEN + "Reloaded config!");
						
					}
					
					return true;
					
				}
				
				else if(args[0].equalsIgnoreCase("help")) {
					
					p.sendMessage(g + "" + u + "--------------------" + ChatColor.GRAY + ChatColor.BOLD + "[ " + b + "PayDay" + g + ChatColor.BOLD + " ]" + u + "--------------------");
					p.sendMessage(b + "/pd -" + g + " Index Command");
					p.sendMessage(b + "/pd help -" + g + " Shows this screen");
					p.sendMessage(b + "/pd lobby - " + g + " Go to the lobby");
					//This plugin is only for the game, lobby is a completely separate plugin.
					
					return true;
					
				}
				
				else if(args[0].equalsIgnoreCase("createarena")) {
				
					if(p.hasPermission("pd.admin")) {
					
						if(WorldEditUtility.getInstance().doesSelectionExist(p)) {
					
							WorldEditUtility.getInstance().setArena(p);
							p.sendMessage(ChatColor.DARK_AQUA + "Arena " + ChatColor.YELLOW + "successfully " + ChatColor.DARK_AQUA + "created!");
						
						} else {
						
							p.sendMessage(prefix + "No selection!");
						
						}
					}
						
					return true;
					
				}
				
				else if(args[0].equalsIgnoreCase("createlootdrop")) {
					
					if(p.hasPermission("pd.admin")) {
						
						if(WorldEditUtility.getInstance().doesSelectionExist(p)) {
							
							WorldEditUtility.getInstance().setLootDropZone(p);
							p.sendMessage(ChatColor.DARK_AQUA + "Lootcap " + ChatColor.YELLOW + "successfully " + ChatColor.DARK_AQUA + "created!");
						
						} else {
						
							p.sendMessage(prefix + "No selection!");
						
						}
						
					}
					
					return true;
					
				}
				
				else if(args[0].equalsIgnoreCase("listtest")) {
					
					if(Game.getInstance().isPlayerInLobby(p)) {
					
						p.sendMessage("lobby");
						
					}
					
					if(Game.getInstance().isPlayerInGame(p)) {
						
						p.sendMessage("game");
						
					}
					
				}
				
				else if(args[0].equalsIgnoreCase("createescape")) {
					
					if(p.hasPermission("pd.admin")) {
						
						if(WorldEditUtility.getInstance().doesSelectionExist(p)) {
							
							WorldEditUtility.getInstance().setEscapeZone(p);
							p.sendMessage(ChatColor.DARK_AQUA + "Escape " + ChatColor.YELLOW + "successfully " + ChatColor.DARK_AQUA + "created!");
						
						} else {
						
							p.sendMessage(prefix + "No selection!");
						
						}
						
					}
					
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
