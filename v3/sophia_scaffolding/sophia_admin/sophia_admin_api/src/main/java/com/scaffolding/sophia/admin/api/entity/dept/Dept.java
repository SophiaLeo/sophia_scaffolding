package com.scaffolding.sophia.admin.api.entity.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scaffolding.sophia.common.base.bo.BaseBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dept
 * @ClassName: Dept
 * @Date: 2019/11/5 10:00
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_dept")
@ApiModel(value = "Dept",description = "部门设置")
public class Dept extends BaseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父部门id
     */
    private Long pid;

    /**
     * 父级ids
     */
    private String pids;

    /**
     * 简称
     */
    private String simpleName;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 提示
     */
    private String tips;

    /**
     * 地址
     */
    private String address;

    /**
     * 部门类型(0 公司 1 部门)
     */
    private Integer deptType;

    /**
     *  部门所在的公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long compId;
}
