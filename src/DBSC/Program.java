package DBSC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		Scanner sc = new Scanner(System.in); 
		System.out.println("[게시글 검색 프로그램]");
		System.out.print("제목 검색어 입력 : ");
		String userInput = sc.next();
		String order = "SELECT ID, TITLE, WRITER_ID FROM NOTICE WHERE TITLE LIKE '%"+userInput+"%'";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = order;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String id = rs.getString("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			System.out.printf("ID:%s, TITLE:%s, WRITER_ID:%s\n", id, title, writerId);

		}

	}
}
