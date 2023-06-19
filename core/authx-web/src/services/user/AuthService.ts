import HttpUtils from '@/utils/HttpUtils'
import SupportUtils from '@/utils/SupportUtils'
import { UserEntity } from '@/entity/UserEntity'

const baseUrl = '/oauth/token'

class AuthService {
  doAuth (username: string, password: string): Promise<any> {
    return HttpUtils.doAuth(baseUrl, username, password)
  }

  saveAuth (token: any) {
    localStorage.setItem(SupportUtils.token, token)
  }

  getAuth (): UserEntity {
    const token: UserEntity = JSON.parse(JSON.stringify(localStorage.getItem(SupportUtils.token) as string)) as UserEntity
    return token
  }
}

export default new AuthService()
