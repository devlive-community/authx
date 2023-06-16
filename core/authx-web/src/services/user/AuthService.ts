import axios from 'axios'
import HttpClient from '@/commons/HttpClient'

const baseUrl = '/oauth/token'

class AuthService {
  doAuth (username: string, password: string): Promise<any> {
    return HttpClient.doAuth(baseUrl, username, password)
  }
}

export default new AuthService()
