package com.scaffolding.sophia.admin.api.entity.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LHL
 */
@Data
@NoArgsConstructor
@TableName("sys_api_logger")
@ApiModel(value = "ApiLogger", description = "日志")
public class ApiLogger implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
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
}