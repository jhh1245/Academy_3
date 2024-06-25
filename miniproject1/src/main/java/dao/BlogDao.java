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
import vo.Post_LikeVo;

public class BlogDao {
	// 싱글톤화
	static BlogDao single = null;
	
	public static BlogDao getinstance() {

		if (single == null)
			single = new BlogDao();

		return single;
	}

	private BlogDao() {
	}

	
	// 회원 가입 기능
	public int memberInsert(MemberVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into member values(seq_member_m_idx.nextval,?,?,?,?,?,sysdate,sysdate,?)";	

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String m_name = Util.escapeHtml(vo.getM_name());
            String m_email = Util.escapeHtml(vo.getM_email());
            String m_intro = Util.escapeHtml(vo.getM_intro());
			
			pstmt.setString(1, m_name);
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, Util.MD5(vo.getM_pw()));
			pstmt.setString(4, m_email);
			pstmt.setString(5, m_intro);
			pstmt.setInt(6, vo.getM_type());
			
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
	
	// 회원 정보 조회
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
	
	// 회원 정보 수정 (비밀번호, 아이디 제외)
	public int memberUpdate(MemberVo vo) {
		
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = null;
		
		try {
			sql = "update member set m_name = ?, m_email = ?, m_intro = ?, m_mdate = sysdate where m_idx = ?";
			
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
			String m_name = Util.escapeHtml(vo.getM_name());
            String m_email = Util.escapeHtml(vo.getM_email());
            String m_intro = Util.escapeHtml(vo.getM_intro());
			
			pstmt.setString(1, m_name);
            pstmt.setString(2, m_email);
            pstmt.setString(3, m_intro);
            pstmt.setInt(4, vo.getM_idx());
            
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
	
	// 회원 탈퇴
	public int memberDelete(int m_idx) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from member where m_idx = ?";

		try {
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,m_idx);

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
	
	// 이메일을 통한 아이디 찾기
	public String getId(String m_email) {
        String id = "";
        
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {
            String sql = "select m_id from member where m_email=?";
            
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m_email);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                id = rs.getString("m_id");
            }
        } catch (Exception e) {
            System.err.println("getId error: " + e);
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
        return id;
    }
	
	// 비밀번호 변경 ( -1 = 업데이트가 이루어지지 않았을때, -2 = 새 비밀번호가 일치하지 않았을때 )
	//                               예전 비밀번호          새 비밀번호            새 비밀번호 확인용
    public int changePassword(String oldPassword, String newPassword, String newPasswordr, int m_idx) {
        int res = 0;
        
        Connection conn = null;
		PreparedStatement pstmt = null;
        
		// 새 비밀번호랑 확인용이 같다면
        if (newPassword.equals(newPasswordr)) {
            try {
                String sql = "update member set m_pw = ?  where m_pw = ? and m_idx = ? ";
                
                conn = DBService.getInstance().getConnection();
                pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, Util.MD5(newPassword));
                pstmt.setString(2, Util.MD5(oldPassword));
                pstmt.setInt(3, m_idx);
                res = pstmt.executeUpdate();
                if (res == 0) {
                	// 업데이트가 이루어지지 않았을 때
                	res = -1;
                }
            } catch (Exception e) {
                System.err.println("changePassword Error : " + e);
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

        } else {
        	// 새로운 비밀번호들이 일치하지 않을 때
        	res = -2;
        }

        return res;
    }
	
	// 게시글 검색
    public List<PostVo> selectPostList() {

		List<PostVo> list = new ArrayList<PostVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from post";
		
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostVo pv = new PostVo();
	            pv.setP_idx(rs.getInt("p_idx"));
	            pv.setP_cate(rs.getString("p_cate"));
	            pv.setP_title(rs.getString("p_title"));
	            pv.setP_content(rs.getString("p_content"));
	            pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
	            pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
	            pv.setP_type(rs.getInt("p_type"));
	            pv.setP_hit(rs.getInt("p_hit"));
	            pv.setM_idx(rs.getInt("m_idx"));

	            list.add(pv);
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
    
    // 게시글 조회 + 페이징			보여줄 페이지의 번호		각 페이지에 표시할 게시글의 개수
    public List<PostVo> selectPostList(int pageNo, int pageSize) {
    	
        List<PostVo> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 페이징 계산
        int start = (pageNo - 1) * pageSize + 1;
        int end = start + pageSize - 1;

        // 메인 쿼리 : 메인 쿼리에서 rownum을 이용해 조회 범위를 지정, 서브 쿼리 : rownum을 이용해 각 게시글에 번호를 붙임
		// rownum을 이용하여 페이징을 처리 																	
        String sql = "select * from (select rownum rnum, p_idx, p_cate, p_title, p_content, p_rdate, p_mdate, p_type, p_hit, m_idx from (select * from post order by p_idx desc)) where rnum between ? and ?";
        
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
        
        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVo pv = new PostVo();
                pv.setP_idx(rs.getInt("p_idx"));
                pv.setP_cate(rs.getString("p_cate"));
                pv.setP_title(rs.getString("p_title"));
                pv.setP_content(rs.getString("p_content"));
                pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
                pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
                pv.setP_type(rs.getInt("p_type"));
                pv.setP_hit(rs.getInt("p_hit"));
                pv.setM_idx(rs.getInt("m_idx"));

                list.add(pv);
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
    
    // 특정 게시글 조회 (p_idx를 이용한 조회)
    public PostVo selectPostByPidx(int p_idx) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PostVo pv = null;

        String sql = "select * from post where p_idx = ?";
        
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
        
        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p_idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	pv = new PostVo();
            	pv.setP_idx(rs.getInt("p_idx"));
            	pv.setP_cate(rs.getString("p_cate"));
            	pv.setP_title(rs.getString("p_title"));
            	pv.setP_content(rs.getString("p_content"));
                pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
                pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
                pv.setP_type(rs.getInt("p_type"));
                pv.setP_hit(rs.getInt("p_hit"));
                pv.setM_idx(rs.getInt("m_idx"));
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

        return pv;
    }
    
    // 특정 회원 게시글 조회 (m_idx를 이용한 조회)
    public List<PostVo> selectPostsByMidx(int m_idx) {

        List<PostVo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from post where m_idx = ?";
        
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m_idx);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVo pv = new PostVo();
                pv.setP_idx(rs.getInt("p_idx"));
                pv.setP_cate(rs.getString("p_cate"));
                pv.setP_title(rs.getString("p_title"));
                pv.setP_content(rs.getString("p_content"));
                pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
                pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
                pv.setP_type(rs.getInt("p_type"));
                pv.setP_hit(rs.getInt("p_hit"));
                pv.setM_idx(rs.getInt("m_idx"));
                list.add(pv);
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
    
    // 게시글 내용 조회
    public List<PostVo> searchPostByContent(String keyword) {

        List<PostVo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from post where p_content like ?";
        
        String keywordF = String.format("%%%s%%", keyword);
        
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, keywordF);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PostVo pv = new PostVo();
                pv.setP_idx(rs.getInt("p_idx"));
                pv.setP_cate(rs.getString("p_cate"));
                pv.setP_title(rs.getString("p_title"));
                pv.setP_content(rs.getString("p_content"));
                pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
                pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
                pv.setP_type(rs.getInt("p_type"));
                pv.setP_hit(rs.getInt("p_hit"));
                pv.setM_idx(rs.getInt("m_idx"));
                list.add(pv);
            }

        } catch (SQLException e) {
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
	
	// 게시글 등록
	public int postInsert(PostVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into post values(seq_post_p_idx.nextval,?,?,?,sysdate,sysdate,?,?,?)";	

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String p_cate = Util.escapeHtml(vo.getP_cate());
            String p_title = Util.escapeHtml(vo.getP_title());
            String p_content = Util.escapeHtml(vo.getP_content());
			
			pstmt.setString(1, p_cate);
			pstmt.setString(2, p_title);
			pstmt.setString(3, p_content);
			pstmt.setInt(4, vo.getP_type());
			pstmt.setInt(5, vo.getP_hit());
			pstmt.setInt(6, vo.getM_idx());
			
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
	
	// 게시글 수정
	public int postUpdate(PostVo vo) {
		
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = null;
		
		try {
			sql = "update posts set p_cate = ?, p_title = ?, p_content = ?, p_mdate = sysdate where p_idx = ?";
			
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String p_cate = Util.escapeHtml(vo.getP_cate());
            String p_title = Util.escapeHtml(vo.getP_title());
            String p_content = Util.escapeHtml(vo.getP_content());
			
			pstmt.setString(1, p_cate);
            pstmt.setString(2, p_title);
            pstmt.setString(3, p_content);
            pstmt.setInt(4, vo.getP_idx());
            
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
	
	// 게시글 삭제
	public int postDelete(int p_idx) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from post where p_idx = ?";

		try {
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,p_idx);

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
	
	// 모든 댓글 조회
	public List<CommentVo> selectCommentlist() {

	    List<CommentVo> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "select * from comments";
	    
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

	    try {
	        conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sql);
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
	
	// 게시글에 달린 댓글 목록 조회
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
	
	// 사용자가 작성한 댓글 조회
	public List<CommentVo> selectCommentByMidx(int m_idx) {

	    List<CommentVo> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "select * from comments where m_idx = ?";
	    
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");

	    try {
	        conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, m_idx);
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
	
	// 댓글 내용 검색
    public List<CommentVo> searchCommentByContent(String keyword) {
        List<CommentVo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "select * from comments where c_content like ?";
        String keywordF = String.format("%%%s%%", keyword);
        
		// 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
        
        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, keywordF);
            
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
            
        } catch (SQLException e) {
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
	
	// 댓글 등록
	public int commentInsert(CommentVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into comments values(seq_comments_c_idx.nextval,?,sysdate,sysdate,?,?)";	

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String c_content = Util.escapeHtml(vo.getC_content());
			
			pstmt.setString(1, c_content);
			pstmt.setInt(2, vo.getP_idx());
			pstmt.setInt(3, vo.getM_idx());
			
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
	
	// 댓글 수정
	public int commentUpdate(CommentVo vo) {
		
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = null;
		
		try {
			sql = "update comments set c_content = ?, c_mdate = sysdate where c_idx = ?";
			
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// XSS 방어를 위해 이스케이프 처리
            String c_content = Util.escapeHtml(vo.getC_content());
			
			pstmt.setString(1, c_content);
            pstmt.setInt(2, vo.getC_idx());;
            
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
	
	// 댓글 삭제
	public int commentDelete(int c_idx) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from comments where c_idx = ?";

		try {
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,c_idx);

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
	
	// 좋아요 또는 스크랩 등록
	public int insertPostLike(Post_LikeVo vo) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into post_like values (seq_post_like_l_idx.nextval, ?, sysdate, sysdate, ?, ?)";	

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getL_type());
			pstmt.setInt(2, vo.getM_idx());
			pstmt.setInt(3, vo.getP_idx());
			
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
	
	// 좋아요 또는 스크랩 취소
	public int postLikeDelete(int l_idx) {

		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from post_like where l_idx = ?";

		try {
			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,l_idx);

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
	
	// 특정 게시글에 대한 사용자의 좋아요 또는 스크랩 여부 확인
	public boolean isPostLikeOrScrap(int m_idx, int p_idx, int l_type) {
		
	    boolean likeOrScrap = false;
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "select * from post_like where m_idx = ? AND p_idx = ? AND l_type = ?";

	    try {
	        conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, m_idx);
	        pstmt.setInt(2, p_idx);
	        pstmt.setInt(3, l_type);
	        rs = pstmt.executeQuery();

	        // 해당 게시글에 대해 좋아요 또는 스크랩을 한 경우
	        if (rs.next()) {
	        	likeOrScrap = true; 
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
	    return likeOrScrap;
	}
	
	// 사용자가 게시글에 대해 좋아요 또는 스크랩한 목록 확인
	public List<PostVo> getUserLikeOrScrapPosts(int m_idx, int l_type) {
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "select p.* from post p join post_like pl on p.p_idx = pl.p_idx where m_idx = ? and l_type = ?";
	    
	    List<PostVo> postList = new ArrayList<>();
	    
	    try {
	        conn = DBService.getInstance().getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, m_idx);
	        pstmt.setInt(2, l_type);
	        rs = pstmt.executeQuery();
	        
	        // 스레드 안정성을 위해 SimpleDateFormate대신 DateTimeFormatter사용
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
	        
	        
	        while (rs.next()) {
                PostVo pv = new PostVo();
                pv.setP_idx(rs.getInt("p_idx"));
                pv.setP_cate(rs.getString("p_cate"));
                pv.setP_title(rs.getString("p_title"));
                pv.setP_content(rs.getString("p_content"));
                pv.setP_rdate(rs.getTimestamp("p_rdate").toLocalDateTime().format(dtf));
    	        pv.setP_mdate(rs.getTimestamp("p_mdate").toLocalDateTime().format(dtf));
    	        pv.setP_type(rs.getInt("p_type"));
    	        pv.setP_hit(rs.getInt("p_hit"));
    	        pv.setM_idx(rs.getInt("m_idx"));
                postList.add(pv);
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
	    return postList;
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
    
    // 사용자가 좋아요한 게시글의 개수
    public int getUserLikeCount(int m_idx) {
        
        int likeCount = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select count(*) from post_like where m_idx = ? and l_type = 2";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m_idx);
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
    
    // 사용자가 스크랩한 게시글의 개수
    public int getUserScrapCount(int m_idx) {
        
        int scrapCount = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select count(*) from post_like where m_idx = ? and l_type = 2";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m_idx);
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
	
    // 공지사항 등록
    public int insertNotice(PostVo vo) {
    	
        int res = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "insert into post values (seq_post_p_idx.nextval, ?, ?, ?, sysdate, sysdate, 2, 1, ?)";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // XSS 방어를 위해 이스케이프 처리
            String p_cate = Util.escapeHtml(vo.getP_cate());
            String p_title = Util.escapeHtml(vo.getP_title());
            String p_content = Util.escapeHtml(vo.getP_content());

            pstmt.setString(1, p_cate);
            pstmt.setString(2, p_title);
            pstmt.setString(3, p_content);
            pstmt.setInt(4, vo.getM_idx());

            res = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    
    // 공지사항 수정
    public int updateNotice(PostVo vo) {
    	
        int res = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update post set p_cate = ?, p_title = ?, p_content = ?, p_mdate = sysdate where p_idx = ? and p_type = 2";

        try {
            conn = DBService.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // XSS 방어를 위해 이스케이프 처리
            String p_cate = Util.escapeHtml(vo.getP_cate());
            String p_title = Util.escapeHtml(vo.getP_title());
            String p_content = Util.escapeHtml(vo.getP_content());

            pstmt.setString(1, p_cate);
            pstmt.setString(2, p_title);
            pstmt.setString(3, p_content);
            pstmt.setInt(4, vo.getP_idx());

            res = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    
}
