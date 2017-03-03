package com.guangbei.common.config.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * 跨过SSL证书认证
 * Created by xugang on 2017/3/3.
 */
public class NotSSLClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
    private static final Logger logger = LoggerFactory.getLogger(NotSSLClientHttpRequestFactory.class);

    private static class NullHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    private HostnameVerifier verifier = new NullHostnameVerifier();

    public static SSLContext trustSelfSignedSSL() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLContext.setDefault(ctx);
            return ctx;
        } catch (Exception e) {
            logger.error("SSL证书认证配置发生异常", e);
            return null;
        }
    }

    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection sslConnection = (HttpsURLConnection)connection;
            try {
                sslConnection.setSSLSocketFactory(trustSelfSignedSSL().getSocketFactory());
                sslConnection.setHostnameVerifier(new NullHostnameVerifier());
                sslConnection.setAllowUserInteraction(true);
            } catch(Exception e) {
                logger.error("SSL证书预连接发生异常", e);
            }
        }
        super.prepareConnection(connection, httpMethod);
    }
}
