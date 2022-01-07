package controller;

 import java.io.IOException;
 import java.io.PrintWriter;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;

 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;


 @WebServlet("/visitordb2")
 public class VisitorDBServlet1 extends HttpServlet {
 	private static final long serialVersionUID = 1L;

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		response.setContentType("text/html; charset=utf-8");
 		PrintWriter out = response.getWriter();

 		String url = "jdbc:mysql://localhost:3306/jdbcdb?characterEncoding=UTF-8&serverTimezone=UTC";
 		String user = "root";
 		String passwd = "2413";

 		try {
 			Class.forName("com.mysql.cj.jdbc.Driver");
 		} catch (Exception e) {
 			System.out.println("드라이버오류 발생");
 			out.println("<h2> 오류발생 <h2>");
 			out.close();
 			return;
 		}
 		try (Connection conn = DriverManager.getConnection(url, user, passwd);
 			Statement stmt = conn.createStatement();
 			ResultSet rs = stmt.executeQuery("select id, name "
 					+ "from meeting");) {
 			out.print("<h1> 방명록 리스트</h1><hr>");
 			out.print("<table border='1'>");
 			while(rs.next()) {
 				out.print("<tr>");
 				out.print("<td>" + rs.getString("id") + "</td>");
 				//out.print("<td>" + rs.getString("name") + "</td>");
 				out.print("</tr>");
 			}
 			out.print("</table>");
 		} catch (SQLException e) {
 			out.print("<h2> SQL Exception 오류 발생 ! </h2>");
 			e.printStackTrace();
 		}
 		out.close();
 	}
 }
