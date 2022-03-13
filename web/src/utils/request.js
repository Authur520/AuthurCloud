import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store/index'
import { getToken, getRefreshToken, getExpireTime } from '@/utils/auth'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    timeout: 10000,
    responseType: 'json',
    validateStatus(status) {
        return status === 200
    }
})

service.interceptors.request.use(
    config => {
        if (getToken()) {
            config.headers['Authorization'] = 'bearer ' + getToken()
        }
        return config
    },
    error => {
        console.error(error)
        return Promise.reject(error)
    }
)

service.interceptors.response.use((config) => {
    return config
}, (error) => {
    if (error.response) {
        const errorMessage = error.response.data === null ? '系统内部异常，请联系网站管理员' : error.response.data.message
        switch (error.response.status) {
            case 404:
                Message({
                    message: '很抱歉，资源未找到' || 'Error',
                    type: 'error',
                    duration: 5 * 1000
                })
                break
            case 403:
                Message({
                    message: '很抱歉，您暂无该操作权限' || 'Error',
                    type: 'error',
                    duration: 5 * 1000
                })
                break
            case 401:
                Message({
                    message: '很抱歉，认证已失效，请重新登录' || 'Error',
                    type: 'error',
                    duration: 5 * 1000
                })
                break
            default:
                if (errorMessage) {
                    Message({
                        message: errorMessage,
                        type: 'error',
                        duration: 5 * 1000
                    })
                }
                break
        }
    }
    return Promise.reject(error)
})

const request = {
    login(url, params) {
        params['grant_type'] = 'password'
        return service.post(url, params, {
            transformRequest: [(params) => {
                return tansParams(params)
            }],
            headers: {
                'Authorization': 'Basic ZmViczoxMjM0NTY='
            }
        })
    },
    post(url, params) {
        return service.post(url, params, {
            transformRequest: [(params) => {
                return tansParams(params)
            }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    put(url, params) {
        return service.put(url, params, {
            transformRequest: [(params) => {
                return tansParams(params)
            }],
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    get(url, params) {
        let _params
        if (Object.is(params, undefined)) {
            _params = ''
        } else {
            _params = '?'
            for (const key in params) {
                if (params.hasOwnProperty(key) && params[key] !== null) {
                    _params += `${key}=${params[key]}&`
                }
            }
        }
        return service.get(`${url}${_params}`)
    },
    delete(url, params) {
        let _params
        if (Object.is(params, undefined)) {
            _params = ''
        } else {
            _params = '?'
            for (const key in params) {
                if (params.hasOwnProperty(key) && params[key] !== null) {
                    _params += `${key}=${params[key]}&`
                }
            }
        }
        return service.delete(`${url}${_params}`)
    },
}

function tansParams(params) {
    let result = ''
    Object.keys(params).forEach((key) => {
        if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
            result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
        }
    })
    return result
}