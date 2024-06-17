package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DBService;
import util.Util;
import vo.MemberVo;

public class MemberDao {
	
	static MemberDao single = null;

	public static MemberDao getinstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	private MemberDao() {
	}
	
	// 회원 로그인 
	// public boolean login(String m_id, String m_pw, HttpServletRequest req, HttpServletResponse resp) {
	public boolean login(String m_id, String m_pw) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean status = false;
		
		try {
			String sql = "select * from member where m_id = ? and m_pw = ?";
			conn = DBService.getinstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			// pstmt.setString(2, m_pw);
			pstmt.setString(2, Util.MD5(m_pw));
			rs = pstmt.executeQuery();
			status = rs.next();
			
//			if(status) {
//				// 세션 생성
//				int m_idx = rs.getInt("m_idx");
//				String m_name = rs.getString("m_name");
//				// 세션에 m_idx와 m_name 추가
//				req.getSession().setAttribute("m_idx", m_idx);
//	            req.getSession().setAttribute("m_name", m_name);
//	            
//	            // 쿠키 생성
//	            if (remember != null) {
//	            	m_name = m_name.replaceAll(" ", "_");
//	            	String vlaue = m_idx + "_" + m_name;
//	            	// user라는 쿠키 추가
//	            	Cookie cookie = new Cookie("user", vlaue);
//	            	// 쿠키 만료 시간 (일주일 유지)
//	                cookie.setMaxAge(60 * 60 * 24 * 7);
//	                // 쿠키 응답 추가
//	                resp.addCookie(cookie);
//	            }
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	} 
	
	// 회원 가입 기능
	public int memberInsert(MemberVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO member (m_idx, m_name, m_id, m_pw, m_email) VALUES (?, ?, ?, ?, ?)";
		try {

			conn = DBService.getinstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String m_name = Util.escapeHtml(vo.getM_name());
            String m_email = Util.escapeHtml(vo.getM_email());
			
            pstmt.setInt(1, 99); // 여기 시퀀스 추가해야됨 
            
			pstmt.setString(2, m_name);
			pstmt.setString(3, vo.getM_id());
			pstmt.setString(4, Util.MD5(vo.getM_pw()));
			pstmt.setString(5, m_email);
			
			res = pstmt.executeUpdate();
			System.out.println("회원가입 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 과정에서 에러");
			
		} finally {
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
	}

	
}
