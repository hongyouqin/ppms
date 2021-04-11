import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

import user from './modules/user'
import expense from './modules/expense'

Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})

export default new Vuex.Store({
  modules: {
    user,
    expense
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  plugins: [vuexLocal.plugin]
})
