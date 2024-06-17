<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Board.Board" %>
<%@ page import="Board.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h1>게시판</h1>
    <ul>
        <% 
            Board board = new Board();
            List<Post> posts = board.getAllPosts();
            for (Post post : posts) {
        %>
            <li>
                <a href="view.jsp?id=<%= post.getId() %>"><%= post.getTitle() %></a> by <%= post.getAuthor() %>
            </li>
        <% } %>
    </ul>
    <a href="create.jsp">새 글 작성</a>
</body>
</html>