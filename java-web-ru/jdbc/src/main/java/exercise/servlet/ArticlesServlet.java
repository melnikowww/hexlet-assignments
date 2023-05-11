package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        final int NUMBER_OF_ROWS = 10;
        int page;
        List<Map<String, String>> articles = new LinkedList<>();

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        } else {
            page = 1;
        }
        if (page < 1) {
            page = 1;
        }

        String query = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (page - 1) * NUMBER_OF_ROWS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                articles.add(Map.of(
                    "id", resultSet.getString("id"),
                    "title", resultSet.getString("title"),
                    "body", resultSet.getString("body")
                ));

            }
        } catch (SQLException exception) {
            response.sendError(500, exception.getMessage());
            return;
        }

        request.setAttribute("page", page);
        request.setAttribute("articles", articles);

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<String> ARTICLES_ID = new LinkedList<>();
        String query1 = "SELECT * FROM articles";
        try {
            PreparedStatement statement = connection.prepareStatement(query1);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ARTICLES_ID.add(resultSet.getString("id"));
            }
        } catch (SQLException exception) {
            response.sendError(500, exception.getMessage());
            return;
        }

        String id = getId(request);
        String query = "SELECT * FROM articles WHERE id = ?";
        if (ARTICLES_ID.contains(id)) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.first();
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                request.setAttribute("title", title);
                request.setAttribute("body", body);
            } catch (SQLException exception) {
                response.sendError(500, exception.getMessage());
                return;
            }
        } else {
            response.sendError(404);
        }
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
