<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%@ page import="vo.*" %>
<%! 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbUser = "test";
    String dbPassword = "test";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입 처리</title>
</head>
<body>
    <%
    try {
        // 파라미터 값 가져오기
        String m_name = request.getParameter("m_name");
        String m_id = request.getParameter("m_id");
        String m_pw = request.getParameter("m_pw");
        String m_email = request.getParameter("m_email");
        
        MemberVo vo = new MemberVo(m_name, m_id, m_pw, m_email);
        
        // SQL 실행
        MemberDao.getinstance().memberInsert(vo);
        
        
    } catch (Exception e) {
        out.println("<p>회원 가입 중 오류가 발생하였습니다.111</p>");
        e.printStackTrace();
    } 
    %>
</body>
</html>
