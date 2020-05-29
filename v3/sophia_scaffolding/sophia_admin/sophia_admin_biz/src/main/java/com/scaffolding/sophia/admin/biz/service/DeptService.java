package com.scaffolding.sophia.admin.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.dto.DeptDto;
import com.scaffolding.sophia.admin.api.entity.vo.DeptVo;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.dept
 * @ClassName: DeptService
 * @Date: 2019/11/5 10:57
 * @Description:
 * @Version: 1.0
 */
public interface DeptService {
    /**
     * 根据登录用户获取
     *
     * @return
     */
    List<Map<String, Object>> queryDeptTree();

    /**
     * 公司分页列表
     *
     * @param params 条件
     * @return IPage<DeptVo>
     */
    IPage<DeptVo> queryCompanyList(Map<String, Object> params);



    /**
     * 公司下拉框查询
     *
     * @return List
     * */
    List<DeptVo> queryCompanySelectList();

    /**
     * 新增或修改公司
     *
     * @param deptDto
     * @return boolean
     */
    boolean addOrUpdateDept(DeptDto deptDto);
    /**
     * 新增或修改公司
     *
     * @param deptDto
     * @return boolean
     */
    boolean addOrUpdateCompany(DeptDto deptDto);

    /**
     * 删除公司或部门
     *
     * @param id id
     * @return boolean
     */
    boolean deleteDept(String id);

    /**
     * 批量删除
     *
     * @param ids 批量删除ids
     * @return boolean
     */
    boolean deleteBatchDept(List<String> ids);

    /**
     * 获取部门公司
     *
     * @param id id
     * @return DeptVo
     */
    DeptVo queryDeptById(String id);
}
