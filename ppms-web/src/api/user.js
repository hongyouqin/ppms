import request from '@/utils/request'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const api = {
    Users: '/sys/users'
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