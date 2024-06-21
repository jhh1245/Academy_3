package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.DeptVo;
import db.vo.SawonVo;
import service.DBService;

/* 
 DAO(Data Access Object)
 Create : insert 추가
 Read : select 조회
 Update
 Delete
 */
public class DeptDao {

	// single-ton pattern : 객체 1개만 생성해서 이용
	static DeptDao single = null;

	static public DeptDao getInstance() {
		// 없으면 생성 
		if (single == null) {
			single = new DeptDao();
		}
		// 2번째 부터는 이미 생성된걸 반환 
		return single;
	}

	// 외부에서 객체 생성 하지 못하도록 한다. 
	private DeptDao() {

	}
	
	// 부서 조회 
	public List<DeptVo> selectList() { //select한 결과를 List로 만든다. 
		List<DeptVo> list = new ArrayList<DeptVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from dept";
		//성적DB.sql에서 뷰를 만들었다. 

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. ResultSet 얻어온다. 
			rs = pstmt.executeQuery();

			while (rs.next()) { // 레코드 하나씩 확인한다. 
				// 저장객체 생성 -> 레코드로 읽은 값 저장 
				DeptVo vo = new DeptVo();

				// rs가 가르키는 레코드 값을 VO에 넣는다. 
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));
				// ArrayList 추가 
				list.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace(); // 어떤문제인지 바로 알 수 있도록 
		} finally {
			// 마무리 작업 (열린 역순으로 닫기)
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 사원
	public List<SawonVo> selectSawonList() { //select한 결과를 List로 만든다. 
		List<SawonVo> list = new ArrayList<SawonVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sawon";
		//성적DB.sql에서 뷰를 만들었다. 

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. ResultSet 얻어온다. 
			rs = pstmt.executeQuery();

			while (rs.next()) { // 레코드 하나씩 확인한다. 
				// 저장객체 생성 -> 레코드로 읽은 값 저장 
				SawonVo vo = new SawonVo();

				// rs가 가르키는 레코드 값을 VO에 넣는다. 
				vo.setSabun(rs.getInt("sabun"));
				vo.setSaname(rs.getString("saname"));
				vo.setSasex(rs.getString("sasex"));
				vo.setSajob(rs.getString("sajob"));
				vo.setSahire(rs.getString("sahire"));
				vo.setSapay(rs.getInt("sapay"));
				
				// ArrayList 추가 
				list.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace(); // 어떤문제인지 바로 알 수 있도록 
		} finally {
			// 마무리 작업 (열린 역순으로 닫기)
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 부서 조회 
	public DeptVo selectOne(int deptno) {
		
		DeptVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from dept where deptno=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setInt(1, deptno);
			
			//4.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			if (rs.next()) {

				//저장객체 생성->레코드에서 읽은 값을 넣는다
				vo = new DeptVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));

			} //end:while

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			//마무리 작업(열린역순으로 닫기)
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
}
