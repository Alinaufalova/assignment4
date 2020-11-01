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

@WebServlet(name = "/Editreturn2")
public class Editreturn2 extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String rId = request.getParameter("id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");

            statement = connection.prepareStatement("select * from Readers where id = ?");
            statement.setString(1,rId);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                out.print("<h2>Edit the reader</h2>");
                out.print("<form action='EditServlet2' method='POST'");
                out.print("<table>");
                out.print("<tr> <td>Reader ID: </td> <td><input type='text' name='id' id='id' value= '" + resultSet.getString("id") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Full name: </td> <td><input type='text' name='full_name' id='full_name' value= '" + resultSet.getString("full_name") + "'/> </td></tr><br><br>");
                out.print("<tr> <td>Books: </td> <td><input type='text' name='books_' id='books_' value= '" + resultSet.getString("books") + "'/> </td></tr><br><br>");
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
