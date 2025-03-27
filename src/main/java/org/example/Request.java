package org.example;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    String method;
    String path;
    String protocol;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> queryParams = new HashMap<>();

    public Request(String method, String fullPath, String protocol, Map<String, String> headers) {
        this.method = method;
        this.protocol = protocol;
        this.headers = headers;

        // Разделение пути и Query String
        if (fullPath.contains("?")) {
            String[] parts = fullPath.split("\\?", 2);
            this.path = parts[0];
            parseQueryParams(parts[1]);
        } else {
            this.path = fullPath;
        }
    }

    private void parseQueryParams(String queryString) {
        List<NameValuePair> params = URLEncodedUtils.parse(URI.create("http://dummy.com?" + queryString), StandardCharsets.UTF_8);
        for (NameValuePair param : params) {
            queryParams.put(param.getName(), param.getValue());
        }
    }

    public String getQueryParam(String name) {
        return queryParams.get(name);
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}