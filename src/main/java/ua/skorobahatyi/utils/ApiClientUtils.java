package ua.skorobahatyi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import ua.skorobahatyi.exceptions.SystemException;

import java.io.IOException;
import java.util.Map;

public class ApiClientUtils {

    public String httpGet(String url, Map<String, String> headers, int... expectedResponseCode) throws Exception {
        HttpGet request = new HttpGet(url);
        appendHeaders(request, headers);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (isResponseOk(statusCode, expectedResponseCode)) {
                    HttpEntity entity = response.getEntity();
                    String resultContent = EntityUtils.toString(entity);

                    return resultContent;
                }
                String message = "Error, Response code: " + statusCode + ", url:  " + url;
                throw new SystemException(statusCode, message);
            }
        } catch (IOException e) {
            throw new Exception("HttpClient is broken");
        }
    }

    public String httpGet(String url, Map<String, String> headers) throws Exception {
        return httpGet(url, headers, HttpStatus.SC_OK);
    }

    private static boolean isResponseOk(int statusCode, int... expectedResponseCode) throws Exception {
        if (expectedResponseCode.length == 0) {
            throw new Exception("An expected response code must be supplied", null);
        }
        for (int code : expectedResponseCode) {
            if (statusCode == code) {
                return true;
            }
        }
        return false;
    }

    public <R> GenericResponse<R> httpGet(String url, Map<String, String> headers, Class<R> valueType) throws Exception {
        return convert(httpGet(url, headers), valueType);
    }

    public <R> GenericResponse<R> httpGet(String url, Map<String, String> headers, TypeReference<R> typeReference) throws Exception {
        return convert(httpGet(url, headers), typeReference);
    }

    private static void appendHeaders(AbstractHttpMessage request, Map<String, String> headers) {
        if (headers != null) {
            headers.entrySet().forEach(e -> request.addHeader(e.getKey(), e.getValue()));
        }
    }

    public static <R> GenericResponse convert(String original, Class<R> valueType) throws Exception {
        if (original == null) {
            throw new Exception("Body of response is null, cannot convert to " + valueType.getName());
        }
        return GenericResponse.createInstance(original, valueType);
    }

    public static <R> GenericResponse convert(String original, TypeReference<R> typeReference) throws Exception {
        if (original == null) {
            throw new Exception("Body of response is null, cannot convert to " + typeReference.getType().getTypeName());
        }
        return GenericResponse.createInstance(original, typeReference);
    }
}
