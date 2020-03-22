import OrganisationAPI from "../api/organisation";

const CREATING_ORGANISATION = "CREATING_ORGANISATION",
    CREATING_ORGANISATION_SUCCESS = "CREATING_ORGANISATION_SUCCESS",
    CREATING_ORGANISATION_ERROR = "CREATING_ORGANISATION_ERROR",
    CREATING_ORGANISATION_PATIENT = "CREATING_ORGANISATION_PATIENT",
    CREATING_ORGANISATION_PATIENT_SUCCESS = "CREATING_ORGANISATION_PATIENT_SUCCESS",
    CREATING_ORGANISATION_PATIENT_ERROR = "CREATING_ORGANISATION_PATIENT_ERROR",
    FETCHING_ORGANISATION_USERS = "FETCHING_ORGANISATION_USERS",
    FETCHING_ORGANISATION_USERS_SUCCESS = "FETCHING_ORGANISATION_USERS_SUCCESS",
    FETCHING_ORGANISATION_USERS_ERROR = "FETCHING_ORGANISATION_USERS_ERROR",
    ADD_HISTORY_TO_PATIENT = "ADD_HISTORY_TO_PATIENT",
    ADD_HISTORY_TO_PATIENT_SUCCESS = "ADD_HISTORY_TO_PATIENT_SUCCESS",
    ADD_HISTORY_TO_PATIENT_ERROR = "ADD_HISTORY_TO_PATIENT_ERROR",
    FETCHING_ORGANISATION_PATIENTS = "FETCHING_ORGANISATION_PATIENTS",
    FETCHING_ORGANISATION_PATIENTS_SUCCESS = "FETCHING_ORGANISATION_PATIENTS_SUCCESS",
    FETCHING_ORGANISATION_PATIENTS_ERROR = "FETCHING_ORGANISATION_PATIENTS_ERROR",
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
        organisationPatients: [],
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
        },
        organisationPatients(state) {
            return state.organisationPatients;
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
        [CREATING_ORGANISATION_PATIENT](state) {
            state.isLoading = true;
            state.error = null;
        },
        [CREATING_ORGANISATION_PATIENT_SUCCESS](state, organisationPatient) {
            state.isLoading = false;
            state.error = null;
            state.organisationPatients.push(organisationPatient);
        },
        [CREATING_ORGANISATION_PATIENT_ERROR](state, error) {
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
        [ADD_HISTORY_TO_PATIENT](state) {
            state.isLoading = true;
            state.error = null;
        },
        [ADD_HISTORY_TO_PATIENT_SUCCESS](state, patient) {
            state.isLoading = false;
            state.error = null;
            state.organisationPatients.forEach(function (oldPatient, key) {
                if (oldPatient.id === patient.id) {
                    state.organisationPatients[key] = patient;
                }
            });
        },
        [ADD_HISTORY_TO_PATIENT_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisationPatients = [];
        },
        [FETCHING_ORGANISATION_PATIENTS](state) {
            state.isLoading = true;
            state.error = null;
        },
        [FETCHING_ORGANISATION_PATIENTS_SUCCESS](state, organisationPatients) {
            state.isLoading = false;
            state.error = null;
            state.organisationPatients = organisationPatients;
        },
        [FETCHING_ORGANISATION_PATIENTS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.organisationPatients = [];
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
        async createOrganisationPatient({commit}, [
            organisationId,
            firstname,
            lastname,
            gender,
            phone,
            dateOfBirth,
            zipcode,
            city,
            address,
            status,
            notes
        ]) {
            commit(CREATING_ORGANISATION_PATIENT);
            try {
                let response = await OrganisationAPI.createPatient(
                    organisationId,
                    firstname,
                    lastname,
                    gender,
                    phone,
                    dateOfBirth,
                    zipcode,
                    city,
                    address,
                    status,
                    notes
                );
                commit(CREATING_ORGANISATION_PATIENT_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(CREATING_ORGANISATION_PATIENT_ERROR, error);
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
        async addHistoryToPatient({commit}, [patientId, type, content]) {
            commit(ADD_HISTORY_TO_PATIENT);
            try {
                let response = await OrganisationAPI.addHistoryToPatient(patientId, type, content);
                commit(ADD_HISTORY_TO_PATIENT_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(ADD_HISTORY_TO_PATIENT_ERROR, error);
                return null;
            }
        },
        async findOrganisationPatients({commit}, id) {
            commit(FETCHING_ORGANISATION_PATIENTS);
            try {
                let response = await OrganisationAPI.findOrganisationPatients(id);
                commit(FETCHING_ORGANISATION_PATIENTS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_ORGANISATION_PATIENTS_ERROR, error);
                return null;
            }
        },
    }
};
