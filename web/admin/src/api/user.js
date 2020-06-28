import request from '@/utils/request'

export function login(data) {
  const username = data.username
  const password = data.password
  const code = data.code
  const randomStr = data.randomStr
  return request({
    url: '/admin/api/token',
    method: 'post',
    // data
    params: { username, password, code, randomStr }
  })
}

export function getInfo() {
  return request({
    url: '/admin/user/principal',
    method: 'get'
    // params: { access_token: token }
  })
}

export function logout() {
  return request({
    url: '/auth/home/logout',
    method: 'delete'
  })
}

export function userList(data) {
  const pageSize = data.pageSize
  const currentPage = data.currentPage
  const username = data.username
  const nickname = data.nickname
  const roleId = data.roleId
  const startTime = data.startTime
  const endTime = data.endTime
  return request({
    url: '/admin/user/web',
    method: 'get',
    params: { pageSize, currentPage, username, nickname, roleId, startTime, endTime }
  })
}

export function updateStatus(data) {
  return request({
    url: `/admin/user/web/status/${data.id}`,
    method: 'put',
    params: { status: data.status }
  })
}

export function userInfo(id) {
  return request({
    url: `/admin/user/web/info/${id}`,
    method: 'get'
  })
}

export function checkUser(data) {
  return request({
    url: `/admin/user/web/check`,
    method: 'get',
    params: { id: data.id, str: data.str, type: data.type }
  })
}

export function deleteUser(id) {
  return request({
    url: `/admin/user/web/del/${id}`,
    method: 'delete'
  })
}

export function deleteBatch(ids) {
  return request({
    url: `/admin/user/web/delBatch`,
    method: 'delete',
    data: { ids }
  })
}

export function saveUser(data) {
  return request({
    url: `/admin/user/web/add`,
    method: 'post',
    data: data
  })
}

export function updateUser(data) {
  return request({
    url: `/admin/user/web/update`,
    method: 'put',
    data: data
  })
}

export function reset(data) {
  return request({
    url: `/admin/user/web/resetPassword`,
    method: 'put',
    data: data
  })
}
