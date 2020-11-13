package org.example.TeremHMS.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, MANAGER, LEADER, STAFF;

    @Override
    public String getAuthority() {
        return name();
    }
}