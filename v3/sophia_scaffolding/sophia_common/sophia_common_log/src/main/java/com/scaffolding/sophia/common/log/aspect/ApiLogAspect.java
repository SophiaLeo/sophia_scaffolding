package com.scaffolding.sophia.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.feign.client.ApiLoggerClient;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author LHL
 * 操作日志使用spring event异步入库
 */
@Aspect
@Component
@Slf4j
public class ApiLogAspect {

    @Autowired
    private ApiLoggerClient apiLoggerClient;

    @Pointcut("execution(public * com.scaffolding.sophia.*.*.controller.*.*(..) )")
    public void log() {

    }

    /**
     * <方法执行前>
     *
     * @param joinpoint [参数说明]
     * @return void [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @Before("log()")
    public void before(JoinPoint joinpoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String operation = request.getMethod();
        if (!GlobalsConstants.GET.equals(operation) && ! (request.getRequestURI().contains(GlobalsConstants.SAVE_LOG)) && ! (request.getRequestURI().contains(GlobalsConstants.GET_TOKEN))) {
            try {
                ApiLogger apiLogger = new ApiLogger();
                apiLogger.setId(UuidUtils.getUuid());
                apiLogger.setCreateTime(LocalDateTime.now());
                if (!request.getRequestURI().contains(GlobalsConstants.LOGIN)) {
                    apiLogger.setUserName(getUsername());
                    apiLogger.setParams(Arrays.toString(joinpoint.getArgs()));
                }
                apiLogger.setMethodName(joinpoint.getSignature().getName());
                apiLogger.setClassName(joinpoint.getSignature().getDeclaringTypeName());
                apiLogger.setMethod(operation);
                apiLogger.setUri(URLUtil.getPath(request.getRequestURI()));
                apiLogger.setIp(getIpAddr(request));
                apiLogger.setServiceId(getClientId());
                apiLoggerClient.saveLog(apiLogger);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }


    /**
     * <方法执行后>
     *
     * @param joinpoint [参数说明]
     * @return void [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @After("log()")
    public void after(JoinPoint joinpoint) {

    }

    /**
     * <获取返回结果后日志>
     *
     * @param joinpoint
     * @param returnValue [参数说明]
     * @return void [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @AfterReturning(returning = "returnValue", pointcut = "log()")
    public void afterReturn(JoinPoint joinpoint, Object returnValue) {

    }

    /**
     * <异常日志>
     *
     * @param joinpoint
     * @param e         [参数说明]
     * @return void [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void handlerException(JoinPoint joinpoint, Exception e) {

    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || GlobalsConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || GlobalsConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || GlobalsConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals(GlobalsConstants.LOCAL_HOST_127) || ip.equals(GlobalsConstants.LOCAL_HOST_1)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

}
