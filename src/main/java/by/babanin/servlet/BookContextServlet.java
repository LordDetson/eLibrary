package by.babanin.servlet;

import by.babanin.controller.BookListController;
import by.babanin.model.Book;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class BookContextServlet extends HttpServlet {
    @FunctionalInterface
    private interface CommandActionContentBook {
        void execute(Book book, BookListController controller, OutputStream os, HttpServletRequest req, HttpServletResponse resp) throws IOException;
    }

    private enum ActionContentBook {
        SAVE_PDF(BookContextServlet::actionSavePDF),
        READ_PDF(BookContextServlet::actionReadPDF),
        SHOW_IMAGE(BookContextServlet::actionShowImage);

        CommandActionContentBook command;

        ActionContentBook(CommandActionContentBook command) {
            this.command = command;
        }

        private CommandActionContentBook getCommand() {
            return command;
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            Long bookId = Long.valueOf(req.getParameter("id"));
            ActionContentBook action = ActionContentBook.valueOf(req.getParameter("action").toUpperCase());
            BookListController bookListController = (BookListController) req.getSession(false).getAttribute("bookListController");
            Book book = bookListController.getCurrentBooks().get(bookId);

            action.getCommand().execute(book, bookListController, outputStream, req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void actionShowImage(Book book, BookListController controller, OutputStream os, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("image/jpeg");
        byte[] image = controller.getBookImage(book.getId());
        resp.setContentLength(image.length);
        os.write(image);
    }

    private static void actionSavePDF(Book book, BookListController controller, OutputStream os, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/pdf");
        byte[] contentBook = controller.getBookContent(book.getId());
        resp.setContentLength(contentBook.length);
        StringBuilder builder = new StringBuilder();
        builder.append("attachment;filename=")
                .append(URLEncoder.encode(book.getAuthor(), "UTF-8"))
                .append("-")
                .append(URLEncoder.encode(book.getName(), "UTF-8"))
                .append(".pdf");
        resp.setHeader("Content-Disposition", builder.toString());
        os.write(contentBook);
    }

    private static void actionReadPDF(Book book, BookListController controller, OutputStream os, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/pdf");
        byte[] contentBook = controller.getBookContent(book.getId());
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
