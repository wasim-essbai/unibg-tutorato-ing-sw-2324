package contatti.db_sqlite;


import java.io.IOException;
import java.sql.*;

public class SelectFromTable {

	public static void main(String args[]) throws IOException, SQLException {

		Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM INDIRIZZI";
		System.out.println("risultati:");
		ResultSet resultSet = stmt.executeQuery(sql);
		// stampa i risultati
		while (resultSet.next()) {
			for (int i = 1; i <= 2; i++) {
				System.out.print(resultSet.getString(i) + " ");
			}
			System.out.println();
		}
		stmt.close();
		conn.close();
		System.out.println("query eseguita con successo");
	}
}