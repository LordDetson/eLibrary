package by.babanin.servlet;

import by.babanin.controller.BookListController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PdfContent extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        ServletOutputStream os = resp.getOutputStream();
        Long bookId = Long.valueOf(req.getParameter("id"));
        BookListController bookListController = (BookListController) req.getSession(false).getAttribute("bookListController");
        byte[] contentBook = bookListController.getBookContent(bookId);
        resp.setContentLength(contentBook.length);
        os.write(contentBook);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
