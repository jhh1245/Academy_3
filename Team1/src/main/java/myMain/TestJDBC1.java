package myMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestJDBC1 {

	// 0. 초기화 작업
	
	static {
		//System.out.println("여기가 제일 먼저 실행됨");
		//new OracleDriver();
		
		// 단, ojdbc14.jar 반드시 드라이버 초기화 해야 한다. 
		// 	  ojdbc60.jar, ojdb80.jar 버전에는 아래 초기화식 생략 가능 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		//1. connection 얻어오기
				//실제로는 변수를 통해 읽어와서 사용해야 함
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "test";
				String pwd = "test";
				Connection conn = DriverManager.getConnection(url, user, pwd);
						
//				System.out.println("---success connect---");
				
				//2. statement 얻어오기 : SQL명령을 처리하는 객체
				Statement stmt = conn.createStatement();	//명령처리객체를 얻어오는 코드
				
				//테이블 생성
//				String sql = "create table AAA(no int)";	//DDL
//				stmt.executeUpdate(sql);		//한번더 사용하면 "기존 객체 사용중"이므로 에러떨어짐
				
				//이미 만들어진 테이블 삭제
//				stmt.executeUpdate("drop table AAA");
				
				//데이터 insert(sungtb):DML
//				stmt.executeUpdate("insert into sungtb values(6,'육길동',1,2,3)");
//				System.out.println(res);
				
				//데이터 삭제(delete) : DML
//				int res = stmt.executeUpdate("delete from sungtb where no = 6");
				
				//데이터 update : DML
//				int res = stmt.executeUpdate("update sungtb set kor=100,eng=100,math=100 where no =1");
				
				
//				select를 통해서 DB를 가져올 경우	: executeQuery(sql)
//				select 외 모든명령 				: executeUpdate(sql)
				
				//3.조회 : ResultSet <- 조회결과를 관리하는 객체
				//방법1)
//				String sql = "select * from dept";
//				ResultSet rs = stmt.executeQuery(sql);
				
				//방법2)
//				StringBuffer sb = new StringBuffer("select *from dept");
//				sb.append("where deptno != 10");
//				sb.append("order by deptno desc");
//				ResultSet rs = stmt.executeQuery(sb.toString());
				
				//방법3)
				ResultSet rs = stmt.executeQuery("select * from dept");	//메모리가 올라오면 rs는 bof를 가리킴
//				ResultSet도 역순으로 닫아야 함		
				//전체 조회
				while(rs.next()) {	//rs.next()한 결과가 데이터 영역인지 확인하는 반복문
					
					//현재 rs가 가리키는 행에 컬럼정보를 읽어오기
					//방법1)
					int 	deptno 	= rs.getInt("deptno");
					
					//DB type과 상관없이 String형으로 읽어올 수 있다
					String str_no = rs.getString("deptno");
					String 	dname 	= rs.getString("dname");
					String 	loc 	= rs.getString("loc");
					
					//방법2) 사용을 권하지 않음
//					int deptno = rs.getInt(1);
//					String dname = rs.getString(2);
//					String loc = rs.getString(3);
					
					System.out.printf("%d-%s-%s\n",deptno,dname,loc);
				}
				
				//닫기(닫는 순서는 열린 역순 순서로 닫아야 함)
				//닫기를 안하면 다른 사람이 사용하지 못함 그러므로 닫기는 철칙
				rs.close();
				stmt.close();//2
				conn.close();//1
	}

}
