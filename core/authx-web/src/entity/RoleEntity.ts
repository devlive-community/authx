import { BaseEntity } from '@/entity/BaseEntity'

export class RoleEntity extends BaseEntity {
}

export class AssignRoleEntity {
  id: number | undefined
  values: Array<any> | undefined
}
