package by.babanin.servlet;

import by.babanin.controller.SearchController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowImageServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("image/jpeg");
            Long bookId = Long.valueOf(request.getParameter("id"));
            SearchController searchController = (SearchController) request.getSession(false).getAttribute("searchController");
            byte[] image = searchController.getBookImage(bookId);
            response.setContentLength(image.length);
            outputStream.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
