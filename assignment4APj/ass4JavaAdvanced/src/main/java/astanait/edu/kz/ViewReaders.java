package astanait.edu.kz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/ViewReaders")
public class ViewReaders extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String books = request.getParameter("books");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");
            String sql;
            sql = "select * from Readers";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            ArrayList arrayList = new ArrayList();
            arrayList.add("Reader ID: ");
            arrayList.add("Full name: ");
            arrayList.add("Books: ");
            arrayList.add("Edit");
            arrayList.add("Delete");


            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr bgcolor='#66ff00'>");
            out.println("<td>Reader ID: </td>");
            out.println("<td>Full name: </td>");
            out.println("<td>Books: </td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");

            while(resultSet.next()){
                out.println("<tr>");
                out.println("<td>" + resultSet.getString("id") + "</td>");
                out.println("<td>" + resultSet.getString("full_name") + "</td>");
                out.println("<td>" + resultSet.getString("books") + "</td>");

                out.println("<td bgcolor='#adff2f'>" + "<a href='Editreturn2?id=" + resultSet.getString("id") + "'> Edit </a>" + "</td>");
                out.println("<td bgcolor='#adff2f'>" + "<a href='Delete2?id=" + resultSet.getString("id") + "'> Delete </a>" + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");



            Cookie cookie1 = new Cookie("id",id);
            Cookie cookie2 = new Cookie("fullName",fullName);
            Cookie cookie3 = new Cookie("books",books);

            cookie1.setMaxAge(3600);
            cookie2.setMaxAge(3600);
            cookie3.setMaxAge(3600);

            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);


      } catch (ClassNotFoundException ex) {
               Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> Something is wrong! </font><h2></center>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
