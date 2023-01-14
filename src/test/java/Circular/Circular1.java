package Circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Circular1 {
    @Autowired
    public Circular2 c2;
}
