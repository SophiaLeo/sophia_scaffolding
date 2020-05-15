import request from '@/utils/request'

export function dictByType(type) {
  return request({
    url: '/admin/dict/web/details/list',
    method: 'get',
    params: { type }
  })
}
