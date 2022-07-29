package com.week4.week4.Service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

    public boolean isAuthorized(String URI, String role) {
        if (URI.startsWith("/api/student") && (role.equals("STUDENT_ROLE") || role.equals("TEACHER_ROLE"))) {
            Logger log = LoggerFactory.getLogger(AuthorizationService.class);
            log.info(URI + " " + role);
            return true;
        }
        if (URI.startsWith("/api/teacher") && role.equals("TEACHER_ROLE")) {
            Logger log = LoggerFactory.getLogger(AuthorizationService.class);
            log.info(URI + " " + role);
            return true;
        }

        Logger log = LoggerFactory.getLogger(AuthorizationService.class);
        log.info(URI + " " + role);
        return false;
    }
}



