import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'

import Login from "../views/Login";
import Register from "../views/Register";
import Logout from "../views/Logout";

import Dashboard from "../views/dashboard/Dashboard";

import Patient from "../views/dashboard/patient/Patient.vue";
import Patients from "../views/dashboard/patient/Patients.vue";

import Organisation from "../views/dashboard/organisation/Organisation.vue";
import OrganisationCreate from "../views/dashboard/organisation/OrganisationCreate.vue";

import Tasks from "../views/dashboard/task/Tasks";

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
                path: '/',
                name: 'Patients',
                component: Patients
            },
            {
                path: '/patientenkartei/:patientId',
                name: 'Patient',
                component: Patient,
                props: true
            },
            {
                path: '/organisation',
                name: 'Organisation',
                component: Organisation
            },
            {
                path: '/organisation/erstellen',
                name: 'OrganisationCreate',
                component: OrganisationCreate
            },
            {
                path: '/aufgaben',
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
        path: '/abmelden',
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
            next('/patientenkartei');
        } else {
            next("/login");
        }
    } else {
        next();
    }
});

export default router
