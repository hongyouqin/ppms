import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/'
import storage from 'store'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import './permission'

import { ACCESS_TOKEN } from '@/store/mutation-types'



Vue.config.productionTip = false

Vue.use(Antd)

new Vue({
  router,
  store,
  created: function () {
    //初始化
    // window.localStorage.clear()
    store.commit('SET_TOKEN', storage.get(ACCESS_TOKEN))
  },
  render: h => h(App)
}).$mount('#app')
