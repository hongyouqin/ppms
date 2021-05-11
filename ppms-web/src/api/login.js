import request from '@/utils/request'
// import { ACCESS_TOKEN } from '@/store/mutation-types'
// import storage from 'store'
import qs from 'qs'

const userApi = {
    Login: '/auth/login',
    Logout: '/auth/logout',
    Register: 'auth/register'
}

export function login(param) {
    return request(
        {
            url: userApi.Login,
            method: 'post',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            data: qs.stringify(param)
        }
    )
}

export function reg(param) {
    return request(
        {
            url: userApi.register,
            method: 'post',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            data: qs.stringify(param)
        }
    )
}

