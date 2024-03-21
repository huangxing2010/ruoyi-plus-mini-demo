import request from '@/utils/request'

// 查询内容分类列表
export function listCategory(query) {
  return request({
    url: '/action/category/list',
    method: 'get',
    params: query
  })
}

// 查询内容分类详细
export function getCategory(categoryId) {
  return request({
    url: '/action/category/' + categoryId,
    method: 'get'
  })
}

// 新增内容分类
export function addCategory(data) {
  return request({
    url: '/action/category',
    method: 'post',
    data: data
  })
}

// 修改内容分类
export function updateCategory(data) {
  return request({
    url: '/action/category',
    method: 'put',
    data: data
  })
}

// 删除内容分类
export function delCategory(categoryId) {
  return request({
    url: '/action/category/' + categoryId,
    method: 'delete'
  })
}
