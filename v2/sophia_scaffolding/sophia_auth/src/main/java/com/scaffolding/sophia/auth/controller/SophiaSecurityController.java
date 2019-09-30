package com.scaffolding.sophia.auth.controller;

import com.scaffolding.sophia.common.security.properties.SophiaSecurityProperties;
import com.scaffolding.sophia.common.base.enums.SophiaHttpStatus;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.auth.controller
 * @ClassName: SophiaSecurityController
 * @Description:
 * @Version: 1.0
 */
@RestController
public class SophiaSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(SophiaSecurityController.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private SophiaSecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    public ApiResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.debug("引发跳转的Url:"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getWeb().getLoginPage());
            }
        }
        response.setStatus(SophiaHttpStatus.INVALID_TOKEN.getCode());
        return new ApiResponse(SophiaHttpStatus.INVALID_TOKEN);
    }
}
