package com.scaffolding.sophia.admin.biz.service.dept;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.bo.Dept;

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
     * @return IPage<Dept>
     */
    IPage<Dept> queryCompanyList(Map<String, Object> params);

    /**
     * 新增或修改公司
     *
     * @param dept
     * @return boolean
     */
    boolean addOrUpdateDept(Dept dept);
    /**
     * 新增或修改公司
     *
     * @param dept
     * @return boolean
     */
    boolean addOrUpdateCompany(Dept dept);

    /**
     * 删除公司或部门
     *
     * @param id id
     * @return boolean
     */
    boolean deleteDept(Long id);

    /**
     * 批量删除
     *
     * @param ids 批量删除ids
     * @return boolean
     */
    boolean deleteBatchDept(List<Long> ids);

}
