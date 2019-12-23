package com.scaffolding.sophia.admin.api.entity.vo;

import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.vo
 * @ClassName: DeptVo
 * @Date: 2019/12/20 14:00
 * @Description:
 * @Version: 1.0
 */
@Data
public class DeptVo implements Serializable {

    /**
     * id
     * */
    private String id;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父部门id
     */
    private String pid;

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
     * 部门类型(0 公司1部门)
     */
    private Integer deptType;

    /**
     * 部门所在的公司id
     */
    private String compId;

    /**
     * 部门所在的公司名称
     */
    private String compName;

    /**
     * bo转vo
     * @param dept
     * @return
     */
    public DeptVo buildVo(Dept dept){
        BeanUtils.copyProperties(dept,this);
        return this;
    }

    /**
     * bo转vo
     * @param list
     * @return
     */
    public List<DeptVo> buildVoList(List<Dept> list){
        List<DeptVo> voList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return voList;
        }
        list.forEach(item ->{
            DeptVo vo = new DeptVo();
            BeanUtils.copyProperties(item,vo);
            voList.add(vo);
        });
        return voList;
    }
}
