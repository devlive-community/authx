import HttpUtils from '@/utils/HttpUtils'

const baseUrl = 'api/v1/overview'

export class OverviewService {
  getSummary (): Promise<any> {
    return HttpUtils.get(`${baseUrl}`)
  }
}

export default new OverviewService()
