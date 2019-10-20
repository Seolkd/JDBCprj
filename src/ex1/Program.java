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
		Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̹� �ε�
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec"); // Ŀ�ؼ� ��ü ����
		Statement st = con.createStatement(); // ����Ʈ��Ʈ ��ü ����
		ResultSet rs = st.executeQuery(sql); // ����Ʈ�� ��ü ���� - ���޹��� ���� ��� �׸�

		while (rs.next()) {
			String title = rs.getString("title");
			int hit = rs.getInt("hit");
			System.out.printf("title:%s, hit:%d\n", title, hit);

		}

		// ��𿡵� new�� ����. = �� �ȿ��� �����ϰ� �� ����� ��ȯ�ϰ� �ִ�.
		// Class -> RTTI(Runtime Type Information) Ŭ����
		// �ΰ��� ������� ���� �� �ִ�.
		// Class ��ü�� ��� ��� �ΰ���
//		ArrayList list = new ArrayList();
//		Class cls = list.getCless();// - ��ü
//		Class cls1 = ArrayList.class; //- Ÿ�Ը�
//		
//		Method[] methods = cls1.getMethods();
//		// -> reflection (�Ųٷ� �������� ��)
//		
//		for(Method m : methods)
//		System.out.println(m.getName());

//		String clsName = "ex1.Exam";
//		new clsName(); <- �̹���� �ȵ�. forname�ۿ� ����.

//		Class cls = Class.forName("ex1.Exam"); //Ŭ���� ������ �о����.
//		Exam exam = (Exam) cls.newInstance();
//		exam.setKor(10);
//		
//		System.out.printf("total:%d, avg:%f\n", exam.total(),exam.avg());

		// Ŭ������ ���Ͽ��� �о���̴� ����̱� ������ �ڵ�ҽ��� �״�� �� �� �ִ�.

//		DriverManager;
//		Connection;
//		Statement;
//		ResultSet;
	}

}
