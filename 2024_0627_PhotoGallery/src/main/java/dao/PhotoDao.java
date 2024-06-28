package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.PhotoVo;
import service.DBService;

public class PhotoDao {

	// single-ton pattern : 객체 1개만 생성해서 이용하자
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		//없으면 생성해라
		if (single == null)
			single = new PhotoDao();

		return single;
	}

	// 외부에서 객체생성하지 말아라...
	private PhotoDao() {

	}
	
	//전체목록
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = new ArrayList<PhotoVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from photo order by p_idx desc";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			while (rs.next()) {

				//저장객체 생성->레코드에서 읽은 값을 넣는다
				PhotoVo vo = new PhotoVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_title(rs.getString("p_title"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));

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
	
	// p_idx에 대한 1건의 정보
	public PhotoVo selectOne(int p_idx) {

		PhotoVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from photo where p_idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setInt(1, p_idx);

			//4.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			if (rs.next()) {

				//저장객체 생성->레코드에서 읽은 값을 넣는다
				vo = new PhotoVo();

				//rs가 가리키는 레코드값을 vo에 넣는다
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_title(rs.getString("p_title"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				

			} //end:if

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
	
	
	public int insert(PhotoVo vo) {
		// TODO Auto-generated method stub

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
        //                                                           1 2 3 4         5 6
		String sql = "insert into photo values(seq_photo_idx.nextVal,?,?,?,?,sysdate,?,?)";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index 채우기
            pstmt.setString(1, vo.getP_title());
            pstmt.setString(2, vo.getP_content());
            pstmt.setString(3, vo.getP_filename());
            pstmt.setString(4, vo.getP_ip());
            pstmt.setInt(   5, vo.getMem_idx());
            pstmt.setString(6, vo.getMem_name());
            
			//4.DB insert
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			//마무리 작업(열린역순으로 닫기)
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;

	}//end:insert()
	
	
}
