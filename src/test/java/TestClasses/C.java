package TestClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C {
    public B bField;

    @Autowired
    public C(B bField) {
        this.bField = bField;
    }
}
