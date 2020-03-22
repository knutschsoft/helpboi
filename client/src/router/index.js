import Vue from 'vue'
import VueRouter from 'vue-router'
import Organisation from '../views/Organisation.vue'
import store from '../store'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Organisation',
        component: Organisation,
        meta: {requiresAuth: true},
    },
    {
        path: '/users',
        name: 'Users',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "users" */ '../views/Users.vue'),
        meta: {requiresAuth: true},
    },
    {
        path: '/aufgaben',
        name: 'Tasks',
        component: () => import('../views/Tasks.vue'),
        meta: {requiresAuth: true},
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue'),
        meta: {requiresAuth: false},
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue'),
        meta: {requiresAuth: false},
    },
    {
        path: '/abmelden',
        name: 'Logout',
        component: () => import('../views/Logout.vue'),
        meta: {requiresAuth: true},
    },
    { path: "*", redirect: "/" }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.

        if (store.getters["security/isAuthenticated"]) {
            next();
        } else {
            next({
                path: "/login",
                query: {redirect: to.fullPath}
            });
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router
