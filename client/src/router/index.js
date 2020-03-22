import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'

import Login from "../views/Login";
import Register from "../views/Register";
import Logout from "../views/Logout";

import Dashboard from "../views/dashboard/Dashboard";
import Organisation from "../views/Organisation.vue";
import Users from "../views/Users";
import Tasks from "../views/Tasks";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Dashboard',
        component: Dashboard,
        meta: {
            requiresAuth: true
        },
        children: [
            {
                path: '',
                name: 'Organisation',
                component: Organisation
            },
            {
                path: '/users',
                name: 'Users',
                component: Users
            },
            {
                path: '/tasks',
                name: 'Tasks',
                component: Tasks
            },
        ]
    },

    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/logout',
        name: 'Logout',
        component: Logout,
        meta: {
            requiresAuth: true
        },
    },
    {
        path: "*",
        redirect: "/"
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (store.getters["security/isAuthenticated"]) {
            next();
        } else if (to.fullPath === "/login"
            && store.getters["security/isAuthenticated"]) {
            next('/');
        } else {
            next("/login");
        }
    } else {
        next();
    }
});

export default router
