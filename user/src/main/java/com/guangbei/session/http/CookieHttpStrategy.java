package com.guangbei.session.http;

import com.guangbei.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CookieHttpStrategy implements HttpStrategy {

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        List<String> sessionIds = CookieSerializer.readCookieValues(request);
        if (sessionIds.size() < 1) {
            return null;
        }
        return sessionIds.get(0);
    }

    @Override
    public void createSession(Session session, HttpServletRequest request,
                              HttpServletResponse response) {
        CookieSerializer.writeCookieValue(session.getId(), request, response);
    }

    @Override
    public void invalidateSession(HttpServletRequest request,
                                  HttpServletResponse response) {
        CookieSerializer.writeCookieValue("", request, response);
    }

}
