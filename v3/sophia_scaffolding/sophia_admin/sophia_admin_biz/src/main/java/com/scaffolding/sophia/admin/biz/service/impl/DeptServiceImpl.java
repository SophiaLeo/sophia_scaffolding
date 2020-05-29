package com.scaffolding.sophia.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import com.scaffolding.sophia.admin.api.entity.dto.DeptDto;
import com.scaffolding.sophia.admin.api.entity.vo.DeptVo;
import com.scaffolding.sophia.admin.biz.mapper.DeptMapper;
import com.scaffolding.sophia.admin.biz.service.DeptService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String deptId = UserUtils.getLoginUser().getDeptId();
        if (StringUtils.isBlank(deptId)) {
            //公司管理员
            deptId = UserUtils.getLoginUser().getCompId();
        }
        List<DeptVo> deptList = baseMapper.selectByDeptId(deptId);
        return treeDept(deptList);
    }

    @Override
    public IPage<DeptVo> queryCompanyList(Map<String, Object> param) {
        Integer currentPage = param.get("currentPage") == null ? 1 : Integer.parseInt(String.valueOf(param.get("currentPage")));
        Integer pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(String.valueOf(param.get("pageSize")));
        Page<DeptVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.findCompanyList(page, param));
    }

    @Override
    public List<DeptVo> queryCompanySelectList() {
        List<Dept> deptList = baseMapper.selectList(new QueryWrapper<Dept>().eq("DEPT_TYPE", BizConstants.COMP));
        return new DeptVo().buildVoList(deptList);
    }

    @Override
    public boolean addOrUpdateDept(DeptDto deptDto) {
        if (StringUtils.isBlank(deptDto.getUserId())) {
            deptDto.setUserId(UserUtils.getLoginUser().getId());
        }
        if (StringUtils.isBlank(deptDto.getCompId())) {
            deptDto.setCompId(UserUtils.getLoginUser().getCompId());
        }
        Dept dept = new Dept();
        dept.buildBo(deptDto);
        if (StringUtils.isBlank(dept.getId())) {
            String uuid = UuidUtils.getUuid();
            dept.setId(uuid);
            dept.setDeptType(BizConstants.DEPT);
            dept.setCompId(deptDto.getCompId());
            dept.setCreateTime(LocalDateTime.now());
            dept.setCreateUser(deptDto.getUserId());
            if (StringUtils.isBlank(dept.getPid())) {
                dept.setPids("[" + dept.getCompId() + "],[" + uuid + "]");
            } else {
                Dept dept1 = baseMapper.selectById(dept.getPid());
                dept.setPids(dept1.getPids() + ",[" + uuid + "]");
            }
            return baseMapper.insert(dept) > 0;
        } else {
            dept.setUpdateTime(LocalDateTime.now());
            dept.setUpdateUser(deptDto.getUserId());
            return baseMapper.updateById(dept) > 0;
        }
    }

    @Override
    public boolean addOrUpdateCompany(DeptDto deptDto) {
        if (StringUtils.isBlank(deptDto.getUserId())) {
            deptDto.setUserId(UserUtils.getLoginUser().getId());
        }
        Dept dept = new Dept();
        dept.buildBo(deptDto);
        if (StringUtils.isBlank(deptDto.getId())) {
            String uuid = UuidUtils.getUuid();
            dept.setId(uuid);
            dept.setPid(BizConstants.NONE_NODE);
            dept.setDeptType(BizConstants.COMP);
            dept.setCreateTime(LocalDateTime.now());
            dept.setCreateUser(deptDto.getUserId());
            dept.setPids("[" + uuid + "]");
            return baseMapper.insert(dept) > 0;
        } else {
            dept.setUpdateTime(LocalDateTime.now());
            dept.setUpdateUser(deptDto.getUserId());
            return baseMapper.updateById(dept) > 0;
        }
    }

    @Override
    public boolean deleteDept(String id) {
        //删除子节点
        List<DeptVo> deptVos = baseMapper.selectByDeptId(id);
        if (deptVos.size() > 0) {
            return baseMapper.deleteBatchIds(deptVos.parallelStream().map(DeptVo::getId).collect(Collectors.toList())) > 0;
        }
        return true;
    }

    @Override
    public boolean deleteBatchDept(List<String> ids) {
        List<DeptVo> result = new LinkedList<>();
        ids.stream().parallel().forEach(
                x -> {
                    List<DeptVo> deptVos = baseMapper.selectByDeptId(x);
                    if (deptVos.size() > 0) {
                        result.addAll(deptVos);
                    }
                }
        );
        List<String> list = result.stream().parallel().distinct().map(DeptVo::getId).collect(Collectors.toList());
        //删除子节点
        return baseMapper.deleteBatchIds(list) > 0;
    }

    @Override
    public DeptVo queryDeptById(String id) {
        return new DeptVo().buildVo(baseMapper.selectById(id));
    }

    private List<Map<String, Object>> treeDept(List<DeptVo> deptList) {
        String deptId = UserUtils.getLoginUser().getDeptId();
        if (StringUtils.isNotBlank(deptId)) {
            Dept dept = baseMapper.selectById(deptId);
            if (null != dept) {
                deptId = dept.getPid();
            }
        } else {
            //超级管理员
            deptId = BizConstants.NONE_NODE;
        }
        List<Map<String, Object>> list = new LinkedList<>();
        for (DeptVo treeNode : deptList) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (treeNode.getPid().equals(deptId)) {
                setTreeMap(mapArr, treeNode, deptList);
                list.add(mapArr);
            }
        }
        return list;
    }

    private List<?> deptChild(String id, List<DeptVo> deptList) {
        List<Map<String, Object>> lists = new ArrayList<>();
        for (DeptVo a : deptList) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (id.equals(a.getPid())) {
                setTreeMap(childArray, a, deptList);
                lists.add(childArray);
            }
        }
        return lists;
    }

    private void setTreeMap(Map<String, Object> mapArr, DeptVo treeNode, List<DeptVo> deptList) {
        mapArr.put("id", treeNode.getId());
        mapArr.put("fullName", treeNode.getFullName());
        mapArr.put("simpleName", treeNode.getSimpleName());
        mapArr.put("address", treeNode.getAddress());
        mapArr.put("tips", treeNode.getTips());
        if (!treeNode.getPid().equals(BizConstants.NONE_NODE)) {
            mapArr.put("pid", treeNode.getPid());
        }
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
