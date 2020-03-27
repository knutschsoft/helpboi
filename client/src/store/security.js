import SecurityAPI from "../api/security";
import axios from "axios";

let authHeader = window.localStorage['helpboi-store-authHeader'];
axios.defaults.headers.common = (undefined !== authHeader) ? JSON.parse(authHeader) : {};
let isAuthenticated = window.localStorage['helpboi-store-isAuthenticated'];
isAuthenticated = (undefined !== isAuthenticated) ? JSON.parse(isAuthenticated) : false;
let user = window.localStorage['helpboi-store-user'];
user = (undefined !== user) ? JSON.parse(user) : false;

const AUTHENTICATING = "AUTHENTICATING",
    AUTHENTICATING_SUCCESS = "AUTHENTICATING_SUCCESS",
    AUTHENTICATING_ERROR = "AUTHENTICATING_ERROR",
    PROVIDING_DATA_ON_REFRESH_SUCCESS = "PROVIDING_DATA_ON_REFRESH_SUCCESS",
    UPDATE_CURRENT_USER = "UPDATE_CURRENT_USER";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        isAuthenticated: isAuthenticated,
        user: user,
    },
    getters: {
        isLoading(state) {
            return state.isLoading;
        },
        hasError(state) {
            return state.error !== null;
        },
        error(state) {
            return state.error;
        },
        isAuthenticated(state) {
            return state.isAuthenticated;
        },
        hasRole(state) {
            return role => {
                return state.user.roles.indexOf(role) !== -1;
            }
        },
        currentUser(state) {
            return state.user;
        },
        isAdminOfOrganisation(state, getters) {
            return state.user && state.user.admin && getters.isMemberOfOrganisation;
        },
        isMemberOfOrganisation(state) {
            return state.user && undefined !== state.user.organisationId;
        },
    },
    mutations: {
        [AUTHENTICATING](state) {
            state.isLoading = true;
            state.error = null;
            state.isAuthenticated = false;
            state.user = null;
        },
        [AUTHENTICATING_SUCCESS](state, user, payload) {
            state.isLoading = false;
            state.error = null;
            state.isAuthenticated = true;
            state.user = user;
            state.payload = payload;
        },
        [AUTHENTICATING_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.isAuthenticated = false;
            state.user = null;
            state.payload = null;
        },
        [PROVIDING_DATA_ON_REFRESH_SUCCESS](state, payload) {
            let credentialsPayload = payload.payload;
            let authHeader = credentialsPayload
                ? { 'Authorization': 'Basic ' + btoa(credentialsPayload.email + ':' + credentialsPayload.password) }
                : {};
            state.isLoading = false;
            state.error = null;
            state.isAuthenticated = payload.isAuthenticated;
            state.user = payload.user;
            window.localStorage['helpboi-store-user'] = (payload.user)
                ? JSON.stringify(payload.user)
                : null;
            window.localStorage['helpboi-store-isAuthenticated'] = (payload.isAuthenticated)
                ? JSON.stringify(payload.isAuthenticated)
                : null;
            window.localStorage['helpboi-store-authHeader'] = JSON.stringify(authHeader);
            axios.defaults.headers.common = authHeader;
        },
        [UPDATE_CURRENT_USER](state, user) {
            state.user = { ...user };
            window.localStorage['helpboi-store-user'] = (user)
                ? JSON.stringify(user)
                : null;
        },
    },
    actions: {
        async login({commit}, payload) {
            commit(AUTHENTICATING);
            try {
                let response = await SecurityAPI.login(payload.email, payload.password);
                commit(AUTHENTICATING_SUCCESS, response.data, payload);
                return response.data;
            } catch (error) {
                commit(AUTHENTICATING_ERROR, error);
                return null;
            }
        },
        onRefresh({commit}, payload) {
            commit(PROVIDING_DATA_ON_REFRESH_SUCCESS, payload);
        },
        updateCurrentUser({commit}, user) {
            commit(UPDATE_CURRENT_USER, user);
        }
    }
}
