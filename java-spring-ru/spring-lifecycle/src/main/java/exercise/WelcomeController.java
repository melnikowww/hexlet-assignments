package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Meal meal;

    @Autowired
    MyApplicationConfig myApplicationConfig;

    @GetMapping("/daytime")
    public String getGreeting() {
        String daytime = myApplicationConfig.getDaytime().getName();
        String mealName = meal.getMealForDaytime(myApplicationConfig.getDaytime().getName());
        return "It is " + daytime + " now." + "Enjoy your " + mealName;
    }
}
// END
