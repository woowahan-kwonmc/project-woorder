import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Order from '../views/Order.vue'
import Coupon from '../views/Coupon.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/order',
        name: 'order',
        component: Order
    },
    {
        path: '/coupon',
        name: 'coupon',
        component: Coupon
    },
]

const router = new VueRouter({
    routes
})

export default router
