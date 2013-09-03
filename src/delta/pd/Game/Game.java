package delta.pd.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import delta.pd.Lobby;
import delta.pd.Main;
import delta.pd.Listener.LootDrop;
import delta.pd.Listener.PlayerJoin;
import delta.pd.Util.FireworkEffectPlayer;
import delta.pd.sql.SQL;
import delta.pd.sql.stats.PlayerDeaths;
import delta.pd.sql.stats.PlayerHeists;
import delta.pd.sql.stats.PlayerKills;
import delta.pd.sql.stats.PlayerMoney;
import delta.pd.sql.stats.StatSearch;

public class Game {

	static Game instance = new Game();

	public static Game getInstance() {
		return instance;
	}

	int cd = 31;
	int countdown;

	int gameScoreTask;
	int lobbyScoreTask;

	public List<String> inLobby = new ArrayList<String>();
	public List<String> inGame = new ArrayList<String>();
	public List<String> spectators = new ArrayList<String>();

	public int onlinePlayers = Bukkit.getOnlinePlayers().length;
	public int maxPlayers = Bukkit.getMaxPlayers();

	public ScoreboardManager sbm = Bukkit.getScoreboardManager();

	public Scoreboard lobbysb = sbm.getNewScoreboard();
	public Objective lobbyobj = lobbysb.registerNewObjective("Titlelobby",
			"dummy");

	public Scoreboard gamesb = sbm.getNewScoreboard();
	public Objective gameobj = lobbysb.registerNewObjective("Titlegame",
			"dummy");

	public boolean escape = false;
	public boolean canLootBeSecured = true;

	FireworkEffectPlayer fplayer = new FireworkEffectPlayer();

	/*
	 * 
	 * End Header
	 */

	public boolean isPlayerInGame(Player p) {

		if (inGame.contains(p.getName())) {

			return true;

		}

		return false;

	}

	public boolean isPlayerInLobby(Player p) {

		if (inLobby.contains(p.getName())) {

			return true;

		}

		return false;

	}

	public void removeFromGame(Player p) {

		inGame.remove(p.getName());

	}

	public void removeFromLobby(Player p) {

		inLobby.remove(p.getName());

	}

	public void addToLobby(Player p) {

		inLobby.add(p.getName());

	}

	public void addToGame(Player p) {

		inGame.add(p.getName());

	}

	public boolean isPlayerSpectating(Player p) {

		if (spectators.contains(p.getName())) {

			return true;

		}

		return false;

	}

	public void addToSpectators(Player p) {

		spectators.add(p.getName());

	}

	public void removeFromSpectators(Player p) {

		spectators.remove(p.getName());

	}

	public void broadcastGame(String s) {

		for (int x = 0; x < inGame.size(); x++) {

			Player p = Bukkit.getServer().getPlayer(inGame.get(x));

			p.sendMessage(s);

		}
	}

	public void broadcastLobby(String s) {

		for (int x = 0; x < inLobby.size(); x++) {

			Player p = Bukkit.getServer().getPlayer(inLobby.get(x));

			p.sendMessage(s);

		}

	}

	public void broadcastSpectators(String s) {

		for (int x = 0; x < spectators.size(); x++) {

			Player p = Bukkit.getPlayer(spectators.get(x));

			p.sendMessage(s);

		}

	}

	public void fillLobbyInv(Player p) throws ClassNotFoundException,
			SQLException {

		p.getInventory().clear();

		p.getInventory().setArmorContents(null);

		StatSearch.setBookStats(p, p.getName());

		ItemStack leave = new ItemStack(Material.COMPASS);
		ItemMeta lm = leave.getItemMeta();
		lm.setDisplayName(ChatColor.RED + "Leave Game");
		leave.setItemMeta(lm);

		/*
		 * ItemStack shop = new ItemStack(Material.DIAMOND); ItemMeta sm =
		 * shop.getItemMeta(); sm.setDisplayName(ChatColor.GREEN + "Shop");
		 * shop.setItemMeta(sm);
		 */

		p.getInventory().addItem(leave);

	}

	public void startGame() throws ClassNotFoundException, SQLException {

		for (int x = 0; x < inLobby.size(); x++) {

			Player p = Bukkit.getPlayer(inLobby.get(x));

			Lobby.getInstance().teleportToGame(p);

			p.getInventory().clear();

			p.setGameMode(GameMode.SURVIVAL);

			inGame.add(p.getName());

			p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
			ItemStack bow = new ItemStack(Material.BOW);
			bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			p.getInventory().addItem(bow);
			p.getInventory().addItem(new ItemStack(Material.ARROW, 1));

			ResultSet rs = SQL.getStatement().executeQuery(
					"SELECT mask FROM payday WHERE username ='"
							+ inLobby.get(x) + "';");

			String maskname = null;

			ItemStack mask = new ItemStack(Material.SKULL_ITEM);
			SkullMeta sm = (SkullMeta) mask.getItemMeta();
			mask.setDurability((short) 3);

			rs = SQL.getStatement().executeQuery(
					"SELECT mask FROM payday WHERE username LIKE '%"
							+ inLobby.get(x) + "';");
			if (rs.next()) {
				maskname = rs.getString(1);
				sm.setOwner(maskname);
			}

			SQL.getConnection().close();

			p.getInventory().setHelmet(mask);

			setGameScoreboard(p);

		}

		broadcastGame(ChatColor.DARK_AQUA + "Heist started on map "
				+ ChatColor.YELLOW
				+ Main.getInstance().getConfig().getString("Map-Name"));
		broadcastGame(ChatColor.DARK_AQUA
				+ Main.getInstance().getConfig().getString("Mission-Objective"));

	}

