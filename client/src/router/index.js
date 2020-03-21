import Vue from 'vue'
import VueRouter from 'vue-router'
import Organisation from '../views/Organisation.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Organisation',
    component: Organisation
  },
  {
    path: '/users',
    name: 'Users',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "users" */ '../views/Users.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
