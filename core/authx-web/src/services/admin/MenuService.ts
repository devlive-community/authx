import { BaseService } from '@/services/BaseService'
import { ResponseEntity } from '@/entity/ResponseEntity'
import HttpUtils from '@/utils/HttpUtils'

const baseUrl = '/api/v1/system/role/menu'

export class MenuService extends BaseService<ResponseEntity> {
  constructor () {
    super(baseUrl)
  }

  getMenusByUser (id: number): Promise<ResponseEntity> {
    return HttpUtils.get(`${baseUrl}?id=${id}`)
  }
}

export default new MenuService()
