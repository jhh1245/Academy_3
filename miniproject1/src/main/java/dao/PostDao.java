package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DBService;
import util.Util;
import vo.CommentVo;
import vo.MemberVo;
import vo.PostVo;

public class PostDao {

	// single-ton pattern : 객체 1개만 생성해서 이용하자
	static PostDao single = null;

	public static PostDao getInstance() {

		// 없으면 생성해라
		if (single == null)
			single = new PostDao();

		return single;
	}

	// 외부에서 객체생성하지 말아라...
	private PostDao() {

	}

	public List<PostVo> selectList() {

		List<PostVo> list = new ArrayList<PostVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from post_list_view order by p_rdate desc"; // 작성일 기준 내림차순으로

		try {
			// 1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			// 2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			// 3.ResultSet 얻어온다
			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 저장객체 생성->레코드에서 읽은 값을 넣는다
				PostVo vo = new PostVo();

				// rs가 가리키는 레코드값을 vo에 넣는다
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_cate(rs.getString("p_cate"));
				vo.setP_title(rs.getString("p_title"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_rdate(rs.getString("p_rdate"));
				vo.setP_mdate(rs.getString("p_mdate"));
				vo.setP_type(rs.getInt("p_type"));
				vo.setP_hit(rs.getInt("p_hit"));
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				
				// ArrayList에 추가
				list.add(vo);

			} // end:while

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			// 마무리 작업(열린역순으로 닫기)
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

	public int insert(PostVo vo) {
		// TODO Auto-generated method stub

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into post(p_idx, p_cate, p_title, p_content, p_type, m_idx) values(seq_post_p_idx.nextVal,?,?,?,?,?)";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index 채우기
			pstmt.setString(1, vo.getP_title());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_cate());
			pstmt.setInt(4, vo.getP_type());
			pstmt.setInt(5, vo.getM_idx());
			
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

	public int delete(int p_idx) {
		// TODO Auto-generated method stub

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from post where p_idx=?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index 채우기
			pstmt.setInt(1,p_idx);
			
			//4.DB delete
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

	}//end:delete()
	
	// 게시글 당 좋아요한 수
    public int getPostLikeCount(int p_idx) {
        
        int likeCount = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select count(*) from post_like where p_idx = ? and l_type = 1";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p_idx);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	likeCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return likeCount;
    }
    
    // 게시글 당 스크랩한 수
    public int getPostScrapCount(int p_idx) {
        
        int scrapCount = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select count(*) from post_like where p_idx = ? and l_type = 2";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p_idx);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                scrapCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return scrapCount;
    }
    
    // 특정 게시글에 대한 사용자의 좋아요 또는 스크랩 여부 확인
    // (좋아요/스크랩 버튼이 토글 형식이니까) 좋아요/스크랩이 이미 존재하면 삭제, 없으면 추가 
	public void isPostLikeOrScrap(int m_idx, int p_idx, int l_type) {    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    // 좋아요 여부 확인
        String sqlSelect = "SELECT * FROM post_like WHERE p_idx = ? AND m_idx = ? AND l_type = ?";
        
        try {
        	conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sqlSelect);
	        pstmt.setInt(1, p_idx);
	        pstmt.setInt(2, m_idx);
	        pstmt.setInt(3, l_type);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // 좋아요가 이미 존재하면 삭제
	            String sqlDelete = "DELETE FROM post_like WHERE p_idx = ? AND m_idx = ? AND l_type = ?";
	            pstmt = conn.prepareStatement(sqlDelete);
	            pstmt.setInt(1, p_idx);
	            pstmt.setInt(2, m_idx);
	            pstmt.setInt(3, l_type);
	            pstmt.executeUpdate();
	        } else {
	            // 좋아요가 없으면 추가
	            String sqlInsert = "INSERT INTO post_like (l_idx, p_idx, m_idx, l_type, l_rdate, l_mdate) VALUES (seq_post_like_l_idx.nextval, ?, ?, ?,  SYSDATE, SYSDATE)";
	            pstmt = conn.prepareStatement(sqlInsert);
	            pstmt.setInt(1, p_idx);
	            pstmt.setInt(2, m_idx);
	            pstmt.setInt(3, l_type);
	            
	            pstmt.executeUpdate();
	        }
	        
        } catch (Exception e) {
	            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	       
	}

	public List<CommentVo> selectCommentByPidx(int p_idx) {

	    List<CommentVo> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "select * from comments where p_idx = ?";
	    
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

	    try {
	        conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, p_idx);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            CommentVo cv = new CommentVo();
	            cv.setC_idx(rs.getInt("c_idx"));
	            cv.setC_content(rs.getString("c_content"));
	            cv.setC_rdate(rs.getTimestamp("c_rdate").toLocalDateTime().format(dtf));
	            cv.setC_mdate(rs.getTimestamp("c_mdate").toLocalDateTime().format(dtf));
	            cv.setP_idx(rs.getInt("p_idx"));
	            cv.setM_idx(rs.getInt("m_idx"));

	            list.add(cv);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return list;
	}
	
	// 로그인 기능
	// remember me 옵션을 선택할 시 쿠키를 생성하여 로그인 상태 유지
	public MemberVo login(String m_id, String m_pw, String remember, HttpServletRequest req, HttpServletResponse resp) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVo mv = null;
		
		try {
			String sql = "select * from member where m_id = ? and m_pw = ?";
			
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m_id);
			pstmt.setString(2, Util.MD5(m_pw));
			// pstmt.setString(2, m_pw);
			
			rs = pstmt.executeQuery();
			// 로그인 성공 했을 경우
			if(rs.next()) {
				mv = new MemberVo();
                mv.setM_idx(rs.getInt("m_idx"));
                mv.setM_name(rs.getString("m_name"));
                mv.setM_id(rs.getString("m_id"));
                mv.setM_email(rs.getString("m_email"));
                mv.setM_intro(rs.getString("m_intro"));
                mv.setM_mdate(rs.getString("m_mdate"));
                mv.setM_type(rs.getInt("m_type"));
                
                // 세션에 필요한 사용자 정보 저장
                HttpSession session = req.getSession();
                session.setAttribute("member", mv);
	            
	            // 쿠키 생성
	            if (remember != null) {
	            	String value = mv.getM_idx() + "_" + mv.getM_name().replaceAll(" ", "_");
	            	// user라는 value를 가진 쿠키 추가
	            	Cookie cookie = new Cookie("user", value);
	            	// 쿠키 만료 시간 (일주일 유지)
	                cookie.setMaxAge(60 * 60 * 24 * 7);
	                // 쿠키 응답 추가
	                resp.addCookie(cookie);
	            }
			}
		} catch (SQLException e) {
			System.err.print("Login Error : " + e);
		} finally {

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
		return mv;
	} 
	
}
