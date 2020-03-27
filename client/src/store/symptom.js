import SymptomAPI from "../api/symptom";

const FETCHING_SYMPTOMS = "FETCHING_SYMPTOMS",
    FETCHING_SYMPTOMS_SUCCESS = "FETCHING_SYMPTOMS_SUCCESS",
    FETCHING_SYMPTOMS_ERROR = "FETCHING_SYMPTOMS_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        symptoms: []
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
        hasSymptoms(state) {
            return state.symptoms.length > 0;
        },
        symptoms(state) {
            return state.symptoms;
        },
        getSymptomById(state) {
            return symptomId => {
                let foundSymptom = false;
                state.symptoms.forEach(
                    (symptom) => {
                        if (symptom.id === symptomId) {
                            foundSymptom = symptom;
                        }
                    }
                );

                return foundSymptom;
            }
        },
    },
    mutations: {
        [FETCHING_SYMPTOMS](state) {
            state.isLoading = true;
            state.error = null;
            state.symptoms = [];
        },
        [FETCHING_SYMPTOMS_SUCCESS](state, symptoms) {
            state.isLoading = false;
            state.error = null;
            state.symptoms = symptoms;
        },
        [FETCHING_SYMPTOMS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.symptoms = [];
        }
    },
    actions: {
        async findAll({ commit }) {
            commit(FETCHING_SYMPTOMS);
            try {
                let response = await SymptomAPI.findAll();
                commit(FETCHING_SYMPTOMS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_SYMPTOMS_ERROR, error);
                return null;
            }
        },
    }
};
