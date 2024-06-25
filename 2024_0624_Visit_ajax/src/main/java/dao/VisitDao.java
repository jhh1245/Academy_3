package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.VisitVo;
import service.DBService;

public class VisitDao {
	// single-ton pattern : 객체 1개만 생성해서 이용
	static VisitDao single = null;

	static public VisitDao getInstance() {
		// 없으면 생성 
		if (single == null) {
			single = new VisitDao();
		}
		// 2번째 부터는 이미 생성된걸 반환 
		return single;
	}

	// 외부에서 객체 생성 하지 못하도록 한다. 
	private VisitDao() {

	}
	
	//목록조회 
	public List<VisitVo> selectList() { //select한 결과를 List로 만든다. 
		List<VisitVo> list = new ArrayList<VisitVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from visit_view";
		//성적DB.sql에서 뷰를 만들었다. 

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. ResultSet 얻어온다. 
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 저장객체 생성 -> 레코드로 읽은 값 저장 
				VisitVo vo = new VisitVo();

				// rs가 가르키는 레코드 값을 VO에 넣는다.
				vo.setNo(rs.getInt("no"));
				vo.setIdx(rs.getInt("idx")); // 컬럼명에 해당하는 값 가져와서 setter에 넣는다.
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				
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
	
	// 쓰기
	public int insert(VisitVo vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
																//   1  2  3  4 <- pstmt index
		String sql = "insert into visit values(seq_visit_idx.nextVal, ?, ?, ?, ?, sysdate)"; // ; 세미콜론은 없어야됨  

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. pstmt parameter index 채우기 
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			
			// 4. DB insert 
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace(); // 어떤문제인지 바로 알 수 있도록 
		} finally {
			// 마무리 작업 (열린 역순으로 닫기)
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	} // end:insert()  

	
	// 삭제
	public int delete(int idx) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from visit where idx = ?"; // 자료형은 int이다.   

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. pstmt parameter index 채우기 
			pstmt.setInt(1, idx);
			
			// 4. DB delete 
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); // 어떤문제인지 바로 알 수 있도록 
		} finally {
			// 마무리 작업 (열린 역순으로 닫기)
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	} // end:delete() 

	public VisitVo selectOne(int idx) {

		VisitVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from visit_view where idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setInt(1, idx);

			//4.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//저장객체 생성->레코드에서 읽은 값을 넣는다
				vo = new VisitVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				// rs가 가르키는 레코드 값을 VO에 넣는다.
				vo.setNo(rs.getInt("no"));
				vo.setIdx(rs.getInt("idx")); // 컬럼명에 해당하는 값 가져와서 setter에 넣는다.
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
			}

		} catch (Exception e) {
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
				e.printStackTrace();
			}
		}
		return vo;
	}


	// 수정
	public int update(VisitVo vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update visit set name=?, content=?, pwd=?, ip=?, regdate=sysdate where idx=?"; // ; 세미콜론은 없어야됨  
		
		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. pstmt parameter index 채우기 
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getIdx());
			
			// 4. DB update 
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace(); // 어떤문제인지 바로 알 수 있도록 
		} finally {
			// 마무리 작업 (열린 역순으로 닫기)
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	} // end:update() 
}
