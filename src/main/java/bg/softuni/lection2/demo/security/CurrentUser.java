package bg.softuni.lection2.demo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser  {

    private static final String ANONYMOUS = "anonymous";

    private String name = ANONYMOUS;

    private boolean isAnonymous;


    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public CurrentUser setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
        return this;
    }


}
