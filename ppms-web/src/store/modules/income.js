
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

    }
}

export default income