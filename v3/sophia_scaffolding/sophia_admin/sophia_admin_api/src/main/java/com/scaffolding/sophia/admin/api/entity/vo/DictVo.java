package com.scaffolding.sophia.admin.api.entity.vo;

import com.scaffolding.sophia.admin.api.entity.bo.Dict;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LHL
 */
@Data
public class DictVo implements Serializable {

    private String id;

    /**
     * 字典值
     * */
    private Integer value;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 拼接名
     */
    private String details;

    /**
     * 子类
     */
    private List<DictVo> children;


    /**
     * bo转vo
     *
     * @param dict
     * @return
     */
    public DictVo buildVo(Dict dict) {
        BeanUtils.copyProperties(dict, this);
        return this;
    }

    /**
     * bo转vo
     *
     * @param list
     * @return
     */
    public List<DictVo> buildVoList(List<Dict> list) {
        List<DictVo> voList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return voList;
        }
        list.forEach(item -> {
            DictVo vo = new DictVo();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        return voList;
    }
}
