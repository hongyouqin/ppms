import request from '@/utils/request'

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
            data: param
        }
    )
}
