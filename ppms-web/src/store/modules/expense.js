//import storage from 'store'

//支出
const expense = {
    state: {
        expenseDatas: []
    },
    getters: {
        getExpenseDatas(state) {
            return state.expenseDatas
        }
    },
    mutations: {
        SET_EXPENSE_DATAS: (state, datas) => {
            state.expenseDatas = datas
        }
    },
    actions: {

    }
}

export default expense