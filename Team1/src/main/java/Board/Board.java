package Board;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Connection con;

    public Board() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:9090/BoardDB", "test", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        
        Post post = new Post(1, "제목임", "내용임", "작성자임");
        posts.add(post);
        
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
//            while (rs.next()) {
//                Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"));
//                posts.add(post);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return posts;
    }

    public void addPost(String title, String content, String author) {
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO posts (title, content, author) VALUES (?, ?, ?)");
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, author);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int id) {
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM posts WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getPostById(int id) {
    	Post post = new Post(1, "제목임", "내용임", "작성자임");
    	
//	    Post post = null;
//	    try {
//	        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM posts WHERE id=?");
//	        pstmt.setInt(1, id);
//	        ResultSet rs = pstmt.executeQuery();
//	        if (rs.next()) {
//	            post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("author"));
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
	    return post;
	}
}