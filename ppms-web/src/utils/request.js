import axios from 'axios'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'
//import https from 'https'


// 创建axios 实例
const request = axios.create({
    baseURL: 'http://localhost:6888',
    timeout: 6000
})

const errorHandler = (error) => {
    if (error.response) {
        const token = storage.get(ACCESS_TOKEN)
        if (error.response.code === 401) {
            if (token) {
                console.log("======= loginout")
                storage.dispatch('LoginOUt').then(() => {
                    setTimeout(() => {
                        console.log("======= loginout reload")
                        window.location.reload()
                    }, 1500)
                })
            }
        }
    }
}

// request.interceptors.request.use(config => {
//     //验证token是否过期
// })

request.interceptors.response.use((response) => {
    return response.data
}, errorHandler)

export default request
