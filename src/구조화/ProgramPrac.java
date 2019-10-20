package 구조화;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//printNoticeList //printNoticeDetail

public class ProgramPrac {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);

		boolean finish = false;
		while (!finish) {

			switch (inputMainMenu()) {
			case 1:
				listMember();
				break;
			case 2:
				listNotice();
				break;
			case 3:
				finish = true;
				break;
			default:
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}
		}
	}

	private static int inputMainMenu() {
		System.out.println("메인메뉴");
		System.out.println("1. 회원관리");
		System.out.println("2. 게시글 관리");
		System.out.println("3. 종료");
		System.out.print("> ");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		return menu;
	}

	private static void listMember() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "SELECT * FROM (SELECT ID, NAME, TO_CHAR(REGDATE,'YYYY-MM-DD') "
				+ "REGDATE FROM MEMBER ORDER BY ID DESC) WHERE ROWNUM BETWEEN 1 and 20";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		printListMember(rs);


		close(con,st,rs);
		
		memberMenu();
	}

	private static void printListMember(ResultSet rs) throws SQLException {
		System.out.println("------------------------");
		System.out.println("<멤버 목록>");
		System.out.println("------------------------");

		while (rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			String regdate = rs.getString("REGDATE");
			System.out.printf("%s(%s) / %s \n", id, name, regdate);
		}
		System.out.println("------------------------");
	}

	private static void memberMenu() throws ClassNotFoundException, SQLException {
		boolean finish = false;
		while (!finish) {
			switch (inputMemberMenu()) {

			case 1:
				System.out.print("ID >");
				Scanner sc = new Scanner(System.in);
				String id = sc.next();
				viewMember(id);
				break;
			case 2:
				findMember();
				break;
			case 3:
				addMember();
				break;
			case 4:
				finish = true;
				break;
			default:
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}
		}

	}

	private static int inputMemberMenu() {
		System.out.println("1.조회  2.검색  3.등록  4.상위메뉴로  >");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		return menu;
	}

	private static void viewMember(String id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "SELECT ID, NAME, GENDER, "
				+ " BIRTHDAY, TO_CHAR(REGDATE,'YYYY-MM-DD') REGDATE, PHONE, EMAIL "
				+ "FROM MEMBER WHERE ID='"+id+"'";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		printListViewMember(rs);
		
		close(con,st,rs);
		
		viewMemberMenu();

	}

	private static void close(Connection con, Statement st, ResultSet rs) throws SQLException {
		rs.close();		
		st.close();
		con.close();
	}

	private static void viewMemberMenu()  throws ClassNotFoundException, SQLException {
		boolean finish = false;
		while (!finish) {
			switch (inputViewMemberMenu()) {

			case 1:
				editMember();
				break;
			case 2:
				delMember();
				break;
			case 3:
				finish = true;
				break;
			default:
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}
		}

	}

	private static void editMember() { 
		System.out.println("지금은 회원을 수정할 수 없습니다.");
	}

	private static void delMember() {
		System.out.println("지금은 회원을 삭제할 수 없습니다.");
		
	}

	private static int inputViewMemberMenu() {
		System.out.println("1.수정  2.삭제  3.목록  >");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		return menu;
	}

	private static void printListViewMember(ResultSet rs) throws SQLException {
		while (rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			String gender = rs.getString("GENDER");
			String birthday = rs.getString("BIRTHDAY");
			String regdate = rs.getString("REGDATE");
			String phone = rs.getString("PHONE");
			String email = rs.getString("EMAIL");

			System.out.println("------------------------");
			System.out.println("<게시글 내용>");
			System.out.println("------------------------");
			System.out.printf("ID : %s\n이름 : %s\n성별 : %s\n생일 : %s\n가입일 : %s\n전화번호 : %s\nE-mail: %s\n",
					id, name, gender, birthday, regdate, phone, email);
		}

	}

	private static void findMember() {
		System.out.println("여기는 회 검색프로그램이 위치할 예정입니다.");

	}

	private static void addMember() {
		System.out.println("여기는 회원 등록하기가 위치할 예정입니다.");

	}

	private static void listNotice() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "SELECT * FROM (SELECT ID, TITLE, WRITER_ID, TO_CHAR(REGDATE,'YYYY-MM-DD') "
				+ "REGDATE FROM NOTICE ORDER BY ID DESC) WHERE ROWNUM BETWEEN 1 and 5";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		printListNotice(rs);


		close(con,st,rs);
		
		noticeMenu();

	}

	private static void printListNotice(ResultSet rs) throws SQLException {
		System.out.println("------------------------");
		System.out.println("<게시글 목록>");
		System.out.println("------------------------");
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			String regdate = rs.getString("REGDATE");
			System.out.printf("%s.%s(%s) / %s\n", id, title, writerId, regdate);
		}
		System.out.println("------------------------");
	}

	private static void noticeMenu() throws ClassNotFoundException, SQLException {
		boolean finish = false;
		while (!finish) {
			switch (inputNoticeMenu()) {

			case 1:
				System.out.print("코드 >");
				Scanner sc = new Scanner(System.in);
				int id = sc.nextInt();
				viewNotice(id);
				break;
			case 2:
				findNotice();
				break;
			case 3:
				addNotice();
				break;
			case 4:
				finish = true;
				break;
			default:
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}
		}

	}

	private static int inputNoticeMenu() {
		System.out.println("1.조회  2.검색  3.등록  4.상위메뉴로  >");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		return menu;
	}

	private static void viewNotice(int idNum) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "SELECT ID, TITLE, WRITER_ID, TO_CHAR(REGDATE,'YYYY-MM-DD') REGDATE, HIT, CONTENT FROM NOTICE WHERE ID="
				+ idNum;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		printListViewNotice(rs);


		close(con,st,rs);
		
		viewNoticeMenu();
	}

	private static void printListViewNotice(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			String regdate = rs.getString("REGDATE");
			String hit = rs.getString("HIT");
			String content = rs.getString("CONTENT");

			System.out.println("------------------------");
			System.out.println("<게시글 내용>");
			System.out.println("------------------------");
			System.out.printf("번호 : %s\n제목 : %s\n작성자 : %s\n작성일 : %s\n조회수 : %s\n내용 : %s\n", id, title, writerId, regdate,
					hit, content);
		}

	}

	private static void viewNoticeMenu() throws ClassNotFoundException, SQLException {
		boolean finish = false;
		while (!finish) {
			switch (inputViewNoticeMenu()) {

			case 1:
				addReply();
				break;
			case 2:
				finish = true;
				break;
			case 3:
				editNotice();
				break;
			case 4:
				delNotice();
				break;
			default:
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}
		}

	}

	private static int inputViewNoticeMenu() {
		System.out.println("1.댓글등록  2.목록  3.수정  4.삭제  >");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		return menu;
	}

	private static void addReply() {
		System.out.println("지금은 댓글을 달 수 없습니다.");
	}

	private static void editNotice() {
		System.out.println("지금은 수정을 할 수 없습니다.");
	}

	private static void delNotice() {
		System.out.println("지금은 게시물을 지울 수 없습니다.");
	}

	private static void findNotice() {
		System.out.println("여기는 공지사항 검색프로그램이 위치할 예정입니다.");
	}

	private static void addNotice() {
		System.out.println("여기는 공지사항 글쓰기가 위치할 예정입니다.");
	}

	
}