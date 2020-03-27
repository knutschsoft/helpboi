import Vue from 'vue';
import Vuex from 'vuex';
import VuexReset from '@ianwalter/vuex-reset';
import OrganisationModule from "./organisation";
import UserModule from "./user";
import SecurityModule from "./security";
import TaskModule from "./task";
import SymptomModule from "./symptom";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {},
    mutations: {
        reset: () => {},
    },
    actions: {},
    modules: {
        organisation: OrganisationModule,
        user: UserModule,
        security: SecurityModule,
        task: TaskModule,
        symptom: SymptomModule,
    },
    plugins: [new VuexReset()],
    strict: process.env.NODE_ENV !== 'production',
})
