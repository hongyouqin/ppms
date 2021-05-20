import request from '@/utils/request'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'
import qs from 'qs'

//收支接口
const api = {
    Income: '/income'
}

export function addIncome(param) {
    let token = storage.get(ACCESS_TOKEN)
    return request(
        {
            url: api.Income,
            method: 'post',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            data: qs.stringify(param)
        }
    )
}
