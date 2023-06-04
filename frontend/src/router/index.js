import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login.vue";
import Home from "@/components/Home.vue";
import OverView from "@/components/OverView.vue";
import Operator from "@/components/Operator.vue";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        children: [
            {
                path: '/',
                name: 'Overview',
                component: OverView
            },
            {
                path: '/operator',
                name: Operator,
                component: Operator
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
router.beforeEach((to, from, next) => {
    if (to.path == "/" || to.path == "/login") {
        next()
        return
    }

    let token = localStorage.getItem("token")
    if (!token) {
        next("/")
    } else {
        next()
    }
})
export default router
