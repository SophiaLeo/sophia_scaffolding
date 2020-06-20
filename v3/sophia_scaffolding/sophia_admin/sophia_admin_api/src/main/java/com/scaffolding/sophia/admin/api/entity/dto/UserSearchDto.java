package com.scaffolding.sophia.admin.api.entity.dto;

import com.scaffolding.sophia.common.base.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dto
 * @ClassName: UserSearchDto
 * @Description:
 * @Version: 1.0
 */
@Data
@ApiModel(value = "UserSearchDto", description = "用户搜索")
public class UserSearchDto extends PageDto {

    @ApiModelProperty("当前登录用户公司或部门id")
    private String deptPid;

    @ApiModelProperty("当前登录用户id")
    private String userId;

    @ApiModelProperty("当前登录用户角色码")
    private String roleCode;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("角色id")
    private String roleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;
}
