package com.guangbei.session.http;

import com.guangbei.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface HttpStrategy {
	
	String getRequestedSessionId(HttpServletRequest request);
	
	void createSession(Session session, HttpServletRequest request, HttpServletResponse response);

	void invalidateSession(HttpServletRequest request, HttpServletResponse response);

}
