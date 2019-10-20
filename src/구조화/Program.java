package 구조화;
//print * (list/detail)
//add * / edit * / del * 
//printNotice (오버로딩) 
//-> printNoticeList():전체목록 출력/printNoticeList(String query):검색+출력 목록/printNoticeList(int page):페이지별 출력
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.jdbc.jdbcNoticeService;

public class Program {

   public static void main(String[] args) throws ClassNotFoundException, SQLException {

      listMainMenu();

   } // main e.

   public static void listMainMenu() throws ClassNotFoundException, SQLException {
      // 메인메뉴용 무한루프 변수 생성
            boolean roopMainMenu = true;

            // IF 문이 아닌 SWITCH문으로 바꾸기
            while (roopMainMenu) {
               switch (inputMainMenu()) {
               case 1:
                  printMemebrList();    // UI(print,input)/Data
                  break;
               case 2:
                  printNoticeList(); // 행위() -> 행위+데이터()
                  break;
               case 3:
                  System.out.println("종료");
                  roopMainMenu = false;
                  break;
               default:
                  System.out.println("잘못 누르셨습니다. 다시 눌러주세요.");
               }
            }
      
   }

   // 메인 화면에서 메뉴 선택 메서드
   public static int inputMainMenu() {
      System.out.println("메인 메뉴");
      System.out.println("1. 회원 관리");
      System.out.println("2. 게시글 관리");
      System.out.println("3. 종료");
      System.out.print("> ");
      
      Scanner sc = new Scanner(System.in);
      return sc.nextInt();      
   }
   
   public static void printMemebrList() throws SQLException, ClassNotFoundException {
      String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
      String sql = "SELECT ID, NAME, GENDER, BIRTHDAY, PHONE, EMAIL FROM MEMBER";
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection(url,"ACORN","newlec");
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      
      System.out.println("회원 목록은 준비 중입니다.");   
   }

   // 절대 공유하지 말자... 스스로 마련할 수 있는 것은 자체적으로 마련하는 것이 정답
   // 예: Scanner, Statement, Connection, ResultSet
   // Statement, Connection 공유하지 말아야 할 절대적인 이유
   // 하나를 생성해서 계속해서 재사용하지 말고,
   // 최대한 빨리 닫아주기 -> 서버가 뻗는다.
   // 게시글 관리 화면 메서드
   public static void printNoticeList() throws SQLException, ClassNotFoundException {
	   //데이터 서비스를 요청한 것이고
	   NoticeService service = new jdbcNoticeService();
	   List<Notice> list = service.getNoticeList();
	   //화면에 출력
	   for(Notice n : list) {
		   System.out.println(n.toString());
	   }
	   
      // 게시글 관리용 무한루프 변수 생성
      boolean roopListNotice = true;

      while (roopListNotice) {
         switch (inputNoticeListMenu()) {
         case 1:
            printNoticeDetail();
            roopListNotice = false;
            break;
         case 2:
        	 Scanner sc = new Scanner(System.in);
            switch(inputNoticeSearchMenu()) {
            		case 1:
            			System.out.print("검색할 아이디를 입력 >");
            			String id = sc.next();
            			NoticeService serviceNum = new jdbcNoticeService();
        	     	   	List<Notice> listNum = serviceNum.getNoticeList("ID",id);
        	     	   	for(Notice n : listNum) {
        	     		   System.out.println(n.toString());
        	     	   	}
        	     	   	break;
            		case 2:
            			System.out.print("검색할 제목을 입력 >");
            			String title = sc.next();
            			NoticeService serviceTitle = new jdbcNoticeService();
        	     	   	List<Notice> listTitle = serviceTitle.getNoticeList("title",title);
        	     	   	for(Notice n : listTitle) {
        	     		   System.out.println(n.toString());
        	     	   	}	
        	     	   	break;
            		case 3:
            			System.out.print("검색할 등록자를 입력 >");
            			String writer_id = sc.next();
            			NoticeService serviceWriter_id = new jdbcNoticeService();
        	     	   	List<Notice> listWriter_id = serviceWriter_id.getNoticeList("WRITER_ID",writer_id);
        	     	   	for(Notice n : listWriter_id) {
        	     		   System.out.println(n.toString());
        	     	   	} 
        	     	   	break;              		
    	     	   	case 4:
            			System.out.print("검색할페이지를 입력 >");
            			int page = sc.nextInt();
            			NoticeService servicePage = new jdbcNoticeService();
        	     	   	List<Notice> listPage = servicePage.getNoticeList(page);
        	     	   	for(Notice n : listPage) {
        	     		   System.out.println(n.toString());
        	     	   	}	
        	     	   	break;
      			
            		default:
            			System.out.println("다시입력하세요.");
            	}
 
            roopListNotice = false;
            break;
         case 3:
            addNoticeDetail();
            roopListNotice = false;
            break;
         case 4:
            inputMainMenu();
            roopListNotice = false;
            break;
         default:
            System.out.println("잘못 눌렀습니다. 다시 눌러 주세요.");
         }
      }
      // sql 닫아주기
//      con.close();
//      st.close();
//      rs.close();
   } // method e.

