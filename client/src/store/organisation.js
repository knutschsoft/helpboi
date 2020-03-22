import OrganisationAPI from "../api/organisation";

const CREATING_ORGANISATION = "CREATING_ORGANISATION",
    CREATING_ORGANISATION_SUCCESS = "CREATING_ORGANISATION_SUCCESS",
    CREATING_ORGANISATION_ERROR = "CREATING_ORGANISATION_ERROR",
    FETCHING_ORGANISATION_USERS = "FETCHING_ORGANISATION_USERS",
    FETCHING_ORGANISATION_USERS_SUCCESS = "FETCHING_ORGANISATION_USERS_SUCCESS",
    FETCHING_ORGANISATION_USERS_ERROR = "FETCHING_ORGANISATION_USERS_ERROR",
    FETCHING_ORGANISATION = "FETCHING_ORGANISATION",
    FETCHING_ORGANISATION_SUCCESS = "FETCHING_ORGANISATION_SUCCESS",
    FETCHING_ORGANISATION_ERROR = "FETCHING_ORGANISATION_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        organisation: null,
        organisationUsers: [],
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
        organisation(state) {
            return state.organisation;
        },
        hasOrganisation(state) {
            return state.organisation;
        },
        organisationUsers(state) {
            return state.organisationUsers;
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
            state.organisation = organisation;
        },
        [CREATING_ORGANISATION_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
        },
        [FETCHING_ORGANISATION_USERS](state) {
            state.isLoading = true;
            state.error = null;
        },
        [FETCHING_ORGANISATION_USERS_SUCCESS](state, organisationUsers) {
            state.isLoading = false;
            state.error = null;
            state.organisationUsers = organisationUsers;
        },
        [FETCHING_ORGANISATION_USERS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisationUsers = [];
        },
        [FETCHING_ORGANISATION](state) {
            state.isLoading = true;
            state.error = null;
        },
        [FETCHING_ORGANISATION_SUCCESS](state, organisation) {
            state.isLoading = false;
            state.error = null;
            state.organisation = organisation;
        },
        [FETCHING_ORGANISATION_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisation = null;
        },
    },
    actions: {
        async create({commit}, [name, zipcode, city, address, userId]) {
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
        async find({commit}, id) {
            commit(FETCHING_ORGANISATION);
            try {
                let response = await OrganisationAPI.find(id);
                commit(FETCHING_ORGANISATION_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_ORGANISATION_ERROR, error);
                return null;
            }
        },
        async findOrganisationUsers({commit}, id) {
            commit(FETCHING_ORGANISATION_USERS);
            try {
                let response = await OrganisationAPI.findOrganisationUsers(id);
                commit(FETCHING_ORGANISATION_USERS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_ORGANISATION_USERS_ERROR, error);
                return null;
            }
        },
    }
};
