package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        if (request.getQueryString() == null
        || !request.getQueryString().contains("search")
        || request.getParameter("search").equals("")) {
            getCompanies().stream()
                .forEach(comp -> pw.println(comp));
        } else if (request.getQueryString().contains("search")) {
            String value = request.getParameter("search");
            List<String> companies = getCompanies().stream()
                .filter(str -> str.contains(value))
                .toList();
            companies.stream()
                .forEach(comp -> pw.println(comp));
            if (companies.isEmpty()) {
                pw.println("Companies not found");
            }
        }
        // END
    }
}
