package com.scaffolding.sophia.admin.api.entity.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scaffolding.sophia.common.base.bo.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dict
 * @ClassName: Dict
 * @Date: 2019/11/5 10:05
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_dict")
@ApiModel(value = "Dict",description = "字典设置")
public class Dict extends BaseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 字典值
     */
    private Integer value;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否删除 (0 是  1否)
     */
    private Integer isDeleted;


}
