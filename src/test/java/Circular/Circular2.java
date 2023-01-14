package Circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Circular2 {
    @Autowired
    public Circular1 c1;
}
