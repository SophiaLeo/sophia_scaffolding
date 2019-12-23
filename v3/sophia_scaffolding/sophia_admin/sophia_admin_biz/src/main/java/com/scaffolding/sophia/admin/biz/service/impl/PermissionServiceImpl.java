package com.scaffolding.sophia.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Permission;
import com.scaffolding.sophia.admin.api.entity.bo.RolePermission;
import com.scaffolding.sophia.admin.api.entity.dto.PermissionDto;
import com.scaffolding.sophia.admin.api.entity.vo.PermissionVo;
import com.scaffolding.sophia.admin.biz.mapper.PermissionMapper;
import com.scaffolding.sophia.admin.biz.mapper.RolePermissionMapper;
import com.scaffolding.sophia.admin.biz.service.PermissionService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @Package: com.scaffolding.sophia.admin.biz.service.impl
 * @ClassName: AuthorityServiceImpl
 * @Date: 2019/11/5 09:30
 * @Description:
 * @Version: 1.0
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<PermissionVo> findAuthorityByUserId(String id) {
        return baseMapper.findAuthorityByUserId(id);
    }

    @Override
    public List<PermissionVo> findAuthorityByRoleId(String id) {
        return baseMapper.findAuthorityByRoleId(id);
    }

    @Override
    public Map<String, Object> getPermissionByUserId(String id) {
        List<PermissionVo> permissionList = baseMapper.getPermissionByUserId(id);
        return treePermission1(permissionList);
    }

    @Override
    public IPage<PermissionVo> queryPermissionList(Map<String,Object> param) {
        Integer currentPage = param.get("currentPage") == null ? 1 : Integer.parseInt(String.valueOf(param.get("currentPage")));
        Integer pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(String.valueOf(param.get("pageSize")));
        Page<PermissionVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.selectPermissionList(page));
    }

    @Override
    public List<Map<String, Object>> queryPermissionTree() {
        return treePermission(baseMapper.selectList(new QueryWrapper<Permission>().eq("STATUS", 1).orderByAsc("SORT").orderByDesc("CREATE_TIME")));
    }

    @Override
    public PermissionVo queryPermissionById(String id) {
        return new PermissionVo().buildVo(baseMapper.selectById(id));
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean saveOrUpdatePermission(PermissionDto permissionDto) {
        if (StringUtils.isBlank(permissionDto.getUserId())) {
            permissionDto.setUserId(UserUtils.getLoginUser().getId());
        }
        if (StringUtils.isBlank(permissionDto.getPid())) {
            permissionDto.setPid(BizConstants.NONE_NODE);
        }
        Permission permission = new Permission();
        permission.buildBo(permissionDto);
        if (StringUtils.isBlank(permissionDto.getId())) {
            permission.setId(UuidUtils.getUuid());
            permission.setCreateTime(LocalDateTime.now());
            permission.setCreateUser(permissionDto.getUserId());
            permission.setStatus(BizConstants.YES);
            return baseMapper.insert(permission) > 0;
        } else {
            permission.setUpdateTime(LocalDateTime.now());
            permission.setUpdateUser(permissionDto.getUserId());
            return baseMapper.updateById(permission) > 0;
        }
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deletePermission(String id) {
        boolean flag = false;
        List<Permission> permissions = baseMapper.selectList(new QueryWrapper<Permission>().eq("STATUS", 1).orderByAsc("SORT").orderByDesc("CREATE_TIME"));
        if (permissions.size() > 0) {
            List<Permission> chilrenPermissionList = new LinkedList<>();
            childPermissionList(permissions, id, chilrenPermissionList);
            chilrenPermissionList = chilrenPermissionList.parallelStream().distinct().collect(Collectors.toList());
            if (chilrenPermissionList.size() > 0) {
                List<String> ids = chilrenPermissionList.parallelStream().map(Permission::getId).collect(Collectors.toList());
                if (ids.size() > 0) {
                    List<RolePermission> list = rolePermissionMapper.selectBatchPerIds(ids);
                    if (list.size() > 0) {
                        rolePermissionMapper.deleteBatchIds(list.stream().map(RolePermission::getId).collect(Collectors.toList()));
                    }
                    flag = baseMapper.updateBatchIds(ids) > 0;
                }
            }
        }
        return flag;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteBatchPermission(List<String> ids) {
        boolean flag = false;
        List<Permission> permissions = baseMapper.selectList(new QueryWrapper<Permission>().eq("STATUS", 1).orderByAsc("SORT").orderByDesc("CREATE_TIME"));
        List<Permission> children = new LinkedList<>();
        if (permissions.size() > 0) {
            List<Permission> finalChildren = children;
            ids.parallelStream().forEach(x -> {
                childPermissionList(permissions, x, finalChildren);
            });
            children = finalChildren;
            children = children.parallelStream().distinct().collect(Collectors.toList());
            if (children.size() > 0) {
                List<String> idList = children.parallelStream().map(Permission::getId).distinct().collect(Collectors.toList());
                if (idList.size() > 0) {
                    List<RolePermission> list = rolePermissionMapper.selectBatchPerIds(idList);
                    if (list.size() > 0) {
                        rolePermissionMapper.deleteBatchIds(list.stream().map(RolePermission::getId).collect(Collectors.toList()));
                    }
                    flag = baseMapper.updateBatchIds(idList) > 0;
                }
            }
        }
        return flag;
    }

    @Override
    public List<String> getPermissionByRoleId(String id) {
        List<String> arrayList = new ArrayList<>();
        List<Permission> list = baseMapper.selectByRoleId(id);
        if (list.size() > 0) {
            arrayList = getPermission(list);
        }
        return arrayList;
    }

    @Override
    public boolean savePermissionAndRole(String id, List<String> ids) {
        List<RolePermission> list = rolePermissionMapper.selectByRoleId(id);
        if (list.size() > 0) {
            rolePermissionMapper.deleteBatchIds(list.parallelStream().map(RolePermission::getId).collect(Collectors.toList()));
        }
        List<RolePermission> collect = ids.stream().parallel().map(x -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(UuidUtils.getUuid());
            rolePermission.setRoleId(id);
            rolePermission.setPermId(x);
            return rolePermission;
        }).collect(Collectors.toList());
        return rolePermissionMapper.insertBatch(collect) > 0;
    }

    private List<String> getPermission(List<Permission> permissions) {
        List<String> list = new ArrayList<>();
        for (Permission treeNode : permissions) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            List<String> stringList = new ArrayList<>();
            if (treeNode.getPid().equals(BizConstants.NONE_NODE)) {
                getList(mapArr, treeNode, permissions, stringList);
                list.addAll(stringList);
            }
        }
        return list.parallelStream().distinct().collect(Collectors.toList());
    }


    private List getList(Map<String, Object> mapArr, Permission treeNode, List<Permission> menuCommon, List<String> stringList) {
        mapArr.put("id", treeNode.getId());
        List<?> childrens = getOrgChild(treeNode.getId(), menuCommon, stringList);
        if (childrens.size() <= 0) {
            stringList.add(treeNode.getId());
        }
        return stringList;
    }

    private List<?> getOrgChild(String id, List<Permission> menuCommon, List<String> stringList) {
        List<Object> lists = new ArrayList<>();
        for (Permission a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (a.getPid().equals(id)) {
                getList(childArray, a, menuCommon, stringList);
                lists.add(childArray);
            }
        }
        return lists;
    }

    /**
     * 获取某个父节点下面的所有子节点
     *
     * @param permissionList 权限
     * @param pid            父节点
     * @param childPerm      子节点
     * @return List<Permission>
     */
    private List<Permission> childPermissionList(List<Permission> permissionList, String pid, List<Permission> childPerm) {
        for (Permission mu : permissionList) {
            if (mu.getId().equals(pid)) {
                childPerm.add(mu);
            }
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getPid().equals(pid)) {
                //递归遍历下一级
                childPerm.add(mu);
                childPermissionList(permissionList, mu.getId(), childPerm);
            }
        }
        return childPerm.parallelStream().distinct().collect(Collectors.toList());
    }


    /**
     * @param permissions 权限
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @description 处理数据, 返回树形
     * @method treePermission
     * @auther LHL
     */
    private List<Map<String, Object>> treePermission(List<Permission> permissions) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Permission treeNode : permissions) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (treeNode.getPid().equals(BizConstants.NONE_NODE)) {
                setTreeMap(mapArr, treeNode, permissions);
                list.add(mapArr);
            }
        }
        return list;
    }

    private List<?> orgChild(String id, List<Permission> menuCommon) {
        List<Object> lists = new ArrayList<>();
        for (Permission a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (null != a.getPid()) {
                if (a.getPid().equals(id)) {
                    setTreeMap(childArray, a, menuCommon);
                    lists.add(childArray);
                }
            }
        }
        return lists;
    }

    private void setTreeMap(Map<String, Object> mapArr, Permission treeNode, List<Permission> menuCommon) {
        mapArr.put("id", treeNode.getId());
        mapArr.put("name", treeNode.getName());
        mapArr.put("pid", treeNode.getPid());
        mapArr.put("code", treeNode.getCode());
        mapArr.put("urlPath", treeNode.getUrlPath());
        mapArr.put("iconPath", treeNode.getIconPath());
        mapArr.put("type", treeNode.getType());
        List<?> childrens = orgChild(treeNode.getId(), menuCommon);
        if (childrens.size() > 0) {
            mapArr.put("hasChildren", true);
        } else {
            mapArr.put("hasChildren", false);
        }
        mapArr.put("childrens", childrens);
    }

    /**
     * @param permissions 权限
     * @return java.util.Map<String, Object>
     * @description 登陆时获取权限 过滤出按钮
     * @method treePermission1
     * @auther LHL
     */
    private Map<String, Object> treePermission1(List<PermissionVo> permissions) {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> meun = new LinkedHashMap<>();
        for (PermissionVo treeNode : permissions) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (treeNode.getPid().equals(BizConstants.NONE_NODE)) {
                setTreeMap1(mapArr, treeNode, permissions, meun);
                list.add(mapArr);
            }
        }
        result.put("permissions", list);
        result.put("buttons", meun);
        return result;
    }

    private List<?> orgChild1(String id, List<PermissionVo> menuCommon, Map<String, Object> menus) {
        List<Object> lists = new ArrayList<>();
        for (PermissionVo a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (null != a.getPid()) {
                if (a.getPid().equals(id)) {
                    setTreeMap1(childArray, a, menuCommon, menus);
                    if (a.getType().equals(0)) {
                        lists.add(childArray);
                    }
                }
            }
        }
        return lists;
    }

    private void setTreeMap1(Map<String, Object> mapArr, PermissionVo treeNode, List<PermissionVo> menuCommon, Map<String, Object> menus) {
        List<Object> list = new ArrayList<>();
        if (treeNode.getType().equals(0)) {
            mapArr.put("id", treeNode.getId());
            mapArr.put("name", treeNode.getName());
            mapArr.put("pid", treeNode.getPid());
            mapArr.put("code", treeNode.getCode());
            mapArr.put("urlPath", treeNode.getUrlPath());
            mapArr.put("iconPath", treeNode.getIconPath());
            mapArr.put("type", treeNode.getType());
        } else {
            list.add(treeNode);
            if (menus.containsKey(treeNode.getPidName())) {
                List o = (List) menus.get(treeNode.getPidName());
                o.addAll(list);
            } else {
                menus.put(treeNode.getPidName(), list);
            }
        }
        List<?> childrens = orgChild1(treeNode.getId(), menuCommon, menus);
        if (treeNode.getType().equals(0)) {
            if (childrens.size() > 0) {
                mapArr.put("hasChildren", true);
            } else {
                mapArr.put("hasChildren", false);
            }
            mapArr.put("childrens", childrens);
        }
    }
}