	public void setLobbyScoreboard(Player p) throws IllegalStateException,
			ClassNotFoundException, SQLException {

		lobbyobj.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD
				+ "PD - Lobby");
		lobbyobj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score kills = lobbyobj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY
				+ "Kills:"));
		kills.setScore(PlayerKills.getPlayerKills(p));

		Score deaths = lobbyobj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY
				+ "Deaths:"));
		deaths.setScore(PlayerDeaths.getPlayerDeaths(p));

		Score heists = lobbyobj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY
				+ "Heists:"));
		heists.setScore(PlayerHeists.getPlayerHeists(p));

		Score money = lobbyobj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY
				+ "Money:"));
		money.setScore(PlayerMoney.getPlayerMoney(p));

		Score playersneeded = lobbyobj.getScore(Bukkit
				.getOfflinePlayer(ChatColor.GRAY + "NeededPlayers:"));
		int pn = Main.getInstance().getConfig().getInt("Players-Needed");
		playersneeded.setScore(pn - inLobby.size());

		p.setScoreboard(lobbysb);

	}

	public void setGameScoreboard(Player p) {

		gameobj.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD
				+ "PD - Game");
		gameobj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score hostages = gameobj.getScore(Bukkit
				.getOfflinePlayer(ChatColor.GRAY + "Hostages:"));
		hostages.setScore(0);

		Score lootcaptured = gameobj.getScore(Bukkit
				.getOfflinePlayer(ChatColor.GRAY + "Loot Captured:"));
		lootcaptured.setScore(LootDrop.ln);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
				new Runnable() {

					@Override
					public void run() {

						for (int x = 0; x < inGame.size(); x++) {

							Player p = Bukkit.getPlayer(inGame.get(x));

							p.setScoreboard(gamesb);

						}

					}

				}, 0, 5);

	}

	public void updateScoreboardLobby(Player p) {

		for (int x = 0; x < inLobby.size(); x++) {

			Player pscb = Bukkit.getPlayer(inLobby.get(x));

			pscb.setScoreboard(lobbysb);

		}

	}

	public void updateScoreboardGame(Player p) {

		for (int x = 0; x < inGame.size(); x++) {

			Player pscb = Bukkit.getPlayer(inGame.get(x));

			pscb.setScoreboard(gamesb);

		}

	}

	public void countDown() {

		countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(
				Main.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (cd != 0) {
							cd--;
						}

						if (cd == 60) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd
									+ ChatColor.DARK_AQUA
									+ " seconds left until heist!");
						}

						if (cd == 30) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd
									+ ChatColor.DARK_AQUA
									+ " seconds left until heist!");
						}

						if (cd < 11 && cd != 0) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + "" + cd
									+ ChatColor.DARK_AQUA
									+ " seconds left until heist!");
						}

						if (cd == 0) {
							cd = 61;
							Bukkit.getScheduler().cancelTask(countdown);

							try {
								startGame();
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							Bukkit.getScheduler().scheduleSyncDelayedTask(
									Main.getInstance(), new Runnable() {

										@Override
										public void run() {
											for (int x = 0; x < inLobby.size(); x++) {

												Player p = Bukkit.getServer()
														.getPlayer(
																inLobby.get(x));

												inLobby.remove(p.getName());

											}
										}

									}, 20);

						}

					}

				}, 0, 20L);

	}

	public void endFail() {

		Bukkit.broadcastMessage(ChatColor.DARK_AQUA
				+ "You are all in custody! Missioned failed!");

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
				new Runnable() {

					@Override
					public void run() {

						for (Player p : Bukkit.getOnlinePlayers()) {

							p.kickPlayer(ChatColor.RED + "Mission Failed");

						}

						Bukkit.getServer().shutdown();

					}
				}, 5 * 20);

	}

	public void endWin() {

		inLobby.clear();
		spectators.clear();

		reset();

		for (int x = 0; x < inGame.size(); x++) {

			Player p = Bukkit.getPlayer(inGame.get(x));

			Lobby.getInstance().teleportToWin(p);

			p.setWalkSpeed(1F);
			
		}

		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
				new Runnable() {

					@Override
					public void run() throws IllegalArgumentException {

						for (int x = 0; x < inGame.size(); x++) {

							Player p = Bukkit.getServer().getPlayer(
									inGame.get(x));

							try {
								fplayer.playFirework(
										p.getWorld(),
										p.getLocation().add(0, 5, 0),
										FireworkEffect.builder()
												.with(Type.STAR)
												.withColor(Color.ORANGE)
												.build());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

				}, 0, 10);

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
				new Runnable() {

					@Override
					public void run() {

						for (Player p : Bukkit.getOnlinePlayers()) {

							p.kickPlayer(ChatColor.GOLD + "Successful heist!");

						}

						Bukkit.getServer().shutdown();

					}

				}, 5 * 20);

	}

	public void reset() {

		Bukkit.getWorld("world").getEntities().clear();

	}

}
