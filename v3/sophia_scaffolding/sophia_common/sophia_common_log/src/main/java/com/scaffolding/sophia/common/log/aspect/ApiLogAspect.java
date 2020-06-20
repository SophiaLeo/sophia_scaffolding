package com.scaffolding.sophia.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.common.base.constants.GlobalsConstants;
import com.scaffolding.sophia.common.log.annotation.SysLog;
import com.scaffolding.sophia.common.log.event.SysLogEvent;
import com.scaffolding.sophia.common.util.UuidUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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
@AllArgsConstructor
public class ApiLogAspect {

    private ApplicationEventPublisher publisher;

    private ThreadLocal<ApiLogger> logThreadLocal = new ThreadLocal<>();

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     *
     * @param publisher
     */
    public ApiLogAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     * //@Pointcut("execution(public * com.scaffolding.sophia.*.*.controller.*.*(..) )")
     */
    @Pointcut("@annotation(com.scaffolding.sophia.common.log.annotation.SysLog)")
    public void log() {

    }

    /**
     * <方法执行前>
     *
     * @param point  [参数说明]
     * @param sysLog sysLog
     * @return void [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    @Before(value = "log()")
    public void before(JoinPoint point) throws Throwable {
        ApiLogger apiLogger = new ApiLogger();
        //将当前实体保存到threadLocal
        log.info("日志开始记录");
        logThreadLocal.set(apiLogger);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String operation = request.getMethod();
        //if (!GlobalsConstants.GET.equals(operation) && ! (request.getRequestURI().contains(GlobalsConstants.SAVE_LOG)) && ! (request.getRequestURI().contains(GlobalsConstants.GET_TOKEN))) {
        apiLogger.setId(UuidUtil.getUuid());
        apiLogger.setCreateTime(LocalDateTime.now());
        if (!request.getRequestURI().contains(GlobalsConstants.LOGIN)) {
            apiLogger.setUserName(getUsername());
            apiLogger.setParams(Arrays.toString(point.getArgs()));
        }
        apiLogger.setMethodName(getMethodDescription(point) + ":" + point.getSignature().getName());
        apiLogger.setClassName(point.getTarget().getClass().getName());
        apiLogger.setMethod(operation);
        apiLogger.setUri(URLUtil.getPath(request.getRequestURI()));
        apiLogger.setIp(getIpAddr(request));
        apiLogger.setServiceId(getClientId());
        log.info("结束日志记录");
    }


    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) {
        //得到当前线程的log对象
        ApiLogger apiLogger = logThreadLocal.get();
        // 发布事件
        publisher.publishEvent(new SysLogEvent(apiLogger));
        //移除当前log实体
        logThreadLocal.remove();
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        //得到当前线程的log对象
        ApiLogger apiLogger = logThreadLocal.get();
        // 发布事件
        publisher.publishEvent(new SysLogEvent(apiLogger));
        //移除当前log实体
        logThreadLocal.remove();
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

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    private String getMethodDescription(JoinPoint point) throws Exception {
        // 获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SysLog.class).value();
                    break;
                }
            }
        }
        return description;
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
