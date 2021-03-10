import axios from 'axios'

// 创建axios 实例
const request = axios.create({
    baseURL: 'http://127.0.0.1/api/',
    timeout: 6000
})

export default request
