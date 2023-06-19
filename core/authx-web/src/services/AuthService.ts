import HttpUtils from '@/utils/HttpUtils'
import SupportUtils from '@/utils/SupportUtils'

const baseUrl = '/oauth/token'

class AuthService {
  doAuth (username: string, password: string): Promise<any> {
    return HttpUtils.doAuth(baseUrl, username, password)
  }

  saveAuth (username: string, token: string) {
    localStorage.setItem(SupportUtils.username, username)
    localStorage.setItem(SupportUtils.token, token)
  }

  getAuth (): string {
    return localStorage.getItem(SupportUtils.token) as string
  }

  getAuthUserName (): string {
    return localStorage.getItem(SupportUtils.username) as string
  }
}

export default new AuthService()
