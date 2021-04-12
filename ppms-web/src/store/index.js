import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

import user from './modules/user'
import expense from './modules/expense'
import income from './modules/income'

Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})

export default new Vuex.Store({
  modules: {
    user,
    expense,
    income
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  plugins: [vuexLocal.plugin]
})
