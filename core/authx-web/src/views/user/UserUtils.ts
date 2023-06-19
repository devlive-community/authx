import { TableColumnEntity } from '@/entity/TableEntity'

const headers: Array<TableColumnEntity> = [
  new TableColumnEntity('#', 'id'),
  new TableColumnEntity('名称', 'name'),
  new TableColumnEntity('邮箱', 'email'),
  new TableColumnEntity('激活状态', 'active'),
  new TableColumnEntity('权限', 'permission', 'permission'),
  new TableColumnEntity('创建时间', 'createTime'),
  new TableColumnEntity('修改时间', 'updateTime')
]

export default {
  headers: headers
}
