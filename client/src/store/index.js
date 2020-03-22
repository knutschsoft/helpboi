import Vue from 'vue'
import Vuex from 'vuex'
import OrganisationModule from "./organisation";
import UserModule from "./user";
import SecurityModule from "./security";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        organisation: OrganisationModule,
        user: UserModule,
        security: SecurityModule,
    }
})
