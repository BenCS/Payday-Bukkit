package delta.pd.sql.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import delta.pd.sql.SQL;

public class PlayerMoney {

    public static void setPlayerMoneyCMD(String player, int value) throws SQLException, ClassNotFoundException {

        int money = 0;

        ResultSet rs = SQL.getStatement().executeQuery("SELECT money FROM payday WHERE username ='" + player + "';");

        if (rs.next()) {

            money = value;
            SQL.getStatement().execute("UPDATE payday SET money=" + money + " WHERE username='" + player + "'");

        }

        SQL.getConnection().close();
    }
	
}