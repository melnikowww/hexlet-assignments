package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int time = LocalDateTime.now().getHour();
        return time >= 6 && time < 12 ? new Morning() :
            time >= 12 && time < 18 ? new Day() :
            time >= 18 && time < 23 ? new Evening() : new Night();
    }
}
// END
