package com.scaffolding.sophia.common.base.support;

import com.scaffolding.sophia.common.base.enums.SophiaHttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.support
 * @ClassName: BaseController
 * @Description: 基础控制器
 * @Version: 1.0
 */
public class BaseController {

    private static final String UNKNOWN = "unknown";

    /**
     * session会话
     */
    protected HttpSession session;
    /**
     * httpRequest
     */
    protected HttpServletRequest request;

    /**
     * response
     */
    protected HttpServletResponse response;

    /**
     * 获取request、response、session
     */
    @ModelAttribute
    public void setRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 获取当前网络ip
     *
     * @return
     */
    public String getIpAddr(){
        String ipAddress = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ipAddress) && !UNKNOWN.equalsIgnoreCase(ipAddress)) {
            return ipAddress;
        }
        ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){
            //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    ///**
    // * 获取IP地址
    // * @return
    // */
    //protected String getIpAddr() {
    //    String ip = request.getHeader("X-Real-IP");
    //    if (!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
    //        return ip;
    //    }
    //    ip = request.getHeader("X-Forwarded-For");
    //    if (!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
    //        // 多次反向代理后会有多个IP值，第一个为真实IP。
    //        int index = ip.indexOf(',');
    //        if (index != -1) {
    //            return ip.substring(0, index);
    //        } else {
    //            return ip;
    //        }
    //    } else {
    //        return request.getRemoteAddr();
    //    }
    //}

    /**
     * 获取请求中说有的请求参数，并转换成Map
     * @return
     */
    protected Map<String,Object> getParams()  {
        Map<String,Object> map = new HashMap<>(16);
        Enumeration em =  this.request.getParameterNames();
        while (em.hasMoreElements()){
            String key = (String) em.nextElement();
            Object value = this.request.getParameter(key);

            if(value!=null){
                if("startTime".equals(key) || "endTime".equals(key)){
                    value = LocalDateTime.ofEpochSecond(Long.parseLong(String.valueOf(value))/1000,0, ZoneOffset.ofHours(8));
                }
                map.put(key,value);
            }
        }
        return map;
    }


    public ApiResponse success(){
        return  new ApiResponse(SophiaHttpStatus.SUCCESS);
    }

    public ApiResponse success(String msg){
        ApiResponse re = new ApiResponse(SophiaHttpStatus.SUCCESS);
        re.setMessage(msg);
        return  re;
    }

    public ApiResponse success(String msg,Object data){
        ApiResponse re = new ApiResponse(SophiaHttpStatus.SUCCESS);
        re.setMessage(msg);
        re.setData(data);
        return  re;
    }

    public ApiResponse success(Object data){
        ApiResponse re = new ApiResponse(SophiaHttpStatus.SUCCESS);
        re.setData(data);
        return  re;
    }

    public ApiResponse fail(){
        return  new ApiResponse(SophiaHttpStatus.COMMON_FAIL);
    }

    public ApiResponse fail(String msg){
        ApiResponse re = new ApiResponse(SophiaHttpStatus.COMMON_FAIL);
        re.setMessage(msg);
        return  re;
    }

    public ApiResponse handle(boolean bl){
        if(bl){
            return success();
        }else {
            return fail();
        }
    }
}
