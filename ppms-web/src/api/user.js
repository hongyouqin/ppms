import request from '@/utils/request'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'
import qs from 'qs'

const api = {
    Users: '/sys/users',
    AddUser: 'sys/user'
}

export function fetchUsers() {
    let token = storage.get(ACCESS_TOKEN)
    return request(
        {
            url: api.Users,
            method: 'get',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            data: null
        }
    )
}

export function addUser(param) {
    let token = storage.get(ACCESS_TOKEN)
    return request(
        {
            url: api.AddUser,
            method: 'post',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            data: qs.stringify(param)
        }
    )
}