import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/user/Login.vue'
import reg from '../views/user/Register.vue'
import home from '../views/Home.vue'
import expense from '../views/Expense.vue'
import dashboard from '../views/Dashboard.vue'
import user from '../views/User.vue'
import role from '../views/Role.vue'
import privilege from '../views/Privilege.vue'
import income from '../views/Income.vue'
import item from '../views/ItemManage.vue'
import investment from '../views/Investment.vue'
import early from '../views/EarlyWarning.vue'

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
        meta: { keepAlive: true },
        component: expense
      },
      {
        path: '/income',
        name: 'income',
        component: income
      },
      {
        path: '/dashboard',
        name: 'dashboard',
        component: dashboard
      },
      {
        path: '/user',
        name: 'sys',
        component: user
      },
      {
        path: '/role',
        name: 'role',
        component: role
      },
      {
        path: '/privilege',
        name: 'privilege',
        component: privilege
      },
      {
        path: '/item',
        name: 'item',
        component: item
      },
      {
        path: '/investment',
        name: 'investment',
        component: investment
      },
      {
        path: '/early',
        name: 'early',
        component: early
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
    component: login,
    redirect: '/dashboard'
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
