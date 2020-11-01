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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "/Books")
public class Books extends HttpServlet {
    Connection connection;
    PreparedStatement statement;
    int numberOfColumns;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/assignment4","root","");
            String isbn = request.getParameter("isbn");
            String bookName = request.getParameter("bookName");
            String bookAuthor = request.getParameter("bookAuthor");
            String countofCopies = request.getParameter("countofCopies");

            statement = connection.prepareStatement("insert into books(isbn, book_name, book_author, countofCopies)values(?,?,?,?) ");
            statement.setString(1,isbn);
            statement.setString(2,bookName);
            statement.setString(3,bookAuthor);
            statement.setString(4,countofCopies);
            numberOfColumns = statement.executeUpdate();


            out.println("<center>");
            out.println("<h2><font color='green'> The book has been added! </font></h2>");
            out.println("<h4>Adding time: " + new Date(session.getCreationTime()) + "</h4>");
            out.println("</center>");


        } catch (ClassNotFoundException ex) {
           Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            out.println("<center><h2><font color='red'> The book with such ISBN is already exist! </font></h2></center>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
