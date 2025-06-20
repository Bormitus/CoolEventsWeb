package com.coolevents.web.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getSessionUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken))
        {
            return authentication.getName();
        }
        return null;
    }

    public static boolean isCurrentUserOwner(String username) {
        String currentUsername = getSessionUser();
        return currentUsername != null && currentUsername.equals(username);
    }

}
