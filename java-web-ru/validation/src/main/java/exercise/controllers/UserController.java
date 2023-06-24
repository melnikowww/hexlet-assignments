package exercise.controllers;

import io.ebeaninternal.server.util.Str;
import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.javalin.validation.Validator;
import io.javalin.validation.ValidationError;
import io.javalin.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;
import io.javalin.http.Context;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    private static void removeFlashMessage(Context ctx) {
        ctx.sessionAttribute("flash", null);
    }

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
        removeFlashMessage(ctx);
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
//        Validator<User> userValidator = ctx.formParamAsClass("User", User.class)
//            .check(it -> !it.getFirstName().isEmpty(), "First Name cannot be empty")
//            .check(it -> !it.getLastName().isEmpty(), "Last Name cannot be empty")
//            .check(it -> !EmailValidator.getInstance().isValid(it.getEmail()), "Email is not valid")
//            .check(it -> StringUtils.isNumeric(it.getPassword()), "Password is not valid")
//            .check(it -> it.getPassword().length() > 4, "Password is shorter than 4 symbols");
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
            .check(it -> !it.isEmpty(), "First Name cannot be empty");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
            .check(it -> !it.isEmpty(), "Last Name cannot be empty");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
            .check(it -> EmailValidator.getInstance().isValid(it), "Email is not valid");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
            .check(it -> StringUtils.isNumeric(it), "Password is not valid")
            .check(pw -> pw.length() > 4, "Password is shorter than 4 symbols");

        Map<String, List<ValidationError<?>>> errorsMap = JavalinValidation.collectErrors(firstNameValidator,
            lastNameValidator,
            emailValidator,
            passwordValidator);

        String firstName = ctx.formParamAsClass("firstName", String.class).getOrDefault(null);
        String lastName = ctx.formParamAsClass("lastName", String.class).getOrDefault(null);
        String email = ctx.formParamAsClass("email", String.class).getOrDefault(null);
        String password = ctx.formParamAsClass("password", String.class).getOrDefault(null);

        User newUser = new User(firstName, lastName, email, password);
//        Map<String, List<ValidationError<?>>> errorsMap = JavalinValidation.collectErrors(userValidator);
        if (errorsMap.isEmpty()) {
            newUser.save();
            ctx.sessionAttribute("flash", "Пользователь успешно создан");
            ctx.redirect("/users");
        } else {
            ctx.status(422);
            ctx.attribute("errors", errorsMap);
            ctx.attribute("user", newUser);
            ctx.render("/users/new.html");
        }
        // END
    };
}
