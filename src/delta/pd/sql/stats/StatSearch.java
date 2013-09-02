package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import delta.pd.sql.SQL;

public class StatSearch {

    public static void getPlayerData(Player player, String target) throws SQLException, ClassNotFoundException {
		String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "PayDay" + ChatColor.GRAY + "] ";
        String username = null;
        int heists = 0;
        double kills = 0, deaths = 0, money = 0;
        ResultSet rs = SQL.getStatement().executeQuery("SELECT COUNT(*) FROM payday WHERE username LIKE '%" + target + "';");
        rs.next();
        if (rs.getInt(1) != 0) {
            rs = SQL.getStatement().executeQuery("SELECT username FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                username = rs.getString(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                kills = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                deaths = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT money FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                money = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT losses FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                heists = rs.getInt(1);
            }
            
            player.sendMessage(prefix + "Stats:");
            player.sendMessage(ChatColor.GRAY + "Kills:" + ChatColor.RED + (int) kills);
            player.sendMessage(ChatColor.GRAY + "Deaths:" + ChatColor.RED + (int) deaths);
            player.sendMessage(ChatColor.GRAY + "Money:" + ChatColor.RED + (int) money);
            player.sendMessage(ChatColor.GRAY + "Heists:" + ChatColor.RED + heists);
            
        } else {
        	
        	player.sendMessage(prefix + ChatColor.RED + "Player not found");
        	
        }
        
    }
    
	public static ItemStack statbook = new ItemStack(Material.WRITTEN_BOOK);
	
    public static void setBookStats(Player player, String target) throws SQLException, ClassNotFoundException {
		String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "PayDay" + ChatColor.GRAY + "] ";
        String username = null;
        int heists = 0;
        double kills = 0, deaths = 0, money = 0;
        ResultSet rs = SQL.getStatement().executeQuery("SELECT COUNT(*) FROM payday WHERE username LIKE '%" + target + "';");
        rs.next();
        if (rs.getInt(1) != 0) {
            rs = SQL.getStatement().executeQuery("SELECT username FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                username = rs.getString(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                kills = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT deaths FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                deaths = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT money FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                money = rs.getInt(1);
            }
            rs = SQL.getStatement().executeQuery("SELECT heists FROM payday WHERE username LIKE '%" + target + "';");
            if (rs.next()) {
                heists = rs.getInt(1);
            }
            
            ItemStack statbook = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bm = (BookMeta) statbook.getItemMeta();
            bm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Stats");
            bm.addPage("\n" + ChatColor.GOLD + ChatColor.BOLD + "        Stats\n\n" + ChatColor.GRAY + "Username: " + ChatColor.RED + username + ChatColor.GRAY + "\nKills: " + ChatColor.RED + kills + ChatColor.GRAY + "\nDeaths: " + ChatColor.RED + deaths + ChatColor.GRAY + "\nMoney: " + ChatColor.RED + money + ChatColor.GRAY + "\nHeists: " + ChatColor.RED + heists);
            statbook.setItemMeta(bm);
            player.getInventory().addItem(statbook);
            
            
           /* player.sendMessage(prefix + "Stats for " + username + ":");
            player.sendMessage(ChatColor.GOLD + "Kills: " + ChatColor.GRAY + (int) kills);
            player.sendMessage(ChatColor.GOLD + "Deaths: " + ChatColor.GRAY + (int) deaths);

            if (deaths != 0) {

                double kd = kills / deaths;
                double kdd = (double) Math.round(kd * 100) / 100;

                player.sendMessage(ChatColor.GOLD + "K/D: " + ChatColor.GRAY + kdd);
            }

            player.sendMessage(ChatColor.GOLD + "Wins: " + ChatColor.GRAY + (int) wins);
            player.sendMessage(ChatColor.GOLD + "Losses: " + ChatColor.GRAY + (int) losses);

            if (losses != 0) {

                double wl = wins / losses;
                double wld = (double) Math.round(wl * 100) / 100;

                player.sendMessage(ChatColor.GOLD + "W/L: " + ChatColor.GRAY + wld);
            }

            player.sendMessage(ChatColor.GOLD + "Played: " + ChatColor.GRAY + played);
        */} else {
            player.sendMessage(prefix + ChatColor.RED + "You do not have stats yet!");
        }
    }
    
}
