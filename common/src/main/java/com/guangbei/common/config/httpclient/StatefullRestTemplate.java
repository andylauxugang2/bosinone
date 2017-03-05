package com.guangbei.common.config.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xugang on 2017/3/5.
 */
public class StatefullRestTemplate extends RestTemplate {
    private final HttpClient httpClient;
    private final CookieStore cookieStore;
    private final HttpContext httpContext;
    private final StatefullHttpComponentsClientHttpRequestFactory statefullHttpComponentsClientHttpRequestFactory;

    public StatefullRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
        HttpParams params = new BasicHttpParams();
        HttpClientParams.setRedirecting(params, false);

        httpClient = new DefaultHttpClient(params);
        cookieStore = new BasicCookieStore();
        httpContext = new BasicHttpContext();
        httpContext.setAttribute(ClientContext.COOKIE_STORE, getCookieStore());
        statefullHttpComponentsClientHttpRequestFactory = new StatefullHttpComponentsClientHttpRequestFactory(httpClient, httpContext);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public HttpContext getHttpContext() {
        return httpContext;
    }

    public StatefullHttpComponentsClientHttpRequestFactory getStatefulHttpClientRequestFactory() {
        return statefullHttpComponentsClientHttpRequestFactory;
    }
}
