package com.tencent.trustsql.sdk.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {

    private static Log logger = LogFactory.getLog(HttpClientUtil.class);
    private static PoolingHttpClientConnectionManager poolConnManager;

    static {
        try {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create
                    ().register(
                    "http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        } catch (Exception e) {
            logger.error("init http client exception", e);
            throw new RuntimeException("init http client exception", e);
        }
    }

    public static String post(String url, String reqBodyJson) throws RuntimeException {
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity strEntity = new StringEntity(reqBodyJson, "UTF-8");
            httpPost.setEntity(strEntity);
            CloseableHttpResponse response = getConnection().execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
            } else {
                logger.error(String.format("Unexpected cft http response statuts: [%s]", status + ""));
                throw new ClientProtocolException(
                        String.format("Unexpected cft http response statuts: [%s]", status + ""));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("ClientProtocolException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    public String post(String url, Map<String, String> reqbody) throws RuntimeException {
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            List<NameValuePair> params = new ArrayList<>();
            for (String key : reqbody.keySet()) {
                if (reqbody.get(key) != null && !StringUtils.isEmpty(reqbody.get(key))) {
                    params.add(new BasicNameValuePair(key, reqbody.get(key)));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            CloseableHttpResponse response = getConnection().execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
            } else {
                logger.error(String.format("Unexpected http response statuts: [%s]", status + ""));
                throw new ClientProtocolException(String.format("Unexpected http response statuts: [%s]", status + ""));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("ClientProtocolException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    private static CloseableHttpClient getConnection() {
        return HttpClients.custom().setConnectionManager(poolConnManager).build();

    }

}