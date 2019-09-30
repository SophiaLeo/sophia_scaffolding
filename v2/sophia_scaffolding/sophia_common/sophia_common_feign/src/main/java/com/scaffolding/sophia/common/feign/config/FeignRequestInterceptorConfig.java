package com.scaffolding.sophia.common.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.feign
 * @ClassName: FeignRequestInterceptorConfig
 * @Description:
 * @Version: 1.0
 */
@Component
public class FeignRequestInterceptorConfig  implements RequestInterceptor {

    private  static  final Logger log = LoggerFactory.getLogger(FeignRequestInterceptorConfig.class);

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if (request != null) {
            log.error("调用feign传递header携带token");

//        只携带token
           String authorization = request.getHeader(AUTHORIZATION_HEADER);
//            requestTemplate.header("Authorization", authorization);
            System.err.println("Authorization :\t\t"+ authorization);

//        携带全部
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                    log.debug("name ：\t\t" + name);
                    log.debug("values ： \t\t" + values);

                }
            }

        }

    }

}