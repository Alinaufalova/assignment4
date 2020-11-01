<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Stack" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
    <style>
        .book,.reader{
            border: 1px solid black;
            margin: 15px;
        }
    </style>
</head>
<body>

<div class="book">
<%

    ArrayList booksList = new ArrayList();
    booksList.add("1. Abai Zholy");
    booksList.add("2. Harry Potter");
    booksList.add("3. Green Mile");

    out.print("<center>");
    out.println("<h2>MOST READ BOOKS</h2>");
    out.println("<h4>" + booksList.get(0).toString().replaceAll("^\\[|\\]$", "") + "</h4>");
    out.println("<h4>" + booksList.get(1).toString().replaceAll("^\\[|\\]$", "") + "</h4>");
    out.println("<h4>" + booksList.get(2).toString().replaceAll("^\\[|\\]$", "") + "</h4>");
    out.print("</center>");




%>

<!--Add a book-->
<form method="post" action="<%=request.getContextPath()%>/Books">
    <center>
    <table width="600px" border="1">
        <tr>
            <td colspan="2">
                <h1>Add Book</h1>
            </td>
        </tr>
        <tr>
            <td>Book ISBN: </td>
            <td><input type="text" name="isbn" id="isbnId"></td>
        </tr>
        <tr>
            <td>Book name: </td>
            <td><input type="text" name="bookName" id="bookNameId"></td>
        </tr>
        <tr>
            <td>Author: </td>
            <td><input type="text" name="bookAuthor" id="bookAuthorId"></td>
        </tr>
        <tr>
            <td>Count of copies: </td>
            <td><input type="text" name="countofCopies" id="countofCopiesId"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add">
            </td>
        </tr>
    </table>
    </center>
</form>
<center><a href="<%=request.getContextPath()%>/ViewBooks">View Books</a></center><br><br><br>

</div><br><br>

<div class="reader"><br><br><br>
<!--Add a reader-->
<form method="post" action="<%=request.getContextPath()%>/Readers">
<center>
    <table width="600px" border="1">
        <tr>
            <td colspan="2">
                <h1>Add Reader</h1>
            </td>
        </tr>
        <tr>
            <td>Reader ID: </td>
            <td><input type="text" name="id" id="id"></td>
        </tr>
        <tr>
            <td>Full name: </td>
            <td><input type="text" name="fullName" id="fullNameId"></td>
        </tr>
        <tr>
            <td>Books: </td>
            <td><input type="text" name="books" id="booksId"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add">
            </td>
        </tr>
    </table>
</center>
</form>
<center><a href="<%=request.getContextPath()%>/ViewReaders">View Readers</a></center>
    <%
        Stack<String> readers = new Stack<>();
        readers.add("Robert Garcia");
        readers.add("Lewis Bush");
        readers.add("Sherry Young");
        readers.add("Lucas Andrews");
        readers.add("Carolinа Donaldson");
        readers.add("Colin Berrington");

        out.println("<center>");
        out.println("<h2>THE MOST FREQUENT READERS</h2>");
        out.println("<h4>1. " + readers.pop() + "</h4>");
        out.println("<h4>2. " + readers.pop() + "</h4>");
        out.println("<h4>3. " + readers.pop() + "</h4>");
        out.println("</center>");

    %>
</div>

</body>
</html>
