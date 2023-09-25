package exercise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.slf4j.event.Level;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public  class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorImpl.class);
    @Autowired
    Calculator  calculator;
    Level level = Level.INFO;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            calculator = (CalculatorImpl) bean;
            String levelStr = bean.getClass().getAnnotation(Inspect.class).level();
            level = levelStr.equals("info") ? Level.INFO : Level.DEBUG;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Calculator calc = (Calculator) Proxy.newProxyInstance(
            CalculatorImpl.class.getClassLoader(),
            CalculatorImpl.class.getInterfaces(),
            (proxy, method, args) -> {
                LOGGER.atLevel(level).log("Was called method: " + method.getName()
                    + "with arguments: {}", args);
                return method.invoke(calculator, args);
            }
        );
        return bean;
    }
}
// END
