import router from './router'
import storage from 'store'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const defaultRoutePath = '/home'
const loginRoutePath = '/login'

const allowList = ['login', 'reg']

//拦截器
router.beforeEach((to, from, next) => {
    console.log("have token router from-name: ", storage.get(ACCESS_TOKEN))
    if (storage.get(ACCESS_TOKEN)) {
        console.log("have token router from-name: ", storage.get(ACCESS_TOKEN))
        console.log("have token path name : ", to.path)
        if (to.path === loginRoutePath || to.path == '/') {
            next({ path: defaultRoutePath })
        } else {
            next()
        }
    } else {
        console.log("router from-name: ", from.name)
        console.log("router to-name: ", to.name)
        if (allowList.includes(to.name)) {
            next()
        } else {
            next({ path: loginRoutePath })
        }
    }
})