package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.validation.BodyValidator;
import io.javalin.validation.JavalinValidation;
import io.javalin.validation.ValidationError;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
            .orderBy()
                .id.asc()
                .findList();
        ctx.json(DB.json().toJson(users));
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
            .id.equalTo(Integer.parseInt(id))
            .findOne();
        ctx.json(DB.json().toJson(user));
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        String user = ctx.body();
        BodyValidator<User> userValidator = ctx.bodyValidator(User.class)
            .check(it -> !it.getFirstName().isEmpty(), "incorrect first name")
            .check(it -> !it.getLastName().isEmpty(), "incorrect last name")
            .check(it -> EmailValidator.getInstance().isValid(it.getEmail()), "incorrect email")
            .check(it ->  StringUtils.isNumeric(it.getPassword()), "incorrect password");
        Map<String, List<ValidationError<?>>> errorsMap = JavalinValidation.collectErrors(userValidator);
        if (errorsMap.isEmpty()) {
            User newUser = DB.json().toBean(User.class, user);
            newUser.save();
        } else {
        ctx.status(422);
        }
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
            .id.equalTo(Integer.parseInt(id))
            .findOne();
        user.delete();
        // END
    };
}
