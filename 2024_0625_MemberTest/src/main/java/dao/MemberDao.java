package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.MemberVo;
import service.DBService;

public class MemberDao {

	// single-ton pattern : 객체 1개만 생성해서 이용하자
	static MemberDao single = null;

	public static MemberDao getInstance() {

		//없으면 생성해라
		if (single == null)
			single = new MemberDao();

		return single;
	}

	// 외부에서 객체생성하지 말아라...
	private MemberDao() {

	}
	
	//전체조회
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member order by mem_idx";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			while (rs.next()) {

				//저장객체 생성->레코드에서 읽은 값을 넣는다
				MemberVo vo = new MemberVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));
				
				//ArrayList에 추가
				list.add(vo);

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

		return list;
	}
	
	// mem_idx 에 해당하는 정보 1건 가져옴 
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setInt(1, mem_idx);

			//4.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//저장객체 생성->레코드에서 읽은 값을 넣는다
				vo = new MemberVo();

				
				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));
				
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
	
	// mem_id 에 해당하는 1건의 정보 얻어온다. 
	public MemberVo selectOne(String mem_id) { // 매개변수 타입, 이름 변경 // 오버로딩!! 

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_id=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setString(1, mem_id);

			//4.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			if (rs.next()) {
				//저장객체 생성->레코드에서 읽은 값을 넣는다
				vo = new MemberVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));
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

	public int insert(MemberVo vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into member values(seq_member_idx.nextVal, ?, ?, ?, ?, ?, ?, default, default)";

		try {
			// 1. Connection 얻어오기 
			conn = DBService.getInstance().getConnection();

			// 2. PreparedStatement 
			pstmt = conn.prepareStatement(sql);

			// 3. pstmt parameter index 채우기 
			pstmt.setString(1, vo.getMem_name());
			pstmt.setString(2, vo.getMem_id());
			pstmt.setString(3, vo.getMem_pwd());
			pstmt.setString(4, vo.getMem_zipcode());
			pstmt.setString(5, vo.getMem_addr());
			pstmt.setString(6, vo.getMem_ip());
			
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
	
}
