package TestClasses;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class F implements InitializingBean {
    public String newMessage;
    @Autowired
    D dField;
    @Override
    public void afterPropertiesSet() throws Exception {
        newMessage = "message: " + dField.message;
    }
}
