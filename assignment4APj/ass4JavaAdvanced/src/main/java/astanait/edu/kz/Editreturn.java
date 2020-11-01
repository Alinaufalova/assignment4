package astanait.edu.kz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/Editreturn")
public class Editreturn extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String bIsbn = request.getParameter("isbn");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");

            statement = connection.prepareStatement("select * from Books where isbn = ?");
            statement.setString(1,bIsbn);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                out.print("<h2>Edit the book</h2>");
                out.print("<form action='EditServlet' method='POST'");
                out.print("<table>");
                out.print("<tr> <td>ISBN: </td> <td><input type='text' name='isbn' id='isbn' value= '" + resultSet.getString("isbn") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Book name: </td> <td><input type='text' name='book_name' id='book_name' value= '" + resultSet.getString("book_name") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Author: </td> <td><input type='text' name='book_author' id='book_author' value= '" + resultSet.getString("book_author") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Count of copies: </td> <td><input type='text' name='countofCopies' id='countofCopies' value= '" + resultSet.getString("countofCopies") + "'/> </td></tr><br><br>");
                out.print("<tr> <td colspan='2'><input type='submit' value= 'Edit'/> </td> </tr>");
                out.print("</table>");
                out.print("</form>");
            }



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
