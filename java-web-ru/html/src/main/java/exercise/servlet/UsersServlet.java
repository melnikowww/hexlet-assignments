package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.lang3.ArrayUtils;

import static java.lang.System.out;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        String content = Files.readString(path).replace("[", "").replace("]", "");
        String[] contentArray = content.split(", ");
        List<User> userList = new ArrayList<>();
        for (String users: contentArray) {
            User user = objectMapper.readValue(users, User.class);
            userList.add(user);
        }
        return userList;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {
        // BEGIN
        PrintWriter pw = response.getWriter();
        List<User> users = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("<table>");
        for (User user: users) {
            body.append("<tr>");
            body.append("<td>" + user.getId() + "</td>");
            body.append("<td>");
            body.append("<a href=\"/users/" + user.getId() + "\">" + user.getFirstName() + " " + user.getLastName()
                + "</a>");
            body.append("</td>");
            body.append("</tr>");
        }
        body.append("</table>");
        response.setContentType("text/html;charset=UTF-8");
        pw.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        List<User> userList = getUsers();
        StringBuilder body = new StringBuilder();
        User rigthUser = null;
        for (User user: userList) {
            if (user.getId().equals(id)) {
                rigthUser = user;
            }
        }
        if (rigthUser == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        body.append("<table>");

        body.append("""
            <tr>
            <td> First Name </td>
            <td>
            """);
        body.append(rigthUser.getFirstName());
        body.append("""
            </td>
            </tr>
            """);

        body.append("""
            <tr>
            <td> Last Name </td>
            <td>
            """);
        body.append(rigthUser.getLastName());
        body.append("""
            </td>
            </tr>
            """);

        body.append("""
            <tr>
            <td> ID </td>
            <td>
            """);
        body.append(rigthUser.getId());
        body.append("""
            </td>
            </tr>
            """);

        body.append("""
            <tr>
            <td> E-mail </td>
            <td>
            """);
        body.append(rigthUser.getEmail());
        body.append("""
            </td>
            </tr>
            """);

        body.append("</table>");
        response.setContentType("text/html;charset=UTF-8");
        pw.println(body);
        // END
    }
}
