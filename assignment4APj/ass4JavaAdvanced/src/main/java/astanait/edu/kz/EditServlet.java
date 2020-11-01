package astanait.edu.kz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/EditServlet")
public class EditServlet extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    int numberOfColumns;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");

            String isbn = request.getParameter("isbn");
            String book_name = request.getParameter("book_name");
            String book_author = request.getParameter("book_author");
            String countofCopies = request.getParameter("countofCopies");

            statement = connection.prepareStatement("update Books set book_name = ?, book_author = ?, countofCopies = ? where isbn = ?");
            statement.setString(1,book_name);
            statement.setString(2,book_author);
            statement.setString(3,countofCopies);
            statement.setString(4,isbn);

            numberOfColumns = statement.executeUpdate();
            out.println("<font color='green'> The book has been edited! </font>");
            out.println("<h4>Editing time: " + new Date(session.getCreationTime()) + "</h4>");



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<font color='red'> Something is wrong! </font>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
