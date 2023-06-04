import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios"

axios.defaults.baseURL = process.env.VUE_APP_BACKEND_URL
axios.defaults.withCredentials = true
Vue.prototype.$axios = axios
Vue.use(ElementUI)
Vue.config.productionTip = false
axios.interceptors.request.use(
    (config) => {
        let token = localStorage.getItem('token')
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)
axios.interceptors.response.use(response => {
    if (response.data == 'tokenFailed') {
        router.push('/login')
        return Promise.reject(response)
    } else {
        return response
    }
})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
