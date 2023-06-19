import axios from 'axios'
import HttpUtils from '@/utils/HttpUtils'

const baseUrl = '/oauth/token'

class AuthService {
  doAuth (username: string, password: string): Promise<any> {
    return HttpUtils.doAuth(baseUrl, username, password)
  }
}

export default new AuthService()
