<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dao.*" %>

<%
	out.println("<h1>엥</h1>");
	String m_id = request.getParameter("m_id");
    String m_pw = request.getParameter("m_pw");
    
    //boolean login = MemberDao.getinstance().login(m_id, m_pw, request, response);
    boolean login = MemberDao.getinstance().login(m_id, m_pw);
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {        
        if (login) {
            // 로그인 성공
            session.setAttribute("m_id", m_id); // 세션에 사용자명 저장
            response.sendRedirect("main-page.jsp"); // 로그인 후 메인 페이지로 이동
            
        } else {
            // 로그인 실패
            out.println("<script>alert('로그인 실패! 사용자명 또는 비밀번호를 확인해주세요.'); history.back();</script>");
        }
    } 
    
    catch (Exception e) {
        e.printStackTrace();
        out.println("<p>오류가 발생하였습니다.</p>");
    } 
    
    
    finally {
        if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
        if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
        if (conn != null) try { conn.close(); } catch (SQLException e) { /* ignored */ }
    }
%>