   private static void printNoticeList(int inputNoticeSearchMenu) {
      System.out.println("검색한 결과리스트가 나오는 고옷");
      
   }

   public static void addNoticeDetail() {
      System.out.println("게시글 등록하는 메뉴 선택");
      
   }

   public static int inputNoticeSearchMenu() {
      System.out.print("1.게시글고유번호 2.제목 3.등록자 4.페이지> ");

      Scanner sc = new Scanner(System.in);
      return sc.nextInt();   
      
   }

   // 게시글 관리에서 메뉴 선택 메서드
   public static int inputNoticeListMenu() {
      System.out.println("----------------------------------------");
      System.out.print("1.조회  2.검색  3.등록  4.상위 메뉴로 > ");

      Scanner sc = new Scanner(System.in);
      return sc.nextInt();
   }

   // 게시글 세부 조회 화면 메서드
   public static void printNoticeDetail() throws SQLException, ClassNotFoundException {
      // 세부 조회할 게시글 번호 입력받기
      System.out.print("조회할 게시글 번호 입력 > ");
      Scanner sc = new Scanner(System.in);
      int chooseId = sc.nextInt();
      // sql 불러오기
      String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
      String sql = "SELECT *  FROM NOTICE WHERE ID = "+ chooseId + "";

      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
         int id = rs.getInt("ID");
         String title = rs.getString("TITLE");
         String writer = rs.getString("WRITER_ID");
         String regdate = rs.getString("REGDATE");
         int hit = rs.getInt("HIT");
         String content = rs.getString("CONTENT");

         System.out.printf("번호: %d\n제목: %s\n작성자: %s\n작성일: %s\n조회수: %d\n내용: %s\n", id, title, writer, regdate,
               hit, content);
      }
      // 게시글 세부 조회용 무한 루프 생성
      boolean roop = true;

      while (roop) {
         switch (inputNoticDetailMenu()) {
         case 2:
            printNoticeList();
            roop = false;
            break;
         default:
            System.out.println("다시 입력해주세요.");
            break;
         }
      }
      // sql 닫아주기
      con.close();
      st.close();
      rs.close();
   } // method e.

   // 게시글 세부 조회에서 메뉴 선택 메서드
   private static int inputNoticDetailMenu() {
      System.out.println("-------------------------------------------");
      System.out.println("1.댓글목록 2.목록 3.수정 4.삭제 > ");

      Scanner sc = new Scanner(System.in);
      return sc.nextInt();
   }

} // class e.

//package 구조화;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Program {
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		inputMember();
//		System.out.println("프로그램 종료");
//	}
//
//	//입력 값을 쿼리에 꽂아넣는 방법
//	//트랜잭션을 처리하는 방법
//	private static void inputMember() throws ClassNotFoundException, SQLException {
//		String id = "kdseol";
//		String pwd = "123890";
//				
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = null;
//		
//		try {
//		String sql = "INSERT INTO member (\r\n "+
//				"id,\r\n" + 
//				"pwd,\r\n" + 
//				"name,\r\n" + 
//				"gender,\r\n" + 
//				"birthday,\r\n" + 
//				"phone,\r\n" + 
//				"email"+
//			") VALUES (?,?,?,?,?,?,?)";
//
//		con = DriverManager.getConnection(url, "ACORN", "newlec");
//		
//		con.setAutoCommit(false);
//		
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1,"kdseol");
//		st.setString(2,"123890");
//		st.setString(3,"광동설");
//		st.setString(4,"남성");
//		st.setString(5,"1985-08-27");
//		st.setString(6,"010-1577-1577");
//		st.setString(7,"asd@asd.com");
//		
//		//st.executeQuery(slq);(X) st.executeQuery();  //SELECT 문장을 실행할 때
//		//st.executeUpdate(sql);(X) st.executeUpdate(); //INSERT/UPDATE/DELETE를 실행할 때
//		
//		int result = st.executeUpdate();
//		
//		String sql2 = "INSERT INTO MEMBER_ROLE VALUES(?,?)";
//		PreparedStatement st2 = con.prepareStatement(sql2);
//		st2.setString(1,"kdseol");
//		st2.setString(2,"ROLE_STUDENT");
//	
//	
//		int result2 = st2.executeUpdate();
//		
//		con.commit();
//		
//		st2.close();
//		st.close();
//		con.close();
//		
//		if(result == 1) System.out.println("정상적으로 사용자 입력이 완료되었습니다.");
//
//		}catch(SQLException e) {
//			try {
//			con.rollback();
//			con.close();
//			System.out.println("트라이구문");
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//}
