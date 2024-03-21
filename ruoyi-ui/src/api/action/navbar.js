import request from '@/utils/request'

// 查询导航信息列表
export function listNavbar(query) {
  return request({
    url: '/action/navbar/list',
    method: 'get',
    params: query
  })
}

// 查询导航信息详细
export function getNavbar(navId) {
  return request({
    url: '/action/navbar/' + navId,
    method: 'get'
  })
}

// 新增导航信息
export function addNavbar(data) {
  return request({
    url: '/action/navbar',
    method: 'post',
    data: data
  })
}

// 修改导航信息
export function updateNavbar(data) {
  return request({
    url: '/action/navbar',
    method: 'put',
    data: data
  })
}

// 删除导航信息
export function delNavbar(navId) {
  return request({
    url: '/action/navbar/' + navId,
    method: 'delete'
  })
}
