
import { addIncome } from '@/api/inex'

const income = {
    state: {
        incomeDatas: []
    },
    getters: {
        getIncomeDatas(state) {
            return state.incomeDatas
        }
    },
    mutations: {
        SET_INCOME_DATAS: (state, datas) => {
            state.incomeDatas = datas
        }
    },
    actions: {
        AddIncome(commit, param) {
            return new Promise((resolve, reject) => {
                addIncome(param).then(response => {
                    resolve(response)
                }).catch(error => {
                    reject(error)
                })
            }
            )
        }
    }
}

export default income