import axios from 'axios'

// 创建axios 实例
const request = axios.create({
    baseURL: 'https://localhost:6888',
    timeout: 6000
})

export default request
