import Circular.Circular1;
import Circular.Circular2;
import TestClasses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan("TestClasses")
@SpringBootConfiguration
public class TestClass {
    ApplicationContext context = null;
    AnnotationConfigApplicationContext circularContext = null;

    @BeforeEach
    public void init() {
        context = SpringApplication.run(TestClass.class);
        circularContext = new AnnotationConfigApplicationContext("Circular");
    }

    @Bean
    public A getA() {
        return new A();
    }

    @Bean
    public B getB() {
        return new B();
    }

    @Bean("NamedD")
    public D getNamedD() {
        return new D();
    }

    @Bean
    public E getE() {
        return new E();
    }
    @Bean("NamedE")
    public E getNamedE() {
        return new E();
    }

    @Test
    @DisplayName("Autowire constructor")
    void testAutowireConstructor() {
        C c = context.getBean(C.class);
        assertNotNull(c.bField);
    }


    @Test
    @DisplayName("Autowire field")
    void testAutowireField() {
        A a = context.getBean(A.class);
        assertNotNull(a.bField);
    }

    @Test
    @DisplayName("Get named bean")
    void testNamedBean() {
        D d = (D) context.getBean("NamedD");
        assertNotNull(d);
    }

    @Test
    @DisplayName("Get bean from class with multiple beans")
    void testMultipleBeanGetBean() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            E e = (E) context.getBean(E.class);
        });
    }

    @Test
    @DisplayName("Get missing bean")
    void testMissingBean() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            Object o = context.getBean("random");
        });
    }

    @Test
    @DisplayName("Get bean from interface")
    void testInterfaceBean() {
        D d = (D) context.getBean(DI.class);
        assertNotNull(d);
    }

    @Test
    @DisplayName("Get bean from non-implemented interface")
    void testNonImplementedInterfaceBean() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            Object o = context.getBean(NotImplemented.class);
        });
    }

    @Test
    @DisplayName("Test afterPropertiesSet Method")
    void testAfterPropertiesSet() {
        String message = "message: test123";
        F f = context.getBean(F.class);
        assertEquals(f.newMessage, message);
    }

    @Test
    @DisplayName("Circular dependencies")
    void testCircularDependenciesInitialization() {
        Circular1 c1 = circularContext.getBean(Circular1.class);
        assertEquals(c1.c2.c1, c1);
    }
}
