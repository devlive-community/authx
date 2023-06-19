import HttpUtils from '@/utils/HttpUtils'
import { ResponseEntity } from '@/entity/ResponseEntity'
import { PageEntity } from '@/entity/PageEntity'

export abstract class BaseService<T> {
  private readonly baseUrl: string;

  protected constructor (baseUrl: string) {
    this.baseUrl = baseUrl
  }

  saveOrUpdate<T> (configure: T): Promise<ResponseEntity> {
    return HttpUtils.post(this.baseUrl, configure)
  }

  getAllByPage<T> (page: PageEntity): Promise<ResponseEntity> {
    return HttpUtils.get(this.baseUrl, page)
  }
}
