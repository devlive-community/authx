import HttpUtils from '@/utils/HttpUtils'
import { ResponseEntity } from '@/entity/ResponseEntity'

export abstract class BaseService<T> {
  private readonly baseUrl: string;

  protected constructor (baseUrl: string) {
    this.baseUrl = baseUrl
  }

  saveOrUpdate<T> (configure: T): Promise<ResponseEntity> {
    return HttpUtils.post(this.baseUrl, configure)
  }
}
