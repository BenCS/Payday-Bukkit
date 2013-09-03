package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import delta.pd.sql.SQL;

public class PlayerKills {

    public static void setPlayerKillsCMD(String player, int value) throws SQLException, ClassNotFoundException {

        int kills = 0;

        ResultSet rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username ='" + player + "';");

        if (rs.next()) {

            kills = rs.getInt(1) + value;
            SQL.getStatement().execute("UPDATE payday SET kills=" + kills + " WHERE username='" + player + "'");

        }

        SQL.getConnection().close();
    }
    
	public static int kills = 0;
	
    public static int getPlayerKills(Player player) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username ='" + player.getName() + "';");

        rs = SQL.getStatement().executeQuery("SELECT kills FROM payday WHERE username LIKE '%" + player.getName() + "';");
        if (rs.next()) {
            kills = rs.getInt(1);
        }

        SQL.getConnection().close();
        
        return kills;
    }
	
}
