import { BaseService } from '@/services/BaseService'
import HttpClient from '@/commons/HttpClient'
import { ResponseEntity } from '@/entity/ResponseEntity'

const baseUrl = '/api/v1/user'

export class UserService extends BaseService<any> {
  constructor () {
    super(baseUrl)
  }

  saveOrUpdate<T> (configure: T): Promise<ResponseEntity> {
    return HttpClient.post(`${baseUrl}/register`, configure)
  }
}

export default new UserService()
