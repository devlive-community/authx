import axios from 'axios'
import { ResponseEntity } from '@/entity/ResponseEntity'
import { Message } from 'view-ui-plus'
import { ErrorValidationEntity } from '@/entity/ErrorValidationEntity'
import MessageUtils from '@/utils/MessageUtils'

axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*'

export class HttpUtils {
  private configure

  constructor () {
    if (process.env.NODE_ENV === 'development') {
      axios.defaults.baseURL = 'http://localhost:9999'
    } else {
      axios.defaults.baseURL = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
    }

    const token = localStorage.getItem('AuthToken')

    const headers = new Headers({
      'Content-Type': 'application/json'
    })
    if (token) {
      headers.append('Authorization', `Bearer ${token}`)
    }

    this.configure = {
      headers: headers,
      cancelToken: undefined,
      params: undefined
    }
  }

  doAuth (url: string, username: string, password: string): Promise<any> {
    const params = new URLSearchParams()
    params.append('username', username)
    params.append('password', password)
    params.append('grant_type', 'password')
    params.append('client_id', 'AuthX-Client')
    const options = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
        Authorization: 'Basic ' + btoa('AuthX-Client:AuthX-Web')
      },
      params: params
    }
    return axios.post(url, JSON.stringify({ username: username, password: password }), options)
  }

  doResponse (response: ResponseEntity): ResponseEntity {
    if (response.code > 2000) {
      const instance = response.data as ErrorValidationEntity
      if (instance) {
        MessageUtils.handlerError(response.data)
      } else {
        Message.error(response.message)
      }
    }
    return response
  }

  post (url: string, data = {}, cancelToken?: any): Promise<ResponseEntity> {
    return new Promise((resolve) => {
      this.configure.cancelToken = cancelToken
      // @ts-ignore
      axios.post(url, data, this.configure)
        .then(result => {
          resolve(this.doResponse(result.data))
        }, error => {
          resolve(this.doResponse(error))
        })
    })
  }
}

export default new HttpUtils()
