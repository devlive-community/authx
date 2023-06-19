import { BaseService } from '@/services/BaseService'
import { ResponseEntity } from '@/entity/ResponseEntity'
import HttpUtils from '@/utils/HttpUtils'
import { PageEntity } from '@/entity/PageEntity'

const baseUrl = '/api/v1/role'

export class RoleService extends BaseService<ResponseEntity> {
  constructor () {
    super(baseUrl)
  }

  getAllByPage<T> (page: PageEntity): Promise<ResponseEntity> {
    return HttpUtils.get(`${baseUrl}`, page)
  }
}

export default new RoleService()
