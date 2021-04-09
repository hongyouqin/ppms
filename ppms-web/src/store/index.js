import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import expense from './modules/expense'

Vue.use(Vuex)

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
  }
})
