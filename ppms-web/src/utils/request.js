import axios from 'axios'
//import https from 'https'


// 创建axios 实例
const request = axios.create({
    baseURL: 'http://localhost:6888',
    timeout: 6000
})

export default request
