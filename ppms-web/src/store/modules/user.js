import storage from 'store'
import { login } from '@/api/login'
import { ACCESS_TOKEN } from '@/store/mutation-types'


const user = {
    state: {
        token: ''
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        }
    },
    actions: {
        //登录
        Login({ commit }, userInfo) {
            console.log("登录请求")
            return new Promise((resolve, reject) => {
                login(userInfo).then(response => {
                    const result = response.result
                    storage.set(ACCESS_TOKEN, result.token, 7 * 24 * 60 * 60 * 1000)
                    commit('SET_TOKEN', result.token)
                    resolve()
                }).catch(error => {
                    console.log("请求失败")
                    storage.set(ACCESS_TOKEN, "12344", 7 * 24 * 60 * 60 * 1000)
                    reject(error)
                })
            })
        }
    }
}

export default user