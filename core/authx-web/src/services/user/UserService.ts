import { BaseService } from '@/services/BaseService'
import { ResponseEntity } from '@/entity/ResponseEntity'
import HttpUtils from '@/utils/HttpUtils'

const baseUrl = '/api/v1/user'

export class UserService extends BaseService<any> {
  constructor () {
    super(baseUrl)
  }

  saveOrUpdate<T> (configure: T): Promise<ResponseEntity> {
    return HttpUtils.post(`${baseUrl}/register`, configure)
  }
}

export default new UserService()
