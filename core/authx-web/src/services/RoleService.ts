import { BaseService } from '@/services/BaseService'
import { ResponseEntity } from '@/entity/ResponseEntity'
import HttpUtils from '@/utils/HttpUtils'
import { PageEntity } from '@/entity/PageEntity'

const baseUrl = '/api/v1/role'

export class RoleService extends BaseService<ResponseEntity> {
  constructor () {
    super(baseUrl)
  }
}

export default new RoleService()
