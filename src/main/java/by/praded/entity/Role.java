package by.praded.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Kiryl Praded
 * 25.11.2020
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
