import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login.vue";
import Home from "@/components/Home.vue";
import OverView from "@/components/OverView.vue";
import Operator from "@/components/Operator.vue";
import Resource from "@/components/Resource.vue";
import Charge from "@/components/Charge.vue";
import OpenAccount from "@/components/OpenAccount.vue";
import OpenAccountB from "@/components/OpenAccountB.vue";
import OpenAccountC from "@/components/OpenAccountC.vue";
import AddAccount from "@/components/AddAccount.vue";
import MergeAccount from "@/components/MergeAccount.vue";
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
            },
            {
                path: '/resource',
                name: Resource,
                component: Resource
            },
            {
                path: '/charge',
                name: Charge,
                component: Charge
            },
            {
                path: '/openAccount',
                name: OpenAccount,
                component: OpenAccount
            },
            {
                path: '/openAccountB',
                name: OpenAccountB,
                component: OpenAccountB
            },
            {
                path: '/openAccountC',
                name: OpenAccountC,
                component: OpenAccountC
            },
            {
                path: '/addAccount',
                name: AddAccount,
                component: AddAccount
            },
            {
                path: '/mergeAccount',
                name: MergeAccount,
                component: MergeAccount
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
