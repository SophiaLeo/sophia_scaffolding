package com.scaffolding.sophia.admin.biz.service.dept.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import com.scaffolding.sophia.admin.biz.dao.dept.DeptMapper;
import com.scaffolding.sophia.admin.biz.service.dept.DeptService;
import com.scaffolding.sophia.common.security.util.UserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.dept.impl
 * @ClassName: DeptServiceImpl
 * @Date: 2019/11/5 10:57
 * @Description:
 * @Version: 1.0
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public List<Map<String, Object>> queryDeptTree() {
        //部门管理员
        Long deptId = UserUtils.getLoginUser().getDeptId();
        if (null == deptId) {
            //公司管理员
            deptId = UserUtils.getLoginUser().getCompId();
        }
        List<Dept> deptList = baseMapper.selectByDeptId(deptId);
        return treeDept(deptList);
    }

    @Override
    public IPage<Dept> queryCompanyList(Map<String, Object> param) {
        Integer currentPage = param.get("currentPage") == null ? 1 : Integer.parseInt(String.valueOf(param.get("currentPage")));
        Integer pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(String.valueOf(param.get("pageSize")));
        Page<Dept> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.findCompanyList(page, param));
    }

    @Override
    public boolean addOrUpdateDept(Dept dept) {
        if (null == dept.getId()) {
            dept.setDeptType(1);
            dept.setCompId(UserUtils.getLoginUser().getCompId());
            dept.setCreateTime(LocalDateTime.now());
            dept.setCreateUser(UserUtils.getLoginUser().getId());
            int i = baseMapper.insertSelective(dept);
            if (null == dept.getPid()) {
                dept.setPids("[" + dept.getCompId() + "],[" + dept.getId() + "]");
            } else {
                Dept dept1 = baseMapper.selectById(dept.getPid());
                dept.setPids(dept1.getPids() + ",[" + dept.getId() + "]");
            }
            int u = baseMapper.updateById(dept);
            return i > 0 && u > 0;
        } else {
            dept.setUpdateTime(LocalDateTime.now());
            dept.setUpdateUser(UserUtils.getLoginUser().getId());
            return baseMapper.updateById(dept) > 0;
        }
    }

    @Override
    public boolean addOrUpdateCompany(Dept dept) {
        if (null == dept.getId()) {
            dept.setDeptType(0);
            dept.setCreateTime(LocalDateTime.now());
            dept.setCreateUser(UserUtils.getLoginUser().getId());
            int i = baseMapper.insertSelective(dept);
            dept.setPids("[" + dept.getId() + "]");
            int u = baseMapper.updateById(dept);
            return i > 0 && u > 0;
        } else {
            dept.setUpdateTime(LocalDateTime.now());
            dept.setUpdateUser(UserUtils.getLoginUser().getId());
            return baseMapper.updateById(dept) > 0;
        }
    }

    @Override
    public boolean deleteDept(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteBatchDept(List<Long> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private List<Map<String, Object>> treeDept(List<Dept> deptList) {
        Long deptId = UserUtils.getLoginUser().getDeptId();
        if (null != deptId) {
            Dept dept = baseMapper.selectById(deptId);
            deptId = dept.getPid();
        }
        List<Map<String, Object>> list = new LinkedList<>();
        for (Dept treeNode : deptList) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (treeNode.getPid() == deptId) {
                setTreeMap(mapArr, treeNode, deptList);
                list.add(mapArr);
            }
        }
        return list;
    }

    private List<?> deptChild(Long id, List<Dept> deptList) {
        List<Map<String, Object>> lists = new ArrayList<>();
        for (Dept a : deptList) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (id == a.getPid()) {
                setTreeMap(childArray, a, deptList);
                lists.add(childArray);
            }
        }
        return lists;
    }

    private void setTreeMap(Map<String, Object> mapArr, Dept treeNode, List<Dept> deptList) {
        mapArr.put("id", treeNode.getId());
        mapArr.put("fullName", treeNode.getFullName());
        mapArr.put("simpleName", treeNode.getSimpleName());
        mapArr.put("address", treeNode.getAddress());
        mapArr.put("tips", treeNode.getTips());
        mapArr.put("pid", treeNode.getPid());
        mapArr.put("type", treeNode.getDeptType());
        List<?> children = deptChild(treeNode.getId(), deptList);
        if (children.size() > 0) {
            mapArr.put("hasChildren", true);
        } else {
            mapArr.put("hasChildren", false);
        }
        mapArr.put("children", children);
    }
}
