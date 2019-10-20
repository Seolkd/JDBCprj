package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		// Driver driver = new oracle.jdbc.driver.OracleDriver();
		// DriverManaver.registerDriver(Driver);
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = "SELECT TITLE, HIT FROM NOTICE WHERE HIT in (0,2,7)";
		Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // 커넥션 객체 생성
		Statement st = con.createStatement(); // 스테트먼트 객체 생성
		ResultSet rs = st.executeQuery(sql); // 리졸트셋 객체 생성 - 전달받은 값을 담는 그릇

		while (rs.next()) {
			String title = rs.getString("title");
			int hit = rs.getInt("hit");
			System.out.printf("title:%s, hit:%d\n", title, hit);

		}

		// 어디에도 new가 없다. = 그 안에서 생성하고 그 결과를 반환하고 있다.
		// Class -> RTTI(Runtime Type Information) 클래스
		// 두가지 방식으로 얻을 수 있다.
		// Class 객체를 얻는 방법 두가지
//		ArrayList list = new ArrayList();
//		Class cls = list.getCless();// - 객체
//		Class cls1 = ArrayList.class; //- 타입명
//		
//		Method[] methods = cls1.getMethods();
//		// -> reflection (거꾸로 꺼내보는 것)
//		
//		for(Method m : methods)
//		System.out.println(m.getName());

//		String clsName = "ex1.Exam";
//		new clsName(); <- 이방법은 안됨. forname밖에 없다.

//		Class cls = Class.forName("ex1.Exam"); //클래스 정보를 읽어오다.
//		Exam exam = (Exam) cls.newInstance();
//		exam.setKor(10);
//		
//		System.out.printf("total:%d, avg:%f\n", exam.total(),exam.avg());

		// 클래스를 파일에서 읽어들이는 방식이기 때문에 코드소스는 그대로 쓸 수 있다.

//		DriverManager;
//		Connection;
//		Statement;
//		ResultSet;
	}

}
