package com.guangbei.session.http;

import com.guangbei.session.Session;
import com.guangbei.session.utils.HttpConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderHttpStrategy implements HttpStrategy {

    public static String headerName = HttpConstants.HEADER_SESSION_NAME;

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        return request.getHeader(headerName);
    }

    @Override
    public void createSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(headerName, session.getId());
    }

    @Override
    public void invalidateSession(HttpServletRequest request,
                                  HttpServletResponse response) {
        response.setHeader(headerName, "");
    }

}
