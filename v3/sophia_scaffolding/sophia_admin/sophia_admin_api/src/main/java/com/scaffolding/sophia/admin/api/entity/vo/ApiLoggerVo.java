package com.scaffolding.sophia.admin.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LHL
 */
@Data
public class ApiLoggerVo implements Serializable {


    private String id;

    /**
     * url
     */
    private String uri;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 类名
     */
    private String className;

    /**
     * 访问时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 服务id
     */
    private String serviceId;
    /**
     * 访问用户名称
     */
    private String userName;
    /**
     * 参数
     */
    private String params;

    /**
     * 访问ip
     */
    private String ip;

    /**
     * 请求方式
     */
    private String method;


    /**
     * bo转vo
     * @param apiLogger
     * @return
     */
    public ApiLoggerVo buildVo(ApiLogger apiLogger){
        BeanUtils.copyProperties(apiLogger,this);
        return this;
    }

    /**
     * bo转vo
     * @param list
     * @return
     */
    public List<ApiLoggerVo> buildVoList(List<ApiLogger> list){
        List<ApiLoggerVo> voList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return voList;
        }
        list.forEach(item ->{
            ApiLoggerVo vo = new ApiLoggerVo();
            BeanUtils.copyProperties(item,vo);
            voList.add(vo);
        });
        return voList;
    }
}
