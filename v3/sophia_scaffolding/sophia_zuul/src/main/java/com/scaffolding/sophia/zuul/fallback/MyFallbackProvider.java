package com.scaffolding.sophia.zuul.fallback;

import com.scaffolding.sophia.common.base.enums.SophiaHttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.zuul.fallback
 * @ClassName: MyFallbackProvider
 * @Description: 网关熔断配置
 * @Version: 1.0
 */
@Component
public class MyFallbackProvider implements FallbackProvider {

    Logger logger = LoggerFactory.getLogger(MyFallbackProvider.class);

    @Override
    public String getRoute() {
        return "*";  //实现对所有的路由服务的熔断
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() {
                SophiaHttpStatus resultEnum = SophiaHttpStatus.SERVER_TIMEOUT;
                String data = "";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", resultEnum.getCode());
                    jsonObject.put("msg", resultEnum.getMessage());
                    jsonObject.put("data", data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data = jsonObject.toString();
                logger.error(data);
                return new ByteArrayInputStream(data.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
