package astanait.edu.kz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/ViewBooks")
public class ViewBooks extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String countofCopies = request.getParameter("countofCopies");

        try {
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");
            String sql;
            sql = "select * from Books";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);



            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr bgcolor='#66ff00'>");
            out.println("<td>Books ISBN: </td>");
            out.println("<td>Books name: </td>");
            out.println("<td>Author: </td>");
            out.println("<td>Count of copies: </td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");

            while(resultSet.next()){
                out.println("<tr>");
                out.println("<td>" + resultSet.getString("isbn") + "</td>");
                out.println("<td>" + resultSet.getString("book_name") + "</td>");
                out.println("<td>" + resultSet.getString("book_author") + "</td>");
                out.println("<td>" + resultSet.getString("countofCopies") + "</td>");

                out.println("<td bgcolor='#adff2f'>" + "<a href='Editreturn?isbn=" + resultSet.getString("isbn") + "'> Edit </a>" + "</td>");
                out.println("<td bgcolor='#adff2f'>" + "<a href='Delete?isbn=" + resultSet.getString("isbn") + "'> Delete </a>" + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");





            Cookie c1 = new Cookie("isbn",isbn);
            Cookie c2 = new Cookie("bookName",bookName);
            Cookie c3 = new Cookie("bookAuthor",bookAuthor);
            Cookie c4 = new Cookie("countofCopies",countofCopies);

            c1.setMaxAge(3600);
            c2.setMaxAge(3600);
            c3.setMaxAge(3600);
            c4.setMaxAge(3600);

            response.addCookie(c1);
            response.addCookie(c2);
            response.addCookie(c3);
            response.addCookie(c4);


        } catch (ClassNotFoundException ex) {
           Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> Something is wrong! </font></h2></center>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
