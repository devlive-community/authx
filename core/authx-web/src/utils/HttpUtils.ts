import axios, { AxiosRequestConfig } from 'axios'
import { ResponseEntity } from '@/entity/ResponseEntity'
import { Message } from 'view-ui-plus'
import { ErrorValidationEntity } from '@/entity/ErrorValidationEntity'
import MessageUtils from '@/utils/MessageUtils'
import SupportUtils from '@/utils/SupportUtils'
import router from '@/router'

axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*'

export class HttpUtils {
  private options = {
    headers: {} || undefined,
    cancelToken: undefined,
    params: undefined
  }

  constructor () {
    if (process.env.NODE_ENV === 'development') {
      axios.defaults.baseURL = 'http://localhost:9999'
    } else {
      axios.defaults.baseURL = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
    }
  }

  doRefresh () {
    const token: string = localStorage.getItem(SupportUtils.token) as string
    const headers = {
      'Content-Type': 'application/json',
      Authorization: token ? `Bearer ${token}` : ''
    }
    this.options.headers = headers
  }

  doAuth (url: string, username: string, password: string): Promise<any> {
    const params: URLSearchParams = new URLSearchParams()
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
        // 当返回状态码为 4000 标志当前 token 无权限，重定向到未授权页面
        if (response.code === 4000) {
          router.push('/common/403')
        }
        Message.error(response.message)
      }
    }
    return response
  }

  post (url: string, data = {}, cancelToken?: any): Promise<ResponseEntity> {
    return new Promise((resolve) => {
      this.options.cancelToken = cancelToken
      // @ts-ignore
      axios.post(url, data, this.options)
        .then(result => {
          resolve(this.doResponse(result.data))
        }, error => {
          resolve(this.doResponse(error))
        })
    })
  }

  get (url: string, params?: any, cancelToken?: any): Promise<ResponseEntity> {
    return new Promise((resolve) => {
      this.doRefresh()
      this.options.cancelToken = cancelToken
      this.options.params = params
      // @ts-ignore
      axios.get(url, this.options)
        .then(result => {
          resolve(this.doResponse(result.data))
        }, error => {
          resolve(this.doResponse(error))
        })
    })
  }

  put (url: string, data = {}, cancelToken?: any): Promise<ResponseEntity> {
    return new Promise((resolve) => {
      this.options.cancelToken = cancelToken
      // @ts-ignore
      axios.put(url, data, this.options)
        .then(result => {
          resolve(this.doResponse(result.data))
        }, error => {
          resolve(this.doResponse(error))
        })
    })
  }
}

export default new HttpUtils()
