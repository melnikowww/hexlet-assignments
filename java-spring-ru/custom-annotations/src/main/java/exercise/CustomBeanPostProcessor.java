package exercise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.slf4j.event.Level;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public  class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    Map<String, Object> annotatedBeans = new HashMap<>();
    Map<String, Level> loggingLevels = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String levelStr = bean.getClass().getAnnotation(Inspect.class).level();
            Level level = levelStr.equals("info") ? Level.INFO : Level.DEBUG;
            annotatedBeans.put(beanName, bean);
            loggingLevels.put(beanName, level);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!annotatedBeans.containsKey(beanName)) {
            return bean;
        }
        Level level = loggingLevels.get(beanName);
        return Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(), CalculatorImpl.class.getInterfaces(),
            (proxy, method, args) -> {
                LOGGER.atLevel(level).log("Was called method: " + method.getName()
                    + "() with arguments: [{}, {}]", args[0], args[1]);
                return method.invoke(bean, args);
            }
        );
    }
}
// END
