import storage from 'store'
import { login } from '@/api/login'
import { ACCESS_TOKEN } from '@/store/mutation-types'


const user = {
    state: {
        token: '',
        userDatas: []
    },
    getters: {
        getUserDatas(state) {
            return state.userDatas
        }
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_USER_DATAS: (state, datas) => {
            state.userDatas = datas
        }
    },
    actions: {
        //登录
        Login({ commit }, userInfo) {
            return new Promise((resolve, reject) => {
                login(userInfo).then(response => {
                    const result = response.data
                    if (result.code == 200) {
                        let tokenHead = result.data.tokenHead
                        let token = result.data.token
                        commit('SET_TOKEN', tokenHead + token)
                        storage.set(ACCESS_TOKEN, tokenHead + token, 7 * 24 * 60 * 60 * 1000)
                        console.log(tokenHead + token)
                    }
                    resolve(result)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        LoginOut({ commit }) {
            commit('SET_TOKEN', "")
            window.localStorage.clear()
        }
    }
}

export default user