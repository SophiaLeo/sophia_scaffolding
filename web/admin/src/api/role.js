import request from '@/utils/request'

export function roleList(data) {
  return request({
    url: '/admin/role/web/list',
    method: 'get',
    params: data
  })
}

export function roleInfo(id) {
  return request({
    url: `/admin/role/web/info/${id}`,
    method: 'get'
  })
}

export function saveRole(data) {
  return request({
    url: '/admin/role/web/save',
    method: 'post',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: `/admin/role/web/update`,
    method: 'put',
    data: data
  })
}

export function deleteRole(id) {
  return request({
    url: `/admin/role/web/del/${id}`,
    method: 'delete'
  })
}

export function deleteBatch(ids) {
  return request({
    url: `/admin/role/web/delBatch`,
    method: 'delete',
    data: { ids }
  })
}

export function roleSelectList(id) {
  return request({
    url: `/admin/role/web/select/list`,
    method: 'get',
    params: { id }
  })
}

