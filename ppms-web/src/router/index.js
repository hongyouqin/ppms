import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/user/Login.vue'
import reg from '../views/user/Register.vue'
import home from '../views/Home.vue'
import expense from '../views/Expense.vue'
import dashboard from '../views/Dashboard.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: home,
    children: [
      {
        path: '/expense',
        name: 'expense',
        component: expense
      },
      {
        path: '/dashboard',
        name: 'dashboard',
        component: dashboard
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/reg',
    name: 'register',
    component: reg
  },
  {
    path: '',
    name: 'def',
    component: login
  }

  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})


export default router
