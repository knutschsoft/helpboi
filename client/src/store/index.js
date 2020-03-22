import Vue from 'vue'
import Vuex from 'vuex'
import UserModule from "./user";
import SecurityModule from "./security";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        user: UserModule,
        security: SecurityModule,
    }
})
