import axios from 'axios'

axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*'

export class HttpClient {
  private configure

  constructor () {
    if (process.env.NODE_ENV === 'development') {
      axios.defaults.baseURL = 'http://localhost:9999'
    } else {
      axios.defaults.baseURL = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
    }

    this.configure = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
        Authorization: 'Basic ' + btoa('AuthX-Client:AuthX-Web')
      },
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

  post (url: string, data = {}, cancelToken?: any): Promise<any> {
    return new Promise((resolve) => {
      this.configure.cancelToken = cancelToken
      axios.post(url, data, this.configure)
        .then(result => {
          return result
        }, error => {
          return error
        })
    })
  }
}

export default new HttpClient()
