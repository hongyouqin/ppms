import storage from 'store'
import { login } from '@/api/login'
import { fetchUsers, addUser } from '@/api/user'
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
                    if (response.code == 200) {
                        let tokenHead = result.tokenHead
                        let token = result.token
                        commit('SET_TOKEN', tokenHead + token)
                        storage.set(ACCESS_TOKEN, tokenHead + token, 7 * 24 * 60 * 60 * 1000)
                    }
                    resolve(response)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        //退出登录
        LoginOut({ commit }) {
            commit('SET_TOKEN', "")
            storage.remove(ACCESS_TOKEN)
        },
        //批量获取用户
        FetchUsers() {
            return new Promise(
                (resolve, reject) => {
                    fetchUsers().then(response => {
                        resolve(response)
                    }).catch(error => {
                        reject(error)
                    })
                }
            )
        },
        //添加用户
        AddUser(commit, userInfo) {
            return new Promise((resolve, reject) => {
                addUser(userInfo).then(response => {
                    console.log("add user: ", response)
                    resolve(response)
                }).catch(error => {
                    reject(error)
                })
            }

            )
        }

    }
}

export default user