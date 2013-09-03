package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import delta.pd.sql.SQL;

public class PlayerHeists {

	    public static void setPlayerHeistsCMD(String player, int value) throws SQLException, ClassNotFoundException {

	        int heists = 0;

	        ResultSet rs = SQL.getStatement().executeQuery("SELECT heists FROM payday WHERE username ='" + player + "';");

	        if (rs.next()) {

	            heists = rs.getInt(1) + value;
	            SQL.getStatement().execute("UPDATE heists SET kills=" + heists + " WHERE username='" + player + "'");

	        }

	        SQL.getConnection().close();
	    }
	    
		public static int heists = 0;
		
	    public static int getPlayerHeists(Player player) throws SQLException, ClassNotFoundException {

	        ResultSet rs = SQL.getStatement().executeQuery("SELECT heists FROM payday WHERE username ='" + player.getName() + "';");

	        rs = SQL.getStatement().executeQuery("SELECT heists FROM payday WHERE username LIKE '%" + player.getName() + "';");
	        if (rs.next()) {
	            heists = rs.getInt(1);
	        }

	        SQL.getConnection().close();
	        
	        return heists;
	    }
	
}
