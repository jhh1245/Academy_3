<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Board.*" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Board board = new Board();
    Post post = board.getPostById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= post.getTitle() %></title>
</head>
<body>
    <h1><%= post.getTitle() %></h1>
    <p><%= post.getContent() %></p>
    <p>작성자: <%= post.getAuthor() %></p>
    <form action="delete.jsp" method="post">
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <input type="submit" value="삭제">
    </form>
</body>
</html>