import request from '@/utils/request'

export function logList(data) {
  const pageSize = data.pageSize
  const currentPage = data.currentPage
  const userName = data.userName
  const methodName = data.methodName
  const method = data.method
  const startTime = data.startTime
  const endTime = data.endTime
  return request({
    url: '/admin/log/web/list',
    method: 'get',
    params: { pageSize, currentPage, userName, methodName, method, startTime, endTime }
  })
}

export function deleteLog(id) {
  return request({
    url: `/admin/log/web/del/${id}`,
    method: 'delete'
  })
}

export function deleteBatch(ids) {
  return request({
    url: `/admin/log/web/delBatch`,
    method: 'delete',
    data: { ids }
  })
}
