import request from '@/utils/request'

export function companySelectList() {
  return request({
    url: `/admin/company/web/select/list`,
    method: 'get'
  })
}