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
import vo.MemberVo;

public class memberDao {
	
	static memberDao single = null;

	public static memberDao getinstance() {

		if (single == null)
			single = new memberDao();

		return single;
	}

	private memberDao() {
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
	
	
	// 회원 가입 기능
	public int memberInsert(MemberVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO member (m_idx, m_name, m_id, m_pw, m_email) VALUES (seq_member_m_idx.nextval, ?, ?, ?, ?)";
		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String m_name = Util.escapeHtml(vo.getM_name());
            String m_email = Util.escapeHtml(vo.getM_email());
			
			pstmt.setString(1, m_name);
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, Util.MD5(vo.getM_pw()));
			pstmt.setString(4, m_email);
			
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

	// 회원 정보 조회 (관리자) 
	public List<MemberVo> selectMemberList() {

		List<MemberVo> list = new ArrayList<MemberVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member";
		
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVo mv = new MemberVo();
	            mv.setM_idx(rs.getInt("m_idx"));
	            mv.setM_name(rs.getString("m_name"));
	            mv.setM_id(rs.getString("m_id"));
	            mv.setM_pw(rs.getString("m_pw"));
	            mv.setM_email(rs.getString("m_email"));
	            mv.setM_intro(rs.getString("m_intro"));
	            mv.setM_rdate(rs.getTimestamp("m_rdate").toLocalDateTime().format(dtf));
	            mv.setM_mdate(rs.getTimestamp("m_mdate").toLocalDateTime().format(dtf));
	            mv.setM_type(rs.getInt("m_type"));

	            list.add(mv);
			}

		} catch (Exception e) {
			e.printStackTrace();
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

		return list;
	}
	
	// 회원 탈퇴
		public int memberDelete(String m_id) {
			int res = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;

			String sql = "delete from member where m_id = ?";

			try {
				conn = DBService.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1,m_id);

				res = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
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
