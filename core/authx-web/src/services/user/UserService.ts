import { BaseService } from '@/services/BaseService'
import { ResponseEntity } from '@/entity/ResponseEntity'
import HttpUtils from '@/utils/HttpUtils'

const baseUrl = '/api/v1/user'

export class UserService extends BaseService<ResponseEntity> {
  constructor () {
    super(baseUrl)
  }

  saveOrUpdate<T> (configure: T): Promise<ResponseEntity> {
    return HttpUtils.post(`${baseUrl}/register`, configure)
  }

  getInfoByUserName<T> (username: string): Promise<ResponseEntity> {
    return HttpUtils.get(`${baseUrl}/info/${username}`)
  }
}

export default new UserService()
