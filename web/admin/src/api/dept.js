import request from '@/utils/request'

export function deptTree(compId) {
  return request({
    url: `/admin/dept/web`,
    method: 'get',
    params: { compId }
  })
}
