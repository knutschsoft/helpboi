import OrganisationAPI from "../api/organisation";

const CREATING_ORGANISATION = "CREATING_ORGANISATION",
    CREATING_ORGANISATION_SUCCESS = "CREATING_ORGANISATION_SUCCESS",
    CREATING_ORGANISATION_ERROR = "CREATING_ORGANISATION_ERROR",
    FETCHING_ORGANISATIONS = "FETCHING_ORGANISATIONS",
    FETCHING_ORGANISATIONS_SUCCESS = "FETCHING_ORGANISATIONS_SUCCESS",
    FETCHING_ORGANISATIONS_ERROR = "FETCHING_ORGANISATIONS_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        organisations: []
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
        hasOrganisations(state) {
            return state.organisations.length > 0;
        },
        organisations(state) {
            return state.organisations;
        }
    },
    mutations: {
        [CREATING_ORGANISATION](state) {
            state.isLoading = true;
            state.error = null;
        },
        [CREATING_ORGANISATION_SUCCESS](state, organisation) {
            state.isLoading = false;
            state.error = null;
            state.organisations.push(organisation);
        },
        [CREATING_ORGANISATION_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisations = [];
        },
        [FETCHING_ORGANISATIONS](state) {
            state.isLoading = true;
            state.error = null;
            state.organisations = [];
        },
        [FETCHING_ORGANISATIONS_SUCCESS](state, organisations) {
            state.isLoading = false;
            state.error = null;
            state.organisations = [organisations];
        },
        [FETCHING_ORGANISATIONS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisations = [];
        }
    },
    actions: {
        async create({ commit }, [ name, zipcode, city, address, userId ]) {
            commit(CREATING_ORGANISATION);
            try {
                let response = await OrganisationAPI.create(name, zipcode, city, address, userId);
                commit(CREATING_ORGANISATION_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(CREATING_ORGANISATION_ERROR, error);
                return null;
            }
        },
        async findAll({ commit }) {
            commit(FETCHING_ORGANISATIONS);
            try {
                let response = await OrganisationAPI.findAll();
                commit(FETCHING_ORGANISATIONS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_ORGANISATIONS_ERROR, error);
                return null;
            }
        },
    }
};
